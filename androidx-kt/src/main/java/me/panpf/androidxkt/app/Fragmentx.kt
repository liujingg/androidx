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
inline fun List<android.support.v4.app.Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? = Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? = Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun android.support.v4.app.Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? = Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)


/* ************************************* SupportFragment Args ***************************************** */


inline fun android.support.v4.app.Fragment.readByteArg(argName: String, defaultValue: Byte): Byte = Fragmentx.readByteArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readByteArrayArg(argName: String): ByteArray = Fragmentx.readByteArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readByteArrayArg(argName: String, defaultValue: ByteArray): ByteArray = Fragmentx.readByteArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalByteArrayArg(argName: String): ByteArray? = Fragmentx.readOptionalByteArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readShortArg(argName: String, defaultValue: Short): Short = Fragmentx.readShortArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readShortArrayArg(argName: String): ShortArray = Fragmentx.readShortArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readShortArrayArg(argName: String, defaultValue: ShortArray): ShortArray = Fragmentx.readShortArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalShortArrayArg(argName: String): ShortArray? = Fragmentx.readOptionalShortArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readIntArg(argName: String, defaultValue: Int): Int = Fragmentx.readIntArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readIntArrayArg(argName: String): IntArray = Fragmentx.readIntArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readIntArrayArg(argName: String, defaultValue: IntArray): IntArray = Fragmentx.readIntArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalIntArrayArg(argName: String): IntArray? = Fragmentx.readOptionalIntArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalIntArrayListArg(argName: String): ArrayList<Int>? = Fragmentx.readOptionalIntArrayListArg(this, argName)


inline fun android.support.v4.app.Fragment.readLongArg(argName: String, defaultValue: Long): Long = Fragmentx.readLongArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readLongArrayArg(argName: String): LongArray = Fragmentx.readLongArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readLongArrayArg(argName: String, defaultValue: LongArray): LongArray = Fragmentx.readLongArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalLongArrayArg(argName: String): LongArray? = Fragmentx.readOptionalLongArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readFloatArg(argName: String, defaultValue: Float): Float = Fragmentx.readFloatArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Fragmentx.readFloatArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readFloatArrayArg(argName: String, defaultValue: FloatArray): FloatArray = Fragmentx.readFloatArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalFloatArrayArg(argName: String): FloatArray? = Fragmentx.readOptionalFloatArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readDoubleArg(argName: String, defaultValue: Double): Double = Fragmentx.readDoubleArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readDoubleArrayArg(argName: String, defaultValue: DoubleArray): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalDoubleArrayArg(argName: String): DoubleArray? = Fragmentx.readOptionalDoubleArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readBooleanArg(argName: String, defaultValue: Boolean): Boolean = Fragmentx.readBooleanArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readBooleanArrayArg(argName: String, defaultValue: BooleanArray): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalBooleanArrayArg(argName: String): BooleanArray? = Fragmentx.readOptionalBooleanArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readCharArg(argName: String, defaultValue: Char): Char = Fragmentx.readCharArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readCharArrayArg(argName: String): CharArray = Fragmentx.readCharArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharArrayArg(argName: String, defaultValue: CharArray): CharArray = Fragmentx.readCharArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalCharArrayArg(argName: String): CharArray? = Fragmentx.readOptionalCharArrayArg(this, argName)


inline fun android.support.v4.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Fragmentx.readCharSequenceArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArg(argName: String, defaultValue: CharSequence): CharSequence = Fragmentx.readCharSequenceArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalCharSequenceArg(argName: String): CharSequence? = Fragmentx.readOptionalCharSequenceArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalCharSequenceArrayArg(argName: String): Array<CharSequence>? = Fragmentx.readOptionalCharSequenceArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Fragmentx.readCharSequenceArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readCharSequenceArrayListArg(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Fragmentx.readCharSequenceArrayListArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalCharSequenceArrayListArg(argName: String): ArrayList<CharSequence>? = Fragmentx.readOptionalCharSequenceArrayListArg(this, argName)


inline fun android.support.v4.app.Fragment.readStringArg(argName: String): String = Fragmentx.readStringArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArg(argName: String, defaultValue: String): String = Fragmentx.readStringArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalStringArg(argName: String): String? = Fragmentx.readOptionalStringArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayArg(argName: String): Array<String> = Fragmentx.readStringArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayArg(argName: String, defaultValue: Array<String>): Array<String> = Fragmentx.readStringArrayArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalStringArrayArg(argName: String): Array<String>? = Fragmentx.readOptionalStringArrayArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName)

