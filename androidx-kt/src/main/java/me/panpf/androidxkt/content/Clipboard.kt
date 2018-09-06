package me.panpf.androidxkt.content

import android.content.*
import android.net.Uri
import android.os.Build
import java.util.*

/**
 * copy
 */
fun Context.copy(clipData: ClipData) {
    val clipboardManager = (this.getSystemService(Context.CLIPBOARD_SERVICE)
            ?: throw IllegalStateException("ClipboardManager not found")) as ClipboardManager

    clipboardManager.primaryClip = clipData
}


/**
 * Copy text
 */
fun Context.copyText(label: CharSequence, texts: Array<CharSequence>) {
    if (texts.isEmpty()) return
    val data = ClipData.newPlainText(label, texts[0])
    if (texts.size > 1) {
        for (index in 1 until texts.size) {
            data.addItem(ClipData.Item(texts[index]))
        }
    }
    this.copy(data)
}

/**
 * Copy text
 */
fun Context.copyText(texts: Array<CharSequence>) {
    this.copyText("text", texts)
}

/**
 * Copy text
 */
fun Context.copyText(label: CharSequence, text: CharSequence) {
    this.copy(ClipData.newPlainText(label, text))
}

/**
 * Copy text
 */
fun Context.copyText(text: CharSequence) {
    this.copyText("text", text)
}


/**
 * Copy html text
 */
fun Context.copyHtmlText(label: CharSequence, htmlContents: Array<ClipHtmlText>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        if (htmlContents.isEmpty()) return
        var htmlContent = htmlContents[0]
        val data = ClipData.newHtmlText(label, htmlContent.text, htmlContent.htmlText)
        if (htmlContents.size > 1) {
            for (index in 1 until htmlContents.size) {
                htmlContent = htmlContents[index]
                data.addItem(ClipData.Item(htmlContent.text, htmlContent.htmlText))
            }
        }
        this.copy(data)
    }
}

/**
 * Copy html text
 */
fun Context.copyHtmlText(htmlContents: Array<ClipHtmlText>) {
    this.copyHtmlText("htmlText", htmlContents)
}

/**
 * Copy html text
 */
fun Context.copyHtmlText(label: CharSequence, text: CharSequence, htmlText: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        this.copy(ClipData.newHtmlText(label, text, htmlText))
    }
}

/**
 * Copy html text
 */
fun Context.copyHtmlText(text: CharSequence, htmlText: String) {
    this.copyHtmlText("htmlText", text, htmlText)
}


/**
 * Copy intent
 */
fun Context.copyIntent(label: CharSequence, intents: Array<Intent>) {
    if (intents.isEmpty()) return
    val data = ClipData.newIntent(label, intents[0])
    if (intents.size > 1) {
        for (index in 1 until intents.size) {
            data.addItem(ClipData.Item(intents[index]))
        }
    }
    this.copy(data)
}

/**
 * Copy intent
 */
fun Context.copyIntent(intents: Array<Intent>) {
    this.copyIntent("intent", intents)
}

/**
 * Copy intent
 */
fun Context.copyIntent(label: CharSequence, intent: Intent) {
    this.copy(ClipData.newIntent(label, intent))
}

/**
 * Copy intent
 */
fun Context.copyIntent(intent: Intent) {
    this.copyIntent("intent", intent)
}


/**
 * Copy uri
 */
fun Context.copyUri(label: CharSequence, uris: Array<Uri>) {
    if (uris.isEmpty()) return
    val data = ClipData.newUri(this.contentResolver, label, uris[0])
    if (uris.size > 1) {
        for (index in 1 until uris.size) {
            data.addItem(ClipData.Item(uris[index]))
        }
    }
    this.copy(data)
}

/**
 * Copy uri
 */
fun Context.copyUri(uris: Array<Uri>) {
    this.copyUri("uri", uris)
}

/**
 * Copy uri
 */
fun Context.copyUri(label: CharSequence, uri: Uri) {
    this.copy(ClipData.newUri(this.contentResolver, label, uri))
}

/**
 * Copy uri
 */
fun Context.copyUri(uri: Uri) {
    this.copyUri("uri", uri)
}


/**
 * Copy raw uri
 */
fun Context.copyRawUri(label: CharSequence, uris: Array<Uri>) {
    if (uris.isEmpty()) return
    val data = ClipData.newRawUri(label, uris[0])
    if (uris.size > 1) {
        for (index in 1 until uris.size) {
            data.addItem(ClipData.Item(uris[index]))
        }
    }
    this.copy(data)
}

