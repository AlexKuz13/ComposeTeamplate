package com.alexkuz.data.network

import android.app.Application
import com.alexkuz.data.network.api.MockApi
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(includes = [NetworkStateProviderModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(application: Application) =
        ChuckerInterceptor.Builder(application).build()
}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkStateProviderModule {

    @Binds
    @Singleton
    fun bindNetworkStateProvider(provider: NetworkStateProviderImpl): NetworkStateProvider
}

@Module
@InstallIn(SingletonComponent::class)
class NetworkApiProviderModule {

    @Provides
    @Singleton
    fun provideMockApi(config: ApiConfig) =
        config.builder(MockApi::class.java)
            .service(ApiService.API)
            .build()
}