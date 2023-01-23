package com.alexkuz.data.json

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSerializationApi::class)
class JsonModule {

    private companion object {
        private const val TYPE = "application/json"
    }

    @Provides
    @Singleton
    fun jsonConverter(jsonProvider: KotlinxSerializationJsonProvider): Converter.Factory {
        return jsonProvider.get().asConverterFactory(TYPE.toMediaType())
    }
}