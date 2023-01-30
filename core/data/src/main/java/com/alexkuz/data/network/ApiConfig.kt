package com.alexkuz.data.network

import com.alexkuz.corecommon.BuildInfo
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val TIMEOUT_DEFAULT_TIME = 15L

class ApiConfig @Inject constructor(
    private val loggerInterceptor: HttpLoggingInterceptor,
    private val chuckerInterceptor: ChuckerInterceptor,
    private val converter: Converter.Factory,
    private val buildInfo: BuildInfo,
) {

    fun <API> builder(clazz: Class<API>): Params<API> = Params(clazz)

    private fun createOkHttpClient(
        baseUrl: String,
        interceptors: Set<Interceptor>,
    ): Retrofit {
        val builder = okHttpClientBuilder()
        interceptors.forEach { builder.addInterceptor(it) }
        builder.build()

        return retrofitBuilder(baseUrl, builder.build()).build()
    }

    private fun retrofitBuilder(baseUrl: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(converter)
        .client(okHttpClient)
        .baseUrl(baseUrl)

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_DEFAULT_TIME, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DEFAULT_TIME, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DEFAULT_TIME, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .followRedirects(true)

        if (!buildInfo.isRelease) {
            builder.addInterceptor(chuckerInterceptor)
            builder.addInterceptor(loggerInterceptor)
        }
        return builder
    }

    inner class Params<API>(var clazz: Class<API>) {

        private var service: ApiService? = null
        private val interceptors = mutableSetOf<Interceptor>()

        fun service(service: ApiService) = apply { this.service = service }

        fun interceptor(interceptor: Interceptor) = apply { interceptors += interceptor }

        fun build(): API = createOkHttpClient(
            requireNotNull(service).getBaseUrl(),
            interceptors,
        ).create(clazz)
    }
}