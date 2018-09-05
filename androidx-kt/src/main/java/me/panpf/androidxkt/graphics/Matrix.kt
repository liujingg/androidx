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

package me.panpf.androidxkt.graphics

import android.graphics.Matrix

private val matrixValues = FloatArray(9)

/**
 * Helper method that 'unpacks' a Matrix and returns the required value
 *
 * @param whichValue - Which value from Matrix.M* to return
 * @return float - returned value
 */
fun Matrix.getValue(whichValue: Int): Float {
    synchronized(matrixValues) {
        this.getValues(matrixValues)
        return matrixValues[whichValue]
    }
}

/**
 * Get scale ratio
 */
fun Matrix.getScale(): Float {
    synchronized(matrixValues) {
        this.getValues(matrixValues)
        val scaleX = matrixValues[Matrix.MSCALE_X]
        val scaleY = matrixValues[Matrix.MSKEW_Y]
        return Math.sqrt((Math.pow(scaleX.toDouble(), 2.0).toFloat() + Math.pow(scaleY.toDouble(), 2.0).toFloat()).toDouble()).toFloat()
    }
}
