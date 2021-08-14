package dev.androidbroadcast.ums.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public fun getInstallerPackageName(context: Context): String? {
    val packageManager: PackageManager = context.packageManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        return packageManager.getInstallSourceInfo(context.packageName).initiatingPackageName
    } else {
        try {
            return packageManager.getInstallerPackageName(context.packageName)
        } catch (e: IllegalArgumentException) {
            return null
        }
    }
}