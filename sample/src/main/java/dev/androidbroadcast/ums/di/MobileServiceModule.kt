package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.core.UmsApiAvailability
import dev.androidbroadcast.ums.push.UmsMessagingManager

@Module
class MobileServiceModule {

    @Provides
    @AppScope
    fun providePushManager(application: Application): UmsMessagingManager {
        if (!UmsMessagingManager.isInitialized) {
            UmsMessagingManager.initWithDefault(application, APP_ID)
        }
        return UmsMessagingManager.instance
    }

    @Provides
    @AppScope
    fun provideAnalytics(application: Application): Analytics {
        return Analytics.getDefault(application)
    }

    @Reusable
    @Provides
    fun provideMobileServices(application: Application): UmsApiAvailability {
        return UmsApiAvailability(application)
    }
}

private const val APP_ID = "dev.androidbroadcast.ums"
