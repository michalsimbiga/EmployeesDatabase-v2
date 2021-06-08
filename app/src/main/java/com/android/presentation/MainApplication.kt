package com.android.presentation

import com.android.di.InjectorApplication
import com.core.analytics.ActivityAnalytics
import com.core.analytics.ActivityCallbacksImpl
import com.prosoma.livingwell.BuildConfig
import timber.log.Timber

class MainApplication : InjectorApplication() {

    private val activityLifecycleCallbacks by lazy { ActivityAnalytics(ActivityCallbacksImpl()) }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        setupLifecycleCallbacks()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupLifecycleCallbacks() =
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)

        super.onTerminate()
    }
}