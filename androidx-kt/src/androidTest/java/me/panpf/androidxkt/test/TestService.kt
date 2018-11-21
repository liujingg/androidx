package me.panpf.androidxkt.test

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TestService : Service() {

    companion object {
        var SHARE_KEY: String? = ""
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        SHARE_KEY = intent.getStringExtra("SHARE_KEY")
        return super.onStartCommand(intent, flags, startId)
    }
}
