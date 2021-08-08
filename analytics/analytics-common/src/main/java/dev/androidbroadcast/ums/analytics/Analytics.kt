package dev.androidbroadcast.ums.analytics

import android.os.Bundle
import androidx.annotation.IntRange
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

public interface Analytics {

    public fun logEvent(name: String, params: Map<String, Any>)

    public fun setUserId(userId: String?)

    public fun setUserProperty(name: String, value: String?)

    public fun setSessionTimeoutDuration(@IntRange(from = 0) milliseconds: Long)

    public fun setDefaultEventParameters(params: Bundle)

    public fun setAnalyticsCollectionEnabled(enabled: Boolean)

    public fun resetAnalyticsData()
}

@OptIn(ExperimentalTime::class)
public fun Analytics.setSessionTimeoutDuration(timeout: Duration) {
    return setSessionTimeoutDuration(timeout.inWholeMilliseconds)
}

public fun Analytics.logEvent(key: String, params: Bundle) {
    logEvent(key, params.asParamMap())
}

private fun Bundle.asParamMap(): Map<String, Any> {
    if (isEmpty) return emptyMap()

    val map = HashMap<String, Any>(size())
    keySet().forEach { key ->
        map[key] = requireNotNull(this.get(key)) { "Param for key '$key' is null" }
    }
    return map
}
