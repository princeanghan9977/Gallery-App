package com.example.galleryapp.ImageAdapter// ImageAdapter.kt

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.activity.Image.ImageDataActivity
import com.example.galleryapp.activity.Image.ShowImageActivity
import com.example.galleryapp.ImageModel.ImageModel

class ImageAdapter(
    var activity: ImageDataActivity,
    private var images: java.util.ArrayList<ImageModel>
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {


//        val image = images[position]
        Glide.with(holder.itemView)
            .load(images[position].path.toString())
            .centerCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            activity.startActivity(
                Intent(activity, ShowImageActivity::class.java)
                    .putExtra("images", images)
                    .putExtra("position", position)
            )
        }


        Log.d(
            "====",
            "onBindViewHolder:images[position].uri.toString() ${images[position].path.toString()}"
        )
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
