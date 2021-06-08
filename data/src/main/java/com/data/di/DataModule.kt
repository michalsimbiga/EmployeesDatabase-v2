package com.data.di

import dagger.Module

@Module(
    includes = [
        RepositoriesModule::class,
        DatabaseModule::class
    ]
)
interface DataModule