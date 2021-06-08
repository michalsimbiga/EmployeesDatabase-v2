package com.data.di

import com.data.repositories.EmployeesRepositoryImpl
import com.domain.repositories.EmployeesRepository
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoriesModule {

    @Binds
    fun bindUserRepository(repository: EmployeesRepositoryImpl): EmployeesRepository
}