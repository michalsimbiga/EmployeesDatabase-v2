package com.domain.repositories

interface UsersRepository {

    suspend fun getUsers(): List<User>
}