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

package me.panpf.androidx.util;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("WeakerAccess")
public class Dimenx {

    public static int dp2px(@NonNull Context context, float dpValue) {
        return (int) ((double) (dpValue * context.getResources().getDisplayMetrics().density) + 0.5D);
    }

    public static int dp2px(@NonNull Context context, int dpValue) {
        return dp2px(context, (float) dpValue);
    }

    public static float px2dp(@NonNull Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density + 0.5F;
    }

    public static float px2dp(@NonNull Context context, int px) {
        return px2dp(context, (float) px);
    }

    public static int sp2px(@NonNull Context context, float dpValue) {
        return (int) ((double) (dpValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5D);
    }

    public static int sp2px(@NonNull Context context, int dpValue) {
        return sp2px(context, (float) dpValue);
    }

    public static float px2sp(@NonNull Context context, float px) {
        return px / context.getResources().getDisplayMetrics().scaledDensity + 0.5F;
    }

    public static float px2sp(@NonNull Context context, int px) {
        return px2sp(context, (float) px);
    }

    public static float unit2px(@NonNull Context context, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }

    public static float unit2px(@NonNull Context context, @DimenUnit int unit, int value) {
        return unit2px(context, unit, (float) value);
    }

    public static int dp2px(@NonNull android.support.v4.app.Fragment fragment, float dpValue) {
        return (int) ((double) (dpValue * fragment.getResources().getDisplayMetrics().density) + 0.5D);
    }

    public static int dp2px(@NonNull android.support.v4.app.Fragment fragment, int dpValue) {
        return dp2px(fragment, (float) dpValue);
    }

    public static float px2dp(@NonNull android.support.v4.app.Fragment fragment, float px) {
        return px / fragment.getResources().getDisplayMetrics().density + 0.5F;
    }

    public static float px2dp(@NonNull android.support.v4.app.Fragment fragment, int px) {
        return px2dp(fragment, (float) px);
    }

    public static int sp2px(@NonNull android.support.v4.app.Fragment fragment, float dpValue) {
        return (int) ((double) (dpValue * fragment.getResources().getDisplayMetrics().scaledDensity) + 0.5D);
    }

    public static int sp2px(@NonNull android.support.v4.app.Fragment fragment, int dpValue) {
        return sp2px(fragment, (float) dpValue);
    }

    public static float px2sp(@NonNull android.support.v4.app.Fragment fragment, float px) {
        return px / fragment.getResources().getDisplayMetrics().scaledDensity + 0.5F;
    }

    public static float px2sp(@NonNull android.support.v4.app.Fragment fragment, int px) {
        return px2sp(fragment, (float) px);
    }

    public static float unit2px(@NonNull android.support.v4.app.Fragment fragment, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, fragment.getResources().getDisplayMetrics());
    }

    public static float unit2px(@NonNull android.support.v4.app.Fragment fragment, @DimenUnit int unit, int value) {
        return unit2px(fragment, unit, (float) value);
    }

    public static int dp2px(@NonNull View view, float dpValue) {
        return (int) ((double) (dpValue * view.getResources().getDisplayMetrics().density) + 0.5D);
    }

    public static int dp2px(@NonNull View view, int dpValue) {
        return dp2px(view, (float) dpValue);
    }

    public static float px2dp(@NonNull View view, float px) {
        return px / view.getResources().getDisplayMetrics().density + 0.5F;
    }

    public static float px2dp(@NonNull View view, int px) {
        return px2dp(view, (float) px);
    }

    public static int sp2px(@NonNull View view, float dpValue) {
        return (int) ((double) (dpValue * view.getResources().getDisplayMetrics().scaledDensity) + 0.5D);
    }

    public static int sp2px(@NonNull View view, int dpValue) {
        return sp2px(view, (float) dpValue);
    }

    public static float px2sp(@NonNull View view, float px) {
        return px / view.getResources().getDisplayMetrics().scaledDensity + 0.5F;
    }

    public static float px2sp(@NonNull View view, int px) {
        return px2sp(view, (float) px);
    }

    public static float unit2px(@NonNull View view, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, view.getResources().getDisplayMetrics());
    }

    public static float unit2px(@NonNull View view, @DimenUnit int unit, int value) {
        return unit2px(view, unit, (float) value);
    }

    @IntDef({TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX,
            TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM})
    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
    @Retention(RetentionPolicy.CLASS)
    public @interface DimenUnit {

    }
}