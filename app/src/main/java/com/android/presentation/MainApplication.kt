package com.android.presentation

import com.android.di.InjectorApplication
import com.core.analytics.ActivityAnalytics
import com.core.analytics.ActivityCallbacksImpl
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.prosoma.livingwell.BuildConfig
import timber.log.Timber

class MainApplication : InjectorApplication() {

    private val activityLifecycleCallbacks by lazy { ActivityAnalytics(ActivityCallbacksImpl()) }

    override fun onCreate() {
        super.onCreate()
        initAppCenter()
        initTimber()
        setupLifecycleCallbacks()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initAppCenter() {
        AppCenter.start(
            this,
            BuildConfig.APP_CENTER_APP_ID,
            Analytics::class.java,
            Crashes::class.java
        )
    }

    private fun setupLifecycleCallbacks() =
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)

        super.onTerminate()
    }
}