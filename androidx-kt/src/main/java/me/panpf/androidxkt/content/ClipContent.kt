package me.panpf.androidxkt.content

import android.content.ClipData

abstract class ClipContent(var mimeType: String) {

    abstract fun toItem(): ClipData.Item
}
