package dev.androidbroadcast.ums.hms.core

import android.content.Context
import com.huawei.hms.api.ConnectionResult
import com.huawei.hms.api.HuaweiApiAvailability
import dev.androidbroadcast.ums.core.ApiAvailabilityServices

internal class HmsApiAvailabilityServices : ApiAvailabilityServices {

    override val serviceId: String = HMS_ID

    override fun isServicesAvailable(context: Context): Boolean {
        val apiAvailability = HuaweiApiAvailability.getInstance()
        return ConnectionResult(apiAvailability.isHuaweiMobileServicesAvailable(context)).isSuccess
    }

    override val storePackageName = APPGALLERY_PACKAGE
}

@Suppress("SpellCheckingInspection")
private const val APPGALLERY_PACKAGE = "com.huawei.appmarket"
public const val HMS_ID: String = "dev.androidbroadcast.ums.hms"