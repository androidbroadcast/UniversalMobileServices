package dev.androidbroadcast.ums.push.hms

import android.content.Context
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessaging
import dev.androidbroadcast.ums.push.PushMessagingService
import dev.androidbroadcast.ums.push.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class HmsMessagingService(
    context: Context,
    private val appId: String,
    private val tokenScope: String = HmsMessaging.DEFAULT_TOKEN_SCOPE
) : PushMessagingService {

    private val messaging = HmsMessaging.getInstance(context)
    private val instanceId = HmsInstanceId.getInstance(context)

    override fun send(message: RemoteMessage) {
        messaging.send(message.asHmsRemoteMessage())
    }

    override fun subscribeToTopic(topic: String) {
        messaging.subscribe(topic)
    }

    override fun unsubscribeFromTopic(topic: String) {
        messaging.unsubscribe(topic)
    }

    override var isAutoInitEnabled: Boolean
        get() = messaging.isAutoInitEnabled
        set(value) {
            messaging.isAutoInitEnabled = value
        }

    override suspend fun getToken(): String = withContext(Dispatchers.IO) {
        instanceId.getToken(appId, tokenScope)
    }

    override suspend fun deleteToken(): Unit = withContext(Dispatchers.IO) {
        instanceId.deleteToken(appId, tokenScope)
    }
}