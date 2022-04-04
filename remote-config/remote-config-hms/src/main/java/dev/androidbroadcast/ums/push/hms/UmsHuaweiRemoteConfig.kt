package dev.androidbroadcast.ums.push.hms

import com.huawei.agconnect.remoteconfig.AGConnectConfig
import dev.androidbroadcast.hms.ktx.await
import dev.androidbroadcast.ums.remoteconfig.RemoteConfigValue
import dev.androidbroadcast.ums.remoteconfig.UniversalRemoteConfig

public class UmsFirebaseRemoteConfig(
    private val agRemoteConfig: AGConnectConfig
) : UniversalRemoteConfig {

    override suspend fun setDefaultAsync(resId: Int) {
        agRemoteConfig.applyDefault(resId)
    }

    override suspend fun setDefaultAsync(defaults: Map<String, Any>) {
        agRemoteConfig.applyDefault(defaults)
    }

    override fun getString(key: String): String {
        return agRemoteConfig.getValueAsString(key)
    }

    override fun getLong(key: String): Long {
        return agRemoteConfig.getValueAsLong(key)
    }

    override fun getDouble(key: String): Double {
        return agRemoteConfig.getValueAsDouble(key)
    }

    override fun getValue(key: String): RemoteConfigValue {
        return HuaweiUmsRemoteConfigValue(key, agRemoteConfig)
    }

    override suspend fun fetch() {
        agRemoteConfig.fetch().await()
    }

    override suspend fun fetch(minimumFetchIntervalInSeconds: Long) {
        agRemoteConfig.fetch(minimumFetchIntervalInSeconds).await()
    }

    override fun getAll(): Map<String, RemoteConfigValue> {
        return agRemoteConfig.mergedAll.mapValues {
            ValueRemoteConfigValue(it.key, it.value, agRemoteConfig)
        }
    }

    override suspend fun reset() {
        agRemoteConfig.clearAll()
    }

    internal class HuaweiUmsRemoteConfigValue(
        private val key: String,
        private val remoteConfig: AGConnectConfig
    ) : RemoteConfigValue {

        override fun asLong(): Long = remoteConfig.getValueAsLong(key)

        override fun asDouble(): Double = remoteConfig.getValueAsDouble(key)

        override fun asString(): String = remoteConfig.getValueAsString(key)

        override fun asByteArray(): ByteArray = remoteConfig.getValueAsByteArray(key)

        override fun asBoolean(): Boolean = remoteConfig.getValueAsBoolean(key)

        override fun getSource(): RemoteConfigValue.Source =
            when (val source = remoteConfig.getSource(key)) {
                AGConnectConfig.SOURCE.DEFAULT -> RemoteConfigValue.Source.DEFAULT
                AGConnectConfig.SOURCE.REMOTE -> RemoteConfigValue.Source.REMOTE
                AGConnectConfig.SOURCE.STATIC -> RemoteConfigValue.Source.STATIC
                else -> error("Unknown source = $source")
            }
    }

    internal class ValueRemoteConfigValue(
        private val key: String,
        private val value: Any,
        private val remoteConfig: AGConnectConfig
    ) : RemoteConfigValue {

        override fun asLong(): Long = value as Long

        override fun asDouble(): Double = value as Double

        override fun asString(): String = value as String

        override fun asByteArray(): ByteArray = value as ByteArray

        override fun asBoolean(): Boolean = value as Boolean

        override fun getSource(): RemoteConfigValue.Source =
            when (val source = remoteConfig.getSource(key)) {
                AGConnectConfig.SOURCE.DEFAULT -> RemoteConfigValue.Source.DEFAULT
                AGConnectConfig.SOURCE.REMOTE -> RemoteConfigValue.Source.REMOTE
                AGConnectConfig.SOURCE.STATIC -> RemoteConfigValue.Source.STATIC
                else -> error("Unknown source = $source")
            }
    }
}
