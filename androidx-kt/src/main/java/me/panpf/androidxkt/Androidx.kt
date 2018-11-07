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
import me.panpf.androidx.Androidx
import me.panpf.androidx.util.NullableResultRunnable
import me.panpf.androidx.util.ResultRunnable

/**
 * Execute the specified code block in the main thread
 */
inline fun runInUI(block: Runnable) = Androidx.runInUI(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runInUI(noinline block: () -> Unit) = Androidx.runInUI(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun waitRunInUI(block: Runnable) = Androidx.waitRunInUI(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun waitRunInUI(noinline block: () -> Unit) = Androidx.waitRunInUI(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUIResult(block: ResultRunnable<T>): T = Androidx.waitRunInUIResult(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUIResult(noinline block: () -> T): T = Androidx.waitRunInUIResult { block() }


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUINullableResult(block: NullableResultRunnable<T>): T? = Androidx.waitRunInUINullableResult(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUINullableResult(noinline block: () -> T?): T? = Androidx.waitRunInUINullableResult { block() }


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