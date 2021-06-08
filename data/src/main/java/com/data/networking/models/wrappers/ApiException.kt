package com.data.networking.models.wrappers

import retrofit2.HttpException
import java.io.IOException

class ApiException private constructor(
    message: String?,
    exception: Throwable,
    val type: Type
) : RuntimeException(message, exception) {

    enum class Type {
        NETWORK,
        HTTP,
        UNEXPECTED
    }

    companion object {

        fun asRestException(throwable: Throwable): ApiException {
            return when (throwable) {
                is ApiException -> throwable
                is HttpException -> asHttpError(throwable)
                is IOException -> asNetworkError(throwable)
                else -> asUnexpectedError(throwable)
            }
        }

        private fun asHttpError(httpException: HttpException): ApiException {
            return ApiException(
                httpException.message,
                httpException,
                Type.HTTP
            )
        }

        private fun asNetworkError(exception: IOException): ApiException {
            return ApiException(
                exception.message,
                exception,
                Type.NETWORK
            )
        }

        private fun asUnexpectedError(exception: Throwable): ApiException {
            return ApiException(
                exception.message,
                exception,
                Type.UNEXPECTED
            )
        }
    }
}