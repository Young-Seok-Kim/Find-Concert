package com.youngs.findconcert.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConcertApiService {

    @GET("concerts")
    suspend fun getConcerts(): List<NetworkConcert>

    @GET("concerts/{id}")
    suspend fun getConcertDetail(@Path("id") id: String): NetworkConcertDetail

    @GET("concerts/search")
    suspend fun searchConcerts(@Query("q") query: String): List<NetworkConcert>
}
