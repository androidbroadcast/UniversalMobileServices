package dev.androidbroadcast.ums.push.gms

import androidx.annotation.XmlRes
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import dev.androidbroadcast.ums.remoteconfig.RemoteConfigValue
import dev.androidbroadcast.ums.remoteconfig.UniversalRemoteConfig
import kotlinx.coroutines.tasks.await

public class UmsFirebaseRemoteConfig(
    private val remoteConfig: FirebaseRemoteConfig
) : UniversalRemoteConfig {

    override suspend fun setDefaultAsync(@XmlRes resId: Int) {
        remoteConfig.setDefaultsAsync(resId)
    }

    override suspend fun setDefaultAsync(defaults: Map<String, Any>) {
        remoteConfig.setDefaultsAsync(defaults)
    }

    override fun getString(key: String): String {
        return remoteConfig.getString(key)
    }

    override fun getLong(key: String): Long {
        return remoteConfig.getLong(key)
    }

    override fun getDouble(key: String): Double {
        return remoteConfig.getDouble(key)
    }

    override fun getValue(key: String): RemoteConfigValue {
        return remoteConfig.getValue(key).asUmsRemoteConfigValue()
    }

    override suspend fun fetch() {
        remoteConfig.fetch().await()
    }

    override suspend fun fetch(minimumFetchIntervalInSeconds: Long) {
        remoteConfig.fetch(minimumFetchIntervalInSeconds).await()
    }

    override fun getAll(): Map<String, RemoteConfigValue> {
        return remoteConfig.all.mapValues { it.value.asUmsRemoteConfigValue() }
    }

    override suspend fun reset() {
        remoteConfig.reset().await()
    }

    internal class FirebaseUmsRemoteConfigValue(
        private val configValue: FirebaseRemoteConfigValue
    ) : RemoteConfigValue {

        override fun asLong() = configValue.asLong()

        override fun asDouble() = configValue.asDouble()

        override fun asString() = configValue.asString()

        override fun asByteArray() = configValue.asByteArray()

        override fun asBoolean() = configValue.asBoolean()

        override fun getSource(): RemoteConfigValue.Source =
            when (val source = configValue.source) {
                FirebaseRemoteConfig.VALUE_SOURCE_DEFAULT -> RemoteConfigValue.Source.DEFAULT
                FirebaseRemoteConfig.VALUE_SOURCE_REMOTE -> RemoteConfigValue.Source.REMOTE
                FirebaseRemoteConfig.VALUE_SOURCE_STATIC -> RemoteConfigValue.Source.STATIC
                else -> error("Unknown source = $source")
            }
    }
}

internal fun FirebaseRemoteConfigValue.asUmsRemoteConfigValue(): RemoteConfigValue =
    UmsFirebaseRemoteConfig.FirebaseUmsRemoteConfigValue(this)
