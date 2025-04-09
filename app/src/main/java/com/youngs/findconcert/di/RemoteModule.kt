// RemoteModule.kt
package com.youngs.findconcert.di

import com.youngs.findconcert.data.remote.ConcertRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.concert.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideConcertService(retrofit: Retrofit): ConcertRemoteDataSource {
        return retrofit.create(ConcertRemoteDataSource::class.java)
    }
}
