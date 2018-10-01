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

package me.panpf.androidxkt.graphics

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.ColorInt
import android.util.TypedValue
import java.io.*

/*
 * Bitmap 相关的扩展方法或属性
 */

fun Bitmap.centerCrop(outWidth: Int, outHeight: Int, outConfig: Bitmap.Config): Bitmap {
    val srcBitmap = this

    val widthScale = outWidth.toFloat() / srcBitmap.width
    val heightScale = outHeight.toFloat() / srcBitmap.height
    val finalScale = Math.max(widthScale, heightScale)
    val dx = (srcBitmap.width * finalScale - outWidth) / 2
    val dy = (srcBitmap.height * finalScale - outHeight) / 2

    val paint = Paint()
    val matrix = Matrix()
    matrix.postScale(finalScale, finalScale)
    matrix.postTranslate(-dx, -dy)

    val newBitmap = Bitmap.createBitmap(outWidth, outHeight, outConfig)
    val canvas = Canvas(newBitmap)
    canvas.drawBitmap(srcBitmap, matrix, paint)

    return newBitmap
}

fun Bitmap.tint(color: Int): Bitmap {
    val newBitmap = Bitmap.createBitmap(width, height, config)
    val canvas = Canvas(newBitmap)
    val mPaint = Paint()
    val mRed = Color.red(color).toFloat()
    val mGreen = Color.green(color).toFloat()
    val mBlue = Color.blue(color).toFloat()
    val src = floatArrayOf(0f, 0f, 0f, 0f, mRed, 0f, 0f, 0f, 0f, mGreen, 0f, 0f, 0f, 0f, mBlue, 0f, 0f, 0f, 1f, 0f)
    val matrix = ColorMatrix()
    matrix.set(src)
    mPaint.colorFilter = ColorMatrixColorFilter(src)
    canvas.drawBitmap(this, Matrix(), mPaint)
    return newBitmap
}

fun createBitmapByColor(width: Int, height: Int, @ColorInt color: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawColor(color)
    return bitmap
}

fun Bitmap.toByteArray(format: Bitmap.CompressFormat, quality: Int): ByteArray {
    val outputStream = ByteArrayOutputStream()
    this.compress(format, quality, outputStream)
    return outputStream.toByteArray()
}

fun Bitmap.writeToFile(file: File, format: Bitmap.CompressFormat, quality: Int) {
    try {
        BufferedOutputStream(FileOutputStream(file)).use { this.compress(format, quality, it) }
    } catch (e: IOException) {
        file.delete()
        throw e
    }
}

fun File.readBitmap(options: BitmapFactory.Options? = null): Bitmap = BitmapFactory.decodeFile(this.path, options)

fun readBitmap(file: File): Bitmap? {
    return BitmapFactory.decodeFile(file.path)
}

fun InputStream.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? {
    return BitmapFactory.decodeStream(this, outPadding, options)
}

fun InputStream.readBitmap(): Bitmap? {
    return BitmapFactory.decodeStream(this)
}

fun ByteArray.readBitmap(offset: Int, length: Int, options: BitmapFactory.Options?): Bitmap? {
    return BitmapFactory.decodeByteArray(this, offset, length, options)
}

fun ByteArray.readBitmap(offset: Int, length: Int): Bitmap? {
    return BitmapFactory.decodeByteArray(this, offset, length)
}

fun FileDescriptor.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? {
    return BitmapFactory.decodeFileDescriptor(this, outPadding, options)
}

fun FileDescriptor.readBitmap(): Bitmap? {
    return BitmapFactory.decodeFileDescriptor(this)
}

fun Resources.readBitmap(resId: Int, options: BitmapFactory.Options?): Bitmap? {
    return BitmapFactory.decodeResource(this, resId, options)
}

fun Resources.readBitmap(resId: Int): Bitmap? {
    return BitmapFactory.decodeResource(this, resId)
}

fun Resources.readBitmap(value: TypedValue?, `is`: InputStream?, pad: Rect?, options: BitmapFactory.Options?): Bitmap? {
    return BitmapFactory.decodeResourceStream(this, value, `is`, pad, options)
}

/**
 * Change the color of the bitmap
 *
 * @receiver Source bitmap
 * @param resources setting initial target density based on the display metrics of the resources.
 */
fun Bitmap.toDrawableByColor(@ColorInt color: Int, resources: Resources? = null): BitmapDrawable {
    val bitmapDrawable = BitmapDrawable(resources, this)
    if (resources == null) {
        bitmapDrawable.setTargetDensity(this.density)
    }
    bitmapDrawable.colorFilter = color.createMatrixColorFilter()
    return bitmapDrawable
}
