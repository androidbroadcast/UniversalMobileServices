@file:Suppress("unused")

package dev.androidbroadcast.ums.push

import android.os.Bundle
import androidx.annotation.RestrictTo
import java.lang.Exception
import java.util.concurrent.CopyOnWriteArraySet

object UmsMessagingManager {

    private val onNewTokenCallbacks = CopyOnWriteArraySet<Callback>()

    fun registerCallback(c: Callback) {
        onNewTokenCallbacks += c
    }

    fun unregisterCallback(c: Callback) {
        onNewTokenCallbacks -= c
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyNewToken(pushId: String, token: String, data: Bundle? = null) {
        onNewTokenCallbacks.forEach { callback -> callback.onNewToken(pushId, token, data) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyMessageReceived(pushId: String, message: RemoteMessage) {
        onNewTokenCallbacks.forEach { callback -> callback.onMessageReceived(pushId, message) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyMessageSent(pushId: String, message: String) {
        onNewTokenCallbacks.forEach { callback -> callback.onMessageSent(pushId, message) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifySentError(pushId: String, msgId: String, exception: Exception) {
        onNewTokenCallbacks.forEach { callback -> callback.onSentError(pushId, msgId, exception) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyMessageDelivered(pushId: String, msgId: String, exception: Exception?) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onMessageDelivered(pushId, msgId, exception)
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyTokenError(pushId: String, exception: Exception, data: Bundle? = null) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onTokenError(pushId, exception, data)
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun notifyDeletedMessages(pushId: String) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onDeletedMessages(pushId)
        }
    }

    interface Callback {

        fun onNewToken(pushId: String, token: String, data: Bundle? = null) {}

        fun onMessageReceived(pushId: String, message: RemoteMessage) {}

        fun onMessageSent(pushId: String, message: String) {}

        fun onSentError(pushId: String, msgId: String, exception: Exception) {}

        /**
         * Only for HMS Push
         */
        fun onMessageDelivered(pushId: String, msgId: String, exception: Exception?) {}

        /**
         * Only for HMS Push
         */
        fun onTokenError(pushId: String, exception: Exception, data: Bundle? = null) {}

        /**
         * Only for Firebase Push
         */
        fun onDeletedMessages(pushId: String) {}
    }
}