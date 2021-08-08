package dev.androidbroadcast.ums.core

import android.content.Context
import dev.androidbroadcast.ums.utils.getInstallerPackageName
import dev.androidbroadcast.ums.utils.loadServices

public class UmsApiAvailability {

    private val apiAvailabilityServices: List<ApiAvailabilityServices> by lazy {
        loadServices<ApiAvailabilityServices>().toList()
    }

    public fun getAvailableServices(context: Context): List<String> {
        return apiAvailabilityServices
            .filter { it.isServicesAvailable(context) }
            .map { it.serviceId }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    public fun getPreferredServices(context: Context): String? {
        val installerPackageName = getInstallerPackageName(context) ?: return null
        return apiAvailabilityServices
            .find { service -> service.storePackageName == installerPackageName }?.serviceId
    }
}

public fun UmsApiAvailability.getPreferredServicesOrDefault(
    context: Context,
    default: String
): String {
    return getPreferredServices(context) ?: default
}