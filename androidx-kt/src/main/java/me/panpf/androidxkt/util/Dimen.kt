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

import android.content.Context
import android.support.annotation.IntDef
import android.util.TypedValue
import android.view.View

/*
 * 尺寸相关的扩展方法或属性
 */

/* ************************************* Context ***************************************** */

fun Context.dp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.density + 0.5).toInt()

fun Context.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun Context.px2dp(px: Float): Float =
        px / this.resources.displayMetrics.density + 0.5f

fun Context.px2dp(px: Int): Float = px2dp(px.toFloat())


fun Context.sp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt()

fun Context.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun Context.px2sp(px: Float): Float =
        px / this.resources.displayMetrics.scaledDensity + 0.5f

fun Context.px2sp(px: Int): Float = px2sp(px.toFloat())


fun Context.unit2px(@DimenUnit unit: Int, value: Float): Float =
        TypedValue.applyDimension(unit, value, this.resources.displayMetrics)

fun Context.unit2px(@DimenUnit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* SupportFragment ***************************************** */


fun android.support.v4.app.Fragment.dp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.density + 0.5).toInt()

fun android.support.v4.app.Fragment.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun android.support.v4.app.Fragment.px2dp(px: Float): Float =
        px / this.resources.displayMetrics.density + 0.5f

fun android.support.v4.app.Fragment.px2dp(px: Int): Float = px2dp(px.toFloat())


fun android.support.v4.app.Fragment.sp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt()

fun android.support.v4.app.Fragment.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun android.support.v4.app.Fragment.px2sp(px: Float): Float =
        px / this.resources.displayMetrics.scaledDensity + 0.5f

fun android.support.v4.app.Fragment.px2sp(px: Int): Float = px2sp(px.toFloat())


fun android.support.v4.app.Fragment.unit2px(@DimenUnit unit: Int, value: Float): Float =
        TypedValue.applyDimension(unit, value, this.resources.displayMetrics)

fun android.support.v4.app.Fragment.unit2px(@DimenUnit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* OriginFragment ***************************************** */


fun android.app.Fragment.dp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.density + 0.5).toInt()

fun android.app.Fragment.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun android.app.Fragment.px2dp(px: Float): Float =
        px / this.resources.displayMetrics.density + 0.5f

fun android.app.Fragment.px2dp(px: Int): Float = px2dp(px.toFloat())


fun android.app.Fragment.sp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt()

fun android.app.Fragment.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun android.app.Fragment.px2sp(px: Float): Float =
        px / this.resources.displayMetrics.scaledDensity + 0.5f

fun android.app.Fragment.px2sp(px: Int): Float = px2sp(px.toFloat())


fun android.app.Fragment.unit2px(@DimenUnit unit: Int, value: Float): Float =
        TypedValue.applyDimension(unit, value, this.resources.displayMetrics)

fun android.app.Fragment.unit2px(@DimenUnit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* View ***************************************** */


fun View.dp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.density + 0.5).toInt()

fun View.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun View.px2dp(px: Float): Float =
        px / this.resources.displayMetrics.density + 0.5f

fun View.px2dp(px: Int): Float = px2dp(px.toFloat())


fun View.sp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt()

fun View.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun View.px2sp(px: Float): Float =
        px / this.resources.displayMetrics.scaledDensity + 0.5f

fun View.px2sp(px: Int): Float = px2sp(px.toFloat())


fun View.unit2px(@DimenUnit unit: Int, value: Float): Float =
        TypedValue.applyDimension(unit, value, this.resources.displayMetrics)

fun View.unit2px(@DimenUnit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())

@IntDef(TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX,
        TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class DimenUnit