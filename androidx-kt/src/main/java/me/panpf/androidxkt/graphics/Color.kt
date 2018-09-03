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

package me.panpf.androidxkt.graphics

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.IntRange

/**
 * White
 */
const val WHITE = -0x1

/**
 * White - translucent
 */
const val WHITE_TRANSLUCENT = -0x7f000001

/**
 * Black
 */
const val BLACK = -0x1000000

/**
 * Black - translucent
 */
const val BLACK_TRANSLUCENT = -0x80000000

/**
 * 透明
 */
const val TRANSPARENT = 0x00000000

/**
 * 红色
 */
const val RED = -0x10000

/**
 * 红色 - 半透明
 */
const val RED_TRANSLUCENT = -0x7f010000

/**
 * 红色 - 深的
 */
const val RED_DARK = -0x750000

/**
 * 红色 - 深的 - 半透明
 */
const val RED_DARK_TRANSLUCENT = -0x7f750000

/**
 * 绿色
 */
const val GREEN = -0xff0100

/**
 * 绿色 - 半透明
 */
const val GREEN_TRANSLUCENT = -0x7fff0100

/**
 * 绿色 - 深的
 */
const val GREEN_DARK = -0xffcd00

/**
 * 绿色 - 深的 - 半透明
 */
const val GREEN_DARK_TRANSLUCENT = -0x7fffcd00

/**
 * 绿色 - 浅的
 */
const val GREEN_LIGHT = -0x330034

/**
 * 绿色 - 浅的 - 半透明
 */
const val GREEN_LIGHT_TRANSLUCENT = -0x7f330034

/**
 * 蓝色
 */
const val BLUE = -0xffff01

/**
 * 蓝色 - 半透明
 */
const val BLUE_TRANSLUCENT = -0x7fffff01

/**
 * 蓝色 - 深的
 */
const val BLUE_DARK = -0xffff75

/**
 * 蓝色 - 深的 - 半透明
 */
const val BLUE_DARK_TRANSLUCENT = -0x7fffff75

/**
 * 蓝色 - 浅的
 */
const val BLUE_LIGHT = -0xc95a1d

/**
 * 蓝色 - 浅的 - 半透明
 */
const val BLUE_LIGHT_TRANSLUCENT = -0x7fc95a1d

/**
 * 天蓝
 */
const val SKY_BLUE = -0x783115

/**
 * 天蓝 - 半透明
 */
const val SKY_BLUE_TRANSLUCENT = -0x7f783115

/**
 * 天蓝 - 深的
 */
const val SKY_BLUE_DARK = -0xff4001

/**
 * 天蓝 - 深的 - 半透明
 */
const val SKY_BLUE_DARK_TRANSLUCENT = -0x7fff4001

/**
 * 天蓝 - 浅的
 */
const val SKY_BLUE_LIGHT = -0x783106

/**
 * 天蓝 - 浅的 - 半透明
 */
const val SKY_BLUE_LIGHT_TRANSLUCENT = -0x7f783106

/**
 * 灰色
 */
const val GRAY = -0x69696a

/**
 * 灰色 - 半透明
 */
const val GRAY_TRANSLUCENT = -0x7f69696a

/**
 * 灰色 - 深的
 */
const val GRAY_DARK = -0x565657

/**
 * 灰色 - 深的 - 半透明
 */
const val GRAY_DARK_TRANSLUCENT = -0x7f565657

/**
 * 灰色 - 暗的
 */
const val GRAY_DIM = -0x969697

/**
 * 灰色 - 暗的 - 半透明
 */
const val GRAY_DIM_TRANSLUCENT = -0x7f969697

/**
 * 灰色 - 浅的
 */
const val GRAY_LIGHT = -0x2c2c2d

/**
 * 灰色 - 浅的 - 半透明
 */
const val GRAY_LIGHT_TRANSLUCENT = -0x7f2c2c2d

/**
 * 橙色
 */
const val ORANGE = -0x5b00

/**
 * 橙色 - 半透明
 */
const val ORANGE_TRANSLUCENT = -0x7f005b00

/**
 * 橙色 - 深的
 */
const val ORANGE_DARK = -0x7800

/**
 * 橙色 - 深的 - 半透明
 */
const val ORANGE_DARK_TRANSLUCENT = -0x7f007800

/**
 * 橙色 - 浅的
 */
const val ORANGE_LIGHT = -0x44cd

/**
 * 橙色 - 浅的 - 半透明
 */
const val ORANGE_LIGHT_TRANSLUCENT = -0x7f0044cd

/**
 * 金色
 */
const val GOLD = -0x2900

/**
 * 金色 - 半透明
 */
const val GOLD_TRANSLUCENT = -0x7f002900

/**
 * 粉色
 */
const val PINK = -0x3f35

/**
 * 粉色 - 半透明
 */
const val PINK_TRANSLUCENT = -0x7f003f35

/**
 * 紫红色
 */
const val FUCHSIA = -0xff01

/**
 * 紫红色 - 半透明
 */
const val FUCHSIA_TRANSLUCENT = -0x7f00ff01

/**
 * 灰白色
 */
const val GRAY_WHITE = -0xd0d0e

/**
 * 灰白色 - 半透明
 */
const val GRAY_WHITE_TRANSLUCENT = -0x7f0d0d0e

/**
 * 紫色
 */
const val PURPLE = -0x7fff80

/**
 * 紫色 - 半透明
 */
const val PURPLE_TRANSLUCENT = -0x7f7fff80

/**
 * 青色
 */
const val CYAN = -0xff0001

