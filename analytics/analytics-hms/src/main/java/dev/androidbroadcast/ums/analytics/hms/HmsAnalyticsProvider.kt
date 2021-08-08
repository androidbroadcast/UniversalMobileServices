@file:Suppress("unused")

package dev.androidbroadcast.ums.analytics.hms

import android.content.Context
import android.os.Bundle
import com.huawei.hms.analytics.HiAnalytics
import dev.androidbroadcast.ums.analytics.AnalyticsProvider

public class HmsAnalyticsProvider(context: Context) : AnalyticsProvider {

    private val analytics = HiAnalytics.getInstance(context)

    override fun logEvent(name: String, params: Bundle) {
        analytics.onEvent(name, params)
    }

    override fun setUserId(userId: String?) {
        analytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String?) {
        analytics.setUserProfile(name, value)
    }

    override fun setSessionTimeoutDuration(milliseconds: Long) {
        analytics.setSessionDuration(milliseconds)
    }

    override fun setDefaultEventParameters(params: Bundle) {
        analytics.addDefaultEventParams(params)
    }

    override fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        analytics.setAnalyticsEnabled(enabled)
    }

    override fun resetAnalyticsData() {
        analytics.clearCachedData()
    }
}