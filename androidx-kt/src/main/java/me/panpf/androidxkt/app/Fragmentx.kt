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

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import me.panpf.androidx.app.Fragmentx
import java.io.Serializable
import java.util.*

/**
 * Return true if the fragment has been destroyed
 */
inline fun android.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)

/**
 * Return true if the fragment has been destroyed
 */
inline fun android.support.v4.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)


/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> android.support.v4.app.Fragment.getImplWithParent(clazz: Class<T>): T? = Fragmentx.getImplWithParent(this, clazz)

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> android.app.Fragment.getImplWithParent(clazz: Class<T>): T? = Fragmentx.getImplWithParent(this, clazz)


/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.support.v4.app.Fragment>.instance(arguments: Bundle): android.support.v4.app.Fragment = Fragmentx.instance(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.support.v4.app.Fragment>.instance(): android.support.v4.app.Fragment = Fragmentx.instance(this)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.app.Fragment>.instanceOrigin(arguments: Bundle): android.app.Fragment = Fragmentx.instanceOrigin(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.app.Fragment>.instanceOrigin(): android.app.Fragment = Fragmentx.instanceOrigin(this)


/**
 * Find the visible fragments visible to the current user from the fragment list
 */
inline fun List<android.support.v4.app.Fragment>?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the FragmentActivity
 */
inline fun FragmentActivity?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the fragment
 */
inline fun android.support.v4.app.Fragment?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)


/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun List<android.support.v4.app.Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun android.support.v4.app.Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)


inline fun android.app.Fragment.requireContext(): Context = Fragmentx.requireContext(this)


inline fun android.support.v4.app.Fragment.requireAppContext(): Context = Fragmentx.requireAppContext(this)

inline fun android.app.Fragment.requireAppContext(): Context = Fragmentx.requireAppContext(this)


/* ************************************* SupportFragment Args ***************************************** */


inline fun android.support.v4.app.Fragment.readByteArgOr(argName: String, defaultValue: Byte): Byte = Fragmentx.readByteArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readByteArrayArg(argName: String): ByteArray = Fragmentx.readByteArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray =
        Fragmentx.readByteArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readByteArrayArgOrNull(argName: String): ByteArray? = Fragmentx.readByteArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readShortArgOr(argName: String, defaultValue: Short): Short = Fragmentx.readShortArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readShortArrayArg(argName: String): ShortArray = Fragmentx.readShortArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray =
        Fragmentx.readShortArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readShortArrayArgOrNull(argName: String): ShortArray? = Fragmentx.readShortArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readIntArgOr(argName: String, defaultValue: Int): Int = Fragmentx.readIntArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readIntArrayArg(argName: String): IntArray = Fragmentx.readIntArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Fragmentx.readIntArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readIntArrayArgOrNull(argName: String): IntArray? = Fragmentx.readIntArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Fragmentx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Fragmentx.readIntArrayListArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readLongArgOr(argName: String, defaultValue: Long): Long = Fragmentx.readLongArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readLongArrayArg(argName: String): LongArray = Fragmentx.readLongArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray =
        Fragmentx.readLongArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readLongArrayArgOrNull(argName: String): LongArray? = Fragmentx.readLongArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readFloatArgOr(argName: String, defaultValue: Float): Float = Fragmentx.readFloatArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Fragmentx.readFloatArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray =
        Fragmentx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readFloatArrayArgOrNull(argName: String): FloatArray? = Fragmentx.readFloatArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readDoubleArgOr(argName: String, defaultValue: Double): Double = Fragmentx.readDoubleArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray =
        Fragmentx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Fragmentx.readDoubleArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Fragmentx.readBooleanArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray =
        Fragmentx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Fragmentx.readBooleanArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readCharArgOr(argName: String, defaultValue: Char): Char = Fragmentx.readCharArgOr(this, argName, defaultValue)


