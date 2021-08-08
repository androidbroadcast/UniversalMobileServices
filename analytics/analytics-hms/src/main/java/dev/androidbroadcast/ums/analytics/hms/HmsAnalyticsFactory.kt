package dev.androidbroadcast.ums.analytics.hms

import android.content.Context
import dev.androidbroadcast.ums.analytics.AnalyticsFactory

internal class HmsAnalyticsFactory : AnalyticsFactory {

    override fun create(context: Context) = HmsAnalytics(context)
}