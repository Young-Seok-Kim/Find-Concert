package com.youngs.findconcert.di

import com.youngs.findconcert.presentation.crawler.InterparkCrawler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterparkCrawlerModule {

    @Provides
    @Singleton
    fun provideInterparkCrawler(): InterparkCrawler {
        return InterparkCrawler()
    }
}