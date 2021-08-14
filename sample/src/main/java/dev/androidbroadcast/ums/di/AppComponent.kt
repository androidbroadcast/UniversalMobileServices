package dev.androidbroadcast.ums.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.androidbroadcast.ums.core.UmsApiAvailability
import javax.inject.Scope

@AppScope
@Component(modules = [MobileServiceModule2::class])
interface AppComponent {

    val apiAvailability: UmsApiAvailability

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

