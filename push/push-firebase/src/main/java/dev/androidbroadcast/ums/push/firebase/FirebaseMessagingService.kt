@file:Suppress("unused")

package dev.androidbroadcast.ums.push.firebase

import com.google.firebase.messaging.FirebaseMessaging
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

public class FirebaseMessagingService : PushMessagingService {

    private val messaging = FirebaseMessaging.getInstance()

    override fun send(message: RemoteMessage) {
        messaging.send(message.asFirebaseRemoteMessage())
    }

    override fun subscribeToTopic(topic: String) {
        messaging.subscribeToTopic(topic)
    }

    override fun unsubscribeFromTopic(topic: String) {
        messaging.unsubscribeFromTopic(topic)
    }

    override var isAutoInitEnabled: Boolean
        get() = messaging.isAutoInitEnabled
        set(value) {
            messaging.isAutoInitEnabled = value
        }

    override suspend fun getToken(): String = withContext(Dispatchers.IO) {
        messaging.token.await()
    }

    override suspend fun deleteToken(): Unit = withContext(Dispatchers.IO) {
        messaging.deleteToken().await()
    }
}