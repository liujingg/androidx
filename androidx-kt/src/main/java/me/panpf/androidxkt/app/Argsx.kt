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

@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")

package me.panpf.androidxkt.app

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import me.panpf.androidx.app.Argsx
import java.io.Serializable
import java.util.*


/* ************************************* Activity Intent Args ***************************************** */


inline fun Activity.readByteArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argName, defaultValue)


inline fun Activity.readByteArrayArg(argName: String): ByteArray = Argsx.readByteArrayArg(this, argName)

inline fun Activity.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argName, defaultValue)

inline fun Activity.readByteArrayArgOrNull(argName: String): ByteArray? = Argsx.readByteArrayArgOrNull(this, argName)


inline fun Activity.readShortArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortArgOr(this, argName, defaultValue)


inline fun Activity.readShortArrayArg(argName: String): ShortArray = Argsx.readShortArrayArg(this, argName)

inline fun Activity.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argName, defaultValue)

inline fun Activity.readShortArrayArgOrNull(argName: String): ShortArray? = Argsx.readShortArrayArgOrNull(this, argName)


inline fun Activity.readIntArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntArgOr(this, argName, defaultValue)


inline fun Activity.readIntArrayArg(argName: String): IntArray = Argsx.readIntArrayArg(this, argName)

inline fun Activity.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayArgOrNull(argName: String): IntArray? = Argsx.readIntArrayArgOrNull(this, argName)


inline fun Activity.readIntArrayListArg(argName: String): ArrayList<Int> = Argsx.readIntArrayListArg(this, argName)

inline fun Activity.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argName)


inline fun Activity.readLongArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongArgOr(this, argName, defaultValue)


inline fun Activity.readLongArrayArg(argName: String): LongArray = Argsx.readLongArrayArg(this, argName)

inline fun Activity.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argName, defaultValue)

inline fun Activity.readLongArrayArgOrNull(argName: String): LongArray? = Argsx.readLongArrayArgOrNull(this, argName)


inline fun Activity.readFloatArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argName, defaultValue)


inline fun Activity.readFloatArrayArg(argName: String): FloatArray = Argsx.readFloatArrayArg(this, argName)

inline fun Activity.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun Activity.readFloatArrayArgOrNull(argName: String): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argName)


inline fun Activity.readDoubleArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argName, defaultValue)


inline fun Activity.readDoubleArrayArg(argName: String): DoubleArray = Argsx.readDoubleArrayArg(this, argName)

inline fun Activity.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argName)


inline fun Activity.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argName, defaultValue)


inline fun Activity.readBooleanArrayArg(argName: String): BooleanArray = Argsx.readBooleanArrayArg(this, argName)

inline fun Activity.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argName)


inline fun Activity.readCharArgOr(argName: String, defaultValue: Char): Char = Argsx.readCharArgOr(this, argName, defaultValue)


inline fun Activity.readCharArrayArg(argName: String): CharArray = Argsx.readCharArrayArg(this, argName)

inline fun Activity.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharArrayArgOrNull(argName: String): CharArray? = Argsx.readCharArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArg(argName: String): CharSequence = Argsx.readCharSequenceArg(this, argName)

inline fun Activity.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArgOrNull(argName: String): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argName)

inline fun Activity.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArg(this, argName)

inline fun Activity.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun Activity.readStringArg(argName: String): String = Argsx.readStringArg(this, argName)

inline fun Activity.readStringArgOr(argName: String, defaultValue: String): String = Argsx.readStringArgOr(this, argName, defaultValue)

inline fun Activity.readStringArgOrNull(argName: String): String? = Argsx.readStringArgOrNull(this, argName)


inline fun Activity.readStringArrayArg(argName: String): Array<String> = Argsx.readStringArrayArg(this, argName)

inline fun Activity.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayArgOrNull(argName: String): Array<String>? = Argsx.readStringArrayArgOrNull(this, argName)


inline fun Activity.readStringArrayListArg(argName: String): ArrayList<String> = Argsx.readStringArrayListArg(this, argName)

inline fun Activity.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArg(argName: String): V = Argsx.readParcelableArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArgOr(argName: String, defaultValue: V): V = Argsx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArgOrNull(argName: String): V? = Argsx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String): Array<V> = Argsx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOrNull(argName: String): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String): ArrayList<V> = Argsx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Serializable> Activity.readSerializableArg(argName: String): V = Argsx.readSerializableArg(this, argName)

inline fun <V : Serializable> Activity.readSerializableArgOr(argName: String, defaultValue: V): V = Argsx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> Activity.readSerializableArgOrNull(argName: String): V? = Argsx.readSerializableArgOrNull(this, argName)


