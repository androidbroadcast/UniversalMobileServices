package dev.androidbroadcast.ums.push.firebase

import com.google.firebase.messaging.FirebaseMessaging
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.RemoteMessage

class FirebaseMessagingService : PushMessagingService {

    override fun send(message: RemoteMessage) {
        FirebaseMessaging.getInstance()
            .send(message.asFirebaseRemoteMessage())
    }

    override fun subscribeToTopic(topic: String) {
        FirebaseMessaging.getInstance()
            .subscribeToTopic(topic)
    }

    override fun unsubscribeFromTopic(topic: String) {
        FirebaseMessaging.getInstance()
            .unsubscribeFromTopic(topic)
    }

    override var isAutoInitEnabled: Boolean
        get() = FirebaseMessaging.getInstance().isAutoInitEnabled
        set(value) {
            FirebaseMessaging.getInstance().isAutoInitEnabled = value
        }
}