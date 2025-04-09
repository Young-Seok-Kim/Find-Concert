package com.youngs.findconcert.di

import android.content.Context
import androidx.room.Room
import com.youngs.findconcert.data.local.ConcertDao
import com.youngs.findconcert.data.local.ConcertLocalDataSource
import com.youngs.findconcert.data.local.ConcertLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ConcertDatabase {
        return Room.databaseBuilder(
            context,
            ConcertDatabase::class.java,
            "concert_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: ConcertDatabase): ConcertLocalDataSource {
        return ConcertLocalDataSourceImpl(db)
    }
}
