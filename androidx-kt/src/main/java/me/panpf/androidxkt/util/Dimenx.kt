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

import android.content.Context
import android.view.View
import me.panpf.androidx.util.Dimenx

/*
 * 尺寸相关的扩展方法或属性
 */

/* ************************************* Context ***************************************** */

inline fun Context.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun Context.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun Context.px2dp(px: Float): Float = Dimenx.px2dp(this, px)

inline fun Context.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun Context.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun Context.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun Context.px2sp(px: Float): Float = Dimenx.px2sp(this, px)

inline fun Context.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun Context.unit2px(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.unit2px(this, unit, value)

inline fun Context.unit2px(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.unit2px(this, unit, value)


/* ************************************* android.support.v4.app.Fragment ***************************************** */


inline fun android.support.v4.app.Fragment.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun android.support.v4.app.Fragment.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun android.support.v4.app.Fragment.px2dp(px: Float): Float = Dimenx.px2dp(this, px)

inline fun android.support.v4.app.Fragment.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun android.support.v4.app.Fragment.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun android.support.v4.app.Fragment.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun android.support.v4.app.Fragment.px2sp(px: Float): Float = Dimenx.px2sp(this, px)

inline fun android.support.v4.app.Fragment.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun android.support.v4.app.Fragment.unit2px(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.unit2px(this, unit, value)

inline fun android.support.v4.app.Fragment.unit2px(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.unit2px(this, unit, value)


/* ************************************* android.app.Fragment ***************************************** */


inline fun android.app.Fragment.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun android.app.Fragment.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun android.app.Fragment.px2dp(px: Float): Float = Dimenx.px2dp(this, px)

inline fun android.app.Fragment.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun android.app.Fragment.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun android.app.Fragment.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun android.app.Fragment.px2sp(px: Float): Float = Dimenx.px2sp(this, px)

inline fun android.app.Fragment.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun android.app.Fragment.unit2px(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.unit2px(this, unit, value)

inline fun android.app.Fragment.unit2px(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.unit2px(this, unit, value)


/* ************************************* View ***************************************** */


inline fun View.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun View.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun View.px2dp(px: Float): Float = Dimenx.px2dp(this, px)

inline fun View.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun View.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun View.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun View.px2sp(px: Float): Float = Dimenx.px2sp(this, px)

inline fun View.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun View.unit2px(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.unit2px(this, unit, value)

inline fun View.unit2px(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.unit2px(this, unit, value)