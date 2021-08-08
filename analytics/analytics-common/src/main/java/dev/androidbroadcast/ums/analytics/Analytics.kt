package dev.androidbroadcast.ums.analytics

import android.os.Bundle
import androidx.annotation.IntRange

public class Analytics private constructor(private val analyticsProvider: AnalyticsProvider) {

    public fun logEvent(key: String, params: Bundle) {
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