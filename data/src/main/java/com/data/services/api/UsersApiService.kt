package com.data.services.api

import com.data.networking.endpoints.UsersEndpoint
import com.data.networking.models.UserApiModel
import com.data.services.UsersService
import com.data.services.base.ApiService
import javax.inject.Inject

class UsersApiService @Inject constructor(
    private val endpoint: UsersEndpoint
) : ApiService(), UsersService.Api {

    override suspend fun getUsers(): List<UserApiModel> = request { endpoint.getUsers() }.results
}