package dev.androidbroadcast.ums.push

public interface PushMessagingService {

    public fun send(message: RemoteMessage)

    public fun subscribeToTopic(topic: String)

    public fun unsubscribeFromTopic(topic: String)

    public var isAutoInitEnabled: Boolean

    public suspend fun getToken(): String

    public suspend fun deleteToken()
}