inline fun android.support.v4.app.Fragment.readStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalStringArrayListArg(argName: String): ArrayList<String>? = Fragmentx.readOptionalStringArrayListArg(this, argName)


inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArg(argName: String): V = Fragmentx.readParcelableArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArg(argName: String, defaultValue: V): V = Fragmentx.readParcelableArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readOptionalParcelableArg(argName: String): V? = Fragmentx.readOptionalParcelableArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Fragmentx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayArg(argName: String, defaultValue: Array<V>): Array<V> = Fragmentx.readParcelableArrayArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readOptionalParcelableArrayArg(argName: String): Array<V>? = Fragmentx.readOptionalParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> = Fragmentx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Fragmentx.readParcelableArrayListArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readOptionalParcelableArrayListArg(argName: String): ArrayList<V>? = Fragmentx.readOptionalParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> = Fragmentx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readSparseParcelableArrayArg(argName: String, defaultValue: SparseArray<V>): SparseArray<V> = Fragmentx.readSparseParcelableArrayArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.support.v4.app.Fragment.readOptionalSparseParcelableArrayArg(argName: String): SparseArray<V>? = Fragmentx.readOptionalSparseParcelableArrayArg(this, argName)


inline fun <V : Serializable> android.support.v4.app.Fragment.readSerializableArg(argName: String): V = Fragmentx.readSerializableArg(this, argName)

inline fun <V : Serializable> android.support.v4.app.Fragment.readSerializableArg(argName: String, defaultValue: V): V = Fragmentx.readSerializableArg(this, argName, defaultValue)

inline fun <V : Serializable> android.support.v4.app.Fragment.readOptionalSerializableArg(argName: String): V? = Fragmentx.readOptionalSerializableArg(this, argName)


inline fun android.support.v4.app.Fragment.readBundleArg(argName: String): Bundle = Fragmentx.readBundleArg(this, argName)

inline fun android.support.v4.app.Fragment.readBundleArg(argName: String, defaultValue: Bundle): Bundle = Fragmentx.readBundleArg(this, argName, defaultValue)

