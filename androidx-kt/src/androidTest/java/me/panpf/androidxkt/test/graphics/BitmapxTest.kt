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

import android.graphics.Color
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.createBitmapByColor
import me.panpf.androidxkt.graphics.drawable.toBitmapWithIntrinsicSize
import me.panpf.androidxkt.graphics.toDrawableByColor
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapxTest {

    @Test
    fun testModifyColorBitmap() {
        val sourceBitmap = createBitmapByColor(100, 100, Color.parseColor("#FF0000"))

        val drawable = sourceBitmap.toDrawableByColor(Color.parseColor("#0000FF"))
        val bitmap = drawable.toBitmapWithIntrinsicSize()

        sourceBitmap.recycle()
        bitmap.recycle()
    }
}
