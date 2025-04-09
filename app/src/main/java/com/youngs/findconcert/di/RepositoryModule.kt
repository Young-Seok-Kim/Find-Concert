package com.youngs.findconcert.di

import com.youngs.findconcert.data.repository.ConcertRepository
import com.youngs.findconcert.data.repository.ConcertRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindConcertRepository(
        impl: ConcertRepositoryImpl
    ): ConcertRepository
}