/**
 * Copy raw uri
 */
fun Context.copyRawUri(uris: Array<Uri>) {
    this.copyRawUri("rawUri", uris)
}

/**
 * Copy raw uri
 */
fun Context.copyRawUri(label: CharSequence, uri: Uri) {
    this.copy(ClipData.newRawUri(label, uri))
}

/**
 * Copy raw uri
 */
fun Context.copyRawUri(uri: Uri) {
    this.copyRawUri("rawUri", uri)
}


/**
 * Copy uri
 */
fun Context.copyMimeTypeUri(label: CharSequence, mimeType: String, uris: Array<Uri>) {
    if (uris.isEmpty()) return
    val data = ClipData(label, arrayOf(mimeType), ClipData.Item(uris[0]))
    if (uris.size > 1) {
        for (index in 1 until uris.size) {
            data.addItem(ClipData.Item(uris[index]))
        }
    }
    this.copy(data)
}

/**
 * Copy uri
 */
fun Context.copyMimeTypeUri(mimeType: String, uris: Array<Uri>) {
    this.copyMimeTypeUri("mimeTypeUri", mimeType, uris)
}

/**
 * Copy uri
 */
fun Context.copyMimeTypeUri(label: CharSequence, mimeType: String, uri: Uri) {
    this.copy(ClipData(label, arrayOf(mimeType), ClipData.Item(uri)))
}

/**
 * Copy uri
 */
fun Context.copyMimeTypeUri(mimeType: String, uri: Uri) {
    this.copyMimeTypeUri("mimeTypeUri", mimeType, uri)
}


/**
 * Copy multi type content
 */
fun Context.copyContents(label: CharSequence, contents: Array<ClipContent>) {
    if (contents.isEmpty()) return

    val mimeTypes = arrayOfNulls<String>(contents.size)
    for (index in 0 until contents.size) {
        mimeTypes[index] = contents[index].mimeType
    }

    val data = ClipData(label, mimeTypes, contents[0].toItem())
    for (index in 1 until contents.size) {
        data.addItem(contents[index].toItem())
    }

    this.copy(data)
}

/**
 * Copy multi type content
 */
fun Context.copyContents(contents: Array<ClipContent>) {
    this.copyContents("contents", contents)
}


/**
 * Get current clip data
 */
fun Context.getClipData(): ClipData? {
    val clipboardManager = (this.getSystemService(Context.CLIPBOARD_SERVICE)
            ?: throw IllegalStateException("ClipboardManager not found")) as ClipboardManager
    return clipboardManager.primaryClip
}

/**
 * Get current clip label
 */
fun Context.getClipDataLabel(): CharSequence? {
    val data = this.getClipData()
    return data?.description?.label
}

/**
 * Get current clip all content
 */
fun Context.getClipDataContents(): Array<ClipContent>? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val clipDescription = data.description
    val objectList = LinkedList<ClipContent>()
    for (index in 0 until data.itemCount) {
        val item = data.getItemAt(index)
        // Usually multiple items have only one mimeType
        val mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.mimeTypeCount - 1))
        if (ClipDescription.MIMETYPE_TEXT_PLAIN == mimeType) {
            objectList.add(ClipPlainText(mimeType, item.text))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && ClipDescription.MIMETYPE_TEXT_HTML == mimeType) {
            objectList.add(ClipHtmlText(mimeType, item.text, item.htmlText))
        } else if (ClipDescription.MIMETYPE_TEXT_INTENT == mimeType) {
            objectList.add(ClipIntent(mimeType, item.intent))
        } else {
            val uri = item.uri
            if (uri != null) {
                objectList.add(ClipUri(mimeType, uri))
            }
        }
    }
    return objectList.toTypedArray()
}


/**
 * Get current clip text data
 */
fun Context.getClipText(): CharSequence? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    return if (ClipDescription.MIMETYPE_TEXT_PLAIN == data.description.getMimeType(0)) {
        data.getItemAt(0).text
    } else {
        null
    }
}

/**
 * Get current clip all text data
 */
fun Context.getClipTexts(): Array<CharSequence>? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val clipDescription = data.description
    val textList = LinkedList<CharSequence>()
    for (index in 0 until data.itemCount) {
        // Usually multiple items have only one mimeType
        val mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.mimeTypeCount - 1))
        if (ClipDescription.MIMETYPE_TEXT_PLAIN == mimeType) {
            textList.add(data.getItemAt(index).text)
        }
    }
    return textList.toTypedArray()
}


