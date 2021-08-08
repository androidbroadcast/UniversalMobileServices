@file:Suppress("unused")

package dev.androidbroadcast.ums.analytics.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import dev.androidbroadcast.ums.analytics.AnalyticsProvider

public class FirebaseAnalyticsProvider(context: Context) : AnalyticsProvider {

    private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(name: String, params: Bundle) {
        analytics.logEvent(name, params)
    }

    override fun setUserId(userId: String?) {
        analytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String?) {
        analytics.setUserProperty(name, value)
    }

    override fun setSessionTimeoutDuration(milliseconds: Long) {
        analytics.setSessionTimeoutDuration(milliseconds)
    }

    override fun setDefaultEventParameters(params: Bundle) {
        analytics.setDefaultEventParameters(params)
    }

    override fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        analytics.setAnalyticsCollectionEnabled(enabled)
    }

    override fun resetAnalyticsData() {
        analytics.resetAnalyticsData()
    }
}