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
import android.util.TypedValue;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

public class Dimenx {

    private Dimenx() {
    }

    /* ************************************* Context ***************************************** */


    public static int dp2px(@NonNull Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dp2px(@NonNull Context context, int dpValue) {
        return (int) ((float) dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static float px2dp(@NonNull Context context, int px) {
        return ((float) px) / context.getResources().getDisplayMetrics().density + 0.5f;
    }


    public static int sp2px(@NonNull Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int sp2px(@NonNull Context context, int dpValue) {
        return (int) ((float) dpValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }


    public static float px2sp(@NonNull Context context, int px) {
        return ((float) px) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }


    public static float applyDimension(@NonNull Context context, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }

    public static float applyDimension(@NonNull Context context, @DimenUnit int unit, int value) {
        return TypedValue.applyDimension(unit, (float) value, context.getResources().getDisplayMetrics());
    }


    /* ************************************* androidx.fragment.app.Fragment ***************************************** */


    public static int dp2px(@NonNull androidx.fragment.app.Fragment fragment, float dpValue) {
        return (int) (dpValue * fragment.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dp2px(@NonNull androidx.fragment.app.Fragment fragment, int dpValue) {
        return (int) ((float) dpValue * fragment.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static float px2dp(@NonNull androidx.fragment.app.Fragment fragment, int px) {
        return ((float) px) / fragment.getResources().getDisplayMetrics().density + 0.5f;
    }


    public static int sp2px(@NonNull androidx.fragment.app.Fragment fragment, float dpValue) {
        return (int) (dpValue * fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int sp2px(@NonNull androidx.fragment.app.Fragment fragment, int dpValue) {
        return (int) ((float) dpValue * fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }


    public static float px2sp(@NonNull androidx.fragment.app.Fragment fragment, int px) {
        return ((float) px) / fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }


    public static float applyDimension(@NonNull androidx.fragment.app.Fragment fragment, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, fragment.getResources().getDisplayMetrics());
    }

    public static float applyDimension(@NonNull androidx.fragment.app.Fragment fragment, @DimenUnit int unit, int value) {
        return TypedValue.applyDimension(unit, (float) value, fragment.getResources().getDisplayMetrics());
    }


    /* ************************************* android.app.Fragment ***************************************** */


    public static int dp2px(@NonNull android.app.Fragment fragment, float dpValue) {
        return (int) (dpValue * fragment.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dp2px(@NonNull android.app.Fragment fragment, int dpValue) {
        return (int) ((float) dpValue * fragment.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static float px2dp(@NonNull android.app.Fragment fragment, int px) {
        return ((float) px) / fragment.getResources().getDisplayMetrics().density + 0.5f;
    }


    public static int sp2px(@NonNull android.app.Fragment fragment, float dpValue) {
        return (int) (dpValue * fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int sp2px(@NonNull android.app.Fragment fragment, int dpValue) {
        return (int) ((float) dpValue * fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }


    public static float px2sp(@NonNull android.app.Fragment fragment, int px) {
        return ((float) px) / fragment.getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }


    public static float applyDimension(@NonNull android.app.Fragment fragment, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, fragment.getResources().getDisplayMetrics());
    }

    public static float applyDimension(@NonNull android.app.Fragment fragment, @DimenUnit int unit, int value) {
        return TypedValue.applyDimension(unit, (float) value, fragment.getResources().getDisplayMetrics());
    }


    /* ************************************* View ***************************************** */


    public static int dp2px(@NonNull View view, float dpValue) {
        return (int) (dpValue * view.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dp2px(@NonNull View view, int dpValue) {
        return (int) ((float) dpValue * view.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static float px2dp(@NonNull View view, int px) {
        return ((float) px) / view.getResources().getDisplayMetrics().density + 0.5f;
    }


    public static int sp2px(@NonNull View view, float dpValue) {
        return (int) (dpValue * view.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int sp2px(@NonNull View view, int dpValue) {
        return (int) ((float) dpValue * view.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }


    public static float px2sp(@NonNull View view, int px) {
        return ((float) px) / view.getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }


    public static float applyDimension(@NonNull View view, @DimenUnit int unit, float value) {
        return TypedValue.applyDimension(unit, value, view.getResources().getDisplayMetrics());
    }

    public static float applyDimension(@NonNull View view, @DimenUnit int unit, int value) {
        return TypedValue.applyDimension(unit, (float) value, view.getResources().getDisplayMetrics());
    }


    @IntDef({TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX,
            TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM})
    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
    @Retention(RetentionPolicy.CLASS)
    public @interface DimenUnit {

    }
}