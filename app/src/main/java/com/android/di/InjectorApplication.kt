package com.android.di

import android.app.Application
import com.prosoma.livingwell.di.ApplicationComponent
import com.prosoma.livingwell.di.DaggerApplicationComponent
import com.prosoma.livingwell.di.first.FirstInjector
import com.prosoma.livingwell.di.main.MainComponent
import com.prosoma.livingwell.di.main.MainInjector
import com.android.presentation.first.FirstFragment
import com.android.presentation.main.MainActivity
import com.android.presentation.second.SecondFragment

open class InjectorApplication : Application(),
    MainInjector,
    FirstInjector {

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

    override fun inject(fragment: FirstFragment) {
        mainComponent?.firstComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()
    }

    override fun inject(fragment: SecondFragment) {
        mainComponent?.inject(fragment)
    }
}