inline fun Activity.readBundleArg(argName: String): Bundle = Argsx.readBundleArg(this, argName)

inline fun Activity.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argName, defaultValue)

inline fun Activity.readBundleArgOrNull(argName: String): Bundle? = Argsx.readBundleArgOrNull(this, argName)


/* ************************************* Activity Uri Args ***************************************** */


inline fun Activity.readByteUriArg(argName: String): Byte = Argsx.readByteUriArg(this, argName)

inline fun Activity.readByteUriArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteUriArgOr(this, argName, defaultValue)

inline fun Activity.readByteUriArgOrNull(argName: String): Byte? = Argsx.readByteUriArgOrNull(this, argName)


inline fun Activity.readShortUriArg(argName: String): Short = Argsx.readShortUriArg(this, argName)

inline fun Activity.readShortUriArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriArgOrNull(argName: String): Short? = Argsx.readShortUriArgOrNull(this, argName)


inline fun Activity.readIntUriArg(argName: String): Int = Argsx.readIntUriArg(this, argName)

inline fun Activity.readIntUriArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriArgOrNull(argName: String): Int? = Argsx.readIntUriArgOrNull(this, argName)


inline fun Activity.readLongUriArg(argName: String): Long = Argsx.readLongUriArg(this, argName)

inline fun Activity.readLongUriArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriArgOrNull(argName: String): Long? = Argsx.readLongUriArgOrNull(this, argName)


inline fun Activity.readFloatUriArg(argName: String): Float = Argsx.readFloatUriArg(this, argName)

inline fun Activity.readFloatUriArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriArgOrNull(argName: String): Float? = Argsx.readFloatUriArgOrNull(this, argName)


inline fun Activity.readDoubleUriArg(argName: String): Double = Argsx.readDoubleUriArg(this, argName)

inline fun Activity.readDoubleUriArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(argName: String): Double? = Argsx.readDoubleUriArgOrNull(this, argName)


inline fun Activity.readBooleanUriArg(argName: String): Boolean = Argsx.readBooleanUriArg(this, argName)

inline fun Activity.readBooleanUriArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(argName: String): Boolean? = Argsx.readBooleanUriArgOrNull(this, argName)


inline fun Activity.readStringUriArg(argName: String): String = Argsx.readStringUriArg(this, argName)

inline fun Activity.readStringUriArgOr(argName: String, defaultValue: String): String = Argsx.readStringUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriArgOrNull(argName: String): String? = Argsx.readStringUriArgOrNull(this, argName)


/* ************************************* Activity Uri Intent Args ***************************************** */


inline fun Activity.readByteUriIntentArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriIntentArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriIntentArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriIntentArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriIntentArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriIntentArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriIntentArg(argName: String): String = Argsx.readStringUriIntentArg(this, argName)

inline fun Activity.readStringUriIntentArgOr(argName: String, defaultValue: String): String = Argsx.readStringUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriIntentArgOrNull(argName: String): String? = Argsx.readStringUriIntentArgOrNull(this, argName)


/* ************************************* Activity Intent Uri Args ***************************************** */


inline fun Activity.readByteIntentUriArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortIntentUriArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntIntentUriArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongIntentUriArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatIntentUriArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleIntentUriArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringIntentUriArg(argName: String): String = Argsx.readStringIntentUriArg(this, argName)

inline fun Activity.readStringIntentUriArgOr(argName: String, defaultValue: String): String = Argsx.readStringIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringIntentUriArgOrNull(argName: String): String? = Argsx.readStringIntentUriArgOrNull(this, argName)


/* ************************************* Activity Intent Args ***************************************** */


inline fun Activity.readByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argNameResId, defaultValue)


inline fun Activity.readByteArrayArg(@StringRes argNameResId: Int): ByteArray = Argsx.readByteArrayArg(this, argNameResId)

inline fun Activity.readByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteArrayArgOrNull(@StringRes argNameResId: Int): ByteArray? = Argsx.readByteArrayArgOrNull(this, argNameResId)


inline fun Activity.readShortArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortArgOr(this, argNameResId, defaultValue)


inline fun Activity.readShortArrayArg(@StringRes argNameResId: Int): ShortArray = Argsx.readShortArrayArg(this, argNameResId)

inline fun Activity.readShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortArrayArgOrNull(@StringRes argNameResId: Int): ShortArray? = Argsx.readShortArrayArgOrNull(this, argNameResId)


inline fun Activity.readIntArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntArgOr(this, argNameResId, defaultValue)


inline fun Activity.readIntArrayArg(@StringRes argNameResId: Int): IntArray = Argsx.readIntArrayArg(this, argNameResId)

