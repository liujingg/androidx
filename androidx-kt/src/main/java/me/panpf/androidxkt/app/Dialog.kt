package me.panpf.androidxkt.app

import android.app.Dialog
import me.panpf.javaxkt.lang.setFieldValue

/**
 * Whether to automatically close the dialog box when setting the click button
 */
fun Dialog.setClickButtonClosable(close: Boolean) {
    try {
        this.setFieldValue("mShowing", close)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    }
}