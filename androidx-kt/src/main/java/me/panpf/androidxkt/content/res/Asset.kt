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

package me.panpf.androidxkt.content.res

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import me.panpf.androidx.content.res.Assetx
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/*
 * Asset resource-related extension methods or properties
 */

@Throws(IOException::class)
inline fun Context.openInputFromAsset(fileName: String): InputStream = Assetx.openInput(this, fileName)

@Throws(IOException::class)
inline fun Context.readBytesFromAsset(fileName: String): ByteArray = Assetx.readBytes(this, fileName)

@Throws(IOException::class)
inline fun Context.readTextFromAsset(fileName: String, charset: Charset): String = Assetx.readText(this, fileName, charset)

@Throws(IOException::class)
inline fun Context.readTextFromAsset(fileName: String): String = Assetx.readText(this, fileName)

@Throws(IOException::class)
inline fun Context.readLinesFromAsset(fileName: String, charset: Charset): List<String> = Assetx.readLines(this, fileName, charset)

@Throws(IOException::class)
inline fun Context.readLinesFromAsset(fileName: String): List<String> = Assetx.readLines(this, fileName)

@Throws(IOException::class)
inline fun Context.readBitmapFromAsset(fileName: String, outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Assetx.readBitmap(this, fileName, outPadding, options)

@Throws(IOException::class)
inline fun Context.readBitmapFromAsset(fileName: String): Bitmap? = Assetx.readBitmap(this, fileName)