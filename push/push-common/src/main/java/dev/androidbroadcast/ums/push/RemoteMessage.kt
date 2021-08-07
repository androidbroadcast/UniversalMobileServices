package dev.androidbroadcast.ums.push

/**
 * Representation of message from a Push Service.
 */
interface RemoteMessage {

    /**
     * Identifier of push services where the message was received
     */
    val pushId: String

    /**
     * The collapse key of the message.
     */
    val collapseKey: String?

    /**
     * The message payload data.
     */
    val data: Map<String, String>


    /**
     * A common string or a string in JSON format, for example, "your data" or
     * "{'param1':'value1', 'param2':'value2'}".
     */
    val rawData: String?

    /**
     * The sender of this message.
     *
     * This will be the sender ID or the topic for topic messages.
     */
    val from: String?

    /**
     * The message destination.
     */
    val to: String?

    /**
     * The message's ID.
     */
    val messageId: String?

    /**
     * The type of message.
     */
    val messageType: String?

    /**
     * Gets the message time to live (TTL) in seconds.
     */
    val ttl: Int

    /**
     * Priority of message.
     *
     * Can be one of [PRIORITY_HIGH], [PRIORITY_NORMAL] or [PRIORITY_UNKNOWN]
     */
    val priority: Int
        get() = PRIORITY_UNKNOWN

    /**
     * Original priority of message
     *
     * Can be one of [PRIORITY_HIGH], [PRIORITY_NORMAL] or [PRIORITY_UNKNOWN]
     */
    val originalPriority: Int
        get() = PRIORITY_UNKNOWN

    /**
     * The Sender ID for the sender of this message.
     */
    val senderId: String?
        get() = null

    /**
     * Return original message from Push Services
     */
    fun asVendorMessage(): Any


    //    val notification: Notification

    interface Notification

    companion object {

        const val PRIORITY_HIGH = 1
        const val PRIORITY_NORMAL = 2
        const val PRIORITY_UNKNOWN = 0
    }
}