package com.dgmltn.helen

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.support.annotation.WorkerThread
import io.particle.android.sdk.cloud.ParticleCloudException
import io.particle.android.sdk.cloud.ParticleCloudSDK
import io.particle.android.sdk.cloud.ParticleDevice

import timber.log.Timber
import java.io.IOException

class ParticleHelenService : IntentService("") {

    override fun onHandleIntent(intent: Intent) {
        when (intent.action) {
            ACTION_LEFT_UP, ACTION_LEFT_MY, ACTION_LEFT_DOWN, ACTION_RIGHT_UP, ACTION_RIGHT_MY, ACTION_RIGHT_DOWN -> {
                performFunction(intent.action)
            }
            else -> {}
        }
    }

    companion object {
        private val ACTION_LEFT_UP = "left_up"
        private val ACTION_LEFT_MY = "left_my"
        private val ACTION_LEFT_DOWN = "left_down"

        private val ACTION_RIGHT_UP = "right_up"
        private val ACTION_RIGHT_MY = "right_my"
        private val ACTION_RIGHT_DOWN = "right_down"

        fun getLeftUpIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_LEFT_UP
            return intent
        }

        fun getLeftMyIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_LEFT_MY
            return intent
        }

        fun getLeftDownIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_LEFT_DOWN
            return intent
        }

        fun getRightUpIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_RIGHT_UP
            return intent
        }

        fun getRightMyIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_RIGHT_MY
            return intent
        }

        fun getRightDownIntent(context: Context): Intent {
            val intent = Intent(context, ParticleHelenService::class.java)
            intent.action = ACTION_RIGHT_DOWN
            return intent
        }

        @WorkerThread
        fun performFunction(f: String): Int {
            Timber.e("command = $f")
            try {
                val result = App.device.callFunction(f)
                Timber.e("command = $f. Result = $result")
                return result
            }
            catch (e: ParticleCloudException) {
                Timber.e(e.bestMessage)
                e.printStackTrace()
            }
            catch (e: ParticleDevice.FunctionDoesNotExistException) {
                Timber.e(e.message)
                e.printStackTrace()
            }
            catch (e: IOException) {
                Timber.e(e.message)
                e.printStackTrace()
            }
            return -1
        }

    }

}
