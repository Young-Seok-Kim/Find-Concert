package com.youngs.findconcert.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youngs.findconcert.data.local.ConcertList
import com.youngs.findconcert.presentation.crawler.InterparkCrawler
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.presentation.crawler.LiveNationCrawler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterparkViewModel @Inject constructor(
    private val interparkCrawler: InterparkCrawler,
    private val liveNationCrawler: LiveNationCrawler
) : ViewModel() {
    private val _concertLists = MutableStateFlow<List<ConcertList>>(emptyList())
    val concertLists: StateFlow<List<ConcertList>> = _concertLists

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
                val interparkList = interparkCrawler.crawlConcerts()
                val liveNationList = liveNationCrawler.crawlConcerts()
                _concertLists.value = listOf(
                    ConcertList("인터파크", interparkList),
                    ConcertList("Live Nation", liveNationList)
                )
        }
    }
}