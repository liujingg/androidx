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


inline fun Activity.readByteArgOr(argName: String, defaultValue: Byte): Byte = Activityx.readByteArgOr(this, argName, defaultValue)


inline fun Activity.readByteArrayArg(argName: String): ByteArray = Activityx.readByteArrayArg(this, argName)

inline fun Activity.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Activityx.readByteArrayArgOr(this, argName, defaultValue)

inline fun Activity.readByteArrayArgOrNull(argName: String): ByteArray? = Activityx.readByteArrayArgOrNull(this, argName)


inline fun Activity.readShortArgOr(argName: String, defaultValue: Short): Short = Activityx.readShortArgOr(this, argName, defaultValue)


inline fun Activity.readShortArrayArg(argName: String): ShortArray = Activityx.readShortArrayArg(this, argName)

inline fun Activity.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Activityx.readShortArrayArgOr(this, argName, defaultValue)

inline fun Activity.readShortArrayArgOrNull(argName: String): ShortArray? = Activityx.readShortArrayArgOrNull(this, argName)


inline fun Activity.readIntArgOr(argName: String, defaultValue: Int): Int = Activityx.readIntArgOr(this, argName, defaultValue)


inline fun Activity.readIntArrayArg(argName: String): IntArray = Activityx.readIntArrayArg(this, argName)

inline fun Activity.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Activityx.readIntArrayArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayArgOrNull(argName: String): IntArray? = Activityx.readIntArrayArgOrNull(this, argName)


inline fun Activity.readIntArrayListArg(argName: String): ArrayList<Int> = Activityx.readIntArrayListArg(this, argName)

inline fun Activity.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Activityx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Activityx.readIntArrayListArgOrNull(this, argName)


inline fun Activity.readLongArgOr(argName: String, defaultValue: Long): Long = Activityx.readLongArgOr(this, argName, defaultValue)


inline fun Activity.readLongArrayArg(argName: String): LongArray = Activityx.readLongArrayArg(this, argName)

inline fun Activity.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Activityx.readLongArrayArgOr(this, argName, defaultValue)

inline fun Activity.readLongArrayArgOrNull(argName: String): LongArray? = Activityx.readLongArrayArgOrNull(this, argName)


inline fun Activity.readFloatArgOr(argName: String, defaultValue: Float): Float = Activityx.readFloatArgOr(this, argName, defaultValue)


inline fun Activity.readFloatArrayArg(argName: String): FloatArray = Activityx.readFloatArrayArg(this, argName)

inline fun Activity.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray = Activityx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun Activity.readFloatArrayArgOrNull(argName: String): FloatArray? = Activityx.readFloatArrayArgOrNull(this, argName)


inline fun Activity.readDoubleArgOr(argName: String, defaultValue: Double): Double = Activityx.readDoubleArgOr(this, argName, defaultValue)


inline fun Activity.readDoubleArrayArg(argName: String): DoubleArray = Activityx.readDoubleArrayArg(this, argName)

inline fun Activity.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray = Activityx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Activityx.readDoubleArrayArgOrNull(this, argName)


inline fun Activity.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanArgOr(this, argName, defaultValue)


inline fun Activity.readBooleanArrayArg(argName: String): BooleanArray = Activityx.readBooleanArrayArg(this, argName)

inline fun Activity.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray = Activityx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Activityx.readBooleanArrayArgOrNull(this, argName)


inline fun Activity.readCharArgOr(argName: String, defaultValue: Char): Char = Activityx.readCharArgOr(this, argName, defaultValue)


inline fun Activity.readCharArrayArg(argName: String): CharArray = Activityx.readCharArrayArg(this, argName)

inline fun Activity.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Activityx.readCharArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharArrayArgOrNull(argName: String): CharArray? = Activityx.readCharArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArg(argName: String): CharSequence = Activityx.readCharSequenceArg(this, argName)

inline fun Activity.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence = Activityx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArgOrNull(argName: String): CharSequence? = Activityx.readCharSequenceArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Activityx.readCharSequenceArrayArg(this, argName)

inline fun Activity.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Activityx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Activityx.readCharSequenceArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Activityx.readCharSequenceArrayListArg(this, argName)

inline fun Activity.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Activityx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? = Activityx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun Activity.readStringArg(argName: String): String = Activityx.readStringArg(this, argName)

inline fun Activity.readStringArgOr(argName: String, defaultValue: String): String = Activityx.readStringArgOr(this, argName, defaultValue)

inline fun Activity.readStringArgOrNull(argName: String): String? = Activityx.readStringArgOrNull(this, argName)


inline fun Activity.readStringArrayArg(argName: String): Array<String> = Activityx.readStringArrayArg(this, argName)

inline fun Activity.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> = Activityx.readStringArrayArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayArgOrNull(argName: String): Array<String>? = Activityx.readStringArrayArgOrNull(this, argName)


