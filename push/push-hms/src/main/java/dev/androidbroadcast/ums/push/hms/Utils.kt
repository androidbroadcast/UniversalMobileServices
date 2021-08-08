@file:JvmName("Utils")

package dev.androidbroadcast.ums.push.hms

import com.huawei.hms.push.RemoteMessage
import dev.androidbroadcast.ums.push.RemoteMessage as UmsRemoteMessage

public const val HMS_PUSH_ID: String = "com.huawei.hms.push"

internal fun UmsRemoteMessage.asHmsRemoteMessage(): RemoteMessage = when (this) {
    is HmsRemoteMessage -> asVendorMessage() as RemoteMessage
    else -> {
        val builder = RemoteMessage.Builder(to).setData(data)
        collapseKey?.let(builder::setCollapseKey)
        messageType?.let(builder::setMessageType)
        messageId?.let(builder::setMessageId)
        ttl.takeIf { it > 0 }?.let(builder::setTtl)
        builder.build()
    }
}