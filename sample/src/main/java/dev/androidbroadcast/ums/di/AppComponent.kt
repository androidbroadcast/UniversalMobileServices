package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.core.UmsApiAvailability
import dev.androidbroadcast.ums.push.UmsMessagingManager
import javax.inject.Scope

@AppScope
@Component(modules = [MobileServiceModule::class])
interface AppComponent {

    val apiAvailability: UmsApiAvailability

    val umsMessagingManager: UmsMessagingManager

    val analytics: Analytics

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

