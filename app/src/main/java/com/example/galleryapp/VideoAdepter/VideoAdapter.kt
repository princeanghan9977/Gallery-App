package com.example.galleryapp.VideoAdepter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.VideoModel.VideoModel
import com.example.galleryapp.activity.Image.ShowImageActivity
import com.example.galleryapp.activity.Video.ShowVideoActivity
import com.example.galleryapp.activity.Video.VideoDataActivity

class VideoAdapter(var videoDataActivity: VideoDataActivity, var videos: ArrayList<VideoModel>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view =
            LayoutInflater.from(videoDataActivity).inflate(R.layout.item_video_folder, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(videos[position].path.toString())
            .centerCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            videoDataActivity.startActivity(
                Intent(videoDataActivity, ShowVideoActivity::class.java)
                    .putExtra("videos", videos)
                    .putExtra("position", position)
            )
        }


        Log.d(
            "====",
            "onBindViewHolder:images[position].uri.toString() ${videos[position].path.toString()}"
        )
    }


    override fun getItemCount(): Int = videos.size


}
