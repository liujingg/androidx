package me.panpf.androidxkt.args

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import android.support.v4.app.Fragment as SupportFragment


/* ************************************* Byte ***************************************** */


fun Activity.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getByteExtra(argName, defaultValue) }

fun Activity.bindByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getByteArrayExtra(argName) }

fun Activity.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getByteArrayExtra(argName) }


/* ************************************* Short ***************************************** */


fun Activity.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getShortExtra(argName, defaultValue) }

fun Activity.bindShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getShortArrayExtra(argName) }

fun Activity.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getShortArrayExtra(argName) }


/* ************************************* Int ***************************************** */


fun Activity.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntExtra(argName, defaultValue) }

fun Activity.bindIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntArrayExtra(argName) }

fun Activity.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getIntArrayExtra(argName) }

fun Activity.bindIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntegerArrayListExtra(argName) }

fun Activity.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getIntegerArrayListExtra(argName) }


/* ************************************* Long ***************************************** */


fun Activity.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getLongExtra(argName, defaultValue) }

fun Activity.bindLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getLongArrayExtra(argName) }

fun Activity.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getLongArrayExtra(argName) }


/* ************************************* Float ***************************************** */


fun Activity.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getFloatExtra(argName, defaultValue) }

fun Activity.bindFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getFloatArrayExtra(argName) }

fun Activity.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getFloatArrayExtra(argName) }


/* ************************************* Double ***************************************** */


fun Activity.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getDoubleExtra(argName, defaultValue) }

fun Activity.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getDoubleArrayExtra(argName) }

fun Activity.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getDoubleArrayExtra(argName) }


/* ************************************* Boolean ***************************************** */


fun Activity.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBooleanExtra(argName, defaultValue) }

fun Activity.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBooleanArrayExtra(argName) }

fun Activity.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getBooleanArrayExtra(argName) }


/* ************************************* Char ***************************************** */


fun Activity.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharExtra(argName, defaultValue) }

fun Activity.bindCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharArrayExtra(argName) }

fun Activity.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharArrayExtra(argName) }


/* ************************************* CharSequence ***************************************** */


fun Activity.bindCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharSequenceExtra(argName) }

fun Activity.bindOptionalCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharSequenceExtra(argName) }

fun Activity.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharSequenceArrayExtra(argName) }

fun Activity.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharSequenceArrayExtra(argName) }


/* ************************************* String ***************************************** */


fun Activity.bindStringArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringExtra(argName) }

fun Activity.bindOptionalStringArg(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringExtra(argName) }

fun Activity.bindStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringArrayExtra(argName) }

fun Activity.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringArrayExtra(argName) }

fun Activity.bindStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringArrayListExtra(argName) }

fun Activity.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringArrayListExtra(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Activity.bindParcelableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getParcelableExtra(argName) }

fun <V : Parcelable> Activity.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableExtra(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getParcelableArrayExtra(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableArrayExtra(argName) as Array<V>? }

fun <V : Parcelable> Activity.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getParcelableArrayListExtra(argName) }

fun <V : Parcelable> Activity.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableArrayListExtra(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getSerializableExtra(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getSerializableExtra(argName) as V? }


/* ************************************* Bundle ***************************************** */


fun Activity.bindBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBundleExtra(argName) }

fun Activity.bindOptionalBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getBundleExtra(argName) }

fun Activity.bindExtrasArg(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.intent.extras }

fun Activity.bindOptionalExtrasArg(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.extras }