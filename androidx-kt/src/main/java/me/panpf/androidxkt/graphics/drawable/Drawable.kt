@file:Suppress("unused")

package me.panpf.androidxkt.graphics.drawable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

/*
 * Drawable 相关的扩展方法或属性
 */

fun Drawable.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888, reuseBitmap: Bitmap? = null): Bitmap {
    setBounds(0, 0, intrinsicWidth, intrinsicHeight)

    val bitmap = reuseBitmap ?: Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}