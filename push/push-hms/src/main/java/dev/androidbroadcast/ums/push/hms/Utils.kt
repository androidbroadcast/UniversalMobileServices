@file:JvmName("Utils")

package dev.androidbroadcast.ums.push.hms

import com.huawei.hms.push.RemoteMessage
import dev.androidbroadcast.ums.push.RemoteMessage as UmsRemoteMessage

const val HMS_PUSH_ID = "com.huawei.hms.push"

internal fun UmsRemoteMessage.asHmsRemoteMessage(): RemoteMessage = when (this) {
    is HmsRemoteMessage -> asVendorMessage() as RemoteMessage
    else -> TODO()
}