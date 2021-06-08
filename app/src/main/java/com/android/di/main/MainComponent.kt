package com.android.di.main

import android.app.Activity
import com.android.di.home.HomeComponent
import com.android.presentation.main.MainActivity
import com.android.di.ActivityScope
import com.prosoma.livingwell.di.main.MainModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    fun homeComponentFactory(): HomeComponent.Factory

    @Subcomponent.Factory
    interface Factory {

        fun bindActivity(@BindsInstance activity: Activity): MainComponent
    }
}