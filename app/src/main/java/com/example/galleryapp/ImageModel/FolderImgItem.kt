package com.example.galleryapp.ImageModel

import java.io.Serializable

data class FolderImgItem(
    var folderName: String,
    var folderPath: String,
    var folderDate: String? = null,
    var imageModels: ArrayList<ImageModel> = ArrayList<ImageModel>()
) : Serializable