inline fun android.support.v4.app.Fragment.readCharArrayArg(argName: String): CharArray = Fragmentx.readCharArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray =
        Fragmentx.readCharArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readCharArrayArgOrNull(argName: String): CharArray? = Fragmentx.readCharArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Fragmentx.readCharSequenceArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence =
        Fragmentx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readCharSequenceArgOrNull(argName: String): CharSequence? = Fragmentx.readCharSequenceArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Fragmentx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? =
        Fragmentx.readCharSequenceArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> =
        Fragmentx.readCharSequenceArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Fragmentx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? =
        Fragmentx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readStringArg(argName: String): String = Fragmentx.readStringArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArgOr(argName: String, defaultValue: String): String = Fragmentx.readStringArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readStringArgOrNull(argName: String): String? = Fragmentx.readStringArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readStringArrayArg(argName: String): Array<String> = Fragmentx.readStringArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> =
        Fragmentx.readStringArrayArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readStringArrayArgOrNull(argName: String): Array<String>? = Fragmentx.readStringArrayArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        Fragmentx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Fragmentx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArg(argName: String): V = Fragmentx.readParcelableArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArgOr(argName: String, defaultValue: V): V =
        Fragmentx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArgOrNull(argName: String): V? = Fragmentx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Fragmentx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> =
        Fragmentx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayArgOrNull(argName: String): Array<V>? =
        Fragmentx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> =
        Fragmentx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        Fragmentx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? =
        Fragmentx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.support.v4.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> =
        Fragmentx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): SparseArray<V> =
        Fragmentx.readSparseParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readSparseParcelableArrayArgOrNull(argName: String): SparseArray<V>? =
        Fragmentx.readSparseParcelableArrayArgOrNull(this, argName)


inline fun <V : Serializable> android.support.v4.app.Fragment.readSerializableArg(argName: String): V = Fragmentx.readSerializableArg(this, argName)

inline fun <V : Serializable> android.support.v4.app.Fragment.readSerializableArgOr(argName: String, defaultValue: V): V =
        Fragmentx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> android.support.v4.app.Fragment.readSerializableArgOrNull(argName: String): V? = Fragmentx.readSerializableArgOrNull(this, argName)


inline fun android.support.v4.app.Fragment.readBundleArg(argName: String): Bundle = Fragmentx.readBundleArg(this, argName)

inline fun android.support.v4.app.Fragment.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Fragmentx.readBundleArgOr(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readBundleArgOrNull(argName: String): Bundle? = Fragmentx.readBundleArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readBinderArg(argName: String): IBinder = Fragmentx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readBinderArgOr(argName: String, defaultValue: IBinder): IBinder = Fragmentx.readBinderArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readBinderArgOrNull(argName: String): IBinder? = Fragmentx.readBinderArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeArg(argName: String): Size = Fragmentx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeArgOr(argName: String, defaultValue: Size): Size = Fragmentx.readSizeArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeArgOrNull(argName: String): Size? = Fragmentx.readSizeArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeFArg(argName: String): SizeF = Fragmentx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeFArgOr(argName: String, defaultValue: SizeF): SizeF = Fragmentx.readSizeFArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeFArgOrNull(argName: String): SizeF? = Fragmentx.readSizeFArgOrNull(this, argName)


/* ************************************* OriginFragment Args ***************************************** */


inline fun android.app.Fragment.readByteArgOr(argName: String, defaultValue: Byte): Byte = Fragmentx.readByteArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readByteArrayArg(argName: String): ByteArray = Fragmentx.readByteArrayArg(this, argName)

inline fun android.app.Fragment.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Fragmentx.readByteArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readByteArrayArgOrNull(argName: String): ByteArray? = Fragmentx.readByteArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readShortArgOr(argName: String, defaultValue: Short): Short = Fragmentx.readShortArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readShortArrayArg(argName: String): ShortArray = Fragmentx.readShortArrayArg(this, argName)

inline fun android.app.Fragment.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Fragmentx.readShortArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readShortArrayArgOrNull(argName: String): ShortArray? = Fragmentx.readShortArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readIntArgOr(argName: String, defaultValue: Int): Int = Fragmentx.readIntArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readIntArrayArg(argName: String): IntArray = Fragmentx.readIntArrayArg(this, argName)

inline fun android.app.Fragment.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Fragmentx.readIntArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readIntArrayArgOrNull(argName: String): IntArray? = Fragmentx.readIntArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName)

inline fun android.app.Fragment.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        Fragmentx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Fragmentx.readIntArrayListArgOrNull(this, argName)


inline fun android.app.Fragment.readLongArgOr(argName: String, defaultValue: Long): Long = Fragmentx.readLongArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readLongArrayArg(argName: String): LongArray = Fragmentx.readLongArrayArg(this, argName)

