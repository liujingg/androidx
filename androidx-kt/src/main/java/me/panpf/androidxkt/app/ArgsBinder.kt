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
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ActivityArgLazy<in REF : Activity, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
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

class SupportFragmentArgLazy<in REF : androidx.fragment.app.Fragment, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
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

class OriginFragmentArgLazy<in REF : android.app.Fragment, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
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


fun Activity.bindByteArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argName) }

fun Activity.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Activity.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun Activity.bindShortArgOr(argName: String, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun Activity.bindShortArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argName) }

fun Activity.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Activity.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun Activity.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun Activity.bindIntArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argName) }

fun Activity.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun Activity.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun Activity.bindIntArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argName) }

fun Activity.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun Activity.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun Activity.bindLongArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argName) }

fun Activity.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Activity.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun Activity.bindFloatArgOr(argName: String, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun Activity.bindFloatArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argName) }

fun Activity.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun Activity.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun Activity.bindDoubleArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argName) }

fun Activity.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun Activity.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun Activity.bindBooleanArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argName) }

fun Activity.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun Activity.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun Activity.bindCharArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argName) }

fun Activity.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Activity.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArgOrThrow(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argName) }

fun Activity.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun Activity.bindCharSequenceArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argName) }

fun Activity.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argName) }

fun Activity.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun Activity.bindStringArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOrThrow(argName) }

fun Activity.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun Activity.bindStringArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun Activity.bindStringArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argName) }

fun Activity.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun Activity.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun Activity.bindStringArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argName) }

fun Activity.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArgOrThrow(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argName) }

fun <V : Parcelable> Activity.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argName) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrThrow(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun Activity.bindBundleArgOrThrow(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argName) }

fun Activity.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun Activity.bindBundleArgOrNull(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


fun Activity.bindExtrasArgOrThrow(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArgOrThrow() }

fun Activity.bindExtrasArgOr(defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArgOr(defaultValue) }

fun Activity.bindExtrasArgOrNull(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readExtrasArgOrNull() }


/* ************************************* Activity Intent Arg ***************************************** */


fun Activity.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun Activity.bindByteArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argNameResId) }

fun Activity.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun Activity.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun Activity.bindShortArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argNameResId) }

fun Activity.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun Activity.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun Activity.bindIntArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argNameResId) }

fun Activity.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun Activity.bindIntArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argNameResId) }

fun Activity.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun Activity.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun Activity.bindLongArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argNameResId) }

fun Activity.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun Activity.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun Activity.bindFloatArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argNameResId) }

fun Activity.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun Activity.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun Activity.bindDoubleArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argNameResId) }

fun Activity.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun Activity.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun Activity.bindBooleanArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argNameResId) }

fun Activity.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun Activity.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun Activity.bindCharArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argNameResId) }

fun Activity.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun Activity.bindStringArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOrThrow(argNameResId) }

fun Activity.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun Activity.bindStringArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argNameResId) }

fun Activity.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun Activity.bindStringArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argNameResId) }

fun Activity.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun Activity.bindBundleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Bundle> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argNameResId) }

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

fun Activity.bindStringIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOrThrow(argName) }

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

fun Activity.bindStringIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArgOrThrow(argNameResId) }

fun Activity.bindStringIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argNameResId) }


/* ************************************* Activity Uri Arg ***************************************** */


fun Activity.bindByteUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOrThrow(argName) }

fun Activity.bindByteUriArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOr(argName, defaultValue) }

fun Activity.bindByteUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argName) }


fun Activity.bindShortUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOrThrow(argName) }

fun Activity.bindShortUriArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOr(argName, defaultValue) }

fun Activity.bindShortUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argName) }


fun Activity.bindIntUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOrThrow(argName) }

fun Activity.bindIntUriArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOr(argName, defaultValue) }

fun Activity.bindIntUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argName) }


fun Activity.bindLongUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOrThrow(argName) }

fun Activity.bindLongUriArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOr(argName, defaultValue) }

fun Activity.bindLongUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argName) }


fun Activity.bindFloatUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOrThrow(argName) }

fun Activity.bindFloatUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOr(argName, defaultValue) }

fun Activity.bindFloatUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argName) }


fun Activity.bindDoubleUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOrThrow(argName) }

fun Activity.bindDoubleUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argName) }


fun Activity.bindBooleanUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOrThrow(argName) }

fun Activity.bindBooleanUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argName) }


fun Activity.bindStringUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOrThrow(argName) }

fun Activity.bindStringUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOr(argName, defaultValue) }

fun Activity.bindStringUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriArgOrNull(argName) }


fun Activity.bindByteUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArgOrThrow(argNameResId) }

fun Activity.bindByteUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArgOr(argNameResId, defaultValue) }

fun Activity.bindByteUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argNameResId) }


fun Activity.bindShortUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArgOrThrow(argNameResId) }

fun Activity.bindShortUriArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArgOr(argNameResId, defaultValue) }

fun Activity.bindShortUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argNameResId) }


fun Activity.bindIntUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArgOrThrow(argNameResId) }

fun Activity.bindIntUriArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArgOr(argNameResId, defaultValue) }

fun Activity.bindIntUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argNameResId) }


fun Activity.bindLongUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArgOrThrow(argNameResId) }

fun Activity.bindLongUriArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArgOr(argNameResId, defaultValue) }

fun Activity.bindLongUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argNameResId) }


