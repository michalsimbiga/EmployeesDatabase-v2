package com.prosoma.livingwell.di

import android.app.Application
import com.core.di.CoreModule
import com.data.di.DataModule
import com.domain.di.DomainModule
import com.prosoma.livingwell.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CoreModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface ApplicationComponent {

    fun getMainComponentFactory(): MainComponent.Factory

    @Component.Factory
    interface Factory {

        fun bindApplication(@BindsInstance application: Application): ApplicationComponent
    }
}