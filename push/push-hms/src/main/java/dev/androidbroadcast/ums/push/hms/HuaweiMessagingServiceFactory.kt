package dev.androidbroadcast.ums.push.hms

import android.content.Context
import com.huawei.hms.push.HmsMessaging
import dev.androidbroadcast.ums.hms.core.HMS_ID
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.PushMessagingServiceFactory

internal class HuaweiMessagingServiceFactory : PushMessagingServiceFactory {

    override val servicesId: String = HMS_ID

    override fun create(
        context: Context,
        appId: String?,
        tokenScope: String?
    ): PushMessagingService {
        return HmsMessagingService(
            context,
            requireNotNull(appId),
            tokenScope ?: HmsMessaging.DEFAULT_TOKEN_SCOPE
        )
    }
}