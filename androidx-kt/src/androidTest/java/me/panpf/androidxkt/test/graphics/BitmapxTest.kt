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

import android.graphics.Bitmap
import android.graphics.Color
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidx.graphics.Bitmapx
import me.panpf.androidx.graphics.Colorx
import me.panpf.androidxkt.graphics.*
import me.panpf.androidxkt.graphics.drawable.toBitmapWithIntrinsicSize
import me.panpf.javax.util.Premisex
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapxTest {

    @Test
    fun testModifyColorBitmap() {
        val sourceBitmap = Bitmapx.createByColor(100, 100, Color.parseColor("#FF0000"))

        val drawable = sourceBitmap.toDrawableByColor(Color.parseColor("#0000FF"))
        val bitmap = drawable.toBitmapWithIntrinsicSize()

        sourceBitmap.recycle()
        bitmap.recycle()
    }

    @Test
    fun testCircular() {
        val context = InstrumentationRegistry.getContext()

        val rectBitmap = Premisex.requireNotNull(context.resources.readBitmap(me.panpf.androidxkt.test.R.drawable.rect))

        val circular1Bitmap = rectBitmap.circular()
        circular1Bitmap.recycle()

        val circular2Bitmap = rectBitmap.circular(Bitmap.Config.RGB_565)
        circular2Bitmap.recycle()

        val circular3Bitmap = rectBitmap.circular(rectBitmap.height / 2)
        circular3Bitmap.recycle()

        val circular4Bitmap = rectBitmap.circular(rectBitmap.height / 2, Bitmap.Config.RGB_565)
        circular4Bitmap.recycle()

        val circular5Bitmap = rectBitmap.circularTo(Bitmap.createBitmap(rectBitmap.height / 2, rectBitmap.height / 2, Bitmap.Config.RGB_565))
        circular5Bitmap.recycle()

        rectBitmap.recycle()
    }

    @Test
    fun testCenterCrop() {
        val context = InstrumentationRegistry.getContext()

        val rectBitmap = Premisex.requireNotNull(context.resources.readBitmap(me.panpf.androidxkt.test.R.drawable.rect))

        val centerCrop1Bitmap = rectBitmap.centerCrop(rectBitmap.height / 2, rectBitmap.height)
        centerCrop1Bitmap.recycle()

        val centerCrop2Bitmap = rectBitmap.centerCrop(rectBitmap.height / 2, rectBitmap.height, Bitmap.Config.RGB_565)
        centerCrop2Bitmap.recycle()

        val centerCrop3Bitmap = rectBitmap.centerCropTo(Bitmap.createBitmap(rectBitmap.height / 2, rectBitmap.height, Bitmap.Config.RGB_565))
        centerCrop3Bitmap.recycle()

        rectBitmap.recycle()
    }

    @Test
    fun testTint() {
        val context = InstrumentationRegistry.getContext()

        val operaBitmap = Premisex.requireNotNull(context.resources.readBitmap(me.panpf.androidxkt.test.R.drawable.ic_opera))

        val centerCrop1Bitmap = operaBitmap.tint(Colorx.YELLOW)
        centerCrop1Bitmap.recycle()

        operaBitmap.recycle()
    }
}
