package com.data.di

import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        RepositoriesModule::class,
        ServicesModule::class,
        EndpointsModule::class]
)
interface DataModule