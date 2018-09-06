package me.panpf.androidxkt.content

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent

class ClipIntent internal constructor(mimeType: String, var intent: Intent) : ClipContent(mimeType) {

    constructor(intent: Intent) : this(ClipDescription.MIMETYPE_TEXT_INTENT, intent) {}

    override fun toItem(): ClipData.Item {
        return ClipData.Item(intent)
    }
}
