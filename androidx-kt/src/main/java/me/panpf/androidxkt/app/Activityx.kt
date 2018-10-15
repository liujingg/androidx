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

package me.panpf.androidxkt.app

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import me.panpf.androidx.app.Activityx
import java.io.Serializable
import java.util.*

/**
 * Return true if the activity has been destroyed
 */
inline fun Activity.isDestroyedCompat(): Boolean = Activityx.isDestroyedCompat(this)

/**
 * Return true if the activity has been destroyed
 */
inline fun FragmentActivity.isDestroyedCompat(): Boolean = Activityx.isDestroyedCompat(this)

/**
 * Convert a translucent themed Activity
 * [android.R.attr.windowIsTranslucent] to a fullscreen opaque
 * Activity.
 *
 * Call this whenever the background of a translucent Activity has changed
 * to become opaque. Doing so will allow the [android.view.Surface] of
 * the Activity behind to be released.
 *
 * This call has no effect on non-translucent activities or on activities
 * with the [android.R.attr.windowIsFloating] attribute.
 */
inline fun Activity.convertFromTranslucentCompat(): Boolean = Activityx.convertFromTranslucent(this)

/**
 * Convert a translucent themed Activity
 * [android.R.attr.windowIsTranslucent] back from opaque to
 * translucent following a call to
 * [.convertActivityFromTranslucent] .
 *
 *
 * Calling this allows the Activity behind this one to be seen again. Once
 * all such Activities have been redrawn
 *
 *
 * This call has no effect on non-translucent activities or on activities
 * with the [android.R.attr.windowIsFloating] attribute.
 */
inline fun Activity.convertToTranslucentCompat(): Boolean = Activityx.convertToTranslucent(this)

/**
 * If the own or parent activity implements the specified [clazz], it returns its implementation.
 */
inline fun <T> Activity.getImplWithParent(clazz: Class<T>): T? = Activityx.getImplWithParent(this, clazz)


/* ************************************* Intent Args ***************************************** */


inline fun Activity.readByteArg(argName: String, defaultValue: Byte): Byte = Activityx.readByteArg(this, argName, defaultValue)

inline fun Activity.readByteArrayArg(argName: String): ByteArray = Activityx.readByteArrayArg(this, argName)

inline fun Activity.readByteArrayArg(argName: String, defaultValue: ByteArray): ByteArray = Activityx.readByteArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalByteArrayArg(argName: String): ByteArray? = Activityx.readOptionalByteArrayArg(this, argName)


inline fun Activity.readShortArg(argName: String, defaultValue: Short): Short = Activityx.readShortArg(this, argName, defaultValue)

inline fun Activity.readShortArrayArg(argName: String): ShortArray = Activityx.readShortArrayArg(this, argName)

inline fun Activity.readShortArrayArg(argName: String, defaultValue: ShortArray): ShortArray = Activityx.readShortArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalShortArrayArg(argName: String): ShortArray? = Activityx.readOptionalShortArrayArg(this, argName)


inline fun Activity.readIntArg(argName: String, defaultValue: Int): Int = Activityx.readIntArg(this, argName, defaultValue)

inline fun Activity.readIntArrayArg(argName: String): IntArray = Activityx.readIntArrayArg(this, argName)

inline fun Activity.readIntArrayArg(argName: String, defaultValue: IntArray): IntArray = Activityx.readIntArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalIntArrayArg(argName: String): IntArray? = Activityx.readOptionalIntArrayArg(this, argName)

inline fun Activity.readIntArrayListArg(argName: String): ArrayList<Int> = Activityx.readIntArrayListArg(this, argName)

inline fun Activity.readIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Activityx.readIntArrayListArg(this, argName, defaultValue)

inline fun Activity.readOptionalIntArrayListArg(argName: String): ArrayList<Int>? = Activityx.readOptionalIntArrayListArg(this, argName)


inline fun Activity.readLongArg(argName: String, defaultValue: Long): Long = Activityx.readLongArg(this, argName, defaultValue)

inline fun Activity.readLongArrayArg(argName: String): LongArray = Activityx.readLongArrayArg(this, argName)

inline fun Activity.readLongArrayArg(argName: String, defaultValue: LongArray): LongArray = Activityx.readLongArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalLongArrayArg(argName: String): LongArray? = Activityx.readOptionalLongArrayArg(this, argName)


inline fun Activity.readFloatArg(argName: String, defaultValue: Float): Float = Activityx.readFloatArg(this, argName, defaultValue)

