package com.alexkuz.app

import android.app.Application
import android.content.Context
import com.alexkuz.corecommon.ContextUtils
import com.alexkuz.data.coroutine.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application(), ApplicationCoroutineScopeHolder {

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ContextUtils.updateLocale(it) })
    }
}