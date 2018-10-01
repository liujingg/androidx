/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.panpf.androidxkt.graphics.drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import me.panpf.androidxkt.graphics.createMatrixColorFilter

/*
 * Drawable related extension methods
 */

/**
 * Convert Drawable to bitmap, use intrinsic size as the size of the new bitmap
 *
 * @receiver Source Drawable
 * @param config      Bitmap configuration, default value Bitmap.Config.ARGB_8888
 * @param reuseBitmap Reusable Bitmap
 */
fun Drawable.toBitmapWithIntrinsicSize(config: Bitmap.Config = Bitmap.Config.ARGB_8888, reuseBitmap: Bitmap? = null): Bitmap {
    val intrinsicWidth = this.intrinsicWidth
    val intrinsicHeight = this.intrinsicHeight
    require(intrinsicWidth > 0 && intrinsicHeight > 0) { "Both drawable intrinsicWidth and intrinsicHeight must be greater than 0" }

    val originBounds = Rect(this.bounds)

    this.setBounds(0, 0, intrinsicWidth, intrinsicHeight)

    var bitmap = reuseBitmap
    if (bitmap == null) {
        bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config)
    }
    val canvas = Canvas(bitmap!!)
    this.draw(canvas)

    // Restore bounds
    this.bounds = originBounds
    return bitmap
}

/**
 * Convert Drawable to bitmap, use bounds size as the size of the new bitmap
 *
 * @receiver Source Drawable
 * @param config      Bitmap configuration, default value Bitmap.Config.ARGB_8888
 * @param reuseBitmap Reusable Bitmap
 */
fun Drawable.toBitmapWithBoundsSize(config: Bitmap.Config = Bitmap.Config.ARGB_8888, reuseBitmap: Bitmap? = null): Bitmap {
    val originBounds = Rect(this.bounds)
    if (originBounds.isEmpty) throw IllegalStateException("drawable bounds is empty")

    var bitmap = reuseBitmap
    if (bitmap == null) {
        bitmap = Bitmap.createBitmap(originBounds.width(), originBounds.height(), config)
    }
    if (originBounds.left != 0 || originBounds.top != 0) {
        this.setBounds(0, 0, originBounds.width(), originBounds.height())
    }
    val canvas = Canvas(bitmap!!)
    this.draw(canvas)

    // Restore bounds
    if (originBounds.left != 0 || originBounds.top != 0) {
        this.bounds = originBounds
    }
    return bitmap
}


/**
 * Change the color of the drawable
 */
fun <T : Drawable> T.toDrawableByColor(@ColorInt color: Int): T {
    @Suppress("UNCHECKED_CAST")
    val newDrawable: T = this.mutate() as T
    newDrawable.colorFilter = color.createMatrixColorFilter()
    return newDrawable
}

/**
 * Change the color of the resource drawable
 *
 * @param resId Drawable resource id
 */
fun Context.toDrawableByColorFromDrawableRes(@DrawableRes resId: Int, @ColorInt color: Int): Drawable {
    return this.resources.getDrawable(resId).toDrawableByColor(color)
}