package com.example.cryptocurrencyratekotlin

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    private val TAG = "MyService"
    var player: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        //Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show()

        player = MediaPlayer.create(this, R.raw.fon)
        player?.setLooping(true)
    }

    override fun onDestroy() {
        //Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show()
        player?.stop()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        //Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show()
        player?.start()
    }
}