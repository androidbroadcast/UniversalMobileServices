@file:Suppress("unused")

package dev.androidbroadcast.ums.analytics.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import dev.androidbroadcast.ums.analytics.Analytics

public class GoogleAnalytics(context: Context) : Analytics {

    private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(name: String, params: Map<String, Any>) {
        analytics.logEvent(name, params.asBundle())
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

private fun Map<String, Any>.asBundle(): Bundle {
    if (isEmpty()) {
        return Bundle.EMPTY
    }

    val bundle = Bundle(size)
    forEach { (key, value) ->
        when (value) {
            is Byte -> bundle.putByte(key, value)
            is Char -> bundle.putChar(key, value)
            is Short -> bundle.putShort(key, value)
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)

            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)

            is String -> bundle.putString(key, value)

            is Boolean -> bundle.putBoolean(key, value)

            is ByteArray -> bundle.putByteArray(key, value)
            is CharArray -> bundle.putCharArray(key, value)
            is ShortArray -> bundle.putShortArray(key, value)
            is IntArray -> bundle.putIntArray(key, value)
            is LongArray -> bundle.putLongArray(key, value)
            is FloatArray -> bundle.putFloatArray(key, value)
            is DoubleArray -> bundle.putDoubleArray(key, value)
            is BooleanArray -> bundle.putBooleanArray(key, value)
        }
    }
    return bundle
}