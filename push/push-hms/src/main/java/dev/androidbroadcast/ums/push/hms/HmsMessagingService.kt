package dev.androidbroadcast.ums.push.hms

import android.content.Context
import com.huawei.hms.push.HmsMessaging
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.RemoteMessage

class HmsMessagingService(private val context: Context) : PushMessagingService {

    override fun send(message: RemoteMessage) {
        HmsMessaging.getInstance(context)
            .send(message.asHmsRemoteMessage())
    }

    override fun subscribeToTopic(topic: String) {
        HmsMessaging.getInstance(context)
            .subscribe(topic)
    }

    override fun unsubscribeFromTopic(topic: String) {
        HmsMessaging.getInstance(context)
            .unsubscribe(topic)
    }

    override var isAutoInitEnabled: Boolean
        get() = HmsMessaging.getInstance(context).isAutoInitEnabled
        set(value) {
            HmsMessaging.getInstance(context).isAutoInitEnabled = value
        }
}