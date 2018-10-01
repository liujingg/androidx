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

package me.panpf.androidxkt.content

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.net.Uri
import me.panpf.androidx.content.Contentx

import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

@Throws(IOException::class)
inline fun Context.openInputFromContent(uri: Uri): InputStream? = Contentx.openInput(this, uri)

@Throws(IOException::class)
inline fun Context.readBytesFromContent(uri: Uri): ByteArray? = Contentx.readBytes(this, uri)

@Throws(IOException::class)
inline fun Context.readTextFromContent(uri: Uri, charset: Charset): String? = Contentx.readText(this, uri, charset)

@Throws(IOException::class)
inline fun Context.readTextFromContent(uri: Uri): String? = Contentx.readText(this, uri)

@Throws(IOException::class)
inline fun Context.readLinesFromContent(uri: Uri, charset: Charset): List<String>? = Contentx.readLines(this, uri, charset)

@Throws(IOException::class)
inline fun Context.readLinesFromContent(uri: Uri): List<String>? = Contentx.readLines(this, uri)

@Throws(FileNotFoundException::class)
inline fun Context.readBitmapFromContent(uri: Uri, outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Contentx.readBitmap(this, uri, outPadding, options)

@Throws(FileNotFoundException::class)
inline fun Context.readBitmapFromContent(uri: Uri): Bitmap? = Contentx.readBitmap(this, uri)
