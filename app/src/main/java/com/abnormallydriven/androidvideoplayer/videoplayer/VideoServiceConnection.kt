package com.abnormallydriven.androidvideoplayer.videoplayer

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.abnormallydriven.androidvideoplayer.common.dagger.ActivityScope
import com.google.android.exoplayer2.SimpleExoPlayer
import javax.inject.Inject

@ActivityScope
class VideoServiceConnection @Inject constructor(private val videoActivity: VideoActivity) : ServiceConnection {

    private var videoService : VideoService? = null



    override fun onServiceDisconnected(name: ComponentName?) {
        videoService = null
        videoActivity.onServiceDisconnected()
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        videoService = (service as VideoServiceBinder).videoService
        videoActivity.onServiceConnected()
    }

    fun playVideo(videoId: String) {
        videoService?.playVideo(videoId)
    }

    fun getExoplayer(): SimpleExoPlayer? {
        return videoService?.exoplayerController?.simpleExoPlayer
    }

    fun stopVideo() {
        videoService?.stopVideo()
    }


}