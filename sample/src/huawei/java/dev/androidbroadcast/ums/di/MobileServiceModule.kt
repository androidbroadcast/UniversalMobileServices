package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.analytics.hms.HmsAnalytics
import dev.androidbroadcast.ums.push.UmsMessagingManager
import dev.androidbroadcast.ums.push.hms.HmsMessagingService
import javax.inject.Singleton

@Module
class MobileServiceModule {

    @Provides
    @Singleton
    fun providePushManager(application: Application): UmsMessagingManager {
        if (!UmsMessagingManager.isInitialized) {
            UmsMessagingManager.init(
                HmsMessagingService(application, "dev.androidbroadcast.ums")
            )
        }
        return UmsMessagingManager.getInstance()
    }

    @Provides
    @Singleton
    fun provideAnalytics(application: Application): Analytics {
        return Analytics.newInstance(HmsAnalytics(application))
    }
}