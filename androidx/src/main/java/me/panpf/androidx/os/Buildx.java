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

package me.panpf.androidx.os;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;

/**
 * Tool methods related to Android builds
 */
public class Buildx {

    public static boolean isAtLeastI() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeast14() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeast4_0() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeastIMR1() {
        return VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeast15() {
        return VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeast4_0_3() {
        return VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeastJ() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeast16() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeast4_1() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeastJMR1() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeast17() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeast4_2() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeastJMR2() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeast18() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeast4_3() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeastK() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeast19() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeast4_4() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeastKW() {
        return VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeast20() {
        return VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeast4_4_W() {
        return VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeastL() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeast21() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeast5_0() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeastLMR1() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeast22() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeast5_1() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeastM() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeast23() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeast6_0() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeastN() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeast24() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeast7_0() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastNMR1() {
        return VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeast25() {
        return VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeast7_1() {
        return VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeastO() {
        return VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeast26() {
        return VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeast8_0() {
        return VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeastOMR1() {
        return VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeast27() {
        return VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeast8_1() {
        return VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeastP() {
        return VERSION.SDK_INT >= 28;
    }

    public static boolean isAtLeast28() {
        return VERSION.SDK_INT >= 28;
    }

    public static boolean isAtLeast9_0() {
        return VERSION.SDK_INT >= 28;
    }

    @NonNull
    public static String getSdkVersionName(int sdkVersion) {
        String name;
        switch (sdkVersion) {
            case 1:
                name = "1.0";
                break;
            case 2:
                name = "1.1";
                break;
            case 3:
                name = "1.5";
                break;
            case 4:
                name = "1.6";
                break;
            case 5:
                name = "2.0";
                break;
            case 6:
                name = "2.0.1";
                break;
            case 7:
                name = "2.1";
                break;
            case 8:
                name = "2.2";
                break;
            case 9:
                name = "2.3";
                break;
            case 10:
                name = "2.3.3";
                break;
            case 11:
                name = "3.0";
                break;
            case 12:
                name = "3.1";
                break;
            case 13:
                name = "3.2";
                break;
            case 14:
                name = "4.0";
                break;
            case 15:
                name = "4.0.3";
                break;
            case 16:
                name = "4.1";
                break;
            case 17:
                name = "4.2";
                break;
            case 18:
                name = "4.3";
                break;
            case 19:
                name = "4.4";
                break;
            case 20:
                name = "4.4W";
                break;
            case 21:
                name = "5.0";
                break;
            case 22:
                name = "5.1";
                break;
            case 23:
                name = "6.0";
                break;
            case 24:
                name = "7.0";
                break;
            case 25:
                name = "7.1.1";
                break;
            case 26:
                name = "8.0";
                break;
            case 27:
                name = "8.1";
                break;
            case 28:
                name = "9.0";
                break;
            default:
                name = "Unknown(" + sdkVersion + ')';
        }

        return name;
    }
}
