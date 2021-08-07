package dev.androidbroadcast.ums.push.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.androidbroadcast.ums.push.UmsMessagingManager

class UmsFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        UmsMessagingManager.notifyNewToken(FIREBASE_PUSH_ID, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        UmsMessagingManager.notifyMessageReceived(FIREBASE_PUSH_ID, FirebaseRemoteMessage(message))
    }

    override fun onMessageSent(msgId: String) {
        UmsMessagingManager.notifyMessageSent(FIREBASE_PUSH_ID, msgId)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        UmsMessagingManager.notifySentError(FIREBASE_PUSH_ID, msgId, exception)
    }

    override fun onDeletedMessages() {
        UmsMessagingManager.notifyDeletedMessages(FIREBASE_PUSH_ID)
    }
}