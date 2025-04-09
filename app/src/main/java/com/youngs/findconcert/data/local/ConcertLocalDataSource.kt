package com.youngs.findconcert.data.local

import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail

interface ConcertLocalDataSource {
    suspend fun cacheConcerts(concerts: List<Concert>)
    suspend fun getCachedConcerts(): List<Concert>
    suspend fun getCachedConcertDetail(id: String): ConcertDetail?
    suspend fun searchCachedConcerts(query: String): List<Concert>
}