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

package me.panpf.androidxkt.graphics.drawable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

/*
 * Drawable 相关的扩展方法或属性
 */

fun Drawable.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888, reuseBitmap: Bitmap? = null): Bitmap {
    setBounds(0, 0, intrinsicWidth, intrinsicHeight)

    val bitmap = reuseBitmap ?: Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}