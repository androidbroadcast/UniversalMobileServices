package dev.androidbroadcast.ums.push

interface PushMessagingService {

    fun send(message: RemoteMessage)

    fun subscribeToTopic(topic: String)

    fun unsubscribeFromTopic(topic: String)

    var isAutoInitEnabled: Boolean
}