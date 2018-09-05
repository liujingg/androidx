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

package me.panpf.androidxkt.content.res

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/*
 * Asset resource-related extension methods or properties
 */

@Throws(IOException::class)
fun Context.openInputFromAsset(fileName: String): InputStream {
    return this.assets.open(fileName)
}

@Throws(IOException::class)
fun Context.readBytesFromAsset(fileName: String): ByteArray {
    return this.assets.open(fileName).use { it.readBytes() }
}

@Throws(IOException::class)
fun Context.readTextFromAsset(fileName: String, charset: Charset): String {
    return this.assets.open(fileName).bufferedReader(charset).use { it.readText() }
}

@Throws(IOException::class)
fun Context.readTextFromAsset(fileName: String): String {
    return this.assets.open(fileName).bufferedReader().use { it.readText() }
}

@Throws(IOException::class)
fun Context.readLinesFromAsset(fileName: String, charset: Charset): List<String> {
    return this.assets.open(fileName).bufferedReader(charset).use { it.readLines() }
}

@Throws(IOException::class)
fun Context.readLinesFromAsset(fileName: String): List<String> {
    return this.assets.open(fileName).bufferedReader().use { it.readLines() }
}

@Throws(IOException::class)
fun Context.readBitmapFromAsset(fileName: String, outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? {
    return this.assets.open(fileName).use { BitmapFactory.decodeStream(it, outPadding, options) }
}

@Throws(IOException::class)
fun Context.readBitmapFromAsset(fileName: String): Bitmap? {
    return this.assets.open(fileName).use { BitmapFactory.decodeStream(it) }
}