inline fun android.support.v4.app.Fragment.readOptionalBundleArg(argName: String): Bundle? = Fragmentx.readOptionalBundleArg(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readBinderArg(argName: String): IBinder = Fragmentx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readBinderArg(argName: String, defaultValue: IBinder): IBinder = Fragmentx.readBinderArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.support.v4.app.Fragment.readOptionalBinderArg(argName: String): IBinder? = Fragmentx.readOptionalBinderArg(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeArg(argName: String): Size = Fragmentx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeArg(argName: String, defaultValue: Size): Size = Fragmentx.readSizeArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readOptionalSizeArg(argName: String): Size? = Fragmentx.readOptionalSizeArg(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeFArg(argName: String): SizeF = Fragmentx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readSizeFArg(argName: String, defaultValue: SizeF): SizeF = Fragmentx.readSizeFArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.support.v4.app.Fragment.readOptionalSizeFArg(argName: String): SizeF? = Fragmentx.readOptionalSizeFArg(this, argName)


/* ************************************* OriginFragment Args ***************************************** */


inline fun android.app.Fragment.readByteArg(argName: String, defaultValue: Byte): Byte = Fragmentx.readByteArg(this, argName, defaultValue)

inline fun android.app.Fragment.readByteArrayArg(argName: String): ByteArray = Fragmentx.readByteArrayArg(this, argName)

inline fun android.app.Fragment.readByteArrayArg(argName: String, defaultValue: ByteArray): ByteArray = Fragmentx.readByteArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalByteArrayArg(argName: String): ByteArray? = Fragmentx.readOptionalByteArrayArg(this, argName)


inline fun android.app.Fragment.readShortArg(argName: String, defaultValue: Short): Short = Fragmentx.readShortArg(this, argName, defaultValue)

inline fun android.app.Fragment.readShortArrayArg(argName: String): ShortArray = Fragmentx.readShortArrayArg(this, argName)

inline fun android.app.Fragment.readShortArrayArg(argName: String, defaultValue: ShortArray): ShortArray = Fragmentx.readShortArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalShortArrayArg(argName: String): ShortArray? = Fragmentx.readOptionalShortArrayArg(this, argName)


inline fun android.app.Fragment.readIntArg(argName: String, defaultValue: Int): Int = Fragmentx.readIntArg(this, argName, defaultValue)

inline fun android.app.Fragment.readIntArrayArg(argName: String): IntArray = Fragmentx.readIntArrayArg(this, argName)

inline fun android.app.Fragment.readIntArrayArg(argName: String, defaultValue: IntArray): IntArray = Fragmentx.readIntArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalIntArrayArg(argName: String): IntArray? = Fragmentx.readOptionalIntArrayArg(this, argName)

inline fun android.app.Fragment.readIntArrayListArg(argName: String): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName)

inline fun android.app.Fragment.readIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Fragmentx.readIntArrayListArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalIntArrayListArg(argName: String): ArrayList<Int>? = Fragmentx.readOptionalIntArrayListArg(this, argName)


inline fun android.app.Fragment.readLongArg(argName: String, defaultValue: Long): Long = Fragmentx.readLongArg(this, argName, defaultValue)

inline fun android.app.Fragment.readLongArrayArg(argName: String): LongArray = Fragmentx.readLongArrayArg(this, argName)

inline fun android.app.Fragment.readLongArrayArg(argName: String, defaultValue: LongArray): LongArray = Fragmentx.readLongArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalLongArrayArg(argName: String): LongArray? = Fragmentx.readOptionalLongArrayArg(this, argName)


inline fun android.app.Fragment.readFloatArg(argName: String, defaultValue: Float): Float = Fragmentx.readFloatArg(this, argName, defaultValue)

inline fun android.app.Fragment.readFloatArrayArg(argName: String): FloatArray = Fragmentx.readFloatArrayArg(this, argName)

inline fun android.app.Fragment.readFloatArrayArg(argName: String, defaultValue: FloatArray): FloatArray = Fragmentx.readFloatArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalFloatArrayArg(argName: String): FloatArray? = Fragmentx.readOptionalFloatArrayArg(this, argName)


inline fun android.app.Fragment.readDoubleArg(argName: String, defaultValue: Double): Double = Fragmentx.readDoubleArg(this, argName, defaultValue)

inline fun android.app.Fragment.readDoubleArrayArg(argName: String): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName)

inline fun android.app.Fragment.readDoubleArrayArg(argName: String, defaultValue: DoubleArray): DoubleArray = Fragmentx.readDoubleArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalDoubleArrayArg(argName: String): DoubleArray? = Fragmentx.readOptionalDoubleArrayArg(this, argName)


inline fun android.app.Fragment.readBooleanArg(argName: String, defaultValue: Boolean): Boolean = Fragmentx.readBooleanArg(this, argName, defaultValue)

inline fun android.app.Fragment.readBooleanArrayArg(argName: String): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName)

inline fun android.app.Fragment.readBooleanArrayArg(argName: String, defaultValue: BooleanArray): BooleanArray = Fragmentx.readBooleanArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalBooleanArrayArg(argName: String): BooleanArray? = Fragmentx.readOptionalBooleanArrayArg(this, argName)


inline fun android.app.Fragment.readCharArg(argName: String, defaultValue: Char): Char = Fragmentx.readCharArg(this, argName, defaultValue)

inline fun android.app.Fragment.readCharArrayArg(argName: String): CharArray = Fragmentx.readCharArrayArg(this, argName)

inline fun android.app.Fragment.readCharArrayArg(argName: String, defaultValue: CharArray): CharArray = Fragmentx.readCharArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalCharArrayArg(argName: String): CharArray? = Fragmentx.readOptionalCharArrayArg(this, argName)


inline fun android.app.Fragment.readCharSequenceArg(argName: String): CharSequence = Fragmentx.readCharSequenceArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArg(argName: String, defaultValue: CharSequence): CharSequence = Fragmentx.readCharSequenceArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalCharSequenceArg(argName: String): CharSequence? = Fragmentx.readOptionalCharSequenceArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Fragmentx.readCharSequenceArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalCharSequenceArrayArg(argName: String): Array<CharSequence>? = Fragmentx.readOptionalCharSequenceArrayArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayListArg(argName: String): ArrayList<CharSequence> = Fragmentx.readCharSequenceArrayListArg(this, argName)

