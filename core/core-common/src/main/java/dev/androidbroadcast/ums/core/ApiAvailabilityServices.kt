package dev.androidbroadcast.ums.core

import android.content.Context

/**
 * Abstraction on ApiAvailability APIs of mobile services
 */
public interface ApiAvailabilityServices {

    public val servicesInfo: ServicesInfo

    /**
     * Check is services present on device
     */
    public fun isServicesAvailable(context: Context): Int

    public fun isServicesAvailable(context: Context, minApkVersion: Int): Int

    public data class ServicesInfo(
        /**
         * Unique string that identify implementation of [ApiAvailabilityServices]
         */
        public val serviceId: String,
        /**
         * Package name of store with what the services is connected. As example,
         * for Google Play Services it will be Google Play with package name 'com.android.vending'.
         *
         * Can be null in case when services are independent of any app store.
         */
        public val storePackageName: String?
    ) {
        override fun toString(): String = serviceId
    }
}

/**
 * Check is services present on device. Maybe require update, or isn't working, but exists
 */
public fun ApiAvailabilityServices.isServicesReady(context: Context): Boolean {
    return isServicesAvailable(context) == ConnectionResult.SUCCESS
}