inline fun Activity.readIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntArrayArgOrNull(@StringRes argNameResId: Int): IntArray? = Argsx.readIntArrayArgOrNull(this, argNameResId)


inline fun Activity.readIntArrayListArg(@StringRes argNameResId: Int): ArrayList<Int> = Argsx.readIntArrayListArg(this, argNameResId)

inline fun Activity.readIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argNameResId)


inline fun Activity.readLongArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongArgOr(this, argNameResId, defaultValue)


inline fun Activity.readLongArrayArg(@StringRes argNameResId: Int): LongArray = Argsx.readLongArrayArg(this, argNameResId)

inline fun Activity.readLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongArrayArgOrNull(@StringRes argNameResId: Int): LongArray? = Argsx.readLongArrayArgOrNull(this, argNameResId)


inline fun Activity.readFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argNameResId, defaultValue)


inline fun Activity.readFloatArrayArg(@StringRes argNameResId: Int): FloatArray = Argsx.readFloatArrayArg(this, argNameResId)

inline fun Activity.readFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatArrayArgOrNull(@StringRes argNameResId: Int): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argNameResId)


inline fun Activity.readDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argNameResId, defaultValue)


inline fun Activity.readDoubleArrayArg(@StringRes argNameResId: Int): DoubleArray = Argsx.readDoubleArrayArg(this, argNameResId)

inline fun Activity.readDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleArrayArgOrNull(@StringRes argNameResId: Int): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argNameResId)


inline fun Activity.readBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argNameResId, defaultValue)


inline fun Activity.readBooleanArrayArg(@StringRes argNameResId: Int): BooleanArray = Argsx.readBooleanArrayArg(this, argNameResId)

inline fun Activity.readBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanArrayArgOrNull(@StringRes argNameResId: Int): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharArgOr(@StringRes argNameResId: Int, defaultValue: Char): Char = Argsx.readCharArgOr(this, argNameResId, defaultValue)


inline fun Activity.readCharArrayArg(@StringRes argNameResId: Int): CharArray = Argsx.readCharArrayArg(this, argNameResId)

inline fun Activity.readCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharArrayArgOrNull(@StringRes argNameResId: Int): CharArray? = Argsx.readCharArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArg(@StringRes argNameResId: Int): CharSequence = Argsx.readCharSequenceArg(this, argNameResId)

inline fun Activity.readCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArgOrNull(@StringRes argNameResId: Int): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArrayArg(@StringRes argNameResId: Int): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argNameResId)

inline fun Activity.readCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArrayListArg(@StringRes argNameResId: Int): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArg(this, argNameResId)

inline fun Activity.readCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argNameResId)


inline fun Activity.readStringArg(@StringRes argNameResId: Int): String = Argsx.readStringArg(this, argNameResId)

inline fun Activity.readStringArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringArgOrNull(this, argNameResId)


inline fun Activity.readStringArrayArg(@StringRes argNameResId: Int): Array<String> = Argsx.readStringArrayArg(this, argNameResId)

inline fun Activity.readStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArrayArgOrNull(@StringRes argNameResId: Int): Array<String>? = Argsx.readStringArrayArgOrNull(this, argNameResId)


inline fun Activity.readStringArrayListArg(@StringRes argNameResId: Int): ArrayList<String> = Argsx.readStringArrayListArg(this, argNameResId)

