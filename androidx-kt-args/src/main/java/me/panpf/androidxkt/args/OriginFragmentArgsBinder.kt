package me.panpf.androidxkt.args

import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import android.support.v4.app.Fragment as SupportFragment


/* ************************************* Byte ***************************************** */


fun android.app.Fragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByte(argName, defaultValue) }

fun android.app.Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByteArray(argName) }

fun android.app.Fragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByteArray(argName) }


/* ************************************* Short ***************************************** */


fun android.app.Fragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShort(argName, defaultValue) }

fun android.app.Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShortArray(argName) }

fun android.app.Fragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShortArray(argName) }


/* ************************************* Int ***************************************** */


fun android.app.Fragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getInt(argName, defaultValue) }

fun android.app.Fragment.bindOptionalIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getInt(argName, defaultValue) }

fun android.app.Fragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntArray(argName) }

fun android.app.Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntegerArrayList(argName) }

fun android.app.Fragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntegerArrayList(argName) }


/* ************************************* Long ***************************************** */


fun android.app.Fragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLong(argName, defaultValue) }

fun android.app.Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLongArray(argName) }

fun android.app.Fragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLongArray(argName) }


/* ************************************* Float ***************************************** */


fun android.app.Fragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloat(argName, defaultValue) }

fun android.app.Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloatArray(argName) }

fun android.app.Fragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloatArray(argName) }


/* ************************************* Double ***************************************** */


fun android.app.Fragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDouble(argName, defaultValue) }

fun android.app.Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDoubleArray(argName) }

fun android.app.Fragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDoubleArray(argName) }


/* ************************************* Boolean ***************************************** */


fun android.app.Fragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBoolean(argName, defaultValue) }

fun android.app.Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBooleanArray(argName) }

fun android.app.Fragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBooleanArray(argName) }


/* ************************************* Char ***************************************** */


fun android.app.Fragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getChar(argName, defaultValue) }

fun android.app.Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharArray(argName) }

fun android.app.Fragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharArray(argName) }


/* ************************************* CharSequence ***************************************** */


fun android.app.Fragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequence(argName, defaultValue) }

fun android.app.Fragment.bindOptionalCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequence(argName, defaultValue) }

fun android.app.Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequenceArray(argName) }

fun android.app.Fragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequenceArray(argName) }


/* ************************************* String ***************************************** */


fun android.app.Fragment.bindStringArg(argName: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getString(argName) }

fun android.app.Fragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getString(argName) }

fun android.app.Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArray(argName) }

fun android.app.Fragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArray(argName) }

fun android.app.Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArrayList(argName) }

fun android.app.Fragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArrayList(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> android.app.Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelable(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelable(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArray(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArray(argName) as Array<V>? }

fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArrayList(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArrayList(argName) }

fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getSparseParcelableArray(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getSparseParcelableArray(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSerializable(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSerializable(argName) as V? }


/* ************************************* Bundle ***************************************** */


fun android.app.Fragment.bindBundleArg(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBundle(argName) }

fun android.app.Fragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBundle(argName) }


/* ************************************* IBinder ***************************************** */


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArg(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBinder(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBinder(argName) }


/* ************************************* Size ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArg(argName: String): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSize(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSize(argName) }


/* ************************************* SizeF ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSizeF(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSizeF(argName) }