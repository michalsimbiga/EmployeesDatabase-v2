package com.data.repositories

import com.data.mappers.toDomain
import com.data.services.UsersService
import com.domain.models.User
import com.domain.repositories.UsersRepository
import javax.inject.Inject

class DefaultUsersRepository @Inject constructor(
    private val usersApiService: UsersService.Api
) : UsersRepository {

    override suspend fun getUsers(): List<User> = usersApiService.getUsers().map { it.toDomain() }
}