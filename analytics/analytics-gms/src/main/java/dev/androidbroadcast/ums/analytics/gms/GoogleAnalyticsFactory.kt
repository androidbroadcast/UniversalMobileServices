package dev.androidbroadcast.ums.analytics.gms

import android.content.Context
import dev.androidbroadcast.ums.analytics.AnalyticsFactory
import dev.androidbroadcast.ums.gms.core.GMS_ID

internal class GoogleAnalyticsFactory : AnalyticsFactory {

    override val servicesId: String = GMS_ID

    override fun create(context: Context) = GoogleAnalytics(context)
}