inline fun Activity.readStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArg(@StringRes argNameResId: Int): V = Argsx.readParcelableArg(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readParcelableArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readParcelableArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArrayArg(@StringRes argNameResId: Int): Array<V> = Argsx.readParcelableArrayArg(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOrNull(@StringRes argNameResId: Int): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArrayListArg(@StringRes argNameResId: Int): ArrayList<V> = Argsx.readParcelableArrayListArg(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argNameResId)


inline fun <V : Serializable> Activity.readSerializableArg(@StringRes argNameResId: Int): V = Argsx.readSerializableArg(this, argNameResId)

inline fun <V : Serializable> Activity.readSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readSerializableArgOr(this, argNameResId, defaultValue)

inline fun <V : Serializable> Activity.readSerializableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readSerializableArgOrNull(this, argNameResId)


inline fun Activity.readBundleArg(@StringRes argNameResId: Int): Bundle = Argsx.readBundleArg(this, argNameResId)

inline fun Activity.readBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBundleArgOrNull(@StringRes argNameResId: Int): Bundle? = Argsx.readBundleArgOrNull(this, argNameResId)


inline fun Activity.readExtrasArg(): Bundle = Argsx.readExtrasArg(this)

inline fun Activity.readExtrasArgOr(defaultValue: Bundle): Bundle = Argsx.readExtrasArgOr(this, defaultValue)

inline fun Activity.readExtrasArgOrNull(): Bundle? = Argsx.readExtrasArgOrNull(this)


/* ************************************* Activity Uri Args ***************************************** */


inline fun Activity.readByteUriArg(@StringRes argNameResId: Int): Byte = Argsx.readByteUriArg(this, argNameResId)

inline fun Activity.readByteUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteUriArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteUriArgOrNull(this, argNameResId)


inline fun Activity.readShortUriArg(@StringRes argNameResId: Int): Short = Argsx.readShortUriArg(this, argNameResId)

inline fun Activity.readShortUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortUriArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortUriArgOrNull(this, argNameResId)


inline fun Activity.readIntUriArg(@StringRes argNameResId: Int): Int = Argsx.readIntUriArg(this, argNameResId)

inline fun Activity.readIntUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntUriArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntUriArgOrNull(this, argNameResId)


inline fun Activity.readLongUriArg(@StringRes argNameResId: Int): Long = Argsx.readLongUriArg(this, argNameResId)

inline fun Activity.readLongUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongUriArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongUriArgOrNull(this, argNameResId)


inline fun Activity.readFloatUriArg(@StringRes argNameResId: Int): Float = Argsx.readFloatUriArg(this, argNameResId)

inline fun Activity.readFloatUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatUriArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatUriArgOrNull(this, argNameResId)


inline fun Activity.readDoubleUriArg(@StringRes argNameResId: Int): Double = Argsx.readDoubleUriArg(this, argNameResId)

inline fun Activity.readDoubleUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleUriArgOrNull(this, argNameResId)


inline fun Activity.readBooleanUriArg(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanUriArg(this, argNameResId)

inline fun Activity.readBooleanUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanUriArgOrNull(this, argNameResId)


inline fun Activity.readStringUriArg(@StringRes argNameResId: Int): String = Argsx.readStringUriArg(this, argNameResId)

inline fun Activity.readStringUriArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringUriArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringUriArgOrNull(this, argNameResId)


/* ************************************* Activity Uri Intent Args ***************************************** */


inline fun Activity.readByteUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringUriIntentArg(@StringRes argNameResId: Int): String = Argsx.readStringUriIntentArg(this, argNameResId)

inline fun Activity.readStringUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringUriIntentArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringUriIntentArgOrNull(this, argNameResId)


/* ************************************* Activity Intent Uri Args ***************************************** */


inline fun Activity.readByteIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringIntentUriArg(@StringRes argNameResId: Int): String = Argsx.readStringIntentUriArg(this, argNameResId)

inline fun Activity.readStringIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringIntentUriArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringIntentUriArgOrNull(this, argNameResId)


/* ************************************* SupportFragment Args ***************************************** */


inline fun androidx.fragment.app.Fragment.readByteArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readByteArrayArg(argName: String): ByteArray = Argsx.readByteArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray =
        Argsx.readByteArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readByteArrayArgOrNull(argName: String): ByteArray? = Argsx.readByteArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readShortArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readShortArrayArg(argName: String): ShortArray = Argsx.readShortArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray =
        Argsx.readShortArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readShortArrayArgOrNull(argName: String): ShortArray? = Argsx.readShortArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readIntArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readIntArrayArg(argName: String): IntArray = Argsx.readIntArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readIntArrayArgOrNull(argName: String): IntArray? = Argsx.readIntArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Argsx.readIntArrayListArg(this, argName)

inline fun androidx.fragment.app.Fragment.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Argsx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readLongArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readLongArrayArg(argName: String): LongArray = Argsx.readLongArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray =
        Argsx.readLongArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readLongArrayArgOrNull(argName: String): LongArray? = Argsx.readLongArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readFloatArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Argsx.readFloatArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray =
        Argsx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readFloatArrayArgOrNull(argName: String): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readDoubleArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Argsx.readDoubleArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray =
        Argsx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Argsx.readBooleanArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray =
        Argsx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readCharArgOr(argName: String, defaultValue: Char): Char = Argsx.readCharArgOr(this, argName, defaultValue)


inline fun androidx.fragment.app.Fragment.readCharArrayArg(argName: String): CharArray = Argsx.readCharArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray =
        Argsx.readCharArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharArrayArgOrNull(argName: String): CharArray? = Argsx.readCharArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Argsx.readCharSequenceArg(this, argName)

inline fun androidx.fragment.app.Fragment.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence =
        Argsx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArgOrNull(argName: String): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? =
        Argsx.readCharSequenceArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArg(this, argName)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? =
        Argsx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readStringArg(argName: String): String = Argsx.readStringArg(this, argName)

inline fun androidx.fragment.app.Fragment.readStringArgOr(argName: String, defaultValue: String): String = Argsx.readStringArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArgOrNull(argName: String): String? = Argsx.readStringArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readStringArrayArg(argName: String): Array<String> = Argsx.readStringArrayArg(this, argName)

inline fun androidx.fragment.app.Fragment.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> =
        Argsx.readStringArrayArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArrayArgOrNull(argName: String): Array<String>? = Argsx.readStringArrayArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Argsx.readStringArrayListArg(this, argName)

inline fun androidx.fragment.app.Fragment.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArg(argName: String): V = Argsx.readParcelableArg(this, argName)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArgOr(argName: String, defaultValue: V): V =
        Argsx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArgOrNull(argName: String): V? = Argsx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Argsx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArgOrNull(argName: String): Array<V>? =
        Argsx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> =
        Argsx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? =
        Argsx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> =
        Argsx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): SparseArray<V> =
        Argsx.readSparseParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArgOrNull(argName: String): SparseArray<V>? =
        Argsx.readSparseParcelableArrayArgOrNull(this, argName)


inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArg(argName: String): V = Argsx.readSerializableArg(this, argName)

inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArgOr(argName: String, defaultValue: V): V =
        Argsx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArgOrNull(argName: String): V? = Argsx.readSerializableArgOrNull(this, argName)


inline fun androidx.fragment.app.Fragment.readBundleArg(argName: String): Bundle = Argsx.readBundleArg(this, argName)

inline fun androidx.fragment.app.Fragment.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argName, defaultValue)

inline fun androidx.fragment.app.Fragment.readBundleArgOrNull(argName: String): Bundle? = Argsx.readBundleArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArg(argName: String): IBinder = Argsx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArgOr(argName: String, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArgOrNull(argName: String): IBinder? = Argsx.readBinderArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArg(argName: String): Size = Argsx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArgOr(argName: String, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArgOrNull(argName: String): Size? = Argsx.readSizeArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArg(argName: String): SizeF = Argsx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArgOr(argName: String, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArgOrNull(argName: String): SizeF? = Argsx.readSizeFArgOrNull(this, argName)


/* ************************************* OriginFragment Args ***************************************** */


inline fun android.app.Fragment.readByteArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readByteArrayArg(argName: String): ByteArray = Argsx.readByteArrayArg(this, argName)

inline fun android.app.Fragment.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readByteArrayArgOrNull(argName: String): ByteArray? = Argsx.readByteArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readShortArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readShortArrayArg(argName: String): ShortArray = Argsx.readShortArrayArg(this, argName)

inline fun android.app.Fragment.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readShortArrayArgOrNull(argName: String): ShortArray? = Argsx.readShortArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readIntArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readIntArrayArg(argName: String): IntArray = Argsx.readIntArrayArg(this, argName)

inline fun android.app.Fragment.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readIntArrayArgOrNull(argName: String): IntArray? = Argsx.readIntArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Argsx.readIntArrayListArg(this, argName)

inline fun android.app.Fragment.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Argsx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argName)


inline fun android.app.Fragment.readLongArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readLongArrayArg(argName: String): LongArray = Argsx.readLongArrayArg(this, argName)

inline fun android.app.Fragment.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readLongArrayArgOrNull(argName: String): LongArray? = Argsx.readLongArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readFloatArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Argsx.readFloatArrayArg(this, argName)

inline fun android.app.Fragment.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray =
        Argsx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readFloatArrayArgOrNull(argName: String): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readDoubleArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Argsx.readDoubleArrayArg(this, argName)

inline fun android.app.Fragment.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray =
        Argsx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Argsx.readBooleanArrayArg(this, argName)

inline fun android.app.Fragment.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray =
        Argsx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharArgOr(argName: String, defaultValue: Char): Char = Argsx.readCharArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readCharArrayArg(argName: String): CharArray = Argsx.readCharArrayArg(this, argName)

inline fun android.app.Fragment.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharArrayArgOrNull(argName: String): CharArray? = Argsx.readCharArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Argsx.readCharSequenceArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence =
        Argsx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArgOrNull(argName: String): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? =
        Argsx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArg(argName: String): String = Argsx.readStringArg(this, argName)

inline fun android.app.Fragment.readStringArgOr(argName: String, defaultValue: String): String = Argsx.readStringArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArgOrNull(argName: String): String? = Argsx.readStringArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArrayArg(argName: String): Array<String> = Argsx.readStringArrayArg(this, argName)

inline fun android.app.Fragment.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> =
        Argsx.readStringArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArrayArgOrNull(argName: String): Array<String>? = Argsx.readStringArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Argsx.readStringArrayListArg(this, argName)

inline fun android.app.Fragment.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArg(argName: String): V = Argsx.readParcelableArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOr(argName: String, defaultValue: V): V = Argsx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOrNull(argName: String): V? = Argsx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Argsx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOrNull(argName: String): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> = Argsx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? =
        Argsx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> = Argsx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): SparseArray<V> =
        Argsx.readSparseParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOrNull(argName: String): SparseArray<V>? =
        Argsx.readSparseParcelableArrayArgOrNull(this, argName)


inline fun <V : Serializable> android.app.Fragment.readSerializableArg(argName: String): V = Argsx.readSerializableArg(this, argName)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOr(argName: String, defaultValue: V): V =
        Argsx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOrNull(argName: String): V? = Argsx.readSerializableArgOrNull(this, argName)


inline fun android.app.Fragment.readBundleArg(argName: String): Bundle = Argsx.readBundleArg(this, argName)

inline fun android.app.Fragment.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readBundleArgOrNull(argName: String): Bundle? = Argsx.readBundleArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArg(argName: String): IBinder = Argsx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOr(argName: String, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOrNull(argName: String): IBinder? = Argsx.readBinderArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArg(argName: String): Size = Argsx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOr(argName: String, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOrNull(argName: String): Size? = Argsx.readSizeArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArg(argName: String): SizeF = Argsx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOr(argName: String, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOrNull(argName: String): SizeF? = Argsx.readSizeFArgOrNull(this, argName)


/* ************************************* SupportFragment Args ***************************************** */


inline fun androidx.fragment.app.Fragment.readByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readByteArrayArg(@StringRes argNameResId: Int): ByteArray = Argsx.readByteArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ByteArray =
        Argsx.readByteArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readByteArrayArgOrNull(@StringRes argNameResId: Int): ByteArray? = Argsx.readByteArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readShortArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readShortArrayArg(@StringRes argNameResId: Int): ShortArray = Argsx.readShortArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ShortArray =
        Argsx.readShortArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readShortArrayArgOrNull(@StringRes argNameResId: Int): ShortArray? = Argsx.readShortArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readIntArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readIntArrayArg(@StringRes argNameResId: Int): IntArray = Argsx.readIntArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readIntArrayArgOrNull(@StringRes argNameResId: Int): IntArray? = Argsx.readIntArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readIntArrayListArg(@StringRes argNameResId: Int): ArrayList<Int> = Argsx.readIntArrayListArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Argsx.readIntArrayListArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readIntArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readLongArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readLongArrayArg(@StringRes argNameResId: Int): LongArray = Argsx.readLongArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): LongArray =
        Argsx.readLongArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readLongArrayArgOrNull(@StringRes argNameResId: Int): LongArray? = Argsx.readLongArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readFloatArrayArg(@StringRes argNameResId: Int): FloatArray = Argsx.readFloatArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): FloatArray =
        Argsx.readFloatArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readFloatArrayArgOrNull(@StringRes argNameResId: Int): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readDoubleArrayArg(@StringRes argNameResId: Int): DoubleArray = Argsx.readDoubleArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): DoubleArray =
        Argsx.readDoubleArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readDoubleArrayArgOrNull(@StringRes argNameResId: Int): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readBooleanArrayArg(@StringRes argNameResId: Int): BooleanArray = Argsx.readBooleanArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): BooleanArray =
        Argsx.readBooleanArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readBooleanArrayArgOrNull(@StringRes argNameResId: Int): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readCharArgOr(@StringRes argNameResId: Int, defaultValue: Char): Char = Argsx.readCharArgOr(this, argNameResId, defaultValue)


inline fun androidx.fragment.app.Fragment.readCharArrayArg(@StringRes argNameResId: Int): CharArray = Argsx.readCharArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): CharArray =
        Argsx.readCharArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharArrayArgOrNull(@StringRes argNameResId: Int): CharArray? = Argsx.readCharArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readCharSequenceArg(@StringRes argNameResId: Int): CharSequence = Argsx.readCharSequenceArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): CharSequence =
        Argsx.readCharSequenceArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArgOrNull(@StringRes argNameResId: Int): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArg(@StringRes argNameResId: Int): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): Array<CharSequence>? =
        Argsx.readCharSequenceArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArg(@StringRes argNameResId: Int): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<CharSequence>? =
        Argsx.readCharSequenceArrayListArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readStringArg(@StringRes argNameResId: Int): String = Argsx.readStringArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readStringArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readStringArrayArg(@StringRes argNameResId: Int): Array<String> = Argsx.readStringArrayArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): Array<String> =
        Argsx.readStringArrayArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArrayArgOrNull(@StringRes argNameResId: Int): Array<String>? = Argsx.readStringArrayArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readStringArrayListArg(@StringRes argNameResId: Int): ArrayList<String> = Argsx.readStringArrayListArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readStringArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArg(@StringRes argNameResId: Int): V = Argsx.readParcelableArg(this, argNameResId)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): V =
        Argsx.readParcelableArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readParcelableArgOrNull(this, argNameResId)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArg(@StringRes argNameResId: Int): Array<V> = Argsx.readParcelableArrayArg(this, argNameResId)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayArgOrNull(@StringRes argNameResId: Int): Array<V>? =
        Argsx.readParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArg(@StringRes argNameResId: Int): ArrayList<V> =
        Argsx.readParcelableArrayListArg(this, argNameResId)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<V>? =
        Argsx.readParcelableArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArg(@StringRes argNameResId: Int): SparseArray<V> =
        Argsx.readSparseParcelableArrayArg(this, argNameResId)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): SparseArray<V> =
        Argsx.readSparseParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> androidx.fragment.app.Fragment.readSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): SparseArray<V>? =
        Argsx.readSparseParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArg(@StringRes argNameResId: Int): V = Argsx.readSerializableArg(this, argNameResId)

inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): V =
        Argsx.readSerializableArgOr(this, argNameResId, defaultValue)

inline fun <V : Serializable> androidx.fragment.app.Fragment.readSerializableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readSerializableArgOrNull(this, argNameResId)


inline fun androidx.fragment.app.Fragment.readBundleArg(@StringRes argNameResId: Int): Bundle = Argsx.readBundleArg(this, argNameResId)

inline fun androidx.fragment.app.Fragment.readBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argNameResId, defaultValue)

inline fun androidx.fragment.app.Fragment.readBundleArgOrNull(@StringRes argNameResId: Int): Bundle? = Argsx.readBundleArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArg(@StringRes argNameResId: Int): IBinder = Argsx.readBinderArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun androidx.fragment.app.Fragment.readBinderArgOrNull(@StringRes argNameResId: Int): IBinder? = Argsx.readBinderArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArg(@StringRes argNameResId: Int): Size = Argsx.readSizeArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeArgOrNull(@StringRes argNameResId: Int): Size? = Argsx.readSizeArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArg(@StringRes argNameResId: Int): SizeF = Argsx.readSizeFArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun androidx.fragment.app.Fragment.readSizeFArgOrNull(@StringRes argNameResId: Int): SizeF? = Argsx.readSizeFArgOrNull(this, argNameResId)


