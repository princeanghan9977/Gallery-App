package com.example.galleryapp.ImageAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.viewpager.widget.PagerAdapter
import com.example.galleryapp.R
import com.example.galleryapp.activity.Image.ShowImageActivity
import com.example.galleryapp.ImageModel.ImageModel

class MyViewPager(var showImageActivity: ShowImageActivity, var imageList: ArrayList<ImageModel>) :
    PagerAdapter() {

    override fun getCount(): Int = imageList.size


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {


        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var seefullimage: ImageView

        var view =
            LayoutInflater.from(showImageActivity).inflate(R.layout.pageviwerimage, container, false)

        seefullimage = view.findViewById(R.id.seefullimage)

        seefullimage.setImageURI(imageList.get(position).path.toUri())

        container.addView(view)


        return view
    }
}
