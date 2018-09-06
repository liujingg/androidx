package me.panpf.androidxkt.content

import android.content.ClipData
import android.content.ClipDescription

class ClipPlainText internal constructor(mimeType: String, var text: CharSequence) : ClipContent(mimeType) {

    constructor(text: CharSequence) : this(ClipDescription.MIMETYPE_TEXT_PLAIN, text) {}

    override fun toItem(): ClipData.Item {
        return ClipData.Item(text)
    }
}
