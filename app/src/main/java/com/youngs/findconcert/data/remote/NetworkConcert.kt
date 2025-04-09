// NetworkConcert.kt  
package com.youngs.findconcert.data.remote

data class NetworkConcert(
    val id: String,
    val title: String,
    val date: String,
    val venue: String,
    val ticketUrl: String,
    val imageUrl: String?
)
