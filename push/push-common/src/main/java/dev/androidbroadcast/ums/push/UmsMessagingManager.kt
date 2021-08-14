@file:Suppress("unused")

package dev.androidbroadcast.ums.push

import android.content.Context
import android.os.Bundle
import androidx.annotation.RestrictTo
import dev.androidbroadcast.ums.core.UmsApiAvailability
import dev.androidbroadcast.ums.utils.loadServices
import java.lang.Exception
import java.util.ServiceLoader
import java.util.concurrent.CopyOnWriteArraySet

public class UmsMessagingManager private constructor(private val service: PushMessagingService) {

    private val onNewTokenCallbacks = CopyOnWriteArraySet<Callback>()

    public fun registerCallback(c: Callback) {
        onNewTokenCallbacks += c
    }

    public fun unregisterCallback(c: Callback) {
        onNewTokenCallbacks -= c
    }

    public fun send(message: RemoteMessage) {
        service.send(message)
    }

    public fun subscribeToTopic(topic: String) {
        service.subscribeToTopic(topic)
    }

    public fun unsubscribeFromTopic(topic: String) {
        service.unsubscribeFromTopic(topic)
    }

    public var isAutoInitEnabled: Boolean
        get() = service.isAutoInitEnabled
        set(value) {
            service.isAutoInitEnabled = value
        }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyNewToken(pushId: String, token: String, data: Bundle? = null) {
        onNewTokenCallbacks.forEach { callback -> callback.onNewToken(pushId, token, data) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyMessageReceived(pushId: String, message: RemoteMessage) {
        onNewTokenCallbacks.forEach { callback -> callback.onMessageReceived(pushId, message) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyMessageSent(pushId: String, message: String) {
        onNewTokenCallbacks.forEach { callback -> callback.onMessageSent(pushId, message) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifySentError(pushId: String, msgId: String, exception: Exception) {
        onNewTokenCallbacks.forEach { callback -> callback.onSentError(pushId, msgId, exception) }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyMessageDelivered(pushId: String, msgId: String, exception: Exception?) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onMessageDelivered(pushId, msgId, exception)
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyTokenError(pushId: String, exception: Exception, data: Bundle? = null) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onTokenError(pushId, exception, data)
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public fun notifyDeletedMessages(pushId: String) {
        onNewTokenCallbacks.forEach { callback ->
            callback.onDeletedMessages(pushId)
        }
    }

    public interface Callback {

        /**
         * Called when a new token for the default project is generated.
         *
         * This is invoked after app install when a token is first generated, and again if the token changes.
         */
        public fun onNewToken(pushId: String, token: String, data: Bundle? = null) {}

        public fun onMessageReceived(pushId: String, message: RemoteMessage) {}

        public fun onMessageSent(pushId: String, message: String) {}

        public fun onSentError(pushId: String, msgId: String, exception: Exception) {}

        /**
         * Only for HMS Push
         */
        public fun onMessageDelivered(pushId: String, msgId: String, exception: Exception?) {}

        /**
         * Only for HMS Push
         */
        public fun onTokenError(pushId: String, exception: Exception, data: Bundle? = null) {}

        /**
         * Only for Firebase Push
         */
        public fun onDeletedMessages(pushId: String) {}
    }

    public companion object {

        @Suppress("ObjectPropertyName")
        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        @get:JvmName("instance")
        public var _instance: UmsMessagingManager? = null
            private set

        public fun init(service: PushMessagingService) {
            if (_instance != null) {
                error("UmsMessagingManager has been already initialized")
            }
            _instance = UmsMessagingManager(service)
        }

        @JvmStatic
        public fun initWithDefault(
            context: Context,
            appId: String? = null,
            tokenScope: String? = null
        ) {
            val serviceLoader: ServiceLoader<PushMessagingServiceFactory> = loadServices()
            val currentServices = UmsApiAvailability(context).currentServicesId
            val pushMessagingServiceFactory = checkNotNull(
                serviceLoader.firstOrNull { it.servicesId == currentServices }
            ) {
                "Not push messaging services for services '$currentServices'"
            }
            _instance =
                UmsMessagingManager(pushMessagingServiceFactory.create(context, appId, tokenScope))
        }

        public val isInitialized: Boolean
            get() = _instance != null

        public fun getInstance(): UmsMessagingManager {
            return checkNotNull(_instance) { "UmsMessagingManager isn't initialized" }
        }
    }
}