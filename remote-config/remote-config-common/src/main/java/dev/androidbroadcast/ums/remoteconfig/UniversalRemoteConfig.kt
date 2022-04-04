package dev.androidbroadcast.ums.remoteconfig

import androidx.annotation.XmlRes

public interface UniversalRemoteConfig {

    public suspend fun setDefaultAsync(@XmlRes resId: Int)

    public suspend fun setDefaultAsync(defaults: Map<String, Any>)

    public fun getString(key: String): String

    public fun getLong(key: String): Long

    public fun getDouble(key: String): Double

    public fun getValue(key: String): RemoteConfigValue

    public suspend fun fetch()

    public suspend fun fetch(minimumFetchIntervalInSeconds: Long)

    public fun getAll(): Map<String, RemoteConfigValue>

    public suspend fun reset()
}

public interface RemoteConfigValue {


    /**
     * Gets the value as a `long`.
     *
     * @return `long` representation of this parameter value.
     * @throws IllegalArgumentException If the value cannot be converted to a `long`.
     */
    @Throws(IllegalArgumentException::class)
    public fun asLong(): Long

    /**
     * Gets the value as a `double`.
     *
     * @return `double` representation of this parameter value.
     * @throws IllegalArgumentException If the value cannot be converted to a `double`.
     */
    @Throws(IllegalArgumentException::class)
    public fun asDouble(): Double

    /**
     * Gets the value as a `String`.
     *
     * @return `String` representation of this parameter value.
     */
    public fun asString(): String

    /**
     * Gets the value as a `byte[]`.
     *
     * @return `byte[]` representation of this parameter value.
     */
    public fun asByteArray(): ByteArray

    /**
     * Gets the value as a `boolean`.
     *
     * @return `boolean` representation of this parameter value.
     * @throws IllegalArgumentException If the value cannot be converted to a `boolean`.
     */
    @Throws(IllegalArgumentException::class)
    public fun asBoolean(): Boolean

    /**
     * Indicates at which source this value came from.
     */
    public fun getSource(): Source

    /**
     * Indicates at which source this value came from.
     */
    public enum class Source {
        /**
         * Value was retrieved from the server
         */
        REMOTE,

        /**
         * Value was set as a default
         */
        DEFAULT,

        /**
         * Value was not found and a static default value was returned instead
         */
        STATIC
    }
}