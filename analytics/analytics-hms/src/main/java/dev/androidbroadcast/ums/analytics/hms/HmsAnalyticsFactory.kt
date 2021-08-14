package dev.androidbroadcast.ums.analytics.hms

import android.content.Context
import dev.androidbroadcast.ums.analytics.AnalyticsFactory
import dev.androidbroadcast.ums.hms.core.HMS_ID

internal class HmsAnalyticsFactory : AnalyticsFactory {

    override val servicesId: String = HMS_ID

    override fun create(context: Context) = HmsAnalytics(context)
}