fun Activity.bindFloatUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArgOrThrow(argNameResId) }

fun Activity.bindFloatUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argNameResId) }


fun Activity.bindDoubleUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArgOrThrow(argNameResId) }

fun Activity.bindDoubleUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argNameResId) }


fun Activity.bindBooleanUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArgOrThrow(argNameResId) }

fun Activity.bindBooleanUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argNameResId) }


fun Activity.bindStringUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriArgOrThrow(argNameResId) }

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

fun Activity.bindStringUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOrThrow(argName) }

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

fun Activity.bindStringUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArgOrThrow(argNameResId) }

fun Activity.bindStringUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argNameResId) }


/* ************************************* SupportFragment Arg ***************************************** */


fun androidx.fragment.app.Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindByteArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindShortArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindIntArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindIntArrayListArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<androidx.fragment.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindLongArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<androidx.fragment.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindFloatArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<androidx.fragment.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindDoubleArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<androidx.fragment.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindBooleanArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<androidx.fragment.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun androidx.fragment.app.Fragment.bindCharArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindCharSequenceArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindStringArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<androidx.fragment.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindStringArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindStringArrayListArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argName) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argName) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argName) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun androidx.fragment.app.Fragment.bindBundleArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argName) }

fun androidx.fragment.app.Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun androidx.fragment.app.Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<androidx.fragment.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOrThrow(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }


/* ************************************* Origin Fragment Arg ***************************************** */


fun android.app.Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun android.app.Fragment.bindByteArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argName) }

fun android.app.Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun android.app.Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun android.app.Fragment.bindShortArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argName) }

fun android.app.Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun android.app.Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun android.app.Fragment.bindIntArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argName) }

fun android.app.Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<android.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun android.app.Fragment.bindIntArrayListArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argName) }

fun android.app.Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun android.app.Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun android.app.Fragment.bindLongArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argName) }

fun android.app.Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun android.app.Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun android.app.Fragment.bindFloatArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argName) }

fun android.app.Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun android.app.Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun android.app.Fragment.bindDoubleArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argName) }

fun android.app.Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun android.app.Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun android.app.Fragment.bindBooleanArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argName) }

fun android.app.Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun android.app.Fragment.bindCharArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argName) }

fun android.app.Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argName) }

fun android.app.Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argName) }

fun android.app.Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun android.app.Fragment.bindCharSequenceArrayListArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argName) }

fun android.app.Fragment.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun android.app.Fragment.bindStringArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOrThrow(argName) }

fun android.app.Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun android.app.Fragment.bindStringArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argName) }

fun android.app.Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun android.app.Fragment.bindStringArrayListArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argName) }

fun android.app.Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun android.app.Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argName) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argName) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argName) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun android.app.Fragment.bindBundleArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argName) }

fun android.app.Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun android.app.Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrThrow(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }


/* ************************************* SupportFragment Arg ***************************************** */


fun androidx.fragment.app.Fragment.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Byte> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindByteArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Short> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindShortArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<androidx.fragment.app.Fragment, Int> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindIntArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindIntArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<androidx.fragment.app.Fragment, Long> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindLongArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0f): ReadOnlyProperty<androidx.fragment.app.Fragment, Float> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindFloatArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<androidx.fragment.app.Fragment, Double> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindDoubleArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<androidx.fragment.app.Fragment, Boolean> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindBooleanArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<androidx.fragment.app.Fragment, Char> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun androidx.fragment.app.Fragment.bindCharArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindCharSequenceArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindStringArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<androidx.fragment.app.Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindStringArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindStringArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argNameResId) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argNameResId) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argNameResId) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> androidx.fragment.app.Fragment.bindSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<androidx.fragment.app.Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> androidx.fragment.app.Fragment.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun androidx.fragment.app.Fragment.bindBundleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argNameResId) }

fun androidx.fragment.app.Fragment.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun androidx.fragment.app.Fragment.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun androidx.fragment.app.Fragment.bindBinderArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): ReadOnlyProperty<androidx.fragment.app.Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun androidx.fragment.app.Fragment.bindSizeFArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<androidx.fragment.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argNameResId) }


/* ************************************* Origin Fragment Arg ***************************************** */


fun android.app.Fragment.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindByteArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindShortArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindIntArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IntArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<android.app.Fragment, IntArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindIntArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argNameResId) }

fun android.app.Fragment.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun android.app.Fragment.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindLongArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, LongArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<android.app.Fragment, LongArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindFloatArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindDoubleArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindBooleanArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }


fun android.app.Fragment.bindCharArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<android.app.Fragment, CharArray> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argNameResId) }

fun android.app.Fragment.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argNameResId) }

fun android.app.Fragment.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, String> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOrThrow(argNameResId) }

fun android.app.Fragment.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<android.app.Fragment, String> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argNameResId) }

fun android.app.Fragment.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun android.app.Fragment.bindStringArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argNameResId) }

fun android.app.Fragment.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argNameResId) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<android.app.Fragment, V> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun android.app.Fragment.bindBundleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Bundle> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argNameResId) }

fun android.app.Fragment.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<android.app.Fragment, Bundle> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun android.app.Fragment.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IBinder> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): ReadOnlyProperty<android.app.Fragment, IBinder> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Size> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): ReadOnlyProperty<android.app.Fragment, Size> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SizeF> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): ReadOnlyProperty<android.app.Fragment, SizeF> =
        OriginFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argNameResId) }

