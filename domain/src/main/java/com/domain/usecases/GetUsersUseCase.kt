package com.domain.usecases

import com.domain.models.User
import com.domain.repositories.UsersRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) : UseCase<Unit, List<User>>() {

    override suspend fun run(params: Unit): List<User> = usersRepository.getUsers()
}