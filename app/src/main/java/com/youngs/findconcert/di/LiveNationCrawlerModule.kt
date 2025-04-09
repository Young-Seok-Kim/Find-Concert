package com.youngs.findconcert.di

import com.youngs.findconcert.presentation.crawler.LiveNationCrawler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LiveNationCrawlerModule {

    @Provides
    @Singleton
    fun provideInterparkCrawler(): LiveNationCrawler {
        return LiveNationCrawler()
    }
}