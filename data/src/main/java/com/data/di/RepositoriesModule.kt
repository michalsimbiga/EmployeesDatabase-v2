package com.data.di

import com.data.repositories.DefaultUsersRepository
import com.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoriesModule {

    @Binds
    fun bindUserRepository(repository: DefaultUsersRepository): UsersRepository
}