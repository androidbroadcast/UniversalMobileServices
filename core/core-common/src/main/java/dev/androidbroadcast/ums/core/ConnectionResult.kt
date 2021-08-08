package dev.androidbroadcast.ums.core

public class ConnectionResult {

    public companion object {

        public const val UNKNOWN: Int = -1
        public const val SUCCESS: Int = 0
        public const val SERVICE_MISSING: Int = 1
        public const val SERVICE_VERSION_UPDATE_REQUIRED: Int = 2
        public const val SERVICE_DISABLED: Int = 3
        public const val SIGN_IN_REQUIRED: Int = 4
        public const val INVALID_ACCOUNT: Int = 5
        public const val RESOLUTION_REQUIRED: Int = 6
        public const val NETWORK_ERROR: Int = 7
        public const val INTERNAL_ERROR: Int = 8
        public const val SERVICE_INVALID: Int = 9
        public const val DEVELOPER_ERROR: Int = 10
        public const val LICENSE_CHECK_FAILED: Int = 11
        public const val CANCELED: Int = 13
        public const val TIMEOUT: Int = 14
        public const val INTERRUPTED: Int = 15
        public const val API_UNAVAILABLE: Int = 16
        public const val SIGN_IN_FAILED: Int = 17
        public const val SERVICE_UPDATING: Int = 18
        public const val SERVICE_MISSING_PERMISSION: Int = 19
        public const val RESTRICTED_PROFILE: Int = 20
        public const val RESOLUTION_ACTIVITY_NOT_FOUND: Int = 22
        public const val API_DISABLED: Int = 23
        public const val API_DISABLED_FOR_CONNECTION: Int = 24
        public const val DRIVE_EXTERNAL_STORAGE_REQUIRED: Int = 25
        public const val BINDFAIL_RESOLUTION_REQUIRED: Int = 26
        public const val BINDFAIL_RESOLUTION_BACKGROUND: Int = 27
    }
}