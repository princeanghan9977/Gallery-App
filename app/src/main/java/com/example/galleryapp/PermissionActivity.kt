package com.example.galleryapp

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapp.*
import com.example.galleryapp.databinding.ActivityPermissionBinding


class PermissionActivity : AppCompatActivity() {
    lateinit var binding: ActivityPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvperm.setOnClickListener {
            askPermissions()
        }
    }


    override fun onResume() {
        super.onResume()
        if (settingAct) {
            settingAct = false
            askPermissions()
        }
    }

    private fun init() {
        startActivity(Intent(this, ChooseActivity::class.java))
        finish()
    }

    private fun askPermissions() {
        Log.e("TAG", "permi........askPermissions .......")
        if (!checkStoragePermission(this)) {
            Log.e("TAG", "permi........askPermissions ....iff...")
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(strArr, 100)
            }
        } else {
            Log.e("TAG", "permi........askPermissions ....else...")
            init()

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(i: Int, strArr: Array<String>, iArr: IntArray) {
        super.onRequestPermissionsResult(i, strArr, iArr)
        Log.e("TAG", "permi........onRequestPermissionsResult: ........i.....$i")

        if (i == 100) {
            if (!isTiramisuPlus()) {
                if (iArr.isEmpty()) {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........isEmpty.....")
                    return
                } else if (iArr.isNotEmpty() && iArr[0] == 0 && iArr[1] == 0) {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........Granted.....")
                    init()
                    toast(getString(R.string.toast_permission))
                } else {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........2111.....")

                    val permission1 =
                        shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    val permission2 =
                        shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    if (!permission1 && !permission2) {
                        openSettingsDialog(false)
                    } else {
                        openDialog()
                    }
                }
            } else {
                if (iArr.isEmpty()) {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........isEmpty.....")
                    return
                } else if (iArr.isNotEmpty() && iArr[0] == 0 && iArr[1] == 0 && iArr[2] == 0 && iArr[3] == 0) {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........Granted.....")
                    init()
                    toast(getString(R.string.toast_permission))
                } else {
                    Log.e("TAG", "permi........onRequestPermissionsResult: ........2111.....")

                    val permission1 =
                        shouldShowRequestPermissionRationale(android.Manifest.permission.READ_MEDIA_AUDIO)
                    val permission2 =
                        shouldShowRequestPermissionRationale(android.Manifest.permission.READ_MEDIA_VIDEO)
                    val permission3 =
                        shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES)
                    val permission7 =
                        shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)

                    if (!permission1 && !permission2 && !permission3 && !permission7) {
                        if (checkStoragePermission_noti(this)) {
                            openSettingsDialog(true)
                        } else {
                            openSettingsDialog(false)
                        }
                    } else {
                        openDialog()
                    }
                }
            }
        }
    }

    private fun openSettingsDialog(check: Boolean) {
        Log.e("vvvv......", "permi..........openSettingsDialog..............")
        val askPer = Dialog(this)
        askPer.setContentView(R.layout.dialog_permission)
        askPer.window?.setGravity(Gravity.CENTER)
        askPer.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        askPer.setCancelable(false)
        askPer.show()
        askPer.findViewById<TextView>(R.id.txt_gnt_per).text = resources.getText(R.string.goto_set)
        askPer.findViewById<View>(R.id.txt_gnt_per).setOnClickListener { v: View? ->
            askPer.dismiss()
            settingAct = true
            permissionRequestCode = 1
            if (check) {
                val intent = Intent()
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("app_package", packageName)
                intent.putExtra("app_uid", applicationInfo.uid)
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName)
                startActivity(intent)
            } else {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
        askPer.findViewById<View>(R.id.ic_close).setOnClickListener { v: View? ->
            askPer.dismiss()
            onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openDialog() {
        Log.e("vvvv......", "permi..........openDialog..............")
        val askPer = Dialog(this)
        askPer.setContentView(R.layout.dialog_permission)
        askPer.window?.setGravity(Gravity.CENTER)
        askPer.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        askPer.show()
        askPer.findViewById<View>(R.id.txt_gnt_per).setOnClickListener { v: View? ->
            askPer.dismiss()
            requestPermissions(strArr, 100)
        }
        askPer.findViewById<View>(R.id.ic_close).setOnClickListener { v: View? ->
            askPer.dismiss()
            onBackPressed()
        }
    }

}