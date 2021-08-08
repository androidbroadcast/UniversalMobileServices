package dev.androidbroadcast.ums.di

import dagger.Component
import javax.inject.Scope

@AppScope
@Component(modules = [MobileServiceModule::class])
interface AppComponent

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

