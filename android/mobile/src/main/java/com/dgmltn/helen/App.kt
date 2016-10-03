package com.dgmltn.helen

import android.app.Application
import com.dgmltn.helen.BuildConfig

import io.particle.android.sdk.cloud.ParticleCloudSDK
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        else {
            // Don't log on production
        }
        ParticleCloudSDK.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}
