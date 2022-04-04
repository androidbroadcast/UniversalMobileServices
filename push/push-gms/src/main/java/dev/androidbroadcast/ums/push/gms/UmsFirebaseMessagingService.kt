package dev.androidbroadcast.ums.push.gms

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.androidbroadcast.ums.push.UmsMessagingManager

public class UmsFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        UmsMessagingManager.instance.notifyNewToken(FIREBASE_PUSH_ID, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        UmsMessagingManager.instance.notifyMessageReceived(FIREBASE_PUSH_ID, FirebaseRemoteMessage(message))
    }

    override fun onMessageSent(msgId: String) {
        UmsMessagingManager.instance.notifyMessageSent(FIREBASE_PUSH_ID, msgId)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        UmsMessagingManager.instance.notifySentError(FIREBASE_PUSH_ID, msgId, exception)
    }

    override fun onDeletedMessages() {
        UmsMessagingManager.instance.notifyDeletedMessages(FIREBASE_PUSH_ID)
    }
}
