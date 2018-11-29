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

@file:Suppress("DEPRECATION")

package me.panpf.androidxkt.app

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ActivityArgLazy<in REF: Activity, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '${thisRef.getString(argNameResId)}' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class SupportFragmentArgLazy<in REF: android.support.v4.app.Fragment, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '${thisRef.resources.getString(argNameResId)}' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class OriginFragmentArgLazy<in REF: android.app.Fragment, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '${thisRef.resources.getString(argNameResId)}' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class ArgLazy<in REF, out OUT : Any>(private val argName: String, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '$argName' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class OptionalArgLazy<in REF, out OUT>(private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT?> {
    private object EMPTY

    private var arg: Any? = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT? {
        if (arg == EMPTY) {
            arg = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}


/* ************************************* Activity Intent Arg ***************************************** */


fun Activity.bindByteArgOr(argName: String, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun Activity.bindByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun Activity.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Activity.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun Activity.bindShortArgOr(argName: String, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun Activity.bindShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun Activity.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Activity.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun Activity.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun Activity.bindIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName) }

fun Activity.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun Activity.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun Activity.bindIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName) }

fun Activity.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun Activity.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun Activity.bindLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun Activity.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Activity.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun Activity.bindFloatArgOr(argName: String, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun Activity.bindFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun Activity.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun Activity.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun Activity.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun Activity.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun Activity.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun Activity.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun Activity.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun Activity.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun Activity.bindCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun Activity.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Activity.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName) }

fun Activity.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun Activity.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName) }

fun Activity.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argName) }

fun Activity.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun Activity.bindStringArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName) }

fun Activity.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun Activity.bindStringArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun Activity.bindStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName) }

fun Activity.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun Activity.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun Activity.bindStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName) }

fun Activity.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName) }

fun <V : Parcelable> Activity.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun Activity.bindBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName) }

fun Activity.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun Activity.bindBundleArgOrNull(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


fun Activity.bindExtrasArg(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArg() }

fun Activity.bindExtrasArgOr(defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArgOr(defaultValue) }

fun Activity.bindExtrasArgOrNull(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readExtrasArgOrNull() }


/* ************************************* Activity Intent Arg ***************************************** */


fun Activity.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun Activity.bindByteArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArg(argNameResId) }

fun Activity.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun Activity.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun Activity.bindShortArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArg(argNameResId) }

fun Activity.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun Activity.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun Activity.bindIntArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArg(argNameResId) }

fun Activity.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun Activity.bindIntArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArg(argNameResId) }

fun Activity.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun Activity.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun Activity.bindLongArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArg(argNameResId) }

fun Activity.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun Activity.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun Activity.bindFloatArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArg(argNameResId) }

fun Activity.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun Activity.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun Activity.bindDoubleArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArg(argNameResId) }

fun Activity.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun Activity.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun Activity.bindBooleanArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArg(argNameResId) }

fun Activity.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun Activity.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun Activity.bindCharArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArg(argNameResId) }

fun Activity.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArg(argNameResId) }

fun Activity.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argNameResId) }

fun Activity.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argNameResId) }

fun Activity.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun Activity.bindStringArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArg(argNameResId) }

fun Activity.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun Activity.bindStringArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArg(argNameResId) }

fun Activity.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun Activity.bindStringArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArg(argNameResId) }

fun Activity.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArg(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun Activity.bindBundleArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Bundle> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArg(argNameResId) }

fun Activity.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun Activity.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


/* ************************************* Activity Intent Uri Arg ***************************************** */


fun Activity.bindByteIntentUriArgOr(argName: String, defaultValue: Byte): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteIntentUriArgOr(argName, defaultValue) }

fun Activity.bindShortIntentUriArgOr(argName: String, defaultValue: Short): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortIntentUriArgOr(argName, defaultValue) }

fun Activity.bindIntIntentUriArgOr(argName: String, defaultValue: Int): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntIntentUriArgOr(argName, defaultValue) }

fun Activity.bindLongIntentUriArgOr(argName: String, defaultValue: Long): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongIntentUriArgOr(argName, defaultValue) }

fun Activity.bindFloatIntentUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatIntentUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleIntentUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOr(argName, defaultValue) }

fun Activity.bindStringIntentUriArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArg(argName) }

fun Activity.bindStringIntentUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argName, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argName) }


