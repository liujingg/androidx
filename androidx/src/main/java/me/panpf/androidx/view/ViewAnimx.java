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
public class ViewAnimx {
    /**
     * 默认动画持续时间
     */
    public static final long DEFAULT_ANIMATION_DURATION = 400;

    private ViewAnimx() {
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis,
                                 final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        final AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(durationMillis);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        animAlpha(view, fromAlpha, toAlpha, durationMillis, false, listener);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha      开始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 持续时间，毫秒
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis) {
        animAlpha(view, fromAlpha, toAlpha, durationMillis, false, null);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha 开始透明度
     * @param toAlpha   结束透明度
     * @param listener  动画监听器
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, @Nullable final Animation.AnimationListener listener) {
        animAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha 开始透明度
     * @param toAlpha   结束透明度
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha) {
        animAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, null);
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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles, long durationMillis, final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        TranslateAnimation anim = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        anim.setDuration(durationMillis);
        if (cycles > 0.0f) {
            anim.setInterpolator(new CycleInterpolator(cycles));
        }
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, false, listener);
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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, DEFAULT_ANIMATION_DURATION, false, listener);
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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles, long durationMillis) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, false, null);
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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 移动动画
     *
     * @param fromXDelta X轴开始坐标
     * @param toXDelta   X轴结束坐标
     * @param fromYDelta Y轴开始坐标
     * @param toYDelta   Y轴结束坐标
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, DEFAULT_ANIMATION_DURATION, false, null);
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
        animTranslate(view, 0.0f, extent, 0.0f, 0.0f, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * 视图左右摇晃
     *
     * @param listener 动画监听器
     */
    public static void shake(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7f, 700, false, listener);
    }

    /**
     * 视图左右摇晃
     */
    public static void shake(@NonNull final View view) {
        animTranslate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7f, 700, false, null);
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
        animTranslate(view, 0.0f, 0.0f, 0.0f, extent, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * 视图上下震动
     *
     * @param listener 动画监听器
     */
    public static void shock(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, 0.0f, 0.0f, 0.0f, 10.f, 7f, 700, false, listener);
    }

    /**
     * 视图上下震动
     */
    public static void shock(@NonNull final View view) {
        animTranslate(view, 0.0f, 0.0f, 0.0f, 10.f, 7f, 700, false, null);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animId   动画资源 ID
     * @param listener 动画监听器
     */
    public static void startAnimFromRes(@NonNull final View view, int animId, final boolean isBanClick,
                                        @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        Animation anim = AnimationUtils.loadAnimation(view.getContext(), animId);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animId   动画资源 ID
     * @param listener 动画监听器
     */
    public static void startAnimFromRes(@NonNull final View view, int animId, @Nullable final Animation.AnimationListener listener) {
        startAnimFromRes(view, animId, false, listener);
    }

    /**
     * 执行资源中定义的动画
     *
     * @param animId 动画资源 ID
     */
    public static void startAnimFromRes(@NonNull final View view, int animId) {
        startAnimFromRes(view, animId, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, long durationMillis,
                                            final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.INVISIBLE) return;
        animAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                view.setVisibility(View.INVISIBLE);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        invisibleByAnimAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, long durationMillis) {
        invisibleByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     *
     * @param listener 动画监听器
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        invisibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 INVISIBLE
     */
    public static void invisibleByAnimAlpha(@NonNull final View view) {
        invisibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void goneByAnimAlpha(@NonNull final View view, long durationMillis,
                                       final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.GONE) return;
        animAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                view.setVisibility(View.GONE);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void goneByAnimAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        goneByAnimAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void goneByAnimAlpha(@NonNull final View view, long durationMillis) {
        goneByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     *
     * @param listener 动画监听器
     */
    public static void goneByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        goneByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画隐藏 View，结束时 visibility 设为 View.GONE
     */
    public static void goneByAnimAlpha(@NonNull final View view) {
        goneByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     * @param isBanClick     在执行动画的过程中是否禁止点击
     * @param listener       动画监听器
     */
    public static void visibleByAnimAlpha(@NonNull final View view, long durationMillis,
                                          final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.VISIBLE) return;
        view.setVisibility(View.VISIBLE);
        animAlpha(view, 0.0f, 1.0f, durationMillis, isBanClick, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     * @param listener       动画监听器
     */
    public static void visibleByAnimAlpha(@NonNull final View view, long durationMillis, @Nullable final Animation.AnimationListener listener) {
        visibleByAnimAlpha(view, durationMillis, false, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param durationMillis 持续时间，毫秒
     */
    public static void visibleByAnimAlpha(@NonNull final View view, long durationMillis) {
        visibleByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * 使用透明度渐变动画显示 View
     *
     * @param listener 动画监听器
     */
    public static void visibleByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        visibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * 使用透明度渐变动画显示 View
     */
    public static void visibleByAnimAlpha(@NonNull final View view) {
        visibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }
}