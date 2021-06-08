package com.data.services.base

import com.data.networking.models.wrappers.ApiException
import retrofit2.Response

abstract class ApiService {

    suspend fun <T> request(apiCall: suspend () -> T): T {
        return try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            throw ApiException.asRestException(throwable)
        }
    }

    suspend fun requestNoContent(apiCall: suspend () -> Response<Unit>) {
        return try {
            apiCall.invoke().body() ?: Unit
        } catch (throwable: Throwable) {
            throw ApiException.asRestException(throwable)
        }
    }
}