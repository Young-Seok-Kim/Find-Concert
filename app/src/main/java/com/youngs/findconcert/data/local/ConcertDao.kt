package com.youngs.findconcert.data.local

import androidx.room.*

@Dao
interface ConcertDao {

    @Query("SELECT * FROM concerts")
    suspend fun getAll(): List<ConcertEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(concerts: List<ConcertEntity>)

    @Query("SELECT * FROM concerts WHERE id = :id")
    suspend fun getById(id: String): ConcertEntity?

    @Query("SELECT * FROM concerts WHERE title LIKE '%' || :query || '%'")
    suspend fun searchByQuery(query: String): List<ConcertEntity>
}
