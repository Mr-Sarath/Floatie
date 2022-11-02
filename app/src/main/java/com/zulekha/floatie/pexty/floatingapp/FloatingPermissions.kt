package com.zulekha.floatie.pexty.floatingapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

object FloatingPermissions {
    @RequiresApi(Build.VERSION_CODES.M)
    fun has(context: Context): Boolean {
        return Settings.canDrawOverlays(context)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun take(context: Context) {
        if (!has(context))
            context.startActivity(Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}"
                )))
    }
}