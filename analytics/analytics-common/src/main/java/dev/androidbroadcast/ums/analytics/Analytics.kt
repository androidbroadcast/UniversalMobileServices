@file:Suppress("unused")

package dev.androidbroadcast.ums.analytics

import android.os.Bundle
import androidx.annotation.IntRange

public class Analytics private constructor(private val analyticsProvider: AnalyticsProvider) {

    public fun logEvent(key: String, params: Map<String, Any>) {
        analyticsProvider.logEvent(key, params)
    }

    public fun setUserId(userId: String) {
        analyticsProvider.setUserId(userId)
    }

    public fun setUserProperty(name: String, value: String) {
        analyticsProvider.setUserProperty(name, value)
    }

    public fun setSessionTimeoutDuration(@IntRange(from = 0) milliseconds: Long) {
        analyticsProvider.setSessionTimeoutDuration(milliseconds)
    }

    public fun setDefaultEventParameters(params: Bundle) {
        analyticsProvider.setDefaultEventParameters(params)
    }

    public fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        analyticsProvider.setAnalyticsCollectionEnabled(enabled)
    }

    public fun resetAnalyticsData() {
        analyticsProvider.resetAnalyticsData()
    }

    public companion object {

        @JvmStatic
        public fun newInstance(provider: AnalyticsProvider): Analytics {
            return Analytics(provider)
        }
    }
}

public fun Analytics.logEvent(key: String, params: Bundle) {
    logEvent(key, params.asParamMap())
}

private fun Bundle.asParamMap(): Map<String, Any> {
    if (isEmpty) {
        return emptyMap()
    }

    val map = HashMap<String, Any>(size())
    keySet().forEach { key ->
        map[key] = requireNotNull(this.get(key)) { "Pram for key '$key' is null" }
    }
    return map
}