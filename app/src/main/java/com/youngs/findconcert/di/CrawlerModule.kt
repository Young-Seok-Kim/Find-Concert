package com.youngs.findconcert.di

import com.youngs.findconcert.InterparkCrawler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CrawlerModule {

    @Provides
    @Singleton
    fun provideInterparkCrawler(): InterparkCrawler {
        return InterparkCrawler()
    }
}