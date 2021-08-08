package dev.androidbroadcast.ums.gms.core

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import dev.androidbroadcast.ums.core.ApiAvailabilityServices

internal class GooglePlayApiAvailabilityServices : ApiAvailabilityServices {

    override val serviceId: String = GMS_ID

    override fun isServicesAvailable(context: Context): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        return ConnectionResult(apiAvailability.isGooglePlayServicesAvailable(context)).isSuccess
    }

    override val storePackageName = GOOGLE_PLAY_PACKAGE
}

private const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
public const val GMS_ID: String = "dev.androidbroadcast.ums.gms"