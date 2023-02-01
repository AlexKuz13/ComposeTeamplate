package com.alexkuz.data.cache

import android.content.Context
import com.alexkuz.data.cache.lru.LruCache
import com.alexkuz.data.cache.lru.LruCacheCleaner
import com.alexkuz.data.cache.lru.LruCacheProvider
import com.alexkuz.data.cache.prefs.PrefsCache
import com.alexkuz.data.cache.prefs.PrefsCacheCleaner
import com.alexkuz.data.cache.prefs.PrefsCacheProvider
import com.alexkuz.data.json.KotlinxSerializationJsonProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    @LruCache
    fun lruCacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Singleton
    @LruCacheCleaner
    fun provideLruCacheCleaner(@LruCache cacheProvider: CacheProvider): CacheCleaner = cacheProvider

    @Provides
    @Singleton
    @PrefsCache
    fun prefsCacheProvider(
        @ApplicationContext context: Context,
        jsonProvider: KotlinxSerializationJsonProvider,
    ): CacheProvider {
        return PrefsCacheProvider(context, jsonProvider)
    }

    @Provides
    @Singleton
    @PrefsCacheCleaner
    fun providePrefsCacheCleaner(@PrefsCache cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}