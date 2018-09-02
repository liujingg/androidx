package me.panpf.androidxkt.util

import android.content.Context
import android.support.annotation.IntDef
import android.util.TypedValue
import android.view.View
import android.support.v4.app.Fragment as SupportFragment

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


fun SupportFragment.dp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.density + 0.5).toInt()

fun SupportFragment.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun SupportFragment.px2dp(px: Float): Float =
        px / this.resources.displayMetrics.density + 0.5f

fun SupportFragment.px2dp(px: Int): Float = px2dp(px.toFloat())


fun SupportFragment.sp2px(dpValue: Float): Int =
        (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt()

fun SupportFragment.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun SupportFragment.px2sp(px: Float): Float =
        px / this.resources.displayMetrics.scaledDensity + 0.5f

fun SupportFragment.px2sp(px: Int): Float = px2sp(px.toFloat())


fun SupportFragment.unit2px(@DimenUnit unit: Int, value: Float): Float =
        TypedValue.applyDimension(unit, value, this.resources.displayMetrics)

fun SupportFragment.unit2px(@DimenUnit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


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
@Retention(AnnotationRetention.RUNTIME)
annotation class DimenUnit