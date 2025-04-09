package com.youngs.findconcert.data.repository

import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail
import retrofit2.http.GET

interface ConcertRepository {
    @GET
    suspend fun getConcerts(): List<Concert>
    @GET
    suspend fun getConcertDetail(id: String): ConcertDetail
    @GET
    suspend fun searchConcerts(query: String): List<Concert>
}
