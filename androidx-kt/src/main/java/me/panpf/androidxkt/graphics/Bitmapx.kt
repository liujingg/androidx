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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.graphics

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.ColorInt
import android.util.TypedValue
import me.panpf.androidx.graphics.Bitmapx
import java.io.File
import java.io.FileDescriptor
import java.io.InputStream

/*
 * Bitmap 相关的扩展方法或属性
 */

inline fun Bitmap.centerCrop(outWidth: Int, outHeight: Int, outConfig: Bitmap.Config): Bitmap = Bitmapx.centerCrop(this, outWidth, outHeight, outConfig)

inline fun Bitmap.tint(color: Int): Bitmap = Bitmapx.tint(this, color)

inline fun createBitmapByColor(width: Int, height: Int, @ColorInt color: Int): Bitmap = Bitmapx.createByColor(width, height, color)

inline fun Bitmap.toByteArray(format: Bitmap.CompressFormat, quality: Int): ByteArray = Bitmapx.toByteArray(this, format, quality)

inline fun Bitmap.writeToFile(file: File, format: Bitmap.CompressFormat, quality: Int) = Bitmapx.writeToFile(this, file, format, quality)

inline fun File.readBitmap(options: BitmapFactory.Options? = null): Bitmap? = Bitmapx.readBitmap(this, options)

inline fun File.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

inline fun InputStream.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, outPadding, options)

inline fun InputStream.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

inline fun ByteArray.readBitmap(offset: Int, length: Int, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, offset, length, options)

inline fun ByteArray.readBitmap(offset: Int, length: Int): Bitmap? = Bitmapx.readBitmap(this, offset, length)

inline fun FileDescriptor.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, outPadding, options)

inline fun FileDescriptor.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

inline fun Resources.readBitmap(resId: Int, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, resId, options)

inline fun Resources.readBitmap(resId: Int): Bitmap? = Bitmapx.readBitmap(this, resId)

inline fun Resources.readBitmap(value: TypedValue?, inputStream: InputStream?, pad: Rect?, options: BitmapFactory.Options?): Bitmap? =
        Bitmapx.readBitmap(this, value, inputStream, pad, options)

/**
 * Change the color of the bitmap
 *
 * @receiver Source bitmap
 * @param resources setting initial target density based on the display metrics of the resources.
 */
inline fun Bitmap.toDrawableByColor(@ColorInt color: Int, resources: Resources? = null): BitmapDrawable = Bitmapx.toDrawableByColor(this, color, resources)
