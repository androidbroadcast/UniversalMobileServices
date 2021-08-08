@file:JvmName("Utils")

package dev.androidbroadcast.ums.push.gms

import com.google.firebase.messaging.RemoteMessage
import dev.androidbroadcast.ums.push.RemoteMessage as UmsRemoteMessage

public const val FIREBASE_PUSH_ID: String = "com.google.firebase.messaging"

internal fun UmsRemoteMessage.asFirebaseRemoteMessage(): RemoteMessage = when (this) {
    is FirebaseRemoteMessage -> asVendorMessage() as RemoteMessage
    else -> {
        val builder = RemoteMessage.Builder(requireNotNull(to))
        builder.setData(data)
        collapseKey?.let(builder::setCollapseKey)
        messageType?.let(builder::setMessageType)
        messageId?.let(builder::setMessageId)
        if (ttl > 0) builder.setTtl(ttl)
        builder.build()
    }
}
