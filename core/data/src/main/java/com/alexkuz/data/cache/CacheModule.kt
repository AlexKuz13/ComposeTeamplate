package com.alexkuz.data.cache

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    @PrefsCacheCleaner
    fun provideCacheCleaner(cacheProvider: PrefsCacheProvider): CacheCleaner = cacheProvider
}