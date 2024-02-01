package com.example.galleryapp.activity.Video

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.galleryapp.ImageModel.ImageModel
import com.example.galleryapp.R
import com.example.galleryapp.VideoModel.VideoModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter


class ShowVideoActivity : AppCompatActivity() {

    lateinit var playerView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_video)

        playerView = findViewById(R.id.playerView)


        var VideoList = intent.getSerializableExtra("videos") as ArrayList<VideoModel>
        var pos = intent.getIntExtra("position", 0)




        playerView.setVideoURI(VideoList[pos].path.toUri())
        playerView.start()

    }
}