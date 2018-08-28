@file:Suppress("unused")

package me.panpf.androidxkt.graphics

import android.graphics.*
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
