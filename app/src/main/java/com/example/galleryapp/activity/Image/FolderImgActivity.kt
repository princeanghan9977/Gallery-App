package com.example.galleryapp.activity.Image

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.ImageAdapter.FolderImgAdapter
import com.example.galleryapp.GalleryImageUtils
import com.example.galleryapp.GalleryImageUtils.getFolderList
import com.example.galleryapp.R
import com.example.galleryapp.folderList
import com.example.galleryapp.ImageModel.ImageModel

class FolderImgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        var images = GalleryImageUtils.getAllImages(this@FolderImgActivity)

        folderList = getFolderList(images as ArrayList<ImageModel>)
        Log.d("====", "onCreate:images ${images}")

        val adapter = FolderImgAdapter(this@FolderImgActivity, folderList)
        recyclerView.adapter = adapter

    }
}