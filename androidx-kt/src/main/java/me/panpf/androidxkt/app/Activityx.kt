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
import android.content.Context
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


inline fun Activity.appContext(): Context = Activityx.appContext(this)


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


inline fun Activity.readByteUriArg(argName: String): Byte = Activityx.readByteUriArg(this, argName)

inline fun Activity.readByteUriArgOr(argName: String, defaultValue: Byte): Byte = Activityx.readByteUriArgOr(this, argName, defaultValue)

inline fun Activity.readByteUriArgOrNull(argName: String): Byte? = Activityx.readByteUriArgOrNull(this, argName)


inline fun Activity.readShortUriArg(argName: String): Short = Activityx.readShortUriArg(this, argName)

inline fun Activity.readShortUriArgOr(argName: String, defaultValue: Short): Short = Activityx.readShortUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriArgOrNull(argName: String): Short? = Activityx.readShortUriArgOrNull(this, argName)


inline fun Activity.readIntUriArg(argName: String): Int = Activityx.readIntUriArg(this, argName)

inline fun Activity.readIntUriArgOr(argName: String, defaultValue: Int): Int = Activityx.readIntUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriArgOrNull(argName: String): Int? = Activityx.readIntUriArgOrNull(this, argName)


inline fun Activity.readLongUriArg(argName: String): Long = Activityx.readLongUriArg(this, argName)

inline fun Activity.readLongUriArgOr(argName: String, defaultValue: Long): Long = Activityx.readLongUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriArgOrNull(argName: String): Long? = Activityx.readLongUriArgOrNull(this, argName)


inline fun Activity.readFloatUriArg(argName: String): Float = Activityx.readFloatUriArg(this, argName)

inline fun Activity.readFloatUriArgOr(argName: String, defaultValue: Float): Float = Activityx.readFloatUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriArgOrNull(argName: String): Float? = Activityx.readFloatUriArgOrNull(this, argName)


inline fun Activity.readDoubleUriArg(argName: String): Double = Activityx.readDoubleUriArg(this, argName)

inline fun Activity.readDoubleUriArgOr(argName: String, defaultValue: Double): Double = Activityx.readDoubleUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(argName: String): Double? = Activityx.readDoubleUriArgOrNull(this, argName)


inline fun Activity.readBooleanUriArg(argName: String): Boolean = Activityx.readBooleanUriArg(this, argName)

inline fun Activity.readBooleanUriArgOr(argName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(argName: String): Boolean? = Activityx.readBooleanUriArgOrNull(this, argName)


inline fun Activity.readStringUriArg(argName: String): String = Activityx.readStringUriArg(this, argName)

inline fun Activity.readStringUriArgOr(argName: String, defaultValue: String): String = Activityx.readStringUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriArgOrNull(argName: String): String? = Activityx.readStringUriArgOrNull(this, argName)


/* ************************************* Uri Intent Args ***************************************** */


inline fun Activity.readByteUriIntentArgOr(argName: String, defaultValue: Byte): Byte = Activityx.readByteUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriIntentArgOr(argName: String, defaultValue: Short): Short = Activityx.readShortUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriIntentArgOr(argName: String, defaultValue: Int): Int = Activityx.readIntUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriIntentArgOr(argName: String, defaultValue: Long): Long = Activityx.readLongUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriIntentArgOr(argName: String, defaultValue: Float): Float = Activityx.readFloatUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriIntentArgOr(argName: String, defaultValue: Double): Double = Activityx.readDoubleUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriIntentArg(argName: String): String = Activityx.readStringUriIntentArg(this, argName)

inline fun Activity.readStringUriIntentArgOr(argName: String, defaultValue: String): String = Activityx.readStringUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriIntentArgOrNull(argName: String): String? = Activityx.readStringUriIntentArgOrNull(this, argName)


/* ************************************* Intent Uri Args ***************************************** */


inline fun Activity.readByteIntentUriArgOr(argName: String, defaultValue: Byte): Byte = Activityx.readByteIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortIntentUriArgOr(argName: String, defaultValue: Short): Short = Activityx.readShortIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntIntentUriArgOr(argName: String, defaultValue: Int): Int = Activityx.readIntIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongIntentUriArgOr(argName: String, defaultValue: Long): Long = Activityx.readLongIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatIntentUriArgOr(argName: String, defaultValue: Float): Float = Activityx.readFloatIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleIntentUriArgOr(argName: String, defaultValue: Double): Double = Activityx.readDoubleIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): Boolean = Activityx.readBooleanIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringIntentUriArg(argName: String): String = Activityx.readStringIntentUriArg(this, argName)

inline fun Activity.readStringIntentUriArgOr(argName: String, defaultValue: String): String = Activityx.readStringIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringIntentUriArgOrNull(argName: String): String? = Activityx.readStringIntentUriArgOrNull(this, argName)