package com.data.di

import com.data.services.UsersService
import com.data.services.api.UsersApiService
import dagger.Binds
import dagger.Module

@Module
internal interface ServicesModule {

    @Binds
    fun bindUsersApiService(service: UsersApiService): UsersService.Api
}