package dev.androidbroadcast.ums.push.gms

import android.content.Context
import dev.androidbroadcast.ums.gms.core.GMS_ID
import dev.androidbroadcast.ums.gms.core.GOOGLE_PLAY_PACKAGE
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.PushMessagingServiceFactory

internal class FirebaseMessagingServiceFactory : PushMessagingServiceFactory {

    override val servicesId: String = GMS_ID

    override fun create(
        context: Context,
        appId: String?,
        tokenScope: String?
    ): PushMessagingService {
        return FirebaseMessagingService()
    }
}