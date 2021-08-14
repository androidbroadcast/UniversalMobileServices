package dev.androidbroadcast.ums.analytics

import android.content.Context
import android.os.Bundle
import androidx.annotation.IntRange
import dev.androidbroadcast.ums.core.UmsApiAvailability
import dev.androidbroadcast.ums.utils.loadServices
import java.util.ServiceLoader
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

public interface Analytics {

    public fun logEvent(name: String, params: Map<String, Any>)

    public fun setUserId(userId: String?)

    public fun setUserProperty(name: String, value: String?)

    public fun setSessionTimeoutDuration(@IntRange(from = 0) milliseconds: Long)

    public fun setDefaultEventParameters(params: Bundle)

    public fun setAnalyticsCollectionEnabled(enabled: Boolean)

    public fun resetAnalyticsData()

    public companion object {

        @JvmStatic
        public fun getDefault(context: Context): Analytics {
            val serviceLoader: ServiceLoader<AnalyticsFactory> = loadServices()
            val currentServicesId = checkNotNull(UmsApiAvailability(context).currentServicesId) {
                "Mobile services isn't initialized"
            }
            val analyticsFactory = checkNotNull(
                serviceLoader.firstOrNull { it.servicesId == currentServicesId }
            ) {
                "No analytics implementation for services '$currentServicesId'"
            }
            return analyticsFactory.create(context)
        }

        @JvmStatic
        public fun getAll(context: Context): List<Analytics> {
            val serviceLoader = ServiceLoader.load(AnalyticsFactory::class.java)
            return serviceLoader.map { factory ->
                factory.create(context)
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
public fun Analytics.setSessionTimeoutDuration(timeout: Duration) {
    return setSessionTimeoutDuration(timeout.inWholeMilliseconds)
}

public fun Analytics.logEvent(key: String, params: Bundle) {
    logEvent(key, params.asParamMap())
}

private fun Bundle.asParamMap(): Map<String, Any> {
    if (isEmpty) return emptyMap()

    val map = HashMap<String, Any>(size())
    keySet().forEach { key ->
        map[key] = requireNotNull(this.get(key)) { "Param for key '$key' is null" }
    }
    return map
}
