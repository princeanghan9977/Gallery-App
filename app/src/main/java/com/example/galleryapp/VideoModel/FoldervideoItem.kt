package com.example.galleryapp.VideoModel

import java.io.Serializable

data class FoldervideoItem(
    var folderName: String,
    var folderPath: String,
    var folderDate: String? = null,
    var VideoModels: ArrayList<VideoModel> = ArrayList<VideoModel>()
) : Serializable