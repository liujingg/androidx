/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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

package me.panpf.androidx.graphics;

import android.graphics.Matrix;
import android.support.annotation.NonNull;

public class Matrixx {
    private static final float[] matrixValues = new float[9];

    /**
     * Helper method that 'unpacks' a Matrix and returns the required value
     *
     * @param matrix     - Matrix to unpack
     * @param whichValue - Which value from Matrix.M* to return
     * @return float - returned value
     */
    public static float getValue(@NonNull Matrix matrix, int whichValue) {
        synchronized (matrixValues) {
            matrix.getValues(matrixValues);
            return matrixValues[whichValue];
        }
    }

    /**
     * Get scale ratio
     */
    public static float getScale(@NonNull Matrix matrix) {
        synchronized (matrixValues) {
            matrix.getValues(matrixValues);
            float scaleX = matrixValues[Matrix.MSCALE_X];
            float scaleY = matrixValues[Matrix.MSKEW_Y];
            return (float) Math.sqrt((float) Math.pow(scaleX, 2) + (float) Math.pow(scaleY, 2));
        }
    }
}
