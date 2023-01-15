package com.alexkuz.data.resources

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ResourcesModule {

    @Binds
    @Singleton
    fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager
}