inline fun android.app.Fragment.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Fragmentx.readLongArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readLongArrayArgOrNull(argName: String): LongArray? = Fragmentx.readLongArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readFloatArgOr(argName: String, defaultValue: Float): Float = Fragmentx.readFloatArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Fragmentx.readFloatArrayArg(this, argName)

inline fun android.app.Fragment.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray =
        Fragmentx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readFloatArrayArgOrNull(argName: String): FloatArray? = Fragmentx.readFloatArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readDoubleArgOr(argName: String, defaultValue: Double): Double = Fragmentx.readDoubleArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName)

inline fun android.app.Fragment.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray =
        Fragmentx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Fragmentx.readDoubleArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Fragmentx.readBooleanArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName)

inline fun android.app.Fragment.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray =
        Fragmentx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Fragmentx.readBooleanArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharArgOr(argName: String, defaultValue: Char): Char = Fragmentx.readCharArgOr(this, argName, defaultValue)


inline fun android.app.Fragment.readCharArrayArg(argName: String): CharArray = Fragmentx.readCharArrayArg(this, argName)

inline fun android.app.Fragment.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Fragmentx.readCharArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharArrayArgOrNull(argName: String): CharArray? = Fragmentx.readCharArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Fragmentx.readCharSequenceArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence =
        Fragmentx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArgOrNull(argName: String): CharSequence? = Fragmentx.readCharSequenceArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        Fragmentx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Fragmentx.readCharSequenceArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Fragmentx.readCharSequenceArrayListArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> =
        Fragmentx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? =
        Fragmentx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArg(argName: String): String = Fragmentx.readStringArg(this, argName)

inline fun android.app.Fragment.readStringArgOr(argName: String, defaultValue: String): String = Fragmentx.readStringArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArgOrNull(argName: String): String? = Fragmentx.readStringArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArrayArg(argName: String): Array<String> = Fragmentx.readStringArrayArg(this, argName)

inline fun android.app.Fragment.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> =
        Fragmentx.readStringArrayArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArrayArgOrNull(argName: String): Array<String>? = Fragmentx.readStringArrayArgOrNull(this, argName)


inline fun android.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName)

inline fun android.app.Fragment.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        Fragmentx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Fragmentx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArg(argName: String): V = Fragmentx.readParcelableArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOr(argName: String, defaultValue: V): V = Fragmentx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArgOrNull(argName: String): V? = Fragmentx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Fragmentx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> =
        Fragmentx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArgOrNull(argName: String): Array<V>? = Fragmentx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> = Fragmentx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        Fragmentx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? =
        Fragmentx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> = Fragmentx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): SparseArray<V> =
        Fragmentx.readSparseParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArgOrNull(argName: String): SparseArray<V>? =
        Fragmentx.readSparseParcelableArrayArgOrNull(this, argName)


inline fun <V : Serializable> android.app.Fragment.readSerializableArg(argName: String): V = Fragmentx.readSerializableArg(this, argName)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOr(argName: String, defaultValue: V): V =
        Fragmentx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> android.app.Fragment.readSerializableArgOrNull(argName: String): V? = Fragmentx.readSerializableArgOrNull(this, argName)


inline fun android.app.Fragment.readBundleArg(argName: String): Bundle = Fragmentx.readBundleArg(this, argName)

inline fun android.app.Fragment.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Fragmentx.readBundleArgOr(this, argName, defaultValue)

inline fun android.app.Fragment.readBundleArgOrNull(argName: String): Bundle? = Fragmentx.readBundleArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArg(argName: String): IBinder = Fragmentx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOr(argName: String, defaultValue: IBinder): IBinder = Fragmentx.readBinderArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArgOrNull(argName: String): IBinder? = Fragmentx.readBinderArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArg(argName: String): Size = Fragmentx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOr(argName: String, defaultValue: Size): Size = Fragmentx.readSizeArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArgOrNull(argName: String): Size? = Fragmentx.readSizeArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArg(argName: String): SizeF = Fragmentx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOr(argName: String, defaultValue: SizeF): SizeF = Fragmentx.readSizeFArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArgOrNull(argName: String): SizeF? = Fragmentx.readSizeFArgOrNull(this, argName)
