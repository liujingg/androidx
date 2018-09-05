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

package me.panpf.androidxkt.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import me.panpf.androidxkt.graphics.getTextHeight
import me.panpf.androidxkt.graphics.getTextLeading
import me.panpf.androidxkt.graphics.getTextWidth


/**
 * 获取一张文字位图
 *
 * @param text       文字
 * @param textColor  文字颜色
 * @param textSize   文字大小
 * @param leftBitmap 可以在文字的左边放置一张图片
 */
fun textToBitmap(text: String, textColor: Int, textSize: Float, leftBitmap: Bitmap? = null): Bitmap {
    val paint = Paint()
    paint.color = textColor
    paint.textSize = textSize
    paint.isAntiAlias = true
    paint.isFilterBitmap = true

    val textWidth = text.getTextWidth(paint)
    val textHeight = paint.getTextHeight()

    var newBitmapWidth = if (textWidth % 1 == 0f) textWidth.toInt() else textWidth.toInt() + 1
    var newBitmapHeight = if (textHeight % 1 == 0f) textHeight.toInt() else textHeight.toInt() + 1

    if (leftBitmap != null) {
        newBitmapWidth += leftBitmap.width
        newBitmapHeight = if (leftBitmap.height > newBitmapHeight) leftBitmap.height else newBitmapHeight
    }

    val bitmap = Bitmap.createBitmap(newBitmapWidth, newBitmapHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    if (leftBitmap != null) {
        canvas.drawBitmap(leftBitmap, 0f, ((newBitmapHeight - leftBitmap.height) / 2).toFloat(), paint)
        canvas.drawText(text, leftBitmap.width.toFloat(), (newBitmapHeight - textHeight) / 2 + paint.getTextLeading(), paint)
    } else {
        canvas.drawText(text, 0f, (newBitmapHeight - textHeight) / 2 + paint.getTextLeading(), paint)
    }
    canvas.save()

    return bitmap
}

/**
 * 使用 Html 的方式给字符串添加颜色标记
 */
fun String.toHtmlColorFlag(color: String): String {
    return "<font color=\"$color\">$this</font>"
}

/**
 * 使用 Html 的方式给字符串添加红色标记
 */
fun String.toHtmlRedFlag(): String {
    return this.toHtmlColorFlag("red")
}

/**
 * 使用 Html 的方式将字符串中所有关键字标记颜色
 *
 * @receiver 字符串
 * @param keyword      关键字
 * @param color        html 支持的颜色
 */
fun String.keywordMadeColorByHtml(keyword: String, color: String): String {
    return this.replace(keyword.toRegex(), "<font color=\"$color\">$keyword</font>")
}

/**
 * 使用 Html 的方式将字符串中所有关键字标记成红色
 *
 * @receiver 字符串
 * @param keyword      关键字
 */
fun String.keywordMadeRedByHtml(keyword: String): String {
    return this.keywordMadeColorByHtml(keyword, "red")
}

/**
 * 使用 Spannable 的方式将字符串中所有的关键字标记颜色
 *
 * @receiver 字符串
 * @param keyword      关键字
 * @param color        颜色
 */
fun String.keywordMadeColorBySpannable(keyword: String, color: Int): SpannableStringBuilder {
    val builder = SpannableStringBuilder(this)

    var fromIndex = 0
    while (fromIndex < this.length) {
        val index = this.indexOf(keyword, fromIndex)
        if (index != -1) {
            val endIndex = index + keyword.length
            builder.setSpan(ForegroundColorSpan(color), index, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            fromIndex = endIndex
        } else {
            break
        }
    }

    return builder
}

/**
 * 使用 Spannable 的方式将字符串中所有的关键字标记成红色
 *
 * @receiver 字符串
 * @param keyword      关键字
 */
fun String.keywordMadeRedBySpannable(keyword: String): SpannableStringBuilder {
    return this.keywordMadeColorBySpannable(keyword, Color.RED)
}