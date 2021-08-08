package dev.androidbroadcast.ums.push

import androidx.annotation.RestrictTo
import org.json.JSONObject

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class SimpleRemoteMessage internal constructor(
    override val to: String,
    override val collapseKey: String?,
    data: Map<String, String>,
    override val messageId: String?,
    override val messageType: String?,
    override val ttl: Int,
) : RemoteMessage {

    override val data = data.toMap()

    override val rawData: String by lazy {
        JSONObject(data).toString()
    }

    override val from: String? = null

    override fun asVendorMessage(): Any? = null
}