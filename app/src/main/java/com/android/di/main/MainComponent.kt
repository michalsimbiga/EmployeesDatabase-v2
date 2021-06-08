package com.prosoma.livingwell.di.main

import android.app.Activity
import com.prosoma.livingwell.di.ActivityScope
import com.prosoma.livingwell.di.first.FirstComponent
import com.android.presentation.main.MainActivity
import com.android.presentation.second.SecondFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: SecondFragment)

    fun firstComponentFactory(): FirstComponent.Factory

    @Subcomponent.Factory
    interface Factory {

        fun bindActivity(@BindsInstance activity: Activity): MainComponent
    }
}