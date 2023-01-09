package com.alexkuz.corecommon

import android.app.NotificationManager
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SystemServiceModule {

    @Provides
    @Singleton
    fun connectivityManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun locationManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Provides
    @Singleton
    fun notificationManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}