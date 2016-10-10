package com.dgmltn.helen

import android.app.Application
import android.os.AsyncTask
import com.dgmltn.helen.BuildConfig

import io.particle.android.sdk.cloud.ParticleCloudSDK
import io.particle.android.sdk.cloud.ParticleDevice
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

        AsyncTask.execute {
            // Force init of device
            device.id
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        val device : ParticleDevice by lazy {
            val cloud = ParticleCloudSDK.getCloud()
            Timber.e("got cloud: ${cloud.accessToken}")
            cloud.logIn(BuildConfig.PARTICLE_USERNAME, BuildConfig.PARTICLE_PASSWORD)
            Timber.e("Logged In")
            val device = cloud.getDevice(BuildConfig.PARTICLE_HELEN_DEVICE_ID)
            Timber.e("Got Device")
            device
        }
    }
}
