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

package me.panpf.androidxkt.view

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation

/*
 * View animation related extension methods or properties
 */

/**
 * 默认动画持续时间
 */
const val DEFAULT_ANIMATION_DURATION: Long = 400

/**
 * 透明度动画
 *
 * @param fromAlpha         开始透明度
 * @param toAlpha           结束透明度
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener 动画监听器
 */
fun View.animAlpha(fromAlpha: Float, toAlpha: Float, durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                        isBanClick: Boolean = false, listener: AnimationListener? = null) {
    this.clearAnimation()
    val isClickable = this.isClickable

    this.startAnimation(AlphaAnimation(fromAlpha, toAlpha).apply {
        duration = durationMillis
        setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {
                if (isClickable && isBanClick) this@animAlpha.isClickable = false
                listener?.onAnimationStart(anim)
            }

            override fun onAnimationRepeat(anim: Animation) {
                listener?.onAnimationRepeat(anim)
            }

            override fun onAnimationEnd(anim: Animation) {
                if (isClickable && isBanClick) this@animAlpha.isClickable = true
                listener?.onAnimationEnd(anim)
            }
        })
    })
}

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
fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, cycles: Float,
                            durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                            isBanClick: Boolean = false, listener: AnimationListener? = null) {
    this.clearAnimation()
    val isClickable = this.isClickable

    this.startAnimation(TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta).apply {
        duration = durationMillis
        if (cycles > 0.0f) interpolator = CycleInterpolator(cycles)
        setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {
                if (isClickable && isBanClick) this@animTranslate.isClickable = false
                listener?.onAnimationStart(anim)
            }

            override fun onAnimationRepeat(anim: Animation) {
                listener?.onAnimationRepeat(anim)
            }

            override fun onAnimationEnd(anim: Animation) {
                if (isClickable && isBanClick) this@animTranslate.isClickable = true
                listener?.onAnimationEnd(anim)
            }
        })
    })
}

/**
 * 视图左右摇晃
 *
 * @param extent         摇晃幅度
 * @param cycles         重复次数
 * @param durationMillis 持续时间
 * @param isBanClick     在执行动画的过程中是否禁止点击
 * @param listener       动画监听器
 */
fun View.shake(extent: Float = 10.0f, cycles: Float = 7f, durationMillis: Long = 700,
               isBanClick: Boolean = false, listener: AnimationListener? = null) {
    animTranslate(0.0f, extent, 0.0f, 0.0f, cycles, durationMillis, isBanClick, listener)
}

/**
 * 视图上下震动
 *
 * @param extent         震动幅度
 * @param cycles         重复次数
 * @param durationMillis 持续时间
 * @param isBanClick     在执行动画的过程中是否禁止点击
 * @param listener       动画监听器
 */
fun View.shock(extent: Float = 10.0f, cycles: Float = 7f, durationMillis: Long = 700,
               isBanClick: Boolean = false, listener: AnimationListener? = null) {
    animTranslate(0.0f, 0.0f, 0.0f, extent, cycles, durationMillis, isBanClick, listener)
}

/**
 * 执行资源中定义的动画
 * @param animId   动画资源 ID
 * @param listener      动画监听器
 */
fun View.startAnimFromRes(animId: Int, isBanClick: Boolean = false, listener: AnimationListener? = null) {
    this.clearAnimation()
    val isClickable = this.isClickable

    startAnimation(android.view.animation.AnimationUtils.loadAnimation(context, animId).apply {
        setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {
                if (isClickable && isBanClick) this@startAnimFromRes.isClickable = false
                listener?.onAnimationStart(anim)
            }

            override fun onAnimationRepeat(anim: Animation) {
                listener?.onAnimationRepeat(anim)
            }

            override fun onAnimationEnd(anim: Animation) {
                if (isClickable && isBanClick) this@startAnimFromRes.isClickable = true
                listener?.onAnimationEnd(anim)
            }
        })
    })
}

/**
 * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.INVISIBLE
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
fun View.invisibleByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                                   isBanClick: Boolean = false, listener: AnimationListener? = null) {
    if (this.visibility == View.INVISIBLE) return
    animAlpha(1.0f, 0.0f, durationMillis, isBanClick, object : AnimationListener {
        override fun onAnimationStart(anim: Animation?) {
            listener?.onAnimationStart(anim)
        }

        override fun onAnimationRepeat(anim: Animation?) {
            listener?.onAnimationRepeat(anim)
        }

        override fun onAnimationEnd(anim: Animation?) {
            this@invisibleByAnimAlpha.visibility = View.INVISIBLE
            listener?.onAnimationEnd(anim)
        }
    })
}

/**
 * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
fun View.goneByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                              isBanClick: Boolean = false, listener: AnimationListener? = null) {
    if (this.visibility == View.GONE) return
    animAlpha(1.0f, 0.0f, durationMillis, isBanClick, object : AnimationListener {
        override fun onAnimationStart(anim: Animation?) {
            listener?.onAnimationStart(anim)
        }

        override fun onAnimationRepeat(anim: Animation?) {
            listener?.onAnimationRepeat(anim)
        }

        override fun onAnimationEnd(anim: Animation?) {
            this@goneByAnimAlpha.visibility = View.GONE
            listener?.onAnimationEnd(anim)
        }
    })
}

/**
 * 使用透明度渐变动画显示 View
 *
 * @param durationMillis    持续时间，毫秒
 * @param isBanClick        在执行动画的过程中是否禁止点击
 * @param listener          动画监听器
 */
fun View.visibleByAnimAlpha(durationMillis: Long = DEFAULT_ANIMATION_DURATION,
                                 isBanClick: Boolean = false, listener: AnimationListener? = null) {
    if (this.visibility == View.VISIBLE) return
    this.visibility = View.VISIBLE
    animAlpha(0.0f, 1.0f, durationMillis, isBanClick, listener)
}
