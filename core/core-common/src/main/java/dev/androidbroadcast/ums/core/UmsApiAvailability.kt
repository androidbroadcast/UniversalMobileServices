@file:Suppress("MemberVisibilityCanBePrivate")

package dev.androidbroadcast.ums.core

import android.content.Context
import dev.androidbroadcast.ums.utils.getInstallerPackageName
import dev.androidbroadcast.ums.utils.loadServices

public class UmsApiAvailability(internal val context: Context) {

    private val apiAvailabilityServices: List<ApiAvailabilityServices> by lazy {
        loadServices<ApiAvailabilityServices>().toList()
    }

    public var currentServicesId: String? = getPreferredServicesId()
        private set

    public fun getAvailableServices(): List<ApiAvailabilityServices.ServicesInfo> {
        return apiAvailabilityServices
            .filter { it.isServicesAvailable(context) == ConnectionResult.SUCCESS }
            .map { it.servicesInfo }
    }

    public fun isServicesAvailable(): Map<ApiAvailabilityServices.ServicesInfo, Int> {
        return apiAvailabilityServices.associateWith { it.isServicesAvailable(context) }
            .mapKeys { (apiAvailabilityServices, _) -> apiAvailabilityServices.servicesInfo }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    public fun getPreferredServices(): ApiAvailabilityServices.ServicesInfo? {
        val installerPackageName = getInstallerPackageName(context)
        val apiAvailabilityServices = getAvailableServices()
        if (installerPackageName != null) {
            return apiAvailabilityServices
                .find { service -> service.storePackageName == installerPackageName }
        }

        if (apiAvailabilityServices.isNotEmpty()) {
            return apiAvailabilityServices[0]
        }

        return null
    }
}

public fun UmsApiAvailability.getAvailableServicesIds(): List<String> {
    return getAvailableServices().map { it.serviceId }
}

public fun UmsApiAvailability.getPreferredServicesId(): String? {
    return getPreferredServices()?.serviceId
}

public fun UmsApiAvailability.getPreferredServicesIdOrDefault(defaultServiceId: String): String {
    return getPreferredServices()?.serviceId ?: defaultServiceId
}

public fun UmsApiAvailability.getPreferredServicesIdOrFirstAvailable(): ApiAvailabilityServices.ServicesInfo? {
    return getPreferredServices() ?: getAvailableServices().firstOrNull()
}