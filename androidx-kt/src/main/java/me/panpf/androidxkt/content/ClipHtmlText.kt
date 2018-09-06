package me.panpf.androidxkt.content

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.support.annotation.RequiresApi

class ClipHtmlText internal constructor(mimeType: String, var text: CharSequence, var htmlText: String) : ClipContent(mimeType) {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    constructor(text: CharSequence, htmlText: String) : this(ClipDescription.MIMETYPE_TEXT_HTML, text, htmlText) {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun toItem(): ClipData.Item {
        return ClipData.Item(text, htmlText)
    }
}
