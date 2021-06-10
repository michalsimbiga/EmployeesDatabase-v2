package com.android.di

import android.app.Application
import com.android.di.add.AddInjector
import com.android.di.home.HomeInjector
import com.android.di.main.MainComponent
import com.android.di.main.MainInjector
import com.android.presentation.edit.EditFragment
import com.android.presentation.home.HomeFragment
import com.android.presentation.main.MainActivity

open class InjectorApplication : Application(),
    MainInjector,
    AddInjector,
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

    override fun inject(fragment: HomeFragment) {
        mainComponent?.homeComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()
    }

    override fun inject(fragment: EditFragment) {
        mainComponent?.addComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()
    }

    override fun clearMainComponent() {
        mainComponent = null
    }
}