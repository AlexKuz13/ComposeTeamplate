package com.alexkuz.data.cache

import kotlin.reflect.KType
import kotlin.time.Duration

interface CacheCleaner {

    suspend fun clear(type: KType, key: String)

    suspend fun clearAll()
}

interface CacheProvider : CacheCleaner {

    suspend fun <T> get(type: KType, key: String): T?

    suspend fun <T> save(type: KType, key: String, data: T, lifeDuration: Duration)
}

class CacheElement<T>(
    val data: T,
    private val lifeDuration: Duration,
    private val creationDate: Long = System.currentTimeMillis()
) {

    suspend fun getOrClear(clearAction: suspend () -> Unit): CacheElement<T>? {
        val isExpired = System.currentTimeMillis() - creationDate > lifeDuration.inWholeMilliseconds
        return if (isExpired) {
            clearAction()
            null
        } else {
            this
        }
    }
}