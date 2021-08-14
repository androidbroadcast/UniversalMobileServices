package dev.androidbroadcast.ums.push

import android.content.Context

public interface PushMessagingServiceFactory {

    public val servicesId: String

    public fun create(
        context: Context,
        appId: String? = null,
        tokenScope: String? = null
    ): PushMessagingService
}