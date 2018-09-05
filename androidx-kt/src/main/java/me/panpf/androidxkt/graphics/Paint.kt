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

import android.graphics.Paint
import android.graphics.Rect

/**
 * 获取给定文字的宽度
 *
 * @param textSize 文字大小
 * @return 给定文字的宽度
 */
fun String.getTextWidth(textSize: Float): Float {
    val paint = Paint()
    paint.textSize = textSize
    return paint.measureText(this)
}

/**
 * 获取当给定的文字使用给定的画笔绘制时的宽度
 *
 * @param paint 指定的画笔
 * @return 当给定的文字使用给定的画笔绘制时的宽度
 */
fun String.getTextWidth(paint: Paint): Float {
    return paint.measureText(this)
}

/**
 * 获取给定尺寸的文字的高度
 *
 * @param textSize 给定尺寸
 * @return 文字的高度
 */
fun getTextHeight(textSize: Float): Float {
    val paint = Paint()
    paint.textSize = textSize
    val fm = paint.fontMetrics
    return fm.descent - fm.ascent
}

/**
 * 获取给定画笔的文字高度
 *
 * @return 文字的高度
 */
fun Paint.getTextHeight(): Float {
    val fm = this.fontMetrics
    return fm.descent - fm.ascent
}

/**
 * 获取给定文字的宽度
 *
 * @param textSize 文字大小
 * @return 文字的宽度
 */
fun String.getTextWidthByBounds(textSize: Float): Int {
    val paint = Paint()
    val bounds = Rect()
    paint.textSize = textSize
    paint.getTextBounds(this, 0, this.length, bounds)
    return bounds.width()
}

/**
 * 获取给定文字的高度
 *
 * @param textSize 文字大小
 * @return 文字的高度
 */
fun String.getTextHeightByBounds(textSize: Float): Int {
    val paint = Paint()
    val bounds = Rect()
    paint.textSize = textSize
    paint.getTextBounds(this, 0, this.length, bounds)
    return bounds.height()
}

/**
 * 获取指定画笔的文字离顶部的基准距离
 *
 * @return 返回指定笔离文字顶部的基准距离
 */
fun Paint.getTextLeading(): Float {
    val fm = this.fontMetrics
    return fm.leading - fm.ascent
}
