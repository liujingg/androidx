package me.panpf.androidxkt.args

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Activity.bindByteArg(argName: String, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArg(argName, defaultValue) }


fun Activity.bindByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArrayArg(argName) }

fun Activity.bindByteArrayArg(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArrayArg(argName, defaultValue) }

fun Activity.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalByteArrayArg(argName) }


/* ************************************* Short ***************************************** */


fun Activity.bindShortArg(argName: String, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArg(argName, defaultValue) }


fun Activity.bindShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArrayArg(argName) }

fun Activity.bindShortArrayArg(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArrayArg(argName, defaultValue) }

fun Activity.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalShortArrayArg(argName) }


/* ************************************* Int ***************************************** */


fun Activity.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArg(argName, defaultValue) }


fun Activity.bindIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayArg(argName) }

fun Activity.bindIntArrayArg(argName: String, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayArg(argName, defaultValue) }

fun Activity.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalIntArrayArg(argName) }


fun Activity.bindIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayListArg(argName) }

fun Activity.bindIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayListArg(argName, defaultValue) }

fun Activity.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalIntArrayListArg(argName) }


/* ************************************* Long ***************************************** */


fun Activity.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArg(argName, defaultValue) }


fun Activity.bindLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArrayArg(argName) }

fun Activity.bindLongArrayArg(argName: String, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArrayArg(argName, defaultValue) }

fun Activity.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalLongArrayArg(argName) }


/* ************************************* Float ***************************************** */


fun Activity.bindFloatArg(argName: String, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArg(argName, defaultValue) }


fun Activity.bindFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArrayArg(argName) }

fun Activity.bindFloatArrayArg(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArrayArg(argName, defaultValue) }

fun Activity.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalFloatArrayArg(argName) }


/* ************************************* Double ***************************************** */


fun Activity.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArg(argName, defaultValue) }


fun Activity.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArrayArg(argName) }

fun Activity.bindDoubleArrayArg(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArrayArg(argName, defaultValue) }

fun Activity.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalDoubleArrayArg(argName) }


/* ************************************* Boolean ***************************************** */


fun Activity.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArg(argName, defaultValue) }


fun Activity.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArrayArg(argName) }

fun Activity.bindBooleanArrayArg(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArrayArg(argName, defaultValue) }

fun Activity.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalBooleanArrayArg(argName) }


/* ************************************* Char ***************************************** */


fun Activity.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArg(argName, defaultValue) }


fun Activity.bindCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArrayArg(argName) }

fun Activity.bindCharArrayArg(argName: String, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArrayArg(argName, defaultValue) }

fun Activity.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharArrayArg(argName) }


/* ************************************* CharSequence ***************************************** */


fun Activity.bindCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArg(argName) }

fun Activity.bindCharSequenceArg(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArg(argName, defaultValue) }

fun Activity.bindOptionalCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharSequenceArg(argName) }


fun Activity.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArrayArg(argName) }

fun Activity.bindCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArrayArg(argName, defaultValue) }

fun Activity.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharSequenceArrayArg(argName) }


/* ************************************* String ***************************************** */


fun Activity.bindStringArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArg(argName) }

fun Activity.bindStringArg(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArg(argName, defaultValue) }

fun Activity.bindOptionalStringArg(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArg(argName) }


fun Activity.bindStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayArg(argName) }

fun Activity.bindStringArrayArg(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayArg(argName, defaultValue) }

fun Activity.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArrayArg(argName) }


fun Activity.bindStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayListArg(argName) }

fun Activity.bindStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayListArg(argName, defaultValue) }

fun Activity.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArrayListArg(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Activity.bindParcelableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArg(argName) }

fun <V : Parcelable> Activity.bindParcelableArg(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArg(argName, defaultValue) }

fun <V : Parcelable> Activity.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArg(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArrayArg(argName) }


fun <V : Parcelable> Activity.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayListArg(argName) }

fun <V : Parcelable> Activity.bindParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayListArg(argName, defaultValue) }

fun <V : Parcelable> Activity.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArrayListArg(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSerializableArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalSerializableArg(argName) }


/* ************************************* Bundle ***************************************** */


fun Activity.bindBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBundleArg(argName) }

fun Activity.bindBundleArg(argName: String, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBundleArg(argName, defaultValue) }

fun Activity.bindOptionalBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalBundleArg(argName) }


fun Activity.bindExtrasArg(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> readExtrasArg() }

fun Activity.bindExtrasArg(defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> readExtrasArg(defaultValue) }

fun Activity.bindOptionalExtrasArg(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalExtrasArg() }