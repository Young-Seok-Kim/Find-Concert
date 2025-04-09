package com.youngs.findconcert.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.youngs.findconcert.data.local.ConcertList
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.presentation.component.ConcertItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConcertListScreen(concertLists: List<ConcertList>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        // 좌우/상하 패딩 유지
    ) {
        concertLists.forEach { concertList ->
            stickyHeader { // stickyHeader 사용
                Column(
                    modifier = Modifier.fillMaxWidth().background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(8.dp) // 타이틀과 Divider 간격 유지
                ) {
                    // 리스트 타이틀 (상단 고정)
                    Text(
                        text = concertList.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth(), // 상단 패딩 제거
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Divider()
                }
            }

            items(concertList.concerts) { concert ->
                ConcertItem(concert = concert)
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    // 실제 로딩 인디케이터 구현 (예: CircularProgressIndicator)
    Text(text = "Loading...")
}

@Composable
private fun ConcertLazyList(concerts: List<Concert>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = concerts,
            key = { _, concert -> concert.id }
        ) { index, concert ->
            ConcertItem(concert = concert)
            if (index < concerts.size - 1) { // 마지막 아이템 뒤에는 Divider를 추가하지 않음
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

// 프리뷰를 위한 샘플 데이터
private val sampleConcerts = listOf(
    Concert(1.toString(), "Concert 1", "Artist A", "Venue X", "Date 1", "ImageUrl1"),
    Concert(2.toString(), "Concert 2", "Artist B", "Venue Y", "Date 2", "ImageUrl2"),
    // 추가 샘플 데이터...
)



//@Preview(showBackground = true)
//@Composable
//fun ConcertListScreenLoadingPreview() {
//    ConcertListScreen()
//}