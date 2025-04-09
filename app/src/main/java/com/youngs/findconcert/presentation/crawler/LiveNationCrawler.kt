package com.youngs.findconcert.presentation.crawler

import android.util.Log
import com.youngs.findconcert.domain.model.Concert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.jsoup.Jsoup

class LiveNationCrawler {

    private val LIVENATION_URL = "https://www.livenation.kr/event/allevents"

    suspend fun crawlConcerts(): List<Concert> = withContext(Dispatchers.IO) {
        val concerts = mutableListOf<Concert>()

        try {
            val doc = Jsoup.connect(LIVENATION_URL)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get()

            // type="application/ld+json" 스크립트 태그 선택
            val scriptTags = doc.select("script[type=application/ld+json]")

            scriptTags.forEachIndexed { index, scriptTag ->
                try {
                    // JSON 데이터 파싱
                    val jsonData = scriptTag.html()
                    val jsonObject = JSONObject(jsonData)

                    // 필요한 데이터 추출
                    val id = index.toString()
                    val title = jsonObject.optString("name")
                    val date = jsonObject.optString("startDate")
                    val venue = jsonObject.getJSONObject("location").optString("name")
                    val detailUrl = jsonObject.optString("url")
                    val imageUrl = jsonObject.optString("image")

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
                } catch (e: Exception) {
                    Log.e("LiveNationCrawler", "JSON 파싱 에러: ${e.message}")
                }
            }

        } catch (e: Exception) {
            Log.e("LiveNationCrawler", "크롤링 에러: ${e.message}")
        }

        return@withContext concerts
    }
}