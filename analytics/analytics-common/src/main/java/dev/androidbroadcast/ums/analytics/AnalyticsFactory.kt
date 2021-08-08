package dev.androidbroadcast.ums.analytics

import android.content.Context

/**
 * Special interface to create default instance of [Analytics]
 */
public interface AnalyticsFactory {

    public fun create(context: Context): Analytics
}