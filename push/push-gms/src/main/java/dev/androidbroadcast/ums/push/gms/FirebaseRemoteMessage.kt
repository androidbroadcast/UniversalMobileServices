package dev.androidbroadcast.ums.push.gms

import com.google.firebase.messaging.RemoteMessage
import java.util.Collections
import dev.androidbroadcast.ums.push.RemoteMessage as UmsRemoteMessage

internal class FirebaseRemoteMessage(private val remoteMessage: RemoteMessage) : UmsRemoteMessage {

    override val pushId = FIREBASE_PUSH_ID

    override val collapseKey: String?
        get() = remoteMessage.collapseKey

    override val data: Map<String, String>
        get() = Collections.unmodifiableMap(remoteMessage.data)

    override val from: String?
        get() = remoteMessage.from

    override val to: String?
        get() = remoteMessage.to

    override val messageId: String?
        get() = remoteMessage.messageId

    override val messageType: String?
        get() = remoteMessage.messageType

    override val ttl: Int
        get() = remoteMessage.ttl

    override val priority: Int
        get() = mapPriority(remoteMessage.priority)

    override val originalPriority: Int
        get() = mapPriority(remoteMessage.originalPriority)

    override val senderId: String?
        get() = remoteMessage.senderId

    override fun asVendorMessage(): Any {
        return this
    }

    override val rawData: String?
        get() = remoteMessage.rawData?.let { String(it) }
}

/**
 * Map Firebase Message Priority to UMS Message Priority
 */
private fun mapPriority(gmsMessagePriority: Int): Int = when (gmsMessagePriority) {
    RemoteMessage.PRIORITY_HIGH -> UmsRemoteMessage.PRIORITY_HIGH
    RemoteMessage.PRIORITY_NORMAL -> UmsRemoteMessage.PRIORITY_NORMAL
    else -> UmsRemoteMessage.PRIORITY_UNKNOWN
}