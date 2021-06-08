package com.domain.repositories

import com.domain.models.User

interface UsersRepository {

    suspend fun getUsers(): List<User>
}