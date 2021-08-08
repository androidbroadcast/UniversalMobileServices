package dev.androidbroadcast.ums.push

import androidx.annotation.IntRange

/**
 * Representation of message from a Push Service.
 */
interface RemoteMessage {

    /**
     * Identifier of push services where the message was received
     */
    val pushId: String?
        get() = null

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
    fun asVendorMessage(): Any?


    //    val notification: Notification

    interface Notification

    companion object {

        const val PRIORITY_HIGH = 1
        const val PRIORITY_NORMAL = 2
        const val PRIORITY_UNKNOWN = 0
    }

    class Builder(private val to: String) {

        private var collapseKey: String? = null
        private var data = mutableMapOf<String, String>()
        private var messageId: String? = null
        private var messageType: String? = null
        private var ttl: Int = -1

        fun collapseKey(collapseKey: String?): Builder {
            this.collapseKey = collapseKey
            return this
        }

        fun data(data: Map<String, String>): Builder {
            this.data.apply {
                clear()
                putAll(data)
            }
            return this
        }

        fun addData(key: String, value: String): Builder {
            this.data[key] = value
            return this
        }

        fun clear(): Builder {
            this.data.clear()
            return this
        }

        fun messageId(messageId: String): Builder {
            this.messageId = messageId
            return this
        }

        fun messageType(messageType: String): Builder {
            this.messageType = messageType
            return this
        }

        fun ttl(@IntRange(from = 0) ttl: Int): Builder {
            this.ttl = ttl
            return this
        }

        fun build(): RemoteMessage {
            return SimpleRemoteMessage(
                to,
                collapseKey,
                data,
                messageId,
                messageType,
                ttl
            )
        }
    }
}