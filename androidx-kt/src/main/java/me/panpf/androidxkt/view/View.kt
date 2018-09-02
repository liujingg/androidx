package me.panpf.androidxkt.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.panpf.androidxkt.widget.showShortToast

/*
 * View related extension method
 */


fun View.setLongClickToastHint(hintContent: String) {
    this.setOnLongClickListener {
        this.showShortToast(hintContent)
        true
    }
}

fun View.setLongClickToastHint(hintContentId: Int) {
    this.setLongClickToastHint(context.getString(hintContentId))
}


fun View.setLayoutWidth(newWidth: Int, initHeight: Int) {
    var layoutParams: ViewGroup.LayoutParams? = this.layoutParams
    if (layoutParams == null) {
        layoutParams = ViewGroup.LayoutParams(newWidth, initHeight)
    } else {
        layoutParams.width = newWidth
    }
    this.layoutParams = layoutParams
}

fun View.setLayoutWidth(newWidth: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null) {
        layoutParams.width = newWidth
        this.layoutParams = layoutParams
    }
}

fun View.setLayoutHeight(newHeight: Int, initWidth: Int) {
    var layoutParams: ViewGroup.LayoutParams? = this.layoutParams
    if (layoutParams == null) {
        layoutParams = ViewGroup.LayoutParams(initWidth, newHeight)
    } else {
        layoutParams.height = newHeight
    }
    this.layoutParams = layoutParams
}

fun View.setLayoutHeight(newHeight: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null) {
        layoutParams.height = newHeight
        this.layoutParams = layoutParams
    }
}

fun View.setLayoutSize(width: Int, height: Int) {
    var layoutParams: ViewGroup.LayoutParams? = this.layoutParams
    if (layoutParams == null) {
        layoutParams = ViewGroup.LayoutParams(width, height)
    } else {
        layoutParams.width = width
        layoutParams.height = height
    }
    this.layoutParams = layoutParams
}

fun View.setLayoutMarginTop(newMarinTop: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null && layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams.topMargin = newMarinTop
        this.layoutParams = layoutParams
    }
}


fun View.addLayoutHeight(addHeight: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null) {
        layoutParams.height += addHeight
        this.layoutParams = layoutParams
    }
}

fun View.addLayoutWidth(addWidth: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null) {
        layoutParams.width += addWidth
        this.layoutParams = layoutParams
    }
}

fun View.addLayoutSize(addWidth: Int, addHeight: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null) {
        layoutParams.width += addWidth
        layoutParams.height += addHeight
        this.layoutParams = layoutParams
    }
}

fun View.addLayoutMarginTop(addMarinTop: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams != null && layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams.topMargin += addMarinTop
        this.layoutParams = layoutParams
    }
}

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

fun Context.inflateLayout(@LayoutRes id: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(id, parent, attachToRoot)
}

fun Context.inflateLayout(@LayoutRes id: Int, parent: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(id, parent, false)
}
