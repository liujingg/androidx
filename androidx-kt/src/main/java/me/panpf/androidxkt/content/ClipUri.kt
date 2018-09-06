package me.panpf.androidxkt.content

import android.content.ClipData
import android.content.ClipDescription
import android.content.ContentResolver
import android.net.Uri
import java.util.*

class ClipUri : ClipContent {

    var uri: Uri

    constructor(mimeType: String, uri: Uri) : super(mimeType) {
        this.uri = uri
    }

    constructor(uri: Uri) : super(ClipDescription.MIMETYPE_TEXT_URILIST) {
        this.uri = uri
    }

    constructor(resolver: ContentResolver, uri: Uri) : super(getMimeTypes(resolver, uri)[0]) {
        this.uri = uri
    }

    override fun toItem(): ClipData.Item {
        return ClipData.Item(uri)
    }

    companion object {

        /**
         * Finds all applicable MIME types for a given URI.
         *
         * @param resolver ContentResolver used to get information about the URI.
         * @param uri      The URI.
         * @return Returns an array of MIME types.
         */
        fun getMimeTypes(resolver: ContentResolver, uri: Uri): Array<String> {
            var mimeTypes: Array<String>? = null
            if (ContentResolver.SCHEME_CONTENT == uri.scheme) {
                val realType = resolver.getType(uri)
                mimeTypes = resolver.getStreamTypes(uri, "*/*")
                if (realType != null) {
                    if (mimeTypes == null) {
                        mimeTypes = arrayOf(realType)
                    } else if (!mimeTypes.contains(realType)) {
                        val tmp = LinkedList<String>()
                        tmp.add(realType)
                        Collections.addAll(tmp, *mimeTypes)
                        mimeTypes = tmp.toTypedArray()
                    }
                }
            }
            if (mimeTypes == null) {
                mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_URILIST)
            }
            return mimeTypes
        }
    }
}
