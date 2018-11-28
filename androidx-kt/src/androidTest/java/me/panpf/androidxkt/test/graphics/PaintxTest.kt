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

import android.graphics.Paint
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.*
import me.panpf.androidxkt.util.dp2px
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaintxTest {

    @Test
    fun testGetTextWidth() {
        val context = InstrumentationRegistry.getContext()
        val text = "0123456789"

        val paint = Paint()
        paint.textSize = context.dp2px(14).toFloat()

        val textWidth = paint.getTextWidth(text)
        Assert.assertTrue(textWidth > 0)
    }

    @Test
    fun testGetTextHeight() {
        val context = InstrumentationRegistry.getContext()

        val paint = Paint()
        paint.textSize = context.dp2px(14).toFloat()

        val textHeight = paint.getTextHeight()
        Assert.assertTrue(textHeight > 0)

        val textHeightCompact = paint.getTextHeightCompact()
        Assert.assertTrue(textHeightCompact > 0)
        Assert.assertTrue(textHeightCompact < textHeight)
    }

    @Test
    fun testGetTextBounds() {
        val context = InstrumentationRegistry.getContext()
        val text = "0123456789"

        val paint = Paint()
        paint.textSize = context.dp2px(14).toFloat()

        val bounds = paint.getTextBounds(text)
        Assert.assertTrue(bounds.width() > 0)
        Assert.assertTrue(bounds.height() > 0)
    }

    @Test
    fun testGetDrawTextVerticalCenterBaseLine() {
        val context = InstrumentationRegistry.getContext()

        val paint = Paint()
        paint.textSize = context.dp2px(14).toFloat()

        val baseLine = paint.getDrawTextVerticalCenterBaseLine(0f, 100f)
        Assert.assertTrue(baseLine > 0)
        Assert.assertTrue(baseLine < 100)

        val baseLineCompact = paint.getDrawTextVerticalCenterBaseLineCompact(0f, 100f)
        Assert.assertTrue(baseLineCompact > 0)
        Assert.assertTrue(baseLineCompact < 100)

        Assert.assertTrue(baseLineCompact < baseLine)
    }
}
