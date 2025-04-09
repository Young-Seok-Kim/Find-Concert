package com.youngs.findconcert.data.repository

import com.youngs.findconcert.data.local.ConcertLocalDataSource
import com.youngs.findconcert.data.remote.ConcertRemoteDataSource
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail
import javax.inject.Inject

class ConcertRepositoryImpl @Inject constructor(
    private val remote: ConcertRemoteDataSource,
    private val local: ConcertLocalDataSource
) : ConcertRepository {

    override suspend fun getConcerts(): List<Concert> {
        return remote.getConcerts().also {
            local.cacheConcerts(it)
        }
    }

    override suspend fun getConcertDetail(id: String): ConcertDetail {
        return remote.getConcertDetail(id)
    }

    override suspend fun searchConcerts(query: String): List<Concert> {
        return remote.searchConcerts(query)
    }
}