/**
 * 青色 - 半透明
 */
const val CYAN_TRANSLUCENT = -0x7fff0001

/**
 * 青色 - 深的
 */
const val CYAN_DARK = -0xff7475

/**
 * 青色 - 深的 - 半透明
 */
const val CYAN_DARK_TRANSLUCENT = -0x7fff7475

/**
 * 黄色
 */
const val YELLOW = -0x100

/**
 * 黄色 - 半透明
 */
const val YELLOW_TRANSLUCENT = -0x7f000100

/**
 * 黄色 - 浅的
 */
const val YELLOW_LIGHT = -0x20

/**
 * 黄色 - 浅的 - 半透明
 */
const val YELLOW_LIGHT_TRANSLUCENT = -0x7f000020

/**
 * 巧克力色
 */
const val CHOCOLATE = -0x2d96e2

/**
 * 巧克力色 - 半透明
 */
const val CHOCOLATE_TRANSLUCENT = -0x7f2d96e2

/**
 * 番茄色
 */
const val TOMATO = -0x9cb9

/**
 * 番茄色 - 半透明
 */
const val TOMATO_TRANSLUCENT = -0x7f009cb9

/**
 * 橙红色
 */
const val ORANGE_RED = -0xbb00

/**
 * 橙红色 - 半透明
 */
const val ORANGE_RED_TRANSLUCENT = -0x7f00bb00

/**
 * 银白色
 */
const val SILVER = -0x3f3f40

/**
 * 银白色 - 半透明
 */
const val SILVER_TRANSLUCENT = -0x7f3f3f40

/**
 * 高光
 */
const val HIGH_LIGHT = 0x33ffffff

/**
 * 低光
 */
const val LOW_LIGHT = 0x33000000


/**
 * Get color alpha value
 *
 * @receiver rgb color value
 */
@IntRange(from = 0, to = 255)
fun Int.getColorAlpha(): Int {
    return Color.alpha(this)
}

/**
 * Modify color alpha value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newAlpha New alpha value (0...255)
 */
@ColorInt
fun Int.setColorAlpha(@IntRange(from = 0, to = 255) newAlpha: Int): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return Color.HSVToColor(newAlpha, hsv)
}

/**
 * Multiply [addRate] based on the color original alpha value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
fun Int.addColorAlpha(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    val newAlpha = (Color.alpha(this) * addRate).toInt()
    return Color.HSVToColor(newAlpha, hsv)
}

/**
 * Get color HSV hue value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 360.0)
fun Int.getColorHSVHue(): Float {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return hsv[0]
}

/**
 * Modify color HSV hue value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newHue New HSV hue value (0...360)
 */
@ColorInt
fun Int.setColorHSVHue(@FloatRange(from = 0.0, to = 360.0) newHue: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[0] = newHue
    return Color.HSVToColor(hsv)
}

/**
 * Get color HSV saturation value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 1.0)
fun Int.getColorHSVSaturation(): Float {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return hsv[1]
}

/**
 * Modify color HSV saturation value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newSaturation New HSV saturation value (0...1)
 */
@ColorInt
fun Int.setColorHSVSaturation(@FloatRange(from = 0.0, to = 1.0) newSaturation: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[1] = newSaturation
    return Color.HSVToColor(hsv)
}

/**
 * Multiply [addRate] based on the color original HSV saturation value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
fun Int.addColorHSVSaturation(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[1] *= addRate
    return Color.HSVToColor(hsv)
}

/**
 * Get color HSV 'value' value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 1.0)
fun Int.getColorHSVValue(): Float {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return hsv[2]
}

/**
 * Modify color HSV 'value' value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newValue New HSV 'value' value (0...1)
 */
@ColorInt
fun Int.setColorHSVValue(@FloatRange(from = 0.0, to = 1.0) newValue: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] = newValue
    return Color.HSVToColor(hsv)
}

/**
 * Multiply [addRate] based on the color original HSV 'value' value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
fun Int.addColorHSVValue(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= addRate
    return Color.HSVToColor(hsv)
}

/**
 * Returns true if the color is a bright color, used to prevent the background color from being close to the text color, causing the text to be invisible
 *
 * @receiver rgb color value
 */
fun Int.isLightColor(): Boolean {
    val R = Color.red(this) / 255f
    val G = Color.green(this) / 255f
    val B = Color.blue(this) / 255f
    val components = floatArrayOf(R, G, B)

    for (i in components.indices) {
        if (components[i] <= 0.03928f) {
            components[i] = components[i] / 12.92f
        } else {
            components[i] = Math.pow(((components[i] + 0.055f) / 1.055f).toDouble(), 2.4).toFloat()
        }
    }

    val L = (0.2126f * components[0] + 0.7152f * components[1] + 0.0722f * components[2]).toDouble()
    return L > 0.179f
}

/**
 * Create a ColorMatrixColorFilter that can be used to change the color of the Drawable
 *
 * @receiver rgb color value (alpha value is useless)
 */
fun Int.makeMatrixColorFilter(): ColorMatrixColorFilter {
    val mRed = Color.red(this).toFloat()
    val mGreen = Color.green(this).toFloat()
    val mBlue = Color.blue(this).toFloat()
    val src = floatArrayOf(0f, 0f, 0f, 0f, mRed, 0f, 0f, 0f, 0f, mGreen, 0f, 0f, 0f, 0f, mBlue, 0f, 0f, 0f, 1f, 0f)
    val matrix = ColorMatrix()
    matrix.set(src)
    return ColorMatrixColorFilter(src)
}
