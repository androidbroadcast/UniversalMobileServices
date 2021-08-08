package dev.androidbroadcast.ums.analytics.firebase

import android.content.Context
import dev.androidbroadcast.ums.analytics.AnalyticsFactory

internal class GoogleAnalyticsFactory : AnalyticsFactory {

    override fun create(context: Context) = GoogleAnalytics(context)
}