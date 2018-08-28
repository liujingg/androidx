@file:Suppress("unused", "FunctionName")

package me.panpf.androidxkt.os

import android.os.Build

/*
 * 构建版本相关的扩展方法或属性
 */

fun isAtLeastI() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
fun isAtLeast14() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
fun isAtLeast4_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

fun isAtLeastIMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
fun isAtLeast15() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
fun isAtLeast4_0_3() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1

fun isAtLeastJ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
fun isAtLeast16() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
fun isAtLeast4_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN

fun isAtLeastJMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
fun isAtLeast17() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
fun isAtLeast4_2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1

fun isAtLeastJMR2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
fun isAtLeast18() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
fun isAtLeast4_3() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2

fun isAtLeastK() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
fun isAtLeast19() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
fun isAtLeast4_4() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

fun isAtLeastKW() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH
fun isAtLeast20() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH
fun isAtLeast4_4_W() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH

fun isAtLeastL() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isAtLeast21() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isAtLeast5_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isAtLeastLMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun isAtLeast22() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun isAtLeast5_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

fun isAtLeastM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isAtLeast23() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isAtLeast6_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isAtLeastN() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isAtLeast24() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isAtLeast7_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isAtLeastNMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isAtLeast25() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isAtLeast7_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

fun isAtLeastO() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isAtLeast26() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isAtLeast8_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isAtLeastOMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isAtLeast27() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isAtLeast8_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

fun isAtLeastP() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1
fun isAtLeast28() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1
fun isAtLeast9_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1

fun getSdkVersionName(sdkVersion: Int): String {
    return when (sdkVersion) {
        1 -> "1.0"
        2 -> "1.1"
        3 -> "1.5"
        4 -> "1.6"
        5 -> "2.0"
        6 -> "2.0.1"
        7 -> "2.1"
        8 -> "2.2"
        9 -> "2.3"
        10 -> "2.3.3"
        11 -> "3.0"
        12 -> "3.1"
        13 -> "3.2"
        14 -> "4.0"
        15 -> "4.0.3"
        16 -> "4.1"
        17 -> "4.2"
        18 -> "4.3"
        19 -> "4.4"
        20 -> "4.4W"
        21 -> "5.0"
        22 -> "5.1"
        23 -> "6.0"
        24 -> "7.0"
        25 -> "7.1.1"
        26 -> "8.0"
        27 -> "8.1"
        28 -> "9.0"
        else -> "Unknown($sdkVersion)"
    }
}
