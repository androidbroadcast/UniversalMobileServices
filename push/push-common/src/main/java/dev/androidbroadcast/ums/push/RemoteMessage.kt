@file:Suppress("unused")

package dev.androidbroadcast.ums.push

import androidx.annotation.IntRange
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 * Representation of message from a Push Service.
 */
public interface RemoteMessage {

    /**
     * Identifier of push services where the message was received
     */
    public val pushId: String?
        get() = null

    /**
     * The collapse key of the message.
     */
    public val collapseKey: String?

    /**
     * The message payload data.
     */
    public val data: Map<String, String>


    /**
     * A common string or a string in JSON format, for example, "your data" or
     * "{'param1':'value1', 'param2':'value2'}".
     */
    public val rawData: String?

    /**
     * The sender of this message.
     *
     * This will be the sender ID or the topic for topic messages.
     */
    public val from: String?

    /**
     * The message destination.
     */
    public val to: String?

    /**
     * The message's ID.
     */
    public val messageId: String?

    /**
     * The type of message.
     */
    public val messageType: String?

    /**
     * Gets the message time to live (TTL) in seconds.
     */
    public val ttl: Int

    /**
     * Priority of message.
     *
     * Can be one of [PRIORITY_HIGH], [PRIORITY_NORMAL] or [PRIORITY_UNKNOWN]
     */
    public val priority: Int
        get() = PRIORITY_UNKNOWN

    /**
     * Original priority of message
     *
     * Can be one of [PRIORITY_HIGH], [PRIORITY_NORMAL] or [PRIORITY_UNKNOWN]
     */
    public val originalPriority: Int
        get() = PRIORITY_UNKNOWN

    /**
     * The Sender ID for the sender of this message.
     */
    public val senderId: String?
        get() = null

    /**
     * Return original message from Push Services
     */
    public fun asVendorMessage(): Any?


    //    val notification: Notification

    public interface Notification

    public companion object {

        public const val PRIORITY_HIGH: Int = 1
        public const val PRIORITY_NORMAL: Int = 2
        public const val PRIORITY_UNKNOWN: Int = 0
    }

    public class Builder(private val to: String) {

        private var collapseKey: String? = null
        private var data = mutableMapOf<String, String>()
        private var messageId: String? = null
        private var messageType: String? = null
        private var ttl: Int = -1

        public fun collapseKey(collapseKey: String?): Builder {
            this.collapseKey = collapseKey
            return this
        }

        public fun data(data: Map<String, String>): Builder {
            this.data.apply {
                clear()
                putAll(data)
            }
            return this
        }

        public fun addData(key: String, value: String): Builder {
            this.data[key] = value
            return this
        }

        public fun clear(): Builder {
            this.data.clear()
            return this
        }

        public fun messageId(messageId: String): Builder {
            this.messageId = messageId
            return this
        }

        public fun messageType(messageType: String): Builder {
            this.messageType = messageType
            return this
        }

        public fun ttl(@IntRange(from = 0) ttl: Int): Builder {
            this.ttl = ttl
            return this
        }

        public fun build(): RemoteMessage {
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

@OptIn(ExperimentalTime::class)
public fun RemoteMessage.Builder.ttl(ttl: Duration): RemoteMessage.Builder {
    return ttl(ttl.inWholeSeconds.toInt())
}