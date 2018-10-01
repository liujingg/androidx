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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.util

import android.graphics.Bitmap
import android.text.SpannableStringBuilder
import me.panpf.androidx.util.Textx


/**
 * 获取一张文字位图
 *
 * @param text       文字
 * @param textColor  文字颜色
 * @param textSize   文字大小
 * @param leftBitmap 可以在文字的左边放置一张图片
 */
inline fun textToBitmap(text: String, textColor: Int, textSize: Float, leftBitmap: Bitmap? = null): Bitmap =
        Textx.textToBitmap(text, textColor, textSize, leftBitmap)

/**
 * 使用 Html 的方式给字符串添加颜色标记
 */
inline fun String.toHtmlColorFlag(color: String): String = Textx.toHtmlColorFlag(this, color)

/**
 * 使用 Html 的方式给字符串添加红色标记
 */
inline fun String.toHtmlRedFlag(): String = Textx.toHtmlRedFlag(this)

/**
 * 使用 Html 的方式将字符串中所有关键字标记颜色
 *
 * @receiver 字符串
 * @param keyword      关键字
 * @param color        html 支持的颜色
 */
inline fun String.keywordMadeColorByHtml(keyword: String, color: String): String = Textx.keywordMadeColorByHtml(this, keyword, color)

/**
 * 使用 Html 的方式将字符串中所有关键字标记成红色
 *
 * @receiver 字符串
 * @param keyword      关键字
 */
inline fun String.keywordMadeRedByHtml(keyword: String): String = Textx.keywordMadeRedByHtml(this, keyword)

/**
 * 使用 Spannable 的方式将字符串中所有的关键字标记颜色
 *
 * @receiver 字符串
 * @param keyword      关键字
 * @param color        颜色
 */
inline fun String.keywordMadeColorBySpannable(keyword: String, color: Int): SpannableStringBuilder =
        Textx.keywordMadeColorBySpannable(this, keyword, color)

/**
 * 使用 Spannable 的方式将字符串中所有的关键字标记成红色
 *
 * @receiver 字符串
 * @param keyword      关键字
 */
inline fun String.keywordMadeRedBySpannable(keyword: String): SpannableStringBuilder = Textx.keywordMadeRedBySpannable(this, keyword)