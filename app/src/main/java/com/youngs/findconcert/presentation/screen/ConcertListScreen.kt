package com.youngs.findconcert.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.presentation.component.ConcertItem
import com.youngs.findconcert.presentation.viewmodel.ConcertViewModel
import com.youngs.findconcert.presentation.viewmodel.InterparkViewModel
import kotlinx.coroutines.launch

@Composable
fun ConcertListScreen(
    viewModel: InterparkViewModel = hiltViewModel()
) {
    val concerts = viewModel.concerts.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> LoadingIndicator() // 실제 로딩 인디케이터로 대체
            error != null -> Text(text = "Error: $error") // 실제 에러 메시지 컴포넌트로 대체
            else -> ConcertLazyList(concerts)
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
        items(
            items = concerts,
            key = { concert -> concert.id }
        ) { concert ->
            ConcertItem(concert = concert)
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
@Composable
fun ConcertListScreenLoadingPreview() {
    ConcertListScreen()
}