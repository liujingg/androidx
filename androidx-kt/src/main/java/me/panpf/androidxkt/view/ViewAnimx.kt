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

package me.panpf.androidxkt.view

import android.view.View
import android.view.animation.Animation.AnimationListener
import me.panpf.androidx.view.ViewAnimx
import me.panpf.androidx.view.ViewAnimx.DEFAULT_ANIMATION_DURATION

/*
 * View animation related extension methods or properties
 */

/**
 * 透明度动画
 *
 * @param fromAlpha         开始透明度
 * @param toAlpha           结束透明度
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener 动画监听器
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float, durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                          isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.animAlpha(this, fromAlpha, toAlpha, durationMillis, isBanClick, listener)

/**
 * 移动动画
 *
 * @param fromXDelta     X轴开始坐标
 * @param toXDelta       X轴结束坐标
 * @param fromYDelta     Y轴开始坐标
 * @param toYDelta       Y轴结束坐标
 * @param cycles         重复
 * @param durationMillis 持续时间
 * @param isBanClick     在执行动画的过程中是否禁止点击
 * @param listener       动画监听器
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, cycles: Float,
                              durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                              isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, isBanClick, listener)

/**
 * 视图左右摇晃
 *
 * @param extent         摇晃幅度
 * @param cycles         重复次数
 * @param durationMillis 持续时间
 * @param isBanClick     在执行动画的过程中是否禁止点击
 * @param listener       动画监听器
 */
inline fun View.shake(extent: Float = 10.0f, cycles: Float = 7f, durationMillis: Long = 700,
                      isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.shake(this, extent, cycles, durationMillis, isBanClick, listener)

/**
 * 视图上下震动
 *
 * @param extent         震动幅度
 * @param cycles         重复次数
 * @param durationMillis 持续时间
 * @param isBanClick     在执行动画的过程中是否禁止点击
 * @param listener       动画监听器
 */
inline fun View.shock(extent: Float = 10.0f, cycles: Float = 7f, durationMillis: Long = 700,
                      isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.shock(this, extent, cycles, durationMillis, isBanClick, listener)

/**
 * 执行资源中定义的动画
 * @param animId   动画资源 ID
 * @param listener      动画监听器
 */
inline fun View.startAnimFromRes(animId: Int, isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.startAnimFromRes(this, animId, isBanClick, listener)

/**
 * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.INVISIBLE
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
inline fun View.invisibleByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                                     isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.invisibleByAnimAlpha(this, durationMillis, isBanClick, listener)

/**
 * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
inline fun View.goneByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                                isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.goneByAnimAlpha(this, durationMillis, isBanClick, listener)

/**
 * 使用透明度渐变动画显示 View
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
inline fun View.visibleByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                                   isBanClick: Boolean = false, listener: AnimationListener? = null) = ViewAnimx.visibleByAnimAlpha(this, durationMillis, isBanClick, listener)
