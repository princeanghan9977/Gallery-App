package com.example.galleryapp.VideoAdepter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.galleryapp.R
import com.example.galleryapp.VideoModel.FoldervideoItem
import com.example.galleryapp.activity.Image.ImageDataActivity
import com.example.galleryapp.activity.Video.FolderVideoActivity
import com.example.galleryapp.activity.Video.VideoDataActivity

class FolderVideoAdapter(
    var folderVideoActivity: FolderVideoActivity,
    var folderItemList: ArrayList<FoldervideoItem>
) : RecyclerView.Adapter<FolderVideoAdapter.VideoView>() {

    class VideoView(itemView: View) :  RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val fname: TextView = itemView.findViewById(R.id.fname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoView{
        val view =
            LayoutInflater.from(folderVideoActivity).inflate(R.layout.item_folder, parent, false)
        return VideoView(view)
    }


    override fun onBindViewHolder(holder: VideoView, position: Int) {

        Glide.with(holder.itemView)
            .load(folderItemList[position].VideoModels[0].path.toString())
            .centerCrop()
            .into(holder.imageView)

        holder.fname.text = folderItemList[position].folderName

        holder.itemView.setOnClickListener {
            folderVideoActivity.startActivity(
                Intent(folderVideoActivity, VideoDataActivity::class.java).putExtra(
                    "position",
                    position
                )
            )
        }
        Log.d(
            "====",
            "onBindViewHolder:images[position].uri.toString() ${folderItemList[position].VideoModels[0].path..toString()}"
        )


    }


    override fun getItemCount(): Int = folderItemList.size




}