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

package me.panpf.androidxkt.test.graphics

import android.graphics.Rect
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.getImageMimeSubType
import me.panpf.androidxkt.graphics.getImageMimeType
import me.panpf.androidxkt.os.storage.getAppExternalCacheDirs
import me.panpf.androidxkt.os.storage.getFileIn
import me.panpf.androidxkt.test.R
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImagexTest {

    @Test
    fun testMimeType() {
        val context = InstrumentationRegistry.getContext()

        val operaFile = context.getAppExternalCacheDirs().getFileIn("ic_opera.png", 0).requireNotNull()
        val rectFile = context.getAppExternalCacheDirs().getFileIn("rect.jpeg", 0).requireNotNull()

        try {
            operaFile.outputStream().use { out ->
                context.resources.openRawResource(R.drawable.ic_opera).use { input ->
                    input.copyTo(out)
                }
            }
            rectFile.outputStream().use { out ->
                context.resources.openRawResource(R.drawable.rect).use { input ->
                    input.copyTo(out)
                }
            }

            Assert.assertEquals(operaFile.getImageMimeType(), "image/png")
            Assert.assertEquals(rectFile.getImageMimeType(), "image/jpeg")

            Assert.assertEquals(context.resources.openRawResource(R.drawable.ic_opera).use { it.getImageMimeType() }, "image/png")
            Assert.assertEquals(context.resources.openRawResource(R.drawable.rect).use { it.getImageMimeType() }, "image/jpeg")

            val operaBytes = operaFile.readBytes()
            val rectBytes = rectFile.readBytes()

            Assert.assertEquals(operaBytes.getImageMimeType(0, operaBytes.size), "image/png")
            Assert.assertEquals(rectBytes.getImageMimeType(0, rectBytes.size), "image/jpeg")

            Assert.assertEquals(operaBytes.getImageMimeType(), "image/png")
            Assert.assertEquals(rectBytes.getImageMimeType(), "image/jpeg")

            operaFile.inputStream().use { Assert.assertEquals(it.fd.getImageMimeType(), "image/png") }
            rectFile.inputStream().use { Assert.assertEquals(it.fd.getImageMimeType(), "image/jpeg") }

            Assert.assertEquals(context.resources.getImageMimeType(R.drawable.ic_opera), "image/png")
            Assert.assertEquals(context.resources.getImageMimeType(R.drawable.rect), "image/jpeg")

            Assert.assertEquals(operaFile.inputStream().use { context.resources.getImageMimeType(null, it, Rect()) }, "image/png")
            Assert.assertEquals(rectFile.inputStream().use { context.resources.getImageMimeType(null, it, Rect()) }, "image/jpeg")
        } finally {
            operaFile.deleteRecursively()
            rectFile.deleteRecursively()
        }
    }

    @Test
    fun testMimeSubType() {
        val context = InstrumentationRegistry.getContext()

        val operaFile = context.getAppExternalCacheDirs().getFileIn("ic_opera.png", 0).requireNotNull()
        val rectFile = context.getAppExternalCacheDirs().getFileIn("rect.jpeg", 0).requireNotNull()

        try {
            operaFile.outputStream().use { out ->
                context.resources.openRawResource(R.drawable.ic_opera).use { input ->
                    input.copyTo(out)
                }
            }
            rectFile.outputStream().use { out ->
                context.resources.openRawResource(R.drawable.rect).use { input ->
                    input.copyTo(out)
                }
            }

            Assert.assertEquals(operaFile.getImageMimeSubType(), "png")
            Assert.assertEquals(rectFile.getImageMimeSubType(), "jpeg")

            Assert.assertEquals(context.resources.openRawResource(R.drawable.ic_opera).use { it.getImageMimeSubType() }, "png")
            Assert.assertEquals(context.resources.openRawResource(R.drawable.rect).use { it.getImageMimeSubType() }, "jpeg")

            val operaBytes = operaFile.readBytes()
            val rectBytes = rectFile.readBytes()

            Assert.assertEquals(operaBytes.getImageMimeSubType(0, operaBytes.size), "png")
            Assert.assertEquals(rectBytes.getImageMimeSubType(0, rectBytes.size), "jpeg")

            Assert.assertEquals(operaBytes.getImageMimeSubType(), "png")
            Assert.assertEquals(rectBytes.getImageMimeSubType(), "jpeg")

            operaFile.inputStream().use { Assert.assertEquals(it.fd.getImageMimeSubType(), "png") }
            rectFile.inputStream().use { Assert.assertEquals(it.fd.getImageMimeSubType(), "jpeg") }

            Assert.assertEquals(context.resources.getImageMimeSubType(R.drawable.ic_opera), "png")
            Assert.assertEquals(context.resources.getImageMimeSubType(R.drawable.rect), "jpeg")

            Assert.assertEquals(operaFile.inputStream().use { context.resources.getImageMimeSubType(null, it, Rect()) }, "png")
            Assert.assertEquals(rectFile.inputStream().use { context.resources.getImageMimeSubType(null, it, Rect()) }, "jpeg")
        } finally {
            operaFile.deleteRecursively()
            rectFile.deleteRecursively()
        }
    }
}