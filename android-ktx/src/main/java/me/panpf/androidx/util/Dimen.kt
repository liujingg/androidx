@file:Suppress("unused")

package me.panpf.androidx.util

import android.content.Context
import android.support.annotation.IntDef
import android.util.TypedValue
import android.view.View
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

/*
 * 尺寸相关的扩展方法或属性
 */

/* ************************************* Context ***************************************** */

fun Context?.dp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.density + 0.5).toInt() else 0

fun Context?.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun Context?.px2dp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.density + 0.5f else 0f

fun Context?.px2dp(px: Int): Float = px2dp(px.toFloat())


fun Context?.sp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt() else 0

fun Context?.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun Context?.px2sp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.scaledDensity + 0.5f else 0f

fun Context?.px2sp(px: Int): Float = px2sp(px.toFloat())


fun Context?.unit2px(@Unit unit: Int, value: Float): Float =
        if (this != null) TypedValue.applyDimension(unit, value, this.resources.displayMetrics) else 0f

fun Context?.unit2px(@Unit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* OriginFragment ***************************************** */


fun OriginFragment?.dp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.density + 0.5).toInt() else 0

fun OriginFragment?.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun OriginFragment?.px2dp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.density + 0.5f else 0f

fun OriginFragment?.px2dp(px: Int): Float = px2dp(px.toFloat())


fun OriginFragment?.sp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt() else 0

fun OriginFragment?.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun OriginFragment?.px2sp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.scaledDensity + 0.5f else 0f

fun OriginFragment?.px2sp(px: Int): Float = px2sp(px.toFloat())


fun OriginFragment?.unit2px(@Unit unit: Int, value: Float): Float =
        if (this != null) TypedValue.applyDimension(unit, value, this.resources.displayMetrics) else 0f

fun OriginFragment?.unit2px(@Unit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* SupportFragment ***************************************** */


fun SupportFragment?.dp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.density + 0.5).toInt() else 0

fun SupportFragment?.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun SupportFragment?.px2dp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.density + 0.5f else 0f

fun SupportFragment?.px2dp(px: Int): Float = px2dp(px.toFloat())


fun SupportFragment?.sp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt() else 0

fun SupportFragment?.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun SupportFragment?.px2sp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.scaledDensity + 0.5f else 0f

fun SupportFragment?.px2sp(px: Int): Float = px2sp(px.toFloat())


fun SupportFragment?.unit2px(@Unit unit: Int, value: Float): Float =
        if (this != null) TypedValue.applyDimension(unit, value, this.resources.displayMetrics) else 0f

fun SupportFragment?.unit2px(@Unit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())


/* ************************************* View ***************************************** */


fun View?.dp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.density + 0.5).toInt() else 0

fun View?.dp2px(dpValue: Int): Int = dp2px(dpValue.toFloat())


fun View?.px2dp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.density + 0.5f else 0f

fun View?.px2dp(px: Int): Float = px2dp(px.toFloat())


fun View?.sp2px(dpValue: Float): Int =
        if (this != null) (dpValue * this.resources.displayMetrics.scaledDensity + 0.5).toInt() else 0

fun View?.sp2px(dpValue: Int): Int = sp2px(dpValue.toFloat())


fun View?.px2sp(px: Float): Float =
        if (this != null) px / this.resources.displayMetrics.scaledDensity + 0.5f else 0f

fun View?.px2sp(px: Int): Float = px2sp(px.toFloat())


fun View?.unit2px(@Unit unit: Int, value: Float): Float =
        if (this != null) TypedValue.applyDimension(unit, value, this.resources.displayMetrics) else 0f

fun View?.unit2px(@Unit unit: Int, value: Int): Float = unit2px(unit, value.toFloat())

@IntDef(TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX,
        TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
annotation class Unit