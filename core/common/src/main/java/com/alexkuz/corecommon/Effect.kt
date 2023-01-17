package com.alexkuz.corecommon

object Completable

internal sealed class Data<DATA : Any>
internal data class Success<DATA : Any>(val data: DATA) : Data<DATA>()
internal data class Error<DATA : Any>(
    val exception: Throwable,
    val cache: DATA? = null,
) : Data<DATA>()

class Effect<DATA : Any> private constructor(
    private val value: Data<DATA>,
) {

    companion object {

        fun <T : Any> success(data: T) = Effect(Success(data))

        fun <T : Any> error(
            exception: Throwable,
            cachedData: T? = null,
        ) = Effect(Error(exception, cachedData))
    }

    val isSuccess: Boolean get() = value is Success<DATA>

    val isError: Boolean get() = value is Error<DATA>

    val requireData: DATA get() = requireNotNull(findData)
    private val findData: DATA?
        get() = when (value) {
            is Success<DATA> -> value.data
            else -> null
        }

    val requireDataOrCache: DATA get() = requireNotNull(dataOrCache)
    private val dataOrCache: DATA?
        get() = when (value) {
            is Success<DATA> -> value.data
            is Error<DATA> -> value.cache
        }

    val exceptionOrNull
        get() = when (value) {
            is Error<DATA> -> value.exception
            else -> null
        }

    suspend fun doOnSuccess(
        block: suspend (data: DATA) -> Unit,
    ): Effect<DATA> {
        if (value is Success<DATA>) {
            block(value.data)
        }
        return this
    }

    suspend fun doOnError(
        block: suspend (exception: Throwable, data: DATA?) -> Unit,
    ): Effect<DATA> {
        if (value is Error<DATA>) {
            block(value.exception, value.cache)
        }
        return this
    }

    suspend fun <OUT : Any> map(
        transform: suspend (value: DATA) -> OUT,
    ): Effect<OUT> = when (value) {
        is Success<DATA> -> success(transform(value.data))
        is Error<DATA> -> error(value.exception, value.cache?.let { transform(it) })
    }

    suspend fun <OUT : Any> flatMap(
        transform: suspend (value: DATA) -> Effect<OUT>,
    ): Effect<OUT> = when (value) {
        is Success<DATA> -> transform(value.data)
        is Error<DATA> -> error(value.exception)
    }

    fun toCompletable() = when (value) {
        is Success<DATA> -> success(Completable)
        is Error<DATA> -> error(value.exception)
    }
}

inline fun <IN, OUT : Any> IN.tryRun(block: IN.() -> OUT): Effect<OUT> {
    return try {
        Effect.success(block())
    } catch (e: Throwable) {
        Effect.error(e)
    }
}

inline fun <OUT : Any> tryRun(block: () -> OUT): Effect<OUT> {
    return try {
        Effect.success(block())
    } catch (e: Throwable) {
        Effect.error(e)
    }
}