package com.alexkuz.data

import com.alexkuz.coredata.BuildConfig

enum class ApiService(
    private val baseUrl: String,
) {
    API(baseUrl = BuildConfig.BASE_URL);

    fun getBaseUrl() = baseUrl
}