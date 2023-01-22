package com.alexkuz.data

import androidx.annotation.StringRes
import com.alexkuz.corecommon.extensions.ifNullOrBlank
import com.alexkuz.data.resources.ResourceManager
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.alexkuz.corecommon.R as CoreCommonR

sealed class ApiError(
    @StringRes val defaultMessageId: Int,
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause) {

    class InvalidAccessTokenException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_invalid_token, message, cause)

    class NoInternetException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_connection, message, cause)

    class ServerException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_server, message, cause)

    class EmptyResponseException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_unknown, message, cause)

    class HostException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_server, message, cause)

    class UnknownException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreCommonR.string.error_unknown, message, cause)

    class CoroutineException : ApiError(CoreCommonR.string.error_unknown)

    fun getMessage(resourceManager: ResourceManager): String {
        return message?.ifNullOrBlank { null } ?: resourceManager.getString(defaultMessageId)
    }
}

fun Throwable.getMessage(resourceManager: ResourceManager): String = when (this) {
    is ApiError -> getMessage(resourceManager)
    else -> resourceManager.getString(CoreCommonR.string.error_unknown)
}

@Serializable
data class ErrorDto(
    @SerialName("message") val message: String?
)