/* ************************************* OriginFragment Args ***************************************** */


inline fun android.app.Fragment.readByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readByteArrayArg(@StringRes argNameResId: Int): ByteArray = Argsx.readByteArrayArg(this, argNameResId)

inline fun android.app.Fragment.readByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readByteArrayArgOrNull(@StringRes argNameResId: Int): ByteArray? = Argsx.readByteArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readShortArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readShortArrayArg(@StringRes argNameResId: Int): ShortArray = Argsx.readShortArrayArg(this, argNameResId)

inline fun android.app.Fragment.readShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readShortArrayArgOrNull(@StringRes argNameResId: Int): ShortArray? = Argsx.readShortArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readIntArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readIntArrayArg(@StringRes argNameResId: Int): IntArray = Argsx.readIntArrayArg(this, argNameResId)

inline fun android.app.Fragment.readIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readIntArrayArgOrNull(@StringRes argNameResId: Int): IntArray? = Argsx.readIntArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readIntArrayListArg(@StringRes argNameResId: Int): ArrayList<Int> = Argsx.readIntArrayListArg(this, argNameResId)

inline fun android.app.Fragment.readIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Argsx.readIntArrayListArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readIntArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readLongArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readLongArrayArg(@StringRes argNameResId: Int): LongArray = Argsx.readLongArrayArg(this, argNameResId)