inline fun Activity.readFloatArrayArg(argName: String): FloatArray = Activityx.readFloatArrayArg(this, argName)

inline fun Activity.readFloatArrayArg(argName: String, defaultValue: FloatArray): FloatArray = Activityx.readFloatArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalFloatArrayArg(argName: String): FloatArray? = Activityx.readOptionalFloatArrayArg(this, argName)


inline fun Activity.readDoubleArg(argName: String, defaultValue: Double): Double = Activityx.readDoubleArg(this, argName, defaultValue)

inline fun Activity.readDoubleArrayArg(argName: String): DoubleArray = Activityx.readDoubleArrayArg(this, argName)

inline fun Activity.readDoubleArrayArg(argName: String, defaultValue: DoubleArray): DoubleArray = Activityx.readDoubleArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalDoubleArrayArg(argName: String): DoubleArray? = Activityx.readOptionalDoubleArrayArg(this, argName)


inline fun Activity.readBooleanArg(argName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanArg(this, argName, defaultValue)

inline fun Activity.readBooleanArrayArg(argName: String): BooleanArray = Activityx.readBooleanArrayArg(this, argName)

inline fun Activity.readBooleanArrayArg(argName: String, defaultValue: BooleanArray): BooleanArray = Activityx.readBooleanArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalBooleanArrayArg(argName: String): BooleanArray? = Activityx.readOptionalBooleanArrayArg(this, argName)


inline fun Activity.readCharArg(argName: String, defaultValue: Char): Char = Activityx.readCharArg(this, argName, defaultValue)

inline fun Activity.readCharArrayArg(argName: String): CharArray = Activityx.readCharArrayArg(this, argName)

inline fun Activity.readCharArrayArg(argName: String, defaultValue: CharArray): CharArray = Activityx.readCharArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalCharArrayArg(argName: String): CharArray? = Activityx.readOptionalCharArrayArg(this, argName)


inline fun Activity.readCharSequenceArg(argName: String): CharSequence = Activityx.readCharSequenceArg(this, argName)

inline fun Activity.readCharSequenceArg(argName: String, defaultValue: CharSequence): CharSequence = Activityx.readCharSequenceArg(this, argName, defaultValue)

inline fun Activity.readOptionalCharSequenceArg(argName: String): CharSequence? = Activityx.readOptionalCharSequenceArg(this, argName)

inline fun Activity.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Activityx.readCharSequenceArrayArg(this, argName)

inline fun Activity.readCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Activityx.readCharSequenceArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalCharSequenceArrayArg(argName: String): Array<CharSequence>? = Activityx.readOptionalCharSequenceArrayArg(this, argName)

inline fun Activity.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Activityx.readCharSequenceArrayListArg(this, argName)

inline fun Activity.readCharSequenceArrayListArg(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Activityx.readCharSequenceArrayListArg(this, argName, defaultValue)

inline fun Activity.readOptionalCharSequenceArrayListArg(argName: String): ArrayList<CharSequence>? = Activityx.readOptionalCharSequenceArrayListArg(this, argName)


inline fun Activity.readStringArg(argName: String): String = Activityx.readStringArg(this, argName)

inline fun Activity.readStringArg(argName: String, defaultValue: String): String = Activityx.readStringArg(this, argName, defaultValue)

inline fun Activity.readOptionalStringArg(argName: String): String? = Activityx.readOptionalStringArg(this, argName)

inline fun Activity.readStringArrayArg(argName: String): Array<String> = Activityx.readStringArrayArg(this, argName)

inline fun Activity.readStringArrayArg(argName: String, defaultValue: Array<String>): Array<String> = Activityx.readStringArrayArg(this, argName, defaultValue)

inline fun Activity.readOptionalStringArrayArg(argName: String): Array<String>? = Activityx.readOptionalStringArrayArg(this, argName)

inline fun Activity.readStringArrayListArg(argName: String): ArrayList<String> = Activityx.readStringArrayListArg(this, argName)

inline fun Activity.readStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Activityx.readStringArrayListArg(this, argName, defaultValue)

inline fun Activity.readOptionalStringArrayListArg(argName: String): ArrayList<String>? = Activityx.readOptionalStringArrayListArg(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArg(argName: String): V = Activityx.readParcelableArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArg(argName: String, defaultValue: V): V = Activityx.readParcelableArg(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readOptionalParcelableArg(argName: String): V? = Activityx.readOptionalParcelableArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String): Array<V> = Activityx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String, defaultValue: Array<V>): Array<V> = Activityx.readParcelableArrayArg(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readOptionalParcelableArrayArg(argName: String): Array<V>? = Activityx.readOptionalParcelableArrayArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String): ArrayList<V> = Activityx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Activityx.readParcelableArrayListArg(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readOptionalParcelableArrayListArg(argName: String): ArrayList<V>? = Activityx.readOptionalParcelableArrayListArg(this, argName)


inline fun <V : Serializable> Activity.readSerializableArg(argName: String): V = Activityx.readSerializableArg(this, argName)

inline fun <V : Serializable> Activity.readSerializableArg(argName: String, defaultValue: V): V = Activityx.readSerializableArg(this, argName, defaultValue)

inline fun <V : Serializable> Activity.readOptionalSerializableArg(argName: String): V? = Activityx.readOptionalSerializableArg(this, argName)


inline fun Activity.readBundleArg(argName: String): Bundle = Activityx.readBundleArg(this, argName)

inline fun Activity.readBundleArg(argName: String, defaultValue: Bundle): Bundle = Activityx.readBundleArg(this, argName, defaultValue)

inline fun Activity.readOptionalBundleArg(argName: String): Bundle? = Activityx.readOptionalBundleArg(this, argName)


inline fun Activity.readExtrasArg(): Bundle = Activityx.readExtrasArg(this)

inline fun Activity.readExtrasArg(defaultValue: Bundle): Bundle = Activityx.readExtrasArg(this, defaultValue)

inline fun Activity.readOptionalExtrasArg(): Bundle? = Activityx.readOptionalExtrasArg(this)


/* ************************************* Uri Args ***************************************** */


inline fun Activity.readByteUriArg(paramName: String): Byte = Activityx.readByteUriArg(this, paramName)

inline fun Activity.readByteUriArg(paramName: String, defaultValue: Byte): Byte = Activityx.readByteUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalByteUriArg(paramName: String): Byte? = Activityx.readOptionalByteUriArg(this, paramName)


inline fun Activity.readShortUriArg(paramName: String): Short = Activityx.readShortUriArg(this, paramName)

inline fun Activity.readShortUriArg(paramName: String, defaultValue: Short): Short = Activityx.readShortUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalShortUriArg(paramName: String): Short? = Activityx.readOptionalShortUriArg(this, paramName)


inline fun Activity.readIntUriArg(paramName: String): Int = Activityx.readIntUriArg(this, paramName)

inline fun Activity.readIntUriArg(paramName: String, defaultValue: Int): Int = Activityx.readIntUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalIntUriArg(paramName: String): Int? = Activityx.readOptionalIntUriArg(this, paramName)


inline fun Activity.readLongUriArg(paramName: String): Long = Activityx.readLongUriArg(this, paramName)

inline fun Activity.readLongUriArg(paramName: String, defaultValue: Long): Long = Activityx.readLongUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalLongUriArg(paramName: String): Long? = Activityx.readOptionalLongUriArg(this, paramName)


inline fun Activity.readFloatUriArg(paramName: String): Float = Activityx.readFloatUriArg(this, paramName)

inline fun Activity.readFloatUriArg(paramName: String, defaultValue: Float): Float = Activityx.readFloatUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalFloatUriArg(paramName: String): Float? = Activityx.readOptionalFloatUriArg(this, paramName)


inline fun Activity.readDoubleUriArg(paramName: String): Double = Activityx.readDoubleUriArg(this, paramName)

inline fun Activity.readDoubleUriArg(paramName: String, defaultValue: Double): Double = Activityx.readDoubleUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalDoubleUriArg(paramName: String): Double? = Activityx.readOptionalDoubleUriArg(this, paramName)


inline fun Activity.readBooleanUriArg(paramName: String): Boolean = Activityx.readBooleanUriArg(this, paramName)

inline fun Activity.readBooleanUriArg(paramName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalBooleanUriArg(paramName: String): Boolean? = Activityx.readOptionalBooleanUriArg(this, paramName)


inline fun Activity.readStringUriArg(paramName: String): String = Activityx.readStringUriArg(this, paramName)

inline fun Activity.readStringUriArg(paramName: String, defaultValue: String): String = Activityx.readStringUriArg(this, paramName, defaultValue)

inline fun Activity.readOptionalStringUriArg(paramName: String): String? = Activityx.readOptionalStringUriArg(this, paramName)