fun Activity.bindByteIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindShortIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindIntIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindLongIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringIntentUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArg(argNameResId) }

fun Activity.bindStringIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argNameResId) }


/* ************************************* Activity Uri Arg ***************************************** */


fun Activity.bindByteUriArg(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArg(argName) }

fun Activity.bindByteUriArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOr(argName, defaultValue) }

fun Activity.bindByteUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argName) }


fun Activity.bindShortUriArg(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArg(argName) }

fun Activity.bindShortUriArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOr(argName, defaultValue) }

fun Activity.bindShortUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argName) }


fun Activity.bindIntUriArg(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArg(argName) }

fun Activity.bindIntUriArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOr(argName, defaultValue) }

fun Activity.bindIntUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argName) }


fun Activity.bindLongUriArg(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArg(argName) }

fun Activity.bindLongUriArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOr(argName, defaultValue) }

fun Activity.bindLongUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argName) }


fun Activity.bindFloatUriArg(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArg(argName) }

fun Activity.bindFloatUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOr(argName, defaultValue) }

fun Activity.bindFloatUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argName) }


fun Activity.bindDoubleUriArg(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArg(argName) }

fun Activity.bindDoubleUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argName) }


fun Activity.bindBooleanUriArg(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArg(argName) }

fun Activity.bindBooleanUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argName) }


fun Activity.bindStringUriArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArg(argName) }

fun Activity.bindStringUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOr(argName, defaultValue) }

fun Activity.bindStringUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriArgOrNull(argName) }


fun Activity.bindByteUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArg(argNameResId) }

fun Activity.bindByteUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArgOr(argNameResId, defaultValue) }

fun Activity.bindByteUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argNameResId) }


fun Activity.bindShortUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArg(argNameResId) }

fun Activity.bindShortUriArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArgOr(argNameResId, defaultValue) }

fun Activity.bindShortUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argNameResId) }


fun Activity.bindIntUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArg(argNameResId) }

fun Activity.bindIntUriArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArgOr(argNameResId, defaultValue) }

fun Activity.bindIntUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argNameResId) }


fun Activity.bindLongUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArg(argNameResId) }

fun Activity.bindLongUriArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArgOr(argNameResId, defaultValue) }

fun Activity.bindLongUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argNameResId) }


fun Activity.bindFloatUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArg(argNameResId) }

fun Activity.bindFloatUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argNameResId) }


fun Activity.bindDoubleUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArg(argNameResId) }

fun Activity.bindDoubleUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argNameResId) }


fun Activity.bindBooleanUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArg(argNameResId) }

fun Activity.bindBooleanUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argNameResId) }


fun Activity.bindStringUriArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriArg(argNameResId) }

fun Activity.bindStringUriArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriArgOrNull(argNameResId) }


/* ************************************* Activity Uri Intent Arg ***************************************** */


fun Activity.bindByteUriIntentArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriIntentArgOr(argName, defaultValue) }

fun Activity.bindShortUriIntentArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriIntentArgOr(argName, defaultValue) }

fun Activity.bindIntUriIntentArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriIntentArgOr(argName, defaultValue) }

fun Activity.bindLongUriIntentArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriIntentArgOr(argName, defaultValue) }

fun Activity.bindFloatUriIntentArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriIntentArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriIntentArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOr(argName, defaultValue) }

fun Activity.bindStringUriIntentArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArg(argName) }

fun Activity.bindStringUriIntentArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argName, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argName) }


fun Activity.bindByteUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindShortUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindIntUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindLongUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriIntentArg(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArg(argNameResId) }

fun Activity.bindStringUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argNameResId) }


/* ************************************* SupportFragment Arg ***************************************** */


fun android.support.v4.app.Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun android.support.v4.app.Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }



fun android.support.v4.app.Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun android.support.v4.app.Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName) }

fun android.support.v4.app.Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName) }

fun android.support.v4.app.Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.support.v4.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun android.support.v4.app.Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.support.v4.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun android.support.v4.app.Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.support.v4.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun android.support.v4.app.Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.support.v4.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun android.support.v4.app.Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.support.v4.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun android.support.v4.app.Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun android.support.v4.app.Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindCharSequenceArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName) }

fun android.support.v4.app.Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindCharSequenceArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argName) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindStringArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName) }

fun android.support.v4.app.Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<android.support.v4.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName) }

