@file:Suppress("MemberVisibilityCanBePrivate")

package dev.androidbroadcast.ums

import android.app.Application
import android.util.Log
import dev.androidbroadcast.ums.core.getRecommendedServiceIdOrFirstAvailable
import dev.androidbroadcast.ums.di.AppComponent
import dev.androidbroadcast.ums.di.DaggerAppComponent

class UmsSampleApp : Application() {

    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder()
            .application(this)
            .create()
    }

    override fun onCreate() {
        super.onCreate()
        val apiAvailability = appComponent.apiAvailability
        val availableServices = apiAvailability.getAvailableServices()
        Log.d(TAG, "availableServices=$availableServices")
        val preferredServices = apiAvailability.getRecommendedServiceIdOrFirstAvailable()
        Log.d(TAG, "preferredServices=$preferredServices")
        val analytics = appComponent.analytics
        Log.d(TAG, "analytics=$analytics")
        val umsMessagingManager = appComponent.umsMessagingManager
        Log.d(TAG, "umsMessagingManager=$umsMessagingManager")
    }

    private companion object {

        private const val TAG = "UmsSampleApp"
    }
}