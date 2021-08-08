package dev.androidbroadcast.ums.hms.core

import android.content.Context
import com.huawei.hms.api.ConnectionResult
import com.huawei.hms.api.HuaweiApiAvailability
import dev.androidbroadcast.ums.core.ApiAvailabilityServices
import dev.androidbroadcast.ums.core.ConnectionResult as UmsConnectionResult

internal class HmsApiAvailabilityServices : ApiAvailabilityServices {

    override val serviceId: String = HMS_ID

    override fun isServicesAvailable(context: Context): Int {
        val apiAvailability = HuaweiApiAvailability.getInstance()
        return mapConnectionResult(apiAvailability.isHuaweiMobileServicesAvailable(context))
    }

    override fun isServicesAvailable(context: Context, minApkVersion: Int): Int {
        val apiAvailability = HuaweiApiAvailability.getInstance()
        return mapConnectionResult(
            apiAvailability.isHuaweiMobileServicesAvailable(context, minApkVersion)
        )
    }

    override val storePackageName = APPGALLERY_PACKAGE
}

@Suppress("SpellCheckingInspection")
private const val APPGALLERY_PACKAGE = "com.huawei.appmarket"
public const val HMS_ID: String = "dev.androidbroadcast.ums.hms"

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
    ConnectionResult.BINDFAIL_RESOLUTION_REQUIRED -> UmsConnectionResult.BINDFAIL_RESOLUTION_REQUIRED
    ConnectionResult.BINDFAIL_RESOLUTION_BACKGROUND -> UmsConnectionResult.BINDFAIL_RESOLUTION_BACKGROUND
    ConnectionResult.SERVICE_UNSUPPORTED -> UmsConnectionResult.API_DISABLED
    ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED -> UmsConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED
    else -> UmsConnectionResult.UNKNOWN
}