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

package me.panpf.androidxkt.content

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.net.Uri

import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

@Throws(IOException::class)
fun Context.openInputFromContent(uri: Uri): InputStream? {
    return this.contentResolver.openInputStream(uri)
}

@Throws(IOException::class)
fun Context.readBytesFromContent(uri: Uri): ByteArray? {
    return this.contentResolver.openInputStream(uri)?.use { it.readBytes() }
}

@Throws(IOException::class)
fun Context.readTextFromContent(uri: Uri, charset: Charset): String? {
    return this.contentResolver.openInputStream(uri)?.bufferedReader(charset)?.use { it.readText() }
}

@Throws(IOException::class)
fun Context.readTextFromContent(uri: Uri): String? {
    return this.contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readText() }
}

@Throws(IOException::class)
fun Context.readLinesFromContent(uri: Uri, charset: Charset): List<String>? {
    return this.contentResolver.openInputStream(uri)?.bufferedReader(charset)?.use { it.readLines() }
}

@Throws(IOException::class)
fun Context.readLinesFromContent(uri: Uri): List<String>? {
    return this.contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readLines() }
}

@Throws(FileNotFoundException::class)
fun Context.readBitmapFromContent(uri: Uri, outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? {
    return this.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, outPadding, options) }
}

@Throws(FileNotFoundException::class)
fun Context.readBitmapFromContent(uri: Uri): Bitmap? {
    return this.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it) }
}
