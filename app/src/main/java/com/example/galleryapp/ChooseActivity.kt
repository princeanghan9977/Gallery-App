package com.example.galleryapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.galleryapp.activity.Image.FolderImgActivity
import com.example.galleryapp.activity.Video.FolderVideoActivity
import com.example.galleryapp.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseBinding
    private val STORAGE_PERMISSION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose)

        // Register ActivityResult handler
        val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            // Handle permission requests results
            // See the permission example in the Android platform samples: https://github.com/android/platform-samples
        }

        // Permission request logic
        val permissionsToRequest = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
//                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            )
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        // Check and request permissions
        if (permissionsToRequest.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }) {
            requestPermissions.launch(permissionsToRequest)
        } else {
            // Permissions already granted
            // Proceed with your storage operations
        }

        binding.pp.setOnClickListener {
            startActivity(Intent(this@ChooseActivity, FolderImgActivity::class.java))
        }
        binding.vv.setOnClickListener {
            startActivity(Intent(this@ChooseActivity, FolderVideoActivity::class.java))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}


//package com.example.galleryapp
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.databinding.DataBindingUtil
//import com.example.galleryapp.activity.Image.FolderImgActivity
//import com.example.galleryapp.activity.Video.FolderVideoActivity
//import com.example.galleryapp.databinding.ActivityChooseBinding
//
//class ChooseActivity : AppCompatActivity() {
//
//    lateinit var binding : ActivityChooseBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding= DataBindingUtil.setContentView(this, R.layout.activity_choose)
//
//
//        binding.pp.setOnClickListener {
//
//            startActivity(Intent(this@ChooseActivity, FolderImgActivity::class.java))
//
//        }
//        binding.vv.setOnClickListener {
//
//            startActivity(Intent(this@ChooseActivity, FolderVideoActivity::class.java))
//
//        }
//
//
//    }
//
//    override fun onBackPressed() {
//      finishAffinity()
//    }
//}