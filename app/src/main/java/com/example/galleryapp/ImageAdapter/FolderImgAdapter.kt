package com.example.galleryapp.ImageAdapter// ImageAdapter.kt

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.activity.Image.ImageDataActivity
import com.example.galleryapp.activity.Image.FolderImgActivity
import com.example.galleryapp.ImageModel.FolderImgItem

class FolderImgAdapter(var mainactivity: FolderImgActivity, private var folderItemList: List<FolderImgItem>) :
    RecyclerView.Adapter<FolderImgAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val fname: TextView = itemView.findViewById(R.id.fname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(mainactivity).inflate(R.layout.item_folder, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        val image = images[position]
        Glide.with(holder.itemView)
            .load(folderItemList[position].imageModels[0].path.toString())
            .centerCrop()
            .into(holder.imageView)

        holder.fname.text = folderItemList[position].folderName

        holder.itemView.setOnClickListener {
            mainactivity.startActivity(
                Intent(mainactivity, ImageDataActivity::class.java).putExtra(
                    "position",
                    position
                )
            )
        }
        Log.d(
            "====",
            "onBindViewHolder:images[position].uri.toString() ${folderItemList[position].imageModels[0].path.toString()}"
        )
    }

    override fun getItemCount(): Int {
        return folderItemList.size
    }


}
