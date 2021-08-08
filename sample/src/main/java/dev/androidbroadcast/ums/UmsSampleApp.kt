package dev.androidbroadcast.ums

import android.app.Application
import dev.androidbroadcast.ums.di.AppComponent
import dev.androidbroadcast.ums.di.DaggerAppComponent

class UmsSampleApp : Application() {

    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.create()
    }
}