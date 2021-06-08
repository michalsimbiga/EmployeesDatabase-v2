package com.data.di

import dagger.Module

@Module(
    includes = [
        RepositoriesModule::class,
    ]
)
interface DataModule