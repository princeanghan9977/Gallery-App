package com.example.galleryapp.activity.Video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.*
import com.example.galleryapp.VideoAdepter.FolderVideoAdapter
import com.example.galleryapp.VideoModel.VideoModel

class FolderVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folder_video)

        val VideorecyclerView: RecyclerView = findViewById(R.id.VideorecyclerView)
        VideorecyclerView.layoutManager = GridLayoutManager(this, 3)

        var Videos = GalleryVideoUtils.getAllVideo(this@FolderVideoActivity)

        folderList2 = GalleryVideoUtils.getVideoFolderList(Videos as ArrayList<VideoModel>)
        Log.d("====", "onCreate:images ${Videos}")

        val adapter = FolderVideoAdapter(this@FolderVideoActivity, folderList2)
        VideorecyclerView.adapter = adapter
    }
}