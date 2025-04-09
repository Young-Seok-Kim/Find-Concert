package com.youngs.findconcert.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youngs.findconcert.InterparkCrawler
import com.youngs.findconcert.data.repository.ConcertRepository
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


    private val _concerts = mutableStateListOf<Concert>()
    val concerts: List<Concert> get() = _concerts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun loadInterpark(){
        viewModelScope.launch {
            _concerts.clear()
            _concerts.addAll(crawler.crawlConcerts())
        }
    }
}