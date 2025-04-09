package com.youngs.findconcert.data.local

import androidx.room.withTransaction
import com.youngs.findconcert.data.toDomain
import com.youngs.findconcert.data.toDomainDetail
import com.youngs.findconcert.data.toEntity
import com.youngs.findconcert.di.ConcertDatabase
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail
import javax.inject.Inject

class ConcertLocalDataSourceImpl @Inject constructor(
    private val db: ConcertDatabase
) : ConcertLocalDataSource {

    private val dao = db.concertDao()

    override suspend fun cacheConcerts(concerts: List<Concert>) {
        db.withTransaction {
            dao.insertAll(concerts.map { it.toEntity() })
        }
    }

    override suspend fun getCachedConcerts(): List<Concert> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun getCachedConcertDetail(id: String): ConcertDetail? {
        return dao.getById(id)?.toDomainDetail()  // 별도 매퍼 필요
    }

    override suspend fun searchCachedConcerts(query: String): List<Concert> {
        return dao.searchByQuery(query).map { it.toDomain() }
    }
}