fun android.support.v4.app.Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName) }

fun android.support.v4.app.Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun android.support.v4.app.Fragment.bindBundleArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName) }

fun android.support.v4.app.Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArg(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<android.support.v4.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }


/* ************************************* Origin Fragment Arg ***************************************** */


fun android.app.Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun android.app.Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun android.app.Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun android.app.Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun android.app.Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun android.app.Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun android.app.Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun android.app.Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName) }

fun android.app.Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<android.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun android.app.Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName) }

fun android.app.Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun android.app.Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun android.app.Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun android.app.Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun android.app.Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun android.app.Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun android.app.Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun android.app.Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun android.app.Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun android.app.Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun android.app.Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun android.app.Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun android.app.Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun android.app.Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun android.app.Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName) }

fun android.app.Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName) }

fun android.app.Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argName) }

fun android.app.Fragment.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun android.app.Fragment.bindStringArg(argName: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName) }

fun android.app.Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun android.app.Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName) }

fun android.app.Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun android.app.Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName) }

fun android.app.Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argName) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun android.app.Fragment.bindBundleArg(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName) }

fun android.app.Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun android.app.Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArg(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArg(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArg(argName: String): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }


/* ************************************* SupportFragment Arg ***************************************** */


fun android.support.v4.app.Fragment.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Byte> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindByteArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }



fun android.support.v4.app.Fragment.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Short> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindShortArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Int> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindIntArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindIntArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArg(argNameResId) }

fun android.support.v4.app.Fragment.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<android.support.v4.app.Fragment, Long> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindLongArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0f): ReadOnlyProperty<android.support.v4.app.Fragment, Float> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindFloatArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.support.v4.app.Fragment, Double> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindDoubleArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<android.support.v4.app.Fragment, Boolean> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindBooleanArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.support.v4.app.Fragment, Char> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun android.support.v4.app.Fragment.bindCharArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindCharSequenceArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArg(argNameResId) }

fun android.support.v4.app.Fragment.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindCharSequenceArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindCharSequenceArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argNameResId) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindStringArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArg(argNameResId) }

fun android.support.v4.app.Fragment.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<android.support.v4.app.Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindStringArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArg(argNameResId) }

fun android.support.v4.app.Fragment.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindStringArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArg(argNameResId) }

fun android.support.v4.app.Fragment.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArg(argNameResId) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argNameResId) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argNameResId) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun android.support.v4.app.Fragment.bindBundleArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArg(argNameResId) }

fun android.support.v4.app.Fragment.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun android.support.v4.app.Fragment.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): ReadOnlyProperty<android.support.v4.app.Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argNameResId) }


/* ************************************* Origin Fragment Arg ***************************************** */


fun android.app.Fragment.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindByteArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArg(argNameResId) }

fun android.app.Fragment.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindShortArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArg(argNameResId) }

fun android.app.Fragment.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindIntArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IntArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArg(argNameResId) }

fun android.app.Fragment.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<android.app.Fragment, IntArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindIntArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArg(argNameResId) }

fun android.app.Fragment.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun android.app.Fragment.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindLongArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, LongArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArg(argNameResId) }

fun android.app.Fragment.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<android.app.Fragment, LongArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindFloatArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArg(argNameResId) }

fun android.app.Fragment.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindDoubleArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArg(argNameResId) }

fun android.app.Fragment.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindBooleanArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArg(argNameResId) }

fun android.app.Fragment.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindCharArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArg(argNameResId) }

fun android.app.Fragment.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<android.app.Fragment, CharArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArg(argNameResId) }

fun android.app.Fragment.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argNameResId) }

fun android.app.Fragment.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArg(argNameResId) }

fun android.app.Fragment.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, String> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArg(argNameResId) }

fun android.app.Fragment.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<android.app.Fragment, String> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArg(argNameResId) }

fun android.app.Fragment.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArg(argNameResId) }

fun android.app.Fragment.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArg(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArg(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun android.app.Fragment.bindBundleArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Bundle> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArg(argNameResId) }

fun android.app.Fragment.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<android.app.Fragment, Bundle> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IBinder> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): ReadOnlyProperty<android.app.Fragment, IBinder> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Size> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): ReadOnlyProperty<android.app.Fragment, Size> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArg(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SizeF> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArg(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): ReadOnlyProperty<android.app.Fragment, SizeF> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argNameResId) }

