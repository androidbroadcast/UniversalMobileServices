@file:Suppress("MemberVisibilityCanBePrivate")

package dev.androidbroadcast.ums.core

import android.content.Context
import dev.androidbroadcast.ums.utils.getInstallerPackageName
import dev.androidbroadcast.ums.utils.loadServices

public class UmsApiAvailability(internal val context: Context) {

    private val apiAvailabilityServices: List<ApiAvailabilityServices> by lazy {
        loadServices<ApiAvailabilityServices>().toList()
    }

    public var currentServicesId: String? = getRecommendedServiceId()
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

    public fun getRecommendedService(): ApiAvailabilityServices.ServicesInfo? {
        val installerPackageName = getInstallerPackageName(context)
        val apiAvailabilityServices = getAvailableServices()
        if (installerPackageName != null) {
            val servicesInfo = apiAvailabilityServices
                .find { service -> service.storePackageName == installerPackageName }
            if (servicesInfo != null) return servicesInfo
        }

        return apiAvailabilityServices.firstOrNull()
    }
}

public fun UmsApiAvailability.getAvailableServicesIds(): List<String> {
    return getAvailableServices().map { it.serviceId }
}

public fun UmsApiAvailability.getRecommendedServiceId(): String? {
    return getRecommendedService()?.serviceId
}

public fun UmsApiAvailability.getRecommendedServiceIdOrDefault(defaultServiceId: String): String {
    return getRecommendedServiceId() ?: defaultServiceId
}

public fun UmsApiAvailability.getRecommendedServiceIdOrFirstAvailable(
): ApiAvailabilityServices.ServicesInfo? {
    return getRecommendedService() ?: getAvailableServices().firstOrNull()
}