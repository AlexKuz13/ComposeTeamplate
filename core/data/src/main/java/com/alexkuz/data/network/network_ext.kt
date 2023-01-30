package com.alexkuz.data.network

import com.alexkuz.corecommon.Completable
import com.alexkuz.corecommon.Effect
import com.alexkuz.data.cache.CacheProvider
import com.alexkuz.data.json.KotlinxSerializationJsonProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

suspend inline fun <reified T : Any> cachedApiCall(
    key: String,
    cacheProvider: CacheProvider,
    dispatcher: CoroutineDispatcher,
    networkStateProvider: NetworkStateProvider,
    needActualData: Boolean = false,
    lifeDuration: Duration = 5.minutes,
    noinline request: suspend () -> Response<T>,
): Effect<T> {
    return if (needActualData) {
        apiCall(dispatcher, networkStateProvider, request)
            .doOnSuccess { cacheProvider.save(typeOf<T>(), key, it, lifeDuration) }
    } else {
        cacheProvider.get<T>(typeOf<T>(), key)
            ?.let { Effect.success(it) }
            ?: apiCall(dispatcher, networkStateProvider, request)
                .doOnSuccess { cacheProvider.save(typeOf<T>(), key, it, lifeDuration) }
    }
}

suspend inline fun <reified T : Any> apiCall(
    dispatcher: CoroutineDispatcher,
    networkStateProvider: NetworkStateProvider,
    noinline block: suspend () -> Response<T>,
): Effect<T> = withContext(dispatcher) {
    try {
        block().toEffect()
    } catch (t: Throwable) {
        if (networkStateProvider.isNetworkAvailable) {
            Effect.error(ApiError.NoInternetException())
        } else {
            Effect.error(t.toApiError())
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T : Any> Response<T>.toEffect() = when {
    isSuccessful -> {
        when (val body = body()) {
            null -> when (T::class) {
                Completable::class -> Effect.success(Completable as T)
                else -> Effect.error(ApiError.EmptyResponseException())
            }
            else -> Effect.success(body)
        }
    }
    else -> {
        val exception = errorBody()?.let { errorBody ->
            val error = KotlinxSerializationJsonProvider().get()
                .decodeFromStream<ErrorDto>(errorBody.byteStream())
            fromHttpToApiError(code(), error.message)
        } ?: ApiError.ServerException()

        Effect.error(exception)
    }
}

fun Throwable.toApiError(): ApiError = when (this) {
    is UnknownHostException,
    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> ApiError.HostException()

    is CancellationException -> ApiError.CoroutineException()

    is HttpException -> fromHttpToApiError(response()?.code())

    else -> ApiError.UnknownException()
}

fun fromHttpToApiError(code: Int?, message: String? = null): ApiError {
    return when (code) {
        HttpURLConnection.HTTP_BAD_REQUEST,
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_NOT_FOUND -> ApiError.HostException(message = message)

        HttpURLConnection.HTTP_UNAUTHORIZED -> ApiError.InvalidAccessTokenException(message = message)

        HttpURLConnection.HTTP_UNAVAILABLE,
        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ApiError.ServerException(message = message)

        else -> ApiError.UnknownException(message = message)
    }
}