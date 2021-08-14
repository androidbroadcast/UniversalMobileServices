package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.analytics.gms.GoogleAnalytics
import dev.androidbroadcast.ums.analytics.hms.HmsAnalytics
import dev.androidbroadcast.ums.core.UmsApiAvailability

import dev.androidbroadcast.ums.push.UmsMessagingManager
import dev.androidbroadcast.ums.push.gms.FirebaseMessagingService
import dev.androidbroadcast.ums.push.hms.HmsMessagingService
import javax.inject.Singleton

@Module
class MobileServiceModule2 {

    @Provides
    @AppScope
    fun providePushManager(application: Application): UmsMessagingManager {
        if(!UmsMessagingManager.isInitialized) {
            UmsMessagingManager.init(FirebaseMessagingService())
        }
//        if (!UmsMessagingManager.isInitialized) {
//            UmsMessagingManager.init(
//                HmsMessagingService(application, "dev.androidbroadcast.ums")
//            )
//        }
        return UmsMessagingManager.getInstance()
    }

    @Provides
    @AppScope
    fun provideAnalytics(application: Application): Analytics {
//        Analytics.newInstance(HmsAnalytics(application))
        return GoogleAnalytics(application)
    }

    @Reusable
    @Provides
    fun provideMobileServices(application: Application): UmsApiAvailability {
        return UmsApiAvailability(application)
    }
}