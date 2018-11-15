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

package me.panpf.androidxkt.test.content

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidx.graphics.Bitmapx
import me.panpf.androidxkt.content.res.*
import me.panpf.javax.collections.Collectionx
import me.panpf.javaxkt.io.closeQuietly
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

@RunWith(AndroidJUnit4::class)
class AssetxTest {

    @Test
    fun testOpenInput() {
        val context = InstrumentationRegistry.getContext()

        var inputStream: InputStream? = null
        try {
            inputStream = context.openInputFromAsset("test.txt")
            Assert.assertNotNull(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Assert.fail()
        } finally {
            inputStream.closeQuietly()
        }

        try {
            context.openInputFromAsset("test_no_file.txt")
            Assert.fail()
        } catch (ignored: IOException) {
        }
    }

    @Test
    @Throws(IOException::class)
    fun testReadBytes() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals(TEST_TXT_CONTENT, String(context.readBytesFromAsset("test.txt")))
    }

    @Test
    @Throws(IOException::class)
    fun testReadTest() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals(TEST_TXT_CONTENT, context.readTextFromAsset("test.txt"))
        Assert.assertEquals(TEST_TXT_CONTENT, context.readTextFromAsset("test.txt", Charset.defaultCharset()))
    }

    @Test
    @Throws(IOException::class)
    fun testReadLines() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals(TEST_TXT_CONTENT.replace("\n", ","), Collectionx.joinToString(context.readLinesFromAsset("test.txt"), ","))
        Assert.assertEquals(TEST_TXT_CONTENT.replace("\n", ","), Collectionx.joinToString(context.readLinesFromAsset("test.txt", Charset.defaultCharset()), ","))
    }

    @Test
    @Throws(IOException::class)
    fun testReadBitmap() {
        val context = InstrumentationRegistry.getContext()

        var bitmap: Bitmap? = null
        try {
            bitmap = context.readBitmapFromAsset("test.png")
            Assert.assertNotNull(bitmap)
            Assert.assertFalse(bitmap!!.isRecycled)
            Assert.assertEquals(128, bitmap.width.toLong())
            Assert.assertEquals(128, bitmap.height.toLong())
        } finally {
            bitmap?.recycle()
        }

        var bitmap2: Bitmap? = null
        try {
            val options = BitmapFactory.Options()
            options.inSampleSize = 2
            val outPadding = Rect(0, 0, 0, 0)
            bitmap2 = context.readBitmapFromAsset("test.png", outPadding, options)
            Assert.assertNotNull(bitmap2)
            Assert.assertFalse(bitmap2!!.isRecycled)
            Assert.assertEquals(Bitmapx.calculateSamplingSize(128, options.inSampleSize).toLong(), bitmap2.width.toLong())
            Assert.assertEquals(Bitmapx.calculateSamplingSize(128, options.inSampleSize).toLong(), bitmap2.height.toLong())
            Assert.assertEquals(Rect(-1, -1, -1, -1).toShortString(), outPadding.toShortString())
        } finally {
            bitmap2?.recycle()
        }
    }

    companion object {
        private const val TEST_TXT_CONTENT = "1\n2\n3\n4\n5\n6\n7\n8\n9"
    }
}
