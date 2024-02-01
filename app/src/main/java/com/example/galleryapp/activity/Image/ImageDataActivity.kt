package com.example.galleryapp.activity.Image

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.ImageAdapter.ImageAdapter
import com.example.galleryapp.R
import com.example.galleryapp.folderList

class ImageDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagedata)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = GridLayoutManager(this, 3)

        var pos = intent.getIntExtra("position", 0)
        var images = folderList[pos].imageModels

        Log.d("====", "onCreate:images ${images}")

//        val adapter = FolderImgAdapter(this@ImageDataActivity, folderList)
        val adapter = ImageAdapter(this@ImageDataActivity, images)
        recyclerView.adapter = adapter
    }
}