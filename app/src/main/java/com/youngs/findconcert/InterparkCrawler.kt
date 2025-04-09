package com.youngs.findconcert

import android.util.Log
import com.youngs.findconcert.domain.model.Concert
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class InterparkCrawler {

    private val INTERPARK_URL = "https://tickets.interpark.com/contents/genre/concert/"

    suspend fun crawlConcerts(): List<Concert> = withContext(Dispatchers.IO) {
        val concerts = mutableListOf<Concert>()

        try {
            val doc = Jsoup.connect(INTERPARK_URL)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get()

            // 콘서트 목록을 감싸는 div 태그 선택
            val concertList = doc.select("div.ProductList_contents__eUxgq")
//            Log.d("인터파크 concertList",concertList.toString())

            // 각 콘서트 정보를 담고 있는 a 태그 선택
            val items = concertList.select("a.TicketItem_ticketItem__")

            // 각 a 태그에서 콘서트 정보 추출
            items.forEachIndexed { index, item ->
                val id = index.toString()
                // 제목
                val title = item.select("li.TicketItem_goodsName__Ju76j").text()

                // 장소
                val venue = item.select("li.TicketItem_placeName__ls_9C").text()

                // 날짜
                val date = item.select("li.TicketItem_playDate__5ePr2").text()

                // 이미지 URL
                val imageUrl = item.select("div.TicketItem_imageWrap__iVEOw img").attr("src")

                // 상세 페이지 URL
                val detailUrl = item.attr("href")

                // Concert 객체 생성 및 리스트에 추가
                concerts.add(
                    Concert(
                        id = id,
                        title = title,
                        date = date,
                        venue = venue,
                        detailUrl = detailUrl,
                        imageUrl = imageUrl
                    )
                )

            }
            Log.d("인터파크 콘서트", concerts.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext concerts
    }

//    private fun parseConcertElement(element: Element): Concert {
//        return Concert(
//            id = element.selectFirst("div.id")?.text() ?: "id 없음",
//            title = element.selectFirst("div.prdTitle")?.text() ?: "제목 없음",
//            date = element.selectFirst("div.prdDate")?.text() ?: "날짜 정보 없음",
//            venue = element.selectFirst("div.prdPlace")?.text() ?: "장소 정보 없음",
//            detailUrl = element.selectFirst("div.ticketUrl")?.text() ?: "ticket 정보 없음",
//            imageUrl = element.selectFirst("div.imageUrl")?.text() ?: "imageUrl 정보 없음"
//        )
//    }
}
