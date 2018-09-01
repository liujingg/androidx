package me.panpf.androidxkt.os

import android.os.Looper

/*
 * Thread related extension methods or properties
 */


/**
 * Is it the main thread?
 */
fun isMainThread(): Boolean {
    return Looper.getMainLooper().thread === Thread.currentThread()
}