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

package me.panpf.androidx.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * View animation tool method
 */
@SuppressWarnings("WeakerAccess")
public class ViewAnimx {
    /**
     * 默认动画持续时间
     */
    public static final long DEFAULT_ANIMATION_DURATION = 400;

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void animationAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis,
                                      final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        final AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(durationMillis);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(animation);
            }
        });
        view.startAnimation(animation);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void animationAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        animationAlpha(view, fromAlpha, toAlpha, durationMillis, false, listener);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     */
    public static void animationAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis) {
        animationAlpha(view, fromAlpha, toAlpha, durationMillis, false, null);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha 开始透明度
     * @param toAlpha   结束透明度
     * @param listener  动画监听器
     */
    public static void animationAlpha(@NonNull final View view, float fromAlpha, float toAlpha, @Nullable final Animation.AnimationListener listener) {
        animationAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha 开始透明度
     * @param toAlpha   结束透明度
     */
    public static void animationAlpha(@NonNull final View view, float fromAlpha, float toAlpha) {
        animationAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, null);
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
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                          float cycles, long durationMillis, final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(durationMillis);
        if (cycles > 0.0f) {
            animation.setInterpolator(new CycleInterpolator(cycles));
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(animation);
            }
        });
        view.startAnimation(animation);
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
     * @param listener       动画监听器
     */
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                          float cycles, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, false, listener);
    }

    /**
     * 移动动画
     *
     * @param fromXDelta X轴开始坐标
     * @param toXDelta   X轴结束坐标
     * @param fromYDelta Y轴开始坐标
     * @param toYDelta   Y轴结束坐标
     * @param cycles     重复
     * @param listener   动画监听器
     */
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                          float cycles, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, DEFAULT_ANIMATION_DURATION, false, listener);
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
     */
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                          float cycles, long durationMillis) {
        animationTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, false, null);
    }

    /**
     * 移动动画
     *
     * @param fromXDelta X轴开始坐标
     * @param toXDelta   X轴结束坐标
     * @param fromYDelta Y轴开始坐标
     * @param toYDelta   Y轴结束坐标
     * @param cycles     重复
     */
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                          float cycles) {
        animationTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 移动动画
     *
     * @param fromXDelta X轴开始坐标
     * @param toXDelta   X轴结束坐标
     * @param fromYDelta Y轴开始坐标
     * @param toYDelta   Y轴结束坐标
     */
    public static void animationTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        animationTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, DEFAULT_ANIMATION_DURATION, false, null);
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
    public static void shake(@NonNull final View view, float extent, float cycles, long durationMillis,
                             boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, 0.0f, extent, 0.0f, 0.0f, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * 视图左右摇晃
     *
     * @param listener 动画监听器
     */
    public static void shake(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7f, 700, false, listener);
    }

    /**
     * 视图左右摇晃
     */
    public static void shake(@NonNull final View view) {
        animationTranslate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7f, 700, false, null);
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
    public static void shock(@NonNull final View view, float extent, float cycles, long durationMillis,
                             boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, 0.0f, 0.0f, 0.0f, extent, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * 视图上下震动
     *
     * @param listener 动画监听器
     */
    public static void shock(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        animationTranslate(view, 0.0f, 0.0f, 0.0f, 10.f, 7f, 700, false, listener);
    }

    /**
     * 视图上下震动
     */
    public static void shock(@NonNull final View view) {
        animationTranslate(view, 0.0f, 0.0f, 0.0f, 10.f, 7f, 700, false, null);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animationId 动画资源 ID
     * @param listener    动画监听器
     */
    public static void startAnimationFromRes(@NonNull final View view, int animationId, final boolean isBanClick,
                                             @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        Animation animation = AnimationUtils.loadAnimation(view.getContext(), animationId);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(animation);
            }
        });
        view.startAnimation(animation);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animationId 动画资源 ID
     * @param listener    动画监听器
     */
    public static void startAnimationFromRes(@NonNull final View view, int animationId, @Nullable final Animation.AnimationListener listener) {
        startAnimationFromRes(view, animationId, false, listener);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animationId 动画资源 ID
     */
    public static void startAnimationFromRes(@NonNull final View view, int animationId) {
        startAnimationFromRes(view, animationId, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void invisibleByAnimationAlpha(@NonNull final View view, long durationMillis,
                                                 final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.INVISIBLE) return;
        animationAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {
                view.setVisibility(View.INVISIBLE);
                if (listener != null) listener.onAnimationEnd(animation);
            }
        });
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void invisibleByAnimationAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        invisibleByAnimationAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void invisibleByAnimationAlpha(@NonNull final View view, long durationMillis) {
        invisibleByAnimationAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param listener 动画监听器
     */
    public static void invisibleByAnimationAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        invisibleByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     */
    public static void invisibleByAnimationAlpha(@NonNull final View view) {
        invisibleByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void goneByAnimationAlpha(@NonNull final View view, long durationMillis,
                                            final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.GONE) return;
        animationAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {
                if (listener != null) listener.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {
                view.setVisibility(View.GONE);
                if (listener != null) listener.onAnimationEnd(animation);
            }
        });
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void goneByAnimationAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        goneByAnimationAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void goneByAnimationAlpha(@NonNull final View view, long durationMillis) {
        goneByAnimationAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param listener 动画监听器
     */
    public static void goneByAnimationAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        goneByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     */
    public static void goneByAnimationAlpha(@NonNull final View view) {
        goneByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void visibleByAnimationAlpha(@NonNull final View view, long durationMillis,
                                               final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.VISIBLE) return;
        view.setVisibility(View.VISIBLE);
        animationAlpha(view, 0.0f, 1.0f, durationMillis, isBanClick, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void visibleByAnimationAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        visibleByAnimationAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void visibleByAnimationAlpha(@NonNull final View view, long durationMillis) {
        visibleByAnimationAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param listener 动画监听器
     */
    public static void visibleByAnimationAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        visibleByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     */
    public static void visibleByAnimationAlpha(@NonNull final View view) {
        visibleByAnimationAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }
}