/**
 * Get current clip html text data
 */
fun Context.getClipHtmlText(): ClipHtmlText? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) return null
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val mimeType = data.description.getMimeType(0)
    return if (ClipDescription.MIMETYPE_TEXT_HTML == mimeType) {
        val clipDataItem = data.getItemAt(0)
        ClipHtmlText(mimeType, clipDataItem.text, clipDataItem.htmlText)
    } else {
        null
    }
}

/**
 * Get current clip all html text data
 */
fun Context.getClipHtmlTexts(): Array<ClipHtmlText>? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) return null
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val clipDescription = data.description
    val htmlTestList = LinkedList<ClipHtmlText>()
    for (index in 0 until data.itemCount) {
        // Usually multiple items have only one mimeType
        val mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.mimeTypeCount - 1))
        if (ClipDescription.MIMETYPE_TEXT_HTML == mimeType) {
            val clipDataItem = data.getItemAt(index)
            htmlTestList.add(ClipHtmlText(mimeType, clipDataItem.text, clipDataItem.htmlText))
        }
    }
    return htmlTestList.toTypedArray()
}


/**
 * Get current clip intent data
 */
fun Context.getClipIntent(): Intent? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    return if (ClipDescription.MIMETYPE_TEXT_INTENT == data.description.getMimeType(0)) {
        data.getItemAt(0).intent
    } else {
        null
    }
}

/**
 * Get current clip all intent data
 */
fun Context.getClipIntents(): Array<Intent>? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val clipDescription = data.description
    val intentList = LinkedList<Intent>()
    for (index in 0 until data.itemCount) {
        // Usually multiple items have only one mimeType
        val mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.mimeTypeCount - 1))
        if (ClipDescription.MIMETYPE_TEXT_INTENT == mimeType) {
            intentList.add(data.getItemAt(index).intent)
        }
    }
    return intentList.toTypedArray()
}


/**
 * Get current clip uri data
 */
fun Context.getClipUri(): ClipUri? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val mimeType = data.description.getMimeType(0)
    if (ClipDescription.MIMETYPE_TEXT_PLAIN != mimeType && ClipDescription.MIMETYPE_TEXT_HTML != mimeType && ClipDescription.MIMETYPE_TEXT_INTENT != mimeType) {
        val uri = data.getItemAt(0).uri
        if (uri != null) {
            return ClipUri(mimeType, uri)
        }
    }
    return null
}

/**
 * Get current clip all uri data
 */
fun Context.getClipUris(): Array<ClipUri>? {
    val data = this.getClipData()
    if (data == null || data.itemCount <= 0 || data.description.mimeTypeCount <= 0)
        return null

    val clipDescription = data.description
    val uriList = LinkedList<ClipUri>()
    for (index in 0 until data.itemCount) {
        // Usually multiple items have only one mimeType
        val mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.mimeTypeCount - 1))
        if (ClipDescription.MIMETYPE_TEXT_PLAIN != mimeType && ClipDescription.MIMETYPE_TEXT_HTML != mimeType && ClipDescription.MIMETYPE_TEXT_INTENT != mimeType) {
            val uri = data.getItemAt(index).uri
            if (uri != null) {
                uriList.add(ClipUri(mimeType, uri))
            }
        }
    }
    return uriList.toTypedArray()
}


/**
 * Add primary clip changed listener
 */
fun Context.addPrimaryClipChangedListener(listener: ClipboardManager.OnPrimaryClipChangedListener) {
    val clipboardManager = (this.getSystemService(Context.CLIPBOARD_SERVICE)
            ?: throw IllegalStateException("ClipboardManager not found")) as ClipboardManager
    clipboardManager.addPrimaryClipChangedListener(listener)
}

/**
 * Remove primary clip changed listener
 */
fun Context.removePrimaryClipChangedListener(listener: ClipboardManager.OnPrimaryClipChangedListener) {
    val clipboardManager = (this.getSystemService(Context.CLIPBOARD_SERVICE)
            ?: throw IllegalStateException("ClipboardManager not found")) as ClipboardManager
    clipboardManager.removePrimaryClipChangedListener(listener)
}


/**
 * Clean clip date
 */
fun Context.clearClip() {
    if (Build.VERSION.SDK_INT >= 28) {
        val clipboardManager = (this.getSystemService(Context.CLIPBOARD_SERVICE)
                ?: throw IllegalStateException("ClipboardManager not found")) as ClipboardManager
        clipboardManager.clearPrimaryClip()
    }
}