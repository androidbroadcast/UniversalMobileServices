package dev.androidbroadcast.ums.push

interface RemoteMessage {

    val collapseKey: String?
    val data: Map<String, String>
    val rawData: String?
    val from: String?
    val to: String?
    val messageId: String?
    val messageType: String?
    val ttl: Int

    //    val notification: Notification

    val priority: Int
        get() = PRIORITY_UNKNOWN

    val originalPriority: Int
        get() = PRIORITY_UNKNOWN

    val senderId: String?
        get() = null

    fun asVendorMessage(): Any

    interface Notification

    companion object {

        const val PRIORITY_HIGH = 1
        const val PRIORITY_NORMAL = 2
        const val PRIORITY_UNKNOWN = 0
    }
}