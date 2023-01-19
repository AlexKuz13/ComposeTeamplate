package com.alexkuz.data.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkStateProviderModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule

@Module
@InstallIn(SingletonComponent::class)
interface NetworkStateProviderModule {

    @Binds
    @Singleton
    fun bindNetworkStateProvider(provider: NetworkStateProviderImpl): NetworkStateProvider
}