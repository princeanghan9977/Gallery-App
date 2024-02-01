package com.example.galleryapp

import android.app.role.RoleManager
import android.content.Context
import android.os.Build
import android.provider.Telephony
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.app.ActivityCompat


public var albumID: Long? = 0
public var artistID: Long? = 0
public var PlaylistID: Long = 0
public var TitelNAME: String? = ""
public var albumName: String? = ""
public var artistNAME: String? = ""

public var settingAct = false
public var permissionRequestCode = 0

public val strArr_noti = arrayOf(
    android.Manifest.permission.READ_MEDIA_VIDEO,
    android.Manifest.permission.READ_MEDIA_AUDIO,
    android.Manifest.permission.READ_MEDIA_IMAGES,
)


@JvmField
public val strArr = if (!isTiramisuPlus()) {
    arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
} else {
    arrayOf(
        android.Manifest.permission.READ_MEDIA_VIDEO,
        android.Manifest.permission.READ_MEDIA_AUDIO,
        android.Manifest.permission.READ_MEDIA_IMAGES,
        android.Manifest.permission.POST_NOTIFICATIONS,

        )
}
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isTiramisuPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun isSetDefaultApp(context: Context): Boolean {
    return isSetDefaultApp1(context) && checkStoragePermission(context)
}

fun isSetDefaultApp1(context: Context): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val roleManager = context.getSystemService(RoleManager::class.java)
        if (roleManager.isRoleAvailable(RoleManager.ROLE_SMS)) {
            return roleManager.isRoleHeld(RoleManager.ROLE_SMS)
        }
    } else {
        if (Telephony.Sms.getDefaultSmsPackage(context) == context.packageName) {
            return true
        }
    }
    return false
}

public fun checkStoragePermission(context: Context): Boolean {
    if (!(Build.VERSION.SDK_INT < 23 || strArr == null)) {
        for (checkSelfPermission in strArr) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    checkSelfPermission
                ) != 0
            ) {
                return false
            }
        }
    }
    return true
}


public fun checkStoragePermission_noti(context: Context): Boolean {

    for (checkSelfPermission in strArr_noti) {
        if (ActivityCompat.checkSelfPermission(
                context,
                checkSelfPermission
            ) != 0
        ) {
            return false
        }
    }
    return true
}