inline fun Activity.readStringArrayListArg(argName: String): ArrayList<String> = Activityx.readStringArrayListArg(this, argName)

inline fun Activity.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Activityx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Activityx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArg(argName: String): V = Activityx.readParcelableArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArgOr(argName: String, defaultValue: V): V = Activityx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArgOrNull(argName: String): V? = Activityx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String): Array<V> = Activityx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> = Activityx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOrNull(argName: String): Array<V>? = Activityx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String): ArrayList<V> = Activityx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Activityx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? = Activityx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Serializable> Activity.readSerializableArg(argName: String): V = Activityx.readSerializableArg(this, argName)

inline fun <V : Serializable> Activity.readSerializableArgOr(argName: String, defaultValue: V): V = Activityx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> Activity.readSerializableArgOrNull(argName: String): V? = Activityx.readSerializableArgOrNull(this, argName)


inline fun Activity.readBundleArg(argName: String): Bundle = Activityx.readBundleArg(this, argName)

inline fun Activity.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Activityx.readBundleArgOr(this, argName, defaultValue)

inline fun Activity.readBundleArgOrNull(argName: String): Bundle? = Activityx.readBundleArgOrNull(this, argName)


inline fun Activity.readExtrasArg(): Bundle = Activityx.readExtrasArg(this)

inline fun Activity.readExtrasArgOr(defaultValue: Bundle): Bundle = Activityx.readExtrasArgOr(this, defaultValue)

inline fun Activity.readExtrasArgOrNull(): Bundle? = Activityx.readExtrasArgOrNull(this)


/* ************************************* Uri Args ***************************************** */


inline fun Activity.readByteUriArg(paramName: String): Byte = Activityx.readByteUriArg(this, paramName)

inline fun Activity.readByteUriArgOr(paramName: String, defaultValue: Byte): Byte = Activityx.readByteUriArgOr(this, paramName, defaultValue)

inline fun Activity.readByteUriArgOrNull(paramName: String): Byte? = Activityx.readByteUriArgOrNull(this, paramName)


inline fun Activity.readShortUriArg(paramName: String): Short = Activityx.readShortUriArg(this, paramName)

inline fun Activity.readShortUriArgOr(paramName: String, defaultValue: Short): Short = Activityx.readShortUriArgOr(this, paramName, defaultValue)

inline fun Activity.readShortUriArgOrNull(paramName: String): Short? = Activityx.readShortUriArgOrNull(this, paramName)


inline fun Activity.readIntUriArg(paramName: String): Int = Activityx.readIntUriArg(this, paramName)

inline fun Activity.readIntUriArgOr(paramName: String, defaultValue: Int): Int = Activityx.readIntUriArgOr(this, paramName, defaultValue)

inline fun Activity.readIntUriArgOrNull(paramName: String): Int? = Activityx.readIntUriArgOrNull(this, paramName)


inline fun Activity.readLongUriArg(paramName: String): Long = Activityx.readLongUriArg(this, paramName)

inline fun Activity.readLongUriArgOr(paramName: String, defaultValue: Long): Long = Activityx.readLongUriArgOr(this, paramName, defaultValue)

inline fun Activity.readLongUriArgOrNull(paramName: String): Long? = Activityx.readLongUriArgOrNull(this, paramName)


inline fun Activity.readFloatUriArg(paramName: String): Float = Activityx.readFloatUriArg(this, paramName)

inline fun Activity.readFloatUriArgOr(paramName: String, defaultValue: Float): Float = Activityx.readFloatUriArgOr(this, paramName, defaultValue)

inline fun Activity.readFloatUriArgOrNull(paramName: String): Float? = Activityx.readFloatUriArgOrNull(this, paramName)


inline fun Activity.readDoubleUriArg(paramName: String): Double = Activityx.readDoubleUriArg(this, paramName)

inline fun Activity.readDoubleUriArgOr(paramName: String, defaultValue: Double): Double = Activityx.readDoubleUriArgOr(this, paramName, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(paramName: String): Double? = Activityx.readDoubleUriArgOrNull(this, paramName)


inline fun Activity.readBooleanUriArg(paramName: String): Boolean = Activityx.readBooleanUriArg(this, paramName)

inline fun Activity.readBooleanUriArgOr(paramName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanUriArgOr(this, paramName, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(paramName: String): Boolean? = Activityx.readBooleanUriArgOrNull(this, paramName)


inline fun Activity.readStringUriArg(paramName: String): String = Activityx.readStringUriArg(this, paramName)

inline fun Activity.readStringUriArgOr(paramName: String, defaultValue: String): String = Activityx.readStringUriArgOr(this, paramName, defaultValue)

inline fun Activity.readStringUriArgOrNull(paramName: String): String? = Activityx.readStringUriArgOrNull(this, paramName)