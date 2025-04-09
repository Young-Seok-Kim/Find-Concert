package com.youngs.findconcert.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youngs.findconcert.presentation.crawler.InterparkCrawler
import com.youngs.findconcert.domain.model.Concert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterparkViewModel @Inject constructor(
    private val crawler: InterparkCrawler
) : ViewModel() {


    private val _concerts = MutableStateFlow<List<Concert>>(emptyList())
    val concerts: StateFlow<List<Concert>> = _concerts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadInterparkCrawler()
    }


    fun loadInterparkCrawler(){
            viewModelScope.launch {
                val result = crawler.crawlConcerts()
                _concerts.value = result // 크롤링 결과를 _concerts에 할당
                result.forEach { concert ->
                    Log.d("CrawledData", """  
                    인터파크
                    제목: ${concert.title}  
                    날짜: ${concert.date}  
                    장소: ${concert.venue}
                    이미지 url: ${concert.imageUrl}
                    상세 url: ${concert.detailUrl}
                """.trimIndent())
                }
        }
    }
}