package dev.androidbroadcast.ums.push.hms

import com.huawei.hms.push.RemoteMessage
import java.util.Collections
import dev.androidbroadcast.ums.push.RemoteMessage as UmsRemoteMessage

internal class HmsRemoteMessage(private val remoteMessage: RemoteMessage) : UmsRemoteMessage {

    override val collapseKey: String?
        get() = remoteMessage.collapseKey

    override val data: Map<String, String>
        get() = Collections.unmodifiableMap(remoteMessage.dataOfMap)

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

    override val rawData: String?
        get() = remoteMessage.data

    override fun asVendorMessage(): Any {
        return this
    }
}