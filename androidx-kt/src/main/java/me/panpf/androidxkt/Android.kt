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

package me.panpf.androidxkt

import android.content.Context
import android.os.Handler
import me.panpf.androidx.Androidx


/**
 * Get the main thread Handler
 */
inline fun getMainHandler(): Handler = Androidx.getMainHandler()

/**
 * Execute the specified code block in the main thread
 */
inline fun runInUI(block: Runnable) = Androidx.runInUI(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runInUI(noinline block: () -> Unit) = Androidx.runInUI(block)

/**
 * Return true if ROOT is already
 */
inline fun isRooted(): Boolean = Androidx.isRooted()


/**
 * Get the name of the current process
 */
inline fun Context.getInProcessName(): String? = Androidx.getInProcessName(this)

/**
 * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
 */
inline fun Context.getInProcessNameSuffix(): String? = Androidx.getInProcessNameSuffix(this)

/**
 * Is in the main process?
 */
inline fun Context.isMainProcess(): Boolean = Androidx.isMainProcess(this)


/**
 * Is it the main thread?
 */
inline fun isMainThread(): Boolean = Androidx.isMainThread()

inline fun isAtLeastJ() = Androidx.isAtLeastJ()
inline fun isAtLeast16() = Androidx.isAtLeast16()
inline fun isAtLeast4_1() = Androidx.isAtLeast4_1()

inline fun isAtLeastJMR1() = Androidx.isAtLeastJMR1()
inline fun isAtLeast17() = Androidx.isAtLeast17()
inline fun isAtLeast4_2() = Androidx.isAtLeast4_2()

inline fun isAtLeastJMR2() = Androidx.isAtLeastJMR2()
inline fun isAtLeast18() = Androidx.isAtLeast18()
inline fun isAtLeast4_3() = Androidx.isAtLeast4_3()

inline fun isAtLeastK() = Androidx.isAtLeastK()
inline fun isAtLeast19() = Androidx.isAtLeast19()
inline fun isAtLeast4_4() = Androidx.isAtLeast4_4()

inline fun isAtLeastKW() = Androidx.isAtLeastKW()
inline fun isAtLeast20() = Androidx.isAtLeast20()
inline fun isAtLeast4_4_W() = Androidx.isAtLeast4_4_W()

inline fun isAtLeastL() = Androidx.isAtLeastL()
inline fun isAtLeast21() = Androidx.isAtLeast21()
inline fun isAtLeast5_0() = Androidx.isAtLeast5_0()

inline fun isAtLeastLMR1() = Androidx.isAtLeastLMR1()
inline fun isAtLeast22() = Androidx.isAtLeast22()
inline fun isAtLeast5_1() = Androidx.isAtLeast5_1()

inline fun isAtLeastM() = Androidx.isAtLeastM()
inline fun isAtLeast23() = Androidx.isAtLeast23()
inline fun isAtLeast6_0() = Androidx.isAtLeast6_0()

inline fun isAtLeastN() = Androidx.isAtLeastN()
inline fun isAtLeast24() = Androidx.isAtLeast24()
inline fun isAtLeast7_0() = Androidx.isAtLeast7_0()

inline fun isAtLeastNMR1() = Androidx.isAtLeastNMR1()
inline fun isAtLeast25() = Androidx.isAtLeast25()
inline fun isAtLeast7_1() = Androidx.isAtLeast7_1()

inline fun isAtLeastO() = Androidx.isAtLeastO()
inline fun isAtLeast26() = Androidx.isAtLeast26()
inline fun isAtLeast8_0() = Androidx.isAtLeast8_0()

inline fun isAtLeastOMR1() = Androidx.isAtLeastOMR1()
inline fun isAtLeast27() = Androidx.isAtLeast27()
inline fun isAtLeast8_1() = Androidx.isAtLeast8_1()

inline fun isAtLeastP() = Androidx.isAtLeastP()
inline fun isAtLeast28() = Androidx.isAtLeast28()
inline fun isAtLeast9_0() = Androidx.isAtLeast9_0()


inline fun getAndroidVersionName(version: Int): String = Androidx.getVersionName(version)

inline fun getAndroidVersionName(): String = Androidx.getVersionName()

inline fun getAndroidVersionCodeName(version: Int): String = Androidx.getVersionCodeName(version)

inline fun getAndroidVersionCodeName(): String = Androidx.getVersionCodeName()