package com.alexkuz.app

import android.app.Application
import android.content.Context
import com.alexkuz.corecommon.ContextUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ContextUtils.updateLocale(it) })
    }
}