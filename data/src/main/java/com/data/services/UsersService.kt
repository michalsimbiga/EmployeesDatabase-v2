package com.data.services

import com.data.networking.models.UserApiModel

interface UsersService {

    suspend fun getUsers(): List<UserApiModel>

    interface Api : UsersService {
    }

    interface Cache : UsersService {
    }
}