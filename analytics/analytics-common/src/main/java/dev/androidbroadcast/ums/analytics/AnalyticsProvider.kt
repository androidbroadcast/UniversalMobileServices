package dev.androidbroadcast.ums.analytics

import android.os.Bundle
import androidx.annotation.IntRange
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

public interface AnalyticsProvider {

    public fun logEvent(name: String, params: Bundle)

    public fun setUserId(userId: String?)

    public fun setUserProperty(name: String, value: String?)

    public fun setSessionTimeoutDuration(@IntRange(from = 0) milliseconds: Long)

    public fun setDefaultEventParameters(params: Bundle)

    public fun setAnalyticsCollectionEnabled(enabled: Boolean)

    public fun resetAnalyticsData()
}

@OptIn(ExperimentalTime::class)
public fun AnalyticsProvider.setSessionTimeoutDuration(timeout: Duration) {
    return setSessionTimeoutDuration(timeout.inWholeMilliseconds)
}