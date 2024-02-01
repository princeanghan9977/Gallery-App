package com.example.galleryapp.activity.Image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.galleryapp.R
import com.example.galleryapp.ImageAdapter.MyViewPager
import com.example.galleryapp.databinding.ActivityShowImageBinding
import com.example.galleryapp.ImageModel.ImageModel

class ShowImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_image)

        var imageList = intent.getSerializableExtra("images") as ArrayList<ImageModel>
        var pos = intent.getIntExtra("position", 0)


        Log.e("GalleryApp", "onCreate: images ::: ${imageList?.size}")
        Log.e("GalleryApp", "onCreate: pos ::: ${pos}")

        var MyViewPager = MyViewPager(this@ShowImageActivity, imageList)
        binding.pager.adapter = MyViewPager
        binding.pager.currentItem = pos


    }
}