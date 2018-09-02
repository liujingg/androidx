package me.panpf.androidxkt

import android.os.Handler
import android.os.Looper
import me.panpf.androidxkt.os.isMainThread

private object MainHandlerHolder {
    val mainHandler = Handler(Looper.getMainLooper())
}

fun runInUI(block: () -> Unit) {
    if (isMainThread()) {
        block()
    } else {
        MainHandlerHolder.mainHandler.post { block() }
    }
}