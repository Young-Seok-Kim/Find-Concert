package com.youngs.findconcert.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.youngs.findconcert.data.local.ConcertDao
import com.youngs.findconcert.data.local.ConcertEntity

@Database(
    entities = [ConcertEntity::class],
    version = 1
)
abstract class ConcertDatabase : RoomDatabase() {
    abstract fun concertDao(): ConcertDao
}
