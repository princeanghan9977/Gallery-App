package com.example.galleryapp// GalleryImageUtils.kt


import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.galleryapp.ImageModel.FolderImgItem
import com.example.galleryapp.ImageModel.ImageModel
import java.io.File


object GalleryImageUtils {
    fun getAllImages(context: Context): List<ImageModel> {
        val imagesList = mutableListOf<ImageModel>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )

        cursor?.use {

            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val uriColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            Log.d("GalleryApp", "------------------------- ${it.count}")
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val uri = it.getString(uriColumn)
                Log.d("GalleryApp", "Image ID: $id, Image URI: $uri")
                imagesList.add(ImageModel(id, uri))
            }
        }
        return imagesList
    }

    fun getFolderList(arrayList: ArrayList<ImageModel>): ArrayList<FolderImgItem> {
        var ftime = true
        val arrayList2: ArrayList<FolderImgItem> = ArrayList<FolderImgItem>()

        if (arrayList != null && arrayList.isNotEmpty()) {

            for (i in arrayList.indices) {

                val file: File = File(arrayList[i].path)
                Log.d("====", "getFolderList:file ${file}")

                val parent = file.parent
                Log.d("====", "getFolderList:parent ${parent}")

                val parentFile = file.parentFile
                Log.d("====", "getFolderList:parentFile ${parentFile}")

                val filedate = parentFile.lastModified().toString()
                Log.d("====", "getFolderList:filedate ${filedate}")

                val name = parentFile.name
                Log.d("====", "getFolderList:name ${name}")

                if (!isFileExits(arrayList2, parent)) {
                    arrayList2.add(FolderImgItem(name, parent, filedate))
                } else {
                    if (name == "0") {
                        if (ftime) {
                            arrayList2.add(FolderImgItem(name, parent, filedate))
                            ftime = false
                        }
                    }
                }
                val it: Iterator<FolderImgItem> = arrayList2.iterator()
                while (it.hasNext()) {
                    val next: FolderImgItem = it.next()
                    if (next.folderPath.equals(parent)) {
                        next.imageModels.add(arrayList[i])
                    }
                }
            }
        }
        return arrayList2
    }

    fun isFileExits(list: ArrayList<FolderImgItem>, str: String?): Boolean {
        val it: Iterator<FolderImgItem> = list.iterator()
        while (it.hasNext()) {
            if (it.next().folderPath.contains(str!!)) {
                return true
            }
        }
        return false
    }
}