inline fun android.app.Fragment.readCharSequenceArrayListArg(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Fragmentx.readCharSequenceArrayListArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalCharSequenceArrayListArg(argName: String): ArrayList<CharSequence>? = Fragmentx.readOptionalCharSequenceArrayListArg(this, argName)


inline fun android.app.Fragment.readStringArg(argName: String): String = Fragmentx.readStringArg(this, argName)

inline fun android.app.Fragment.readStringArg(argName: String, defaultValue: String): String = Fragmentx.readStringArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalStringArg(argName: String): String? = Fragmentx.readOptionalStringArg(this, argName)

inline fun android.app.Fragment.readStringArrayArg(argName: String): Array<String> = Fragmentx.readStringArrayArg(this, argName)

inline fun android.app.Fragment.readStringArrayArg(argName: String, defaultValue: Array<String>): Array<String> = Fragmentx.readStringArrayArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalStringArrayArg(argName: String): Array<String>? = Fragmentx.readOptionalStringArrayArg(this, argName)

inline fun android.app.Fragment.readStringArrayListArg(argName: String): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName)

inline fun android.app.Fragment.readStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Fragmentx.readStringArrayListArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalStringArrayListArg(argName: String): ArrayList<String>? = Fragmentx.readOptionalStringArrayListArg(this, argName)


inline fun <V : Parcelable> android.app.Fragment.readParcelableArg(argName: String): V = Fragmentx.readParcelableArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArg(argName: String, defaultValue: V): V = Fragmentx.readParcelableArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readOptionalParcelableArg(argName: String): V? = Fragmentx.readOptionalParcelableArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArg(argName: String): Array<V> = Fragmentx.readParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayArg(argName: String, defaultValue: Array<V>): Array<V> = Fragmentx.readParcelableArrayArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readOptionalParcelableArrayArg(argName: String): Array<V>? = Fragmentx.readOptionalParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> = Fragmentx.readParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Fragmentx.readParcelableArrayListArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readOptionalParcelableArrayListArg(argName: String): ArrayList<V>? = Fragmentx.readOptionalParcelableArrayListArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> = Fragmentx.readSparseParcelableArrayArg(this, argName)

inline fun <V : Parcelable> android.app.Fragment.readSparseParcelableArrayArg(argName: String, defaultValue: SparseArray<V>): SparseArray<V> = Fragmentx.readSparseParcelableArrayArg(this, argName, defaultValue)

inline fun <V : Parcelable> android.app.Fragment.readOptionalSparseParcelableArrayArg(argName: String): SparseArray<V>? = Fragmentx.readOptionalSparseParcelableArrayArg(this, argName)


inline fun <V : Serializable> android.app.Fragment.readSerializableArg(argName: String): V = Fragmentx.readSerializableArg(this, argName)

inline fun <V : Serializable> android.app.Fragment.readSerializableArg(argName: String, defaultValue: V): V = Fragmentx.readSerializableArg(this, argName, defaultValue)

inline fun <V : Serializable> android.app.Fragment.readOptionalSerializableArg(argName: String): V? = Fragmentx.readOptionalSerializableArg(this, argName)


inline fun android.app.Fragment.readBundleArg(argName: String): Bundle = Fragmentx.readBundleArg(this, argName)

inline fun android.app.Fragment.readBundleArg(argName: String, defaultValue: Bundle): Bundle = Fragmentx.readBundleArg(this, argName, defaultValue)

inline fun android.app.Fragment.readOptionalBundleArg(argName: String): Bundle? = Fragmentx.readOptionalBundleArg(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArg(argName: String): IBinder = Fragmentx.readBinderArg(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readBinderArg(argName: String, defaultValue: IBinder): IBinder = Fragmentx.readBinderArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun android.app.Fragment.readOptionalBinderArg(argName: String): IBinder? = Fragmentx.readOptionalBinderArg(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArg(argName: String): Size = Fragmentx.readSizeArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeArg(argName: String, defaultValue: Size): Size = Fragmentx.readSizeArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readOptionalSizeArg(argName: String): Size? = Fragmentx.readOptionalSizeArg(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArg(argName: String): SizeF = Fragmentx.readSizeFArg(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readSizeFArg(argName: String, defaultValue: SizeF): SizeF = Fragmentx.readSizeFArg(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun android.app.Fragment.readOptionalSizeFArg(argName: String): SizeF? = Fragmentx.readOptionalSizeFArg(this, argName)
