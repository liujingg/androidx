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

import android.graphics.Matrix
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.getScale
import me.panpf.androidxkt.graphics.getValue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatrixxTest {

    @Test
    fun testGetValue() {
        val matrix = Matrix()
        Assert.assertEquals(0f, matrix.getValue(Matrix.MTRANS_X), 0f)
        Assert.assertEquals(0f, matrix.getValue(Matrix.MTRANS_Y), 0f)
        matrix.postTranslate(100f, 210f)
        Assert.assertEquals(100f, matrix.getValue(Matrix.MTRANS_X), 0f)
        Assert.assertEquals(210f, matrix.getValue(Matrix.MTRANS_Y), 0f)

        val matrix3 = Matrix()
        Assert.assertEquals(1f, matrix3.getValue(Matrix.MSCALE_X), 0f)
        Assert.assertEquals(1f, matrix3.getValue(Matrix.MSCALE_Y), 0f)
        matrix3.postScale(3f, 4f)
        Assert.assertEquals(3f, matrix3.getValue(Matrix.MSCALE_X), 0f)
        Assert.assertEquals(4f, matrix3.getValue(Matrix.MSCALE_Y), 0f)

        val matrix4 = Matrix()
        Assert.assertEquals(0f, matrix4.getValue(Matrix.MSKEW_X), 0f)
        Assert.assertEquals(0f, matrix4.getValue(Matrix.MSKEW_Y), 0f)
        matrix4.postSkew(10f, 9f)
        Assert.assertEquals(10f, matrix4.getValue(Matrix.MSKEW_X), 0f)
        Assert.assertEquals(9f, matrix4.getValue(Matrix.MSKEW_Y), 0f)
    }

    @Test
    fun testGetScale() {
        val matrix = Matrix()
        Assert.assertEquals(1f, matrix.getScale(), 0f)
        matrix.postScale(3f, 4f)
        Assert.assertEquals(3f, matrix.getScale(), 0f)
        matrix.setScale(4f, 3f)
        Assert.assertEquals(4f, matrix.getScale(), 0f)
    }
}
