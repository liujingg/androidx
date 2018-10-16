package me.panpf.androidxkt.args

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Activity.bindByteArgOr(argName: String, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun Activity.bindByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun Activity.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Activity.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


/* ************************************* Short ***************************************** */


fun Activity.bindShortArgOr(argName: String, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun Activity.bindShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun Activity.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Activity.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


/* ************************************* Int ***************************************** */


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


/* ************************************* Long ***************************************** */


fun Activity.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun Activity.bindLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun Activity.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Activity.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


/* ************************************* Float ***************************************** */


fun Activity.bindFloatArgOr(argName: String, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun Activity.bindFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun Activity.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


/* ************************************* Double ***************************************** */


fun Activity.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun Activity.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun Activity.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


/* ************************************* Boolean ***************************************** */


fun Activity.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun Activity.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun Activity.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


/* ************************************* Char ***************************************** */


fun Activity.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun Activity.bindCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun Activity.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Activity.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


/* ************************************* CharSequence ***************************************** */


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


/* ************************************* String ***************************************** */


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


/* ************************************* Parcelable ***************************************** */


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


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


/* ************************************* Bundle ***************************************** */


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