package com.youngs.findconcert.domain.model

data class ConcertDetail(
    val id: String,
    val title: String,
    val date: String,
    val venue: String,
    val description: String,
    val performers: List<String>,
    val ticketUrl: String,
    val imageUrls: List<String>
)
