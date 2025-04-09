package com.youngs.findconcert.data.remote

import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail
import retrofit2.http.GET

interface ConcertRemoteDataSource {
    @GET("concerts")
    suspend fun getConcerts(): List<Concert>
    suspend fun getConcertDetail(id: String): ConcertDetail
    suspend fun searchConcerts(query: String): List<Concert>
}
