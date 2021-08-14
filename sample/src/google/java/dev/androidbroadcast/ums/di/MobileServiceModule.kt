package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.analytics.gms.GoogleAnalytics
import dev.androidbroadcast.ums.core.UmsApiAvailability

import dev.androidbroadcast.ums.push.UmsMessagingManager
import dev.androidbroadcast.ums.push.gms.FirebaseMessagingService
import javax.inject.Singleton

@Module
class MobileServiceModule {

    @Provides
    @AppScope
    fun providePushManager(): UmsMessagingManager {
        if(!UmsMessagingManager.isInitialized) {
            UmsMessagingManager.init(FirebaseMessagingService())
        }
        return UmsMessagingManager.getInstance()
    }

    @Provides
    @AppScope
    fun provideAnalytics(application: Application): Analytics {
        return GoogleAnalytics(application)
    }

    @Reusable
    @Provides
    fun provideMobileServices(application: Application): UmsApiAvailability {
        return UmsApiAvailability(application)
    }
}