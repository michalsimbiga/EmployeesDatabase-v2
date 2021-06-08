package com.data.networking.endpoints

import com.data.networking.models.wrappers.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersEndpoint {

    @GET("/api")
    suspend fun getUsers(@Query("results") results: String = "100"): UsersResponse
}