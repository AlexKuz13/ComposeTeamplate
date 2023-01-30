package com.alexkuz.data.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.alexkuz.data.json.KotlinxSerializationJsonProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.serializerOrNull
import java.util.Collections
import javax.inject.Inject
import kotlin.reflect.KType
import kotlin.time.Duration

private const val CACHE_STORE = "cache_store"

class PrefsCacheProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonProvider: KotlinxSerializationJsonProvider,
) : CacheProvider {

    private val Context.datastore by preferencesDataStore(name = CACHE_STORE)
    private val keysMap = Collections.synchronizedMap(
        mutableMapOf<KType, Preferences.Key<String>>()
    )

    override suspend fun <T> get(type: KType, key: String): T? {
        val dataSerializer = serializerOrNull(type) ?: return null

        return context.datastore
            .get(getKey(type, key))
            .firstOrNull()
            ?.let { cacheItemJson ->
                jsonProvider.get().decodeFromString(cacheItemJson) as CacheElement<String>
            }
            ?.getOrClear { clear(type, key) }
            ?.data
            ?.let { dataJson ->
                jsonProvider.get().decodeFromString(dataSerializer, dataJson) as? T?
            }
    }

    override suspend fun <T> save(type: KType, key: String, data: T, lifeDuration: Duration) {
        val dataSerializer = serializerOrNull(type) ?: return
        val dataJson = jsonProvider.get().encodeToString(dataSerializer, data)
        val cacheItem = CacheElement(dataJson, lifeDuration)
        val cacheItemJson = jsonProvider.get().encodeToString(cacheItem)
        context.datastore.set(stringPreferencesKey(key), cacheItemJson)
    }

    override suspend fun clear(type: KType, key: String) {
        context.datastore.edit { it.remove(getKey(type, key)) }
        keysMap.remove(type, stringPreferencesKey(key))
    }

    override suspend fun clearAll() {
        context.datastore.edit { it.clear() }
    }

    private fun getKey(type: KType, key: String) = keysMap.getOrPut(type) {
        stringPreferencesKey(key)
    }
}

private fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>) = data.map { it[key] }

private suspend fun <T> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T) = edit {
    it[key] = value
}