package dev.androidbroadcast.ums.gms.core

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import dev.androidbroadcast.ums.core.ConnectionResult as UmsConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import dev.androidbroadcast.ums.core.ApiAvailabilityServices

internal class GooglePlayApiAvailabilityServices : ApiAvailabilityServices {

    override val serviceId: String = GMS_ID

    override fun isServicesAvailable(context: Context): Int {
        val apiAvailability = GoogleApiAvailability.getInstance()
        return mapConnectionResult(apiAvailability.isGooglePlayServicesAvailable(context))
    }

    override fun isServicesAvailable(context: Context, minApkVersion: Int): Int {
        val apiAvailability = GoogleApiAvailability.getInstance()
        return mapConnectionResult(
            apiAvailability.isGooglePlayServicesAvailable(context, minApkVersion)
        )
    }

    override val storePackageName = GOOGLE_PLAY_PACKAGE
}

private const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
public const val GMS_ID: String = "dev.androidbroadcast.ums.gms"

private fun mapConnectionResult(result: Int): Int = when (result) {
    ConnectionResult.SUCCESS -> UmsConnectionResult.SUCCESS
    ConnectionResult.SERVICE_MISSING -> UmsConnectionResult.SERVICE_MISSING
    ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> UmsConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
    ConnectionResult.SERVICE_DISABLED -> UmsConnectionResult.SERVICE_DISABLED
    ConnectionResult.SIGN_IN_REQUIRED -> UmsConnectionResult.SIGN_IN_REQUIRED
    ConnectionResult.INVALID_ACCOUNT -> UmsConnectionResult.INVALID_ACCOUNT
    ConnectionResult.RESOLUTION_REQUIRED -> UmsConnectionResult.RESOLUTION_REQUIRED
    ConnectionResult.NETWORK_ERROR -> UmsConnectionResult.NETWORK_ERROR
    ConnectionResult.INTERNAL_ERROR -> UmsConnectionResult.INTERNAL_ERROR
    ConnectionResult.SERVICE_INVALID -> UmsConnectionResult.SERVICE_INVALID
    ConnectionResult.DEVELOPER_ERROR -> UmsConnectionResult.DEVELOPER_ERROR
    ConnectionResult.LICENSE_CHECK_FAILED -> UmsConnectionResult.LICENSE_CHECK_FAILED
    ConnectionResult.CANCELED -> UmsConnectionResult.CANCELED
    ConnectionResult.TIMEOUT -> UmsConnectionResult.TIMEOUT
    ConnectionResult.INTERRUPTED -> UmsConnectionResult.INTERRUPTED
    ConnectionResult.API_UNAVAILABLE -> UmsConnectionResult.API_UNAVAILABLE
    ConnectionResult.SIGN_IN_FAILED -> UmsConnectionResult.SIGN_IN_FAILED
    ConnectionResult.SERVICE_UPDATING -> UmsConnectionResult.SERVICE_UPDATING
    ConnectionResult.SERVICE_MISSING_PERMISSION -> UmsConnectionResult.SERVICE_MISSING_PERMISSION
    ConnectionResult.RESTRICTED_PROFILE -> UmsConnectionResult.RESTRICTED_PROFILE
    ConnectionResult.RESOLUTION_ACTIVITY_NOT_FOUND -> UmsConnectionResult.RESOLUTION_ACTIVITY_NOT_FOUND
    ConnectionResult.API_DISABLED -> UmsConnectionResult.API_DISABLED
    ConnectionResult.API_DISABLED_FOR_CONNECTION -> UmsConnectionResult.API_DISABLED_FOR_CONNECTION
    else -> UmsConnectionResult.UNKNOWN
}