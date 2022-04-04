package dev.androidbroadcast.ums.push.hms

import android.os.Bundle
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import dev.androidbroadcast.ums.push.UmsMessagingManager
import java.lang.Exception

public class HuaweiMessagingService : HmsMessageService() {

    override fun onNewToken(token: String) {
        UmsMessagingManager.instance.notifyNewToken(HMS_PUSH_ID, token)
    }

    override fun onNewToken(token: String, data: Bundle) {
        UmsMessagingManager.instance.notifyNewToken(HMS_PUSH_ID, token, data)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        UmsMessagingManager.instance.notifyMessageReceived(HMS_PUSH_ID, HmsRemoteMessage(message))
    }

    override fun onMessageSent(message: String) {
        UmsMessagingManager.instance.notifyMessageSent(HMS_PUSH_ID, message)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        UmsMessagingManager.instance.notifySentError(HMS_PUSH_ID, msgId, exception)
    }

    override fun onTokenError(exception: Exception) {
        UmsMessagingManager.instance.notifyTokenError(HMS_PUSH_ID, exception)
    }

    override fun onTokenError(exception: Exception, data: Bundle?) {
        UmsMessagingManager.instance.notifyTokenError(HMS_PUSH_ID, exception, data)
    }

    override fun onMessageDelivered(msgId: String, exception: Exception?) {
        UmsMessagingManager.instance.notifyMessageDelivered(HMS_PUSH_ID, msgId, exception)
    }
}