inline fun android.app.Fragment.readLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readLongArrayArgOrNull(@StringRes argNameResId: Int): LongArray? = Argsx.readLongArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readFloatArrayArg(@StringRes argNameResId: Int): FloatArray = Argsx.readFloatArrayArg(this, argNameResId)

inline fun android.app.Fragment.readFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): FloatArray =
        Argsx.readFloatArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readFloatArrayArgOrNull(@StringRes argNameResId: Int): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readDoubleArrayArg(@StringRes argNameResId: Int): DoubleArray = Argsx.readDoubleArrayArg(this, argNameResId)

inline fun android.app.Fragment.readDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): DoubleArray =
        Argsx.readDoubleArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readDoubleArrayArgOrNull(@StringRes argNameResId: Int): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readBooleanArrayArg(@StringRes argNameResId: Int): BooleanArray = Argsx.readBooleanArrayArg(this, argNameResId)

inline fun android.app.Fragment.readBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): BooleanArray =
        Argsx.readBooleanArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readBooleanArrayArgOrNull(@StringRes argNameResId: Int): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readCharArgOr(@StringRes argNameResId: Int, defaultValue: Char): Char = Argsx.readCharArgOr(this, argNameResId, defaultValue)


inline fun android.app.Fragment.readCharArrayArg(@StringRes argNameResId: Int): CharArray = Argsx.readCharArrayArg(this, argNameResId)

