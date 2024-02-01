package com.example.galleryapp

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.galleryapp.VideoModel.FoldervideoItem
import com.example.galleryapp.VideoModel.VideoModel
import java.io.File

object GalleryVideoUtils {

    fun getAllVideo(context: Context): List<VideoModel> {

        val VideoList = mutableListOf<VideoModel>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
        )
        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"
        val cursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )

        cursor?.use {

            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val uriColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            Log.d("GalleryApp", "------------------------- ${it.count}")
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val uri = it.getString(uriColumn)
                Log.d("GalleryApp", "Image ID: $id, Image URI: $uri")
                VideoList.add(VideoModel(id, uri))
            }
        }
        return VideoList
    }


    fun getVideoFolderList(arrayList: ArrayList<VideoModel>): ArrayList<FoldervideoItem> {
        var ftime = true
        val arrayList2: ArrayList<FoldervideoItem> = ArrayList<FoldervideoItem>()

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
                    arrayList2.add(FoldervideoItem(name, parent, filedate))
                } else {
                    if (name == "0") {
                        if (ftime) {
                            arrayList2.add(FoldervideoItem(name, parent, filedate))
                            ftime = false
                        }
                    }
                }
                val it: MutableIterator<FoldervideoItem> = arrayList2.iterator()
                while (it.hasNext()) {
                    val next: FoldervideoItem = it.next()
                    if (next.folderPath.equals(parent)) {
                        next.VideoModels.add(arrayList[i])
                    }
                }
            }
        }
        return arrayList2
    }

    fun isFileExits(list: ArrayList<FoldervideoItem>, str: String?): Boolean {
        val it: MutableIterator<FoldervideoItem> = list.iterator()
        while (it.hasNext()) {
            if (it.next().folderPath.contains(str!!)) {
                return true
            }
        }
        return false
    }



}