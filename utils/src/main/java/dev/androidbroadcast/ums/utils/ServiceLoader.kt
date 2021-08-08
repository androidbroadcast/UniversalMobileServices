package dev.androidbroadcast.ums.utils

import java.util.ServiceLoader

public inline fun <reified T : Any> loadServices(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}