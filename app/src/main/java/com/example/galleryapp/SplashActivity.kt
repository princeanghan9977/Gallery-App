package com.example.galleryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed(
            object : Runnable {

                override fun run() {

                    if (checkStoragePermission(this@SplashActivity))
                        startActivity(Intent(this@SplashActivity, ChooseActivity::class.java)

                    ) else
                        startActivity(Intent(this@SplashActivity, ChooseActivity::class.java)
//                        startActivity(Intent(this@SplashActivity, PermissionActivity::class.java)
                    )

                }
            }, 4500
        )


    }
}