package dev.androidbroadcast.ums.core

import android.content.Context

public interface ApiAvailabilityServices {

    public val serviceId: String

    public fun isServicesAvailable(context: Context): Boolean

    public val storePackageName: String?
        get() = null
}