inline fun android.app.Fragment.readCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readCharArrayArgOrNull(@StringRes argNameResId: Int): CharArray? = Argsx.readCharArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readCharSequenceArg(@StringRes argNameResId: Int): CharSequence = Argsx.readCharSequenceArg(this, argNameResId)

inline fun android.app.Fragment.readCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): CharSequence =
        Argsx.readCharSequenceArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readCharSequenceArgOrNull(@StringRes argNameResId: Int): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readCharSequenceArrayArg(@StringRes argNameResId: Int): Array<CharSequence> = Argsx.readCharSequenceArrayArg(this, argNameResId)

inline fun android.app.Fragment.readCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Argsx.readCharSequenceArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readCharSequenceArrayListArg(@StringRes argNameResId: Int): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArg(this, argNameResId)

inline fun android.app.Fragment.readCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Argsx.readCharSequenceArrayListArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<CharSequence>? =
        Argsx.readCharSequenceArrayListArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readStringArg(@StringRes argNameResId: Int): String = Argsx.readStringArg(this, argNameResId)

inline fun android.app.Fragment.readStringArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readStringArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readStringArrayArg(@StringRes argNameResId: Int): Array<String> = Argsx.readStringArrayArg(this, argNameResId)

inline fun android.app.Fragment.readStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): Array<String> =
        Argsx.readStringArrayArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readStringArrayArgOrNull(@StringRes argNameResId: Int): Array<String>? = Argsx.readStringArrayArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readStringArrayListArg(@StringRes argNameResId: Int): ArrayList<String> = Argsx.readStringArrayListArg(this, argNameResId)

inline fun android.app.Fragment.readStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ArrayList<String> =
        Argsx.readStringArrayListArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readStringArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArg(@StringRes argNameResId: Int): V = Argsx.readParcelableArg(this, argNameResId)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readParcelableArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readParcelableArgOrNull(this, argNameResId)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArg(@StringRes argNameResId: Int): Array<V> = Argsx.readParcelableArrayArg(this, argNameResId)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): Array<V> =
        Argsx.readParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOrNull(@StringRes argNameResId: Int): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArg(@StringRes argNameResId: Int): ArrayList<V> = Argsx.readParcelableArrayListArg(this, argNameResId)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ArrayList<V> =
        Argsx.readParcelableArrayListArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<V>? =
        Argsx.readParcelableArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArg(@StringRes argNameResId: Int): SparseArray<V> = Argsx.readSparseParcelableArrayArg(this, argNameResId)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): SparseArray<V> =
        Argsx.readSparseParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): SparseArray<V>? =
        Argsx.readSparseParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Serializable> android.app.Fragment.readSerializableArg(@StringRes argNameResId: Int): V = Argsx.readSerializableArg(this, argNameResId)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): V =
        Argsx.readSerializableArgOr(this, argNameResId, defaultValue)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readSerializableArgOrNull(this, argNameResId)


inline fun android.app.Fragment.readBundleArg(@StringRes argNameResId: Int): Bundle = Argsx.readBundleArg(this, argNameResId)

inline fun android.app.Fragment.readBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argNameResId, defaultValue)

inline fun android.app.Fragment.readBundleArgOrNull(@StringRes argNameResId: Int): Bundle? = Argsx.readBundleArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArg(@StringRes argNameResId: Int): IBinder = Argsx.readBinderArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOrNull(@StringRes argNameResId: Int): IBinder? = Argsx.readBinderArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArg(@StringRes argNameResId: Int): Size = Argsx.readSizeArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOrNull(@StringRes argNameResId: Int): Size? = Argsx.readSizeArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArg(@StringRes argNameResId: Int): SizeF = Argsx.readSizeFArg(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOrNull(@StringRes argNameResId: Int): SizeF? = Argsx.readSizeFArgOrNull(this, argNameResId)