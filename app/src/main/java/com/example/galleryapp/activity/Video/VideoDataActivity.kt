package com.example.galleryapp.activity.Video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.R
import com.example.galleryapp.VideoAdepter.VideoAdapter
import com.example.galleryapp.folderList2

class VideoDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_data)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)

        recyclerView.layoutManager = GridLayoutManager(this, 3)

        var pos = intent.getIntExtra("position", 0)
        var Videos = folderList2[pos].VideoModels

        Log.d("====", "onCreate:images ${Videos}")

//        val adapter = FolderImgAdapter(this@ImageDataActivity, folderList)
        val adapter = VideoAdapter(this@VideoDataActivity, Videos)
        recyclerView.adapter = adapter
    }
}