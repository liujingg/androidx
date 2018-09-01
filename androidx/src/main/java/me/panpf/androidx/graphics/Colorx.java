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

package me.panpf.androidx.graphics;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

@SuppressWarnings("WeakerAccess")
public class Colorx {
    /**
     * 白色
     */
    public static final int WHITE = 0xffffffff;

    /**
     * 白色 - 半透明
     */
    public static final int WHITE_TRANSLUCENT = 0x80ffffff;

    /**
     * 黑色
     */
    public static final int BLACK = 0xff000000;

    /**
     * 黑色 - 半透明
     */
    public static final int BLACK_TRANSLUCENT = 0x80000000;

    /**
     * 透明
     */
    public static final int TRANSPARENT = 0x00000000;

    /**
     * 红色
     */
    public static final int RED = 0xffff0000;

    /**
     * 红色 - 半透明
     */
    public static final int RED_TRANSLUCENT = 0x80ff0000;

    /**
     * 红色 - 深的
     */
    public static final int RED_DARK = 0xff8b0000;

    /**
     * 红色 - 深的 - 半透明
     */
    public static final int RED_DARK_TRANSLUCENT = 0x808b0000;

    /**
     * 绿色
     */
    public static final int GREEN = 0xff00ff00;

    /**
     * 绿色 - 半透明
     */
    public static final int GREEN_TRANSLUCENT = 0x8000ff00;

    /**
     * 绿色 - 深的
     */
    public static final int GREEN_DARK = 0xff003300;

    /**
     * 绿色 - 深的 - 半透明
     */
    public static final int GREEN_DARK_TRANSLUCENT = 0x80003300;

    /**
     * 绿色 - 浅的
     */
    public static final int GREEN_LIGHT = 0xffccffcc;

    /**
     * 绿色 - 浅的 - 半透明
     */
    public static final int GREEN_LIGHT_TRANSLUCENT = 0x80ccffcc;

    /**
     * 蓝色
     */
    public static final int BLUE = 0xff0000ff;

    /**
     * 蓝色 - 半透明
     */
    public static final int BLUE_TRANSLUCENT = 0x800000ff;

    /**
     * 蓝色 - 深的
     */
    public static final int BLUE_DARK = 0xff00008b;

    /**
     * 蓝色 - 深的 - 半透明
     */
    public static final int BLUE_DARK_TRANSLUCENT = 0x8000008b;

    /**
     * 蓝色 - 浅的
     */
    public static final int BLUE_LIGHT = 0xff36a5E3;

    /**
     * 蓝色 - 浅的 - 半透明
     */
    public static final int BLUE_LIGHT_TRANSLUCENT = 0x8036a5E3;

    /**
     * 天蓝
     */
    public static final int SKY_BLUE = 0xff87ceeb;

    /**
     * 天蓝 - 半透明
     */
    public static final int SKY_BLUE_TRANSLUCENT = 0x8087ceeb;

    /**
     * 天蓝 - 深的
     */
    public static final int SKY_BLUE_DARK = 0xff00bfff;

    /**
     * 天蓝 - 深的 - 半透明
     */
    public static final int SKY_BLUE_DARK_TRANSLUCENT = 0x8000bfff;

    /**
     * 天蓝 - 浅的
     */
    public static final int SKY_BLUE_LIGHT = 0xff87cefa;

    /**
     * 天蓝 - 浅的 - 半透明
     */
    public static final int SKY_BLUE_LIGHT_TRANSLUCENT = 0x8087cefa;

    /**
     * 灰色
     */
    public static final int GRAY = 0xff969696;

    /**
     * 灰色 - 半透明
     */
    public static final int GRAY_TRANSLUCENT = 0x80969696;

    /**
     * 灰色 - 深的
     */
    public static final int GRAY_DARK = 0xffa9a9a9;

    /**
     * 灰色 - 深的 - 半透明
     */
    public static final int GRAY_DARK_TRANSLUCENT = 0x80a9a9a9;

    /**
     * 灰色 - 暗的
     */
    public static final int GRAY_DIM = 0xff696969;

    /**
     * 灰色 - 暗的 - 半透明
     */
    public static final int GRAY_DIM_TRANSLUCENT = 0x80696969;

    /**
     * 灰色 - 浅的
     */
    public static final int GRAY_LIGHT = 0xffd3d3d3;

    /**
     * 灰色 - 浅的 - 半透明
     */
    public static final int GRAY_LIGHT_TRANSLUCENT = 0x80d3d3d3;

    /**
     * 橙色
     */
    public static final int ORANGE = 0xffffa500;

    /**
     * 橙色 - 半透明
     */
    public static final int ORANGE_TRANSLUCENT = 0x80ffa500;

    /**
     * 橙色 - 深的
     */
    public static final int ORANGE_DARK = 0xffff8800;

    /**
     * 橙色 - 深的 - 半透明
     */
    public static final int ORANGE_DARK_TRANSLUCENT = 0x80ff8800;

    /**
     * 橙色 - 浅的
     */
    public static final int ORANGE_LIGHT = 0xffffbb33;

    /**
     * 橙色 - 浅的 - 半透明
     */
    public static final int ORANGE_LIGHT_TRANSLUCENT = 0x80ffbb33;

    /**
     * 金色
     */
    public static final int GOLD = 0xffffd700;

    /**
     * 金色 - 半透明
     */
    public static final int GOLD_TRANSLUCENT = 0x80ffd700;

    /**
     * 粉色
     */
    public static final int PINK = 0xffffc0cb;

    /**
     * 粉色 - 半透明
     */
    public static final int PINK_TRANSLUCENT = 0x80ffc0cb;

    /**
     * 紫红色
     */
    public static final int FUCHSIA = 0xffff00ff;

    /**
     * 紫红色 - 半透明
     */
    public static final int FUCHSIA_TRANSLUCENT = 0x80ff00ff;

    /**
     * 灰白色
     */
    public static final int GRAY_WHITE = 0xfff2f2f2;

    /**
     * 灰白色 - 半透明
     */
    public static final int GRAY_WHITE_TRANSLUCENT = 0x80f2f2f2;

    /**
     * 紫色
     */
    public static final int PURPLE = 0xff800080;

    /**
     * 紫色 - 半透明
     */
    public static final int PURPLE_TRANSLUCENT = 0x80800080;

    /**
     * 青色
     */
    public static final int CYAN = 0xff00ffff;

    /**
     * 青色 - 半透明
     */
    public static final int CYAN_TRANSLUCENT = 0x8000ffff;

    /**
     * 青色 - 深的
     */
    public static final int CYAN_DARK = 0xff008b8b;

    /**
     * 青色 - 深的 - 半透明
     */
    public static final int CYAN_DARK_TRANSLUCENT = 0x80008b8b;

    /**
     * 黄色
     */
    public static final int YELLOW = 0xffffff00;

    /**
     * 黄色 - 半透明
     */
    public static final int YELLOW_TRANSLUCENT = 0x80ffff00;

    /**
     * 黄色 - 浅的
     */
    public static final int YELLOW_LIGHT = 0xffffffe0;

    /**
     * 黄色 - 浅的 - 半透明
     */
    public static final int YELLOW_LIGHT_TRANSLUCENT = 0x80ffffe0;

    /**
     * 巧克力色
     */
    public static final int CHOCOLATE = 0xffd2691e;

    /**
     * 巧克力色 - 半透明
     */
    public static final int CHOCOLATE_TRANSLUCENT = 0x80d2691e;

    /**
     * 番茄色
     */
    public static final int TOMATO = 0xffff6347;

    /**
     * 番茄色 - 半透明
     */
    public static final int TOMATO_TRANSLUCENT = 0x80ff6347;

    /**
     * 橙红色
     */
    public static final int ORANGE_RED = 0xffff4500;

    /**
     * 橙红色 - 半透明
     */
    public static final int ORANGE_RED_TRANSLUCENT = 0x80ff4500;

    /**
     * 银白色
     */
    public static final int SILVER = 0xffc0c0c0;

    /**
     * 银白色 - 半透明
     */
    public static final int SILVER_TRANSLUCENT = 0x80c0c0c0;

    /**
     * 高光
     */
    public static final int HIGH_LIGHT = 0x33ffffff;

    /**
     * 低光
     */
    public static final int LOW_LIGHT = 0x33000000;

    /**
     * 修改透明度
     *
     * @param color 原始颜色
     * @param alpha 透明度，0到255
     * @return 新的颜色
     */
    public static int setAlpha(int color, int alpha) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return Color.HSVToColor(alpha, hsv);
    }

    /**
     * 修改饱和度
     *
     * @param color 原始颜色
     * @param rate  饱和度比例，0到1
     * @return 新的颜色
     */
    public static int setSaturation(int color, float rate) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] * rate;
        return Color.HSVToColor(hsv);
    }

    /**
     * 让颜色深一点
     *
     * @param color 原始颜色
     * @param rate  深的比例，0到1
     * @return 新的颜色
     */
    public static int setDarken(int color, float rate) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = hsv[2] * rate;
        return Color.HSVToColor(hsv);
    }

    /**
     * 让颜色深一点儿，0.8
     *
     * @param color 原始颜色
     * @return 新的颜色
     */
    public static int setDarken(int color) {
        return setDarken(color, 0.8f);
    }

    /**
     * 判断颜色属于亮色或者暗色，用于防止背景色与文字颜色接近造成文字看不清
     * http://codepen.io/znak/pen/aOvMOd
     *
     * @param color 原始颜色
     * @return boolean 是否亮色
     */
    public static boolean isLight(int color) {
        float R = Color.red(color) / 255f;
        float G = Color.green(color) / 255f;
        float B = Color.blue(color) / 255f;
        float[] components = new float[]{R, G, B};

        for (int i = 0; i < components.length; i++) {
            if (components[i] <= 0.03928f) {
                components[i] = components[i] / 12.92f;
            } else {
                components[i] = (float) Math.pow((components[i] + 0.055f) / 1.055f, 2.4f);
            }
        }

        double L = 0.2126f * components[0] + 0.7152f * components[1] + 0.0722f * components[2];
        return L > 0.179f;
    }

    /**
     * 创建一个可以改变颜色的 ColorFilter
     *
     * @param color 透明度是没有用的
     * @return ColorMatrixColorFilter
     */
    public static ColorMatrixColorFilter makeResetColorFilter(int color) {
        float mRed = Color.red(color);
        float mGreen = Color.green(color);
        float mBlue = Color.blue(color);
        float[] src = new float[]{
                0, 0, 0, 0, mRed,
                0, 0, 0, 0, mGreen,
                0, 0, 0, 0, mBlue,
                0, 0, 0, 1, 0};
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(src);
        return new ColorMatrixColorFilter(src);
    }
}
