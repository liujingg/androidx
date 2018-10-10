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

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.getImageMimeSubType
import me.panpf.androidxkt.graphics.getImageMimeType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImagexTest {

    @Test
    fun testMimeType() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals(context.resources.getImageMimeType(me.panpf.androidxkt.test.R.drawable.ic_opera), "image/png")
        Assert.assertEquals(context.resources.getImageMimeType(me.panpf.androidxkt.test.R.drawable.rect), "image/jpeg")
    }

    @Test
    fun testMimeSubType() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals(context.resources.getImageMimeSubType(me.panpf.androidxkt.test.R.drawable.ic_opera), "png")
        Assert.assertEquals(context.resources.getImageMimeSubType(me.panpf.androidxkt.test.R.drawable.rect), "jpeg")
    }
}