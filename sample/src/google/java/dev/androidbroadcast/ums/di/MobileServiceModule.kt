package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.analytics.firebase.GoogleAnalytics

import dev.androidbroadcast.ums.push.UmsMessagingManager
import dev.androidbroadcast.ums.push.firebase.FirebaseMessagingService
import javax.inject.Singleton

@Module
class MobileServiceModule {

    @Provides
    @Singleton
    fun providePushManager(): UmsMessagingManager {
        if(!UmsMessagingManager.isInitialized) {
            UmsMessagingManager.init(FirebaseMessagingService())
        }
        return UmsMessagingManager.getInstance()
    }

    @Provides
    @Singleton
    fun provideAnalytics(application: Application): Analytics {
        return GoogleAnalytics(application)
    }
}