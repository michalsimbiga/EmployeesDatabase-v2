package com.android.di

import android.app.Application
import com.android.di.home.HomeInjector
import com.android.presentation.home.HomeFragment
import com.android.presentation.main.MainActivity
import com.android.di.main.MainComponent
import com.android.di.main.MainInjector

open class InjectorApplication : Application(),
    MainInjector,
    HomeInjector {

    private lateinit var applicationComponent: ApplicationComponent

    private var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        DaggerApplicationComponent.factory()
            .bindApplication(this)
            .also { applicationComponent = it }
    }

    override fun inject(activity: MainActivity) {
        mainComponent ?: applicationComponent.getMainComponentFactory()
            .bindActivity(activity)
            .also { mainComponent = it }

        mainComponent?.inject(activity)
    }

    override fun clearMainComponent() {
        mainComponent = null
    }


    override fun inject(fragment: HomeFragment) {
        mainComponent?.homeComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()
    }
}