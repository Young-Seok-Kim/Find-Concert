package com.youngs.findconcert.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youngs.findconcert.data.repository.ConcertRepository
import com.youngs.findconcert.domain.model.Concert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConcertViewModel @Inject constructor(
    private val repository: ConcertRepository
) : ViewModel() {

    private val _concerts = MutableStateFlow<List<Concert>>(emptyList())
    val concerts: StateFlow<List<Concert>> = _concerts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadConcerts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _concerts.value = repository.getConcerts()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}