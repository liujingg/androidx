@file:Suppress("unused")

package me.panpf.androidxkt.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.View
import android.view.ViewGroup

/*
 * View 相关的扩展方法或属性
 */

fun View.toBitmap(config: Bitmap.Config, scale: Float = 1.0f): Bitmap {
    var bitmapWidth = this.width
    var bitmapHeight = this.height
    val matrix = Matrix()

    if (scale > 0) {
        bitmapWidth = (bitmapWidth * scale).toInt()
        bitmapHeight = (bitmapHeight * scale).toInt()
        matrix.setScale(scale, scale)
    }

    val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, config)
    val canvas = Canvas(bitmap)
    canvas.matrix = matrix
    this.draw(canvas)

    return bitmap
}

fun View.toBitmapByMaxWidth(config: Bitmap.Config, maxWidth: Int): Bitmap {
    return toBitmap(config, Math.min(maxWidth.toFloat() / this.width, 1.0f))
}

fun View.toBitmapByMaxHeight(config: Bitmap.Config, maxHeight: Int): Bitmap {
    return toBitmap(config, Math.min(maxHeight.toFloat() / this.height, 1.0f))
}

fun View.setLayoutSize(width: Int, height: Int) {
    this.layoutParams = (this.layoutParams ?: ViewGroup.LayoutParams(0, 0)).also {
        it.width = width
        it.height = height
    }
}

fun View.setLayoutWidth(width: Int, initHeight: Int) {
    this.layoutParams = (this.layoutParams ?: ViewGroup.LayoutParams(0, 0)).also {
        it.width = width
        it.height = initHeight
    }
}

fun View.setLayoutHeight(height: Int, initWidth: Int) {
    this.layoutParams = (this.layoutParams ?: ViewGroup.LayoutParams(0, 0)).also {
        it.width = initWidth
        it.height = height
    }
}

fun View.setMarginTop(marginTop: Int) {
    this.layoutParams?.takeIf { it is ViewGroup.MarginLayoutParams }?.let { it as ViewGroup.MarginLayoutParams }?.let {
        it.topMargin = marginTop
        this@setMarginTop.layoutParams = it
    }
}
