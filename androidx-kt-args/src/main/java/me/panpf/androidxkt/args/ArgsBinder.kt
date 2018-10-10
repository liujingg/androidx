package me.panpf.androidxkt.args

import android.app.Activity
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

/* ************************************* android.support.v4.app.Fragment ***************************************** */

// todo 基础类型没有 Optional
// todo 支持从 uri 中取参数

// Boolean
fun android.support.v4.app.Fragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.support.v4.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBoolean(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalBooleanArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBoolean(argName) }

// BooleanArray
fun android.support.v4.app.Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBooleanArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBooleanArray(argName) }


// Byte
fun android.support.v4.app.Fragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByte(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByte(argName, defaultValue) }

// ByteArray
fun android.support.v4.app.Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByteArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByteArray(argName) }


// Char
fun android.support.v4.app.Fragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.support.v4.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getChar(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.support.v4.app.Fragment, Char?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getChar(argName, defaultValue) }

// CharArray
fun android.support.v4.app.Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharArray(argName) }


// Short
fun android.support.v4.app.Fragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShort(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShort(argName, defaultValue) }

// ShortArray
fun android.support.v4.app.Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShortArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShortArray(argName) }


// Float
fun android.support.v4.app.Fragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.support.v4.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloat(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.support.v4.app.Fragment, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloat(argName, defaultValue) }

// FloatArray
fun android.support.v4.app.Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloatArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloatArray(argName) }


// Int
fun android.support.v4.app.Fragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getInt(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.support.v4.app.Fragment, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getInt(argName, defaultValue) }

// IntArray
fun android.support.v4.app.Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntArray(argName) }

// ArrayList<Int>
fun android.support.v4.app.Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntegerArrayList(argName) }

fun android.support.v4.app.Fragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntegerArrayList(argName) }


// Double
fun android.support.v4.app.Fragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.support.v4.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDouble(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.support.v4.app.Fragment, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDouble(argName, defaultValue) }

// DoubleArray
fun android.support.v4.app.Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDoubleArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDoubleArray(argName) }


// Long
fun android.support.v4.app.Fragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.support.v4.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLong(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.support.v4.app.Fragment, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLong(argName, defaultValue) }

// LongArray
fun android.support.v4.app.Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLongArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLongArray(argName) }


// CharSequence
fun android.support.v4.app.Fragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequence(argName, defaultValue) }

fun android.support.v4.app.Fragment.bindOptionalCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.support.v4.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequence(argName, defaultValue) }

// Array<CharSequence>
fun android.support.v4.app.Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequenceArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequenceArray(argName) }


// String
fun android.support.v4.app.Fragment.bindStringArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getString(argName) }

fun android.support.v4.app.Fragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getString(argName) }

// Array<String>
fun android.support.v4.app.Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArray(argName) }

fun android.support.v4.app.Fragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArray(argName) }

// ArrayList<String>
fun android.support.v4.app.Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArrayList(argName) }

fun android.support.v4.app.Fragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArrayList(argName) }


// Parcelable
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelable(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelable(argName) }

// Array<Parcelable>
@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArray(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.support.v4.app.Fragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArray(argName) as Array<V>? }

// ArrayList<Parcelable>
fun <V : Parcelable> android.support.v4.app.Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArrayList(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArrayList(argName) }

// SparseArray<Parcelable>
fun <V : Parcelable> android.support.v4.app.Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getSparseParcelableArray(argName) }

fun <V : Parcelable> android.support.v4.app.Fragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getSparseParcelableArray(argName) }

// Serializable
@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSerializable(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.support.v4.app.Fragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSerializable(argName) as V? }


// IBinder
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindBinderArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBinder(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.support.v4.app.Fragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBinder(argName) }


// Bundle
fun android.support.v4.app.Fragment.bindBundleArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBundle(argName) }

fun android.support.v4.app.Fragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBundle(argName) }


// Size
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSize(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSize(argName) }


// SizeF
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSizeF(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.support.v4.app.Fragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<android.support.v4.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSizeF(argName) }

/* ************************************* android.app.Fragment ***************************************** */

// Boolean
fun android.app.Fragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBoolean(argName, defaultValue) }

fun android.app.Fragment.bindOptionalBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<android.app.Fragment, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBoolean(argName, defaultValue) }

// BooleanArray
fun android.app.Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBooleanArray(argName) }

fun android.app.Fragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBooleanArray(argName) }


// Byte
fun android.app.Fragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByte(argName, defaultValue) }

fun android.app.Fragment.bindOptionalByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<android.app.Fragment, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByte(argName, defaultValue) }

// ByteArray
fun android.app.Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByteArray(argName) }

fun android.app.Fragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByteArray(argName) }


// Char
fun android.app.Fragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getChar(argName, defaultValue) }

fun android.app.Fragment.bindOptionalCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<android.app.Fragment, Char?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getChar(argName, defaultValue) }

// CharArray
fun android.app.Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharArray(argName) }

fun android.app.Fragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharArray(argName) }


// Short
fun android.app.Fragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShort(argName, defaultValue) }

fun android.app.Fragment.bindOptionalShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<android.app.Fragment, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShort(argName, defaultValue) }

// ShortArray
fun android.app.Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShortArray(argName) }

fun android.app.Fragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShortArray(argName) }


// Float
fun android.app.Fragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloat(argName, defaultValue) }

fun android.app.Fragment.bindOptionalFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<android.app.Fragment, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloat(argName, defaultValue) }

// FloatArray
fun android.app.Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloatArray(argName) }

fun android.app.Fragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloatArray(argName) }


// Int
fun android.app.Fragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getInt(argName, defaultValue) }

fun android.app.Fragment.bindOptionalIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<android.app.Fragment, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getInt(argName, defaultValue) }

// IntArray
fun android.app.Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntArray(argName) }

fun android.app.Fragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntArray(argName) }

// ArrayList<Int>
fun android.app.Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntegerArrayList(argName) }

fun android.app.Fragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntegerArrayList(argName) }


// Double
fun android.app.Fragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDouble(argName, defaultValue) }

fun android.app.Fragment.bindOptionalDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<android.app.Fragment, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDouble(argName, defaultValue) }

// DoubleArray
fun android.app.Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDoubleArray(argName) }

fun android.app.Fragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDoubleArray(argName) }


// Long
fun android.app.Fragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLong(argName, defaultValue) }

fun android.app.Fragment.bindOptionalLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<android.app.Fragment, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLong(argName, defaultValue) }

// LongArray
fun android.app.Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLongArray(argName) }

fun android.app.Fragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLongArray(argName) }


// CharSequence
fun android.app.Fragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.app.Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequence(argName, defaultValue) }

fun android.app.Fragment.bindOptionalCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<android.app.Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequence(argName, defaultValue) }

// Array<CharSequence>
fun android.app.Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequenceArray(argName) }

fun android.app.Fragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequenceArray(argName) }


// String
fun android.app.Fragment.bindStringArg(argName: String): ReadOnlyProperty<android.app.Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getString(argName) }

fun android.app.Fragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<android.app.Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getString(argName) }

// Array<String>
fun android.app.Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArray(argName) }

fun android.app.Fragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArray(argName) }

// ArrayList<String>
fun android.app.Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArrayList(argName) }

fun android.app.Fragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArrayList(argName) }


// Parcelable
fun <V : Parcelable> android.app.Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelable(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelable(argName) }

// Array<Parcelable>
@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArray(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArray(argName) as Array<V>? }

// ArrayList<Parcelable>
fun <V : Parcelable> android.app.Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArrayList(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<android.app.Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArrayList(argName) }

// SparseArray<Parcelable>
fun <V : Parcelable> android.app.Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getSparseParcelableArray(argName) }

fun <V : Parcelable> android.app.Fragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<android.app.Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getSparseParcelableArray(argName) }

// Serializable
@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSerializable(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> android.app.Fragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<android.app.Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSerializable(argName) as V? }


// IBinder
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindBinderArg(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBinder(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun android.app.Fragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<android.app.Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBinder(argName) }


// Bundle
fun android.app.Fragment.bindBundleArg(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBundle(argName) }

fun android.app.Fragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<android.app.Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBundle(argName) }


// Size
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeArg(argName: String): ReadOnlyProperty<android.app.Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSize(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<android.app.Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSize(argName) }


// SizeF
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSizeF(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun android.app.Fragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<android.app.Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSizeF(argName) }


/* ************************************* Activity ***************************************** */

// Boolean
fun Activity.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBooleanExtra(argName, defaultValue) }

fun Activity.bindOptionalBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getBooleanExtra(argName, defaultValue) }

// BooleanArray
fun Activity.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBooleanArrayExtra(argName) }

fun Activity.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getBooleanArrayExtra(argName) }


// Byte
fun Activity.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getByteExtra(argName, defaultValue) }

fun Activity.bindOptionalByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getByteExtra(argName, defaultValue) }

// ByteArray
fun Activity.bindByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getByteArrayExtra(argName) }

fun Activity.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getByteArrayExtra(argName) }


// Char
fun Activity.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharExtra(argName, defaultValue) }

fun Activity.bindOptionalCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharExtra(argName, defaultValue) }

// CharArray
fun Activity.bindCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharArrayExtra(argName) }

fun Activity.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharArrayExtra(argName) }


// Short
fun Activity.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getShortExtra(argName, defaultValue) }

fun Activity.bindOptionalShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getShortExtra(argName, defaultValue) }

// ShortArray
fun Activity.bindShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getShortArrayExtra(argName) }

fun Activity.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getShortArrayExtra(argName) }


// Float
fun Activity.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getFloatExtra(argName, defaultValue) }

fun Activity.bindOptionalFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getFloatExtra(argName, defaultValue) }

// FloatArray
fun Activity.bindFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getFloatArrayExtra(argName) }

fun Activity.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getFloatArrayExtra(argName) }


// Int
fun Activity.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntExtra(argName, defaultValue) }

fun Activity.bindOptionalIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getIntExtra(argName, defaultValue) }

// IntArray
fun Activity.bindIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntArrayExtra(argName) }

fun Activity.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getIntArrayExtra(argName) }

// ArrayList<Int>
fun Activity.bindIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getIntegerArrayListExtra(argName) }

fun Activity.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getIntegerArrayListExtra(argName) }


// Double
fun Activity.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getDoubleExtra(argName, defaultValue) }

fun Activity.bindOptionalDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getDoubleExtra(argName, defaultValue) }

// DoubleArray
fun Activity.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getDoubleArrayExtra(argName) }

fun Activity.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getDoubleArrayExtra(argName) }


// Long
fun Activity.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getLongExtra(argName, defaultValue) }

fun Activity.bindOptionalLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getLongExtra(argName, defaultValue) }

// LongArray
fun Activity.bindLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getLongArrayExtra(argName) }

fun Activity.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getLongArrayExtra(argName) }


// CharSequence
fun Activity.bindCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharSequenceExtra(argName) }

fun Activity.bindOptionalCharSequenceArg(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharSequenceExtra(argName) }

// Array<CharSequence>
fun Activity.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getCharSequenceArrayExtra(argName) }

fun Activity.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getCharSequenceArrayExtra(argName) }


// String
fun Activity.bindStringArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringExtra(argName) }

fun Activity.bindOptionalStringArg(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringExtra(argName) }

// Array<String>
fun Activity.bindStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringArrayExtra(argName) }

fun Activity.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringArrayExtra(argName) }

// ArrayList<String>
fun Activity.bindStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getStringArrayListExtra(argName) }

fun Activity.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getStringArrayListExtra(argName) }


// Parcelable
fun <V : Parcelable> Activity.bindParcelableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.intent.getParcelableExtra(argName)
        }

fun <V : Parcelable> Activity.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableExtra(argName) }

// Array<Parcelable>
@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getParcelableArrayExtra(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableArrayExtra(argName) as Array<V>? }

// ArrayList<Parcelable>
fun <V : Parcelable> Activity.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getParcelableArrayListExtra(argName) }

fun <V : Parcelable> Activity.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getParcelableArrayListExtra(argName) }

// Serializable
@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArg(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getSerializableExtra(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getSerializableExtra(argName) as V? }


// Bundle
fun Activity.bindBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.intent.getBundleExtra(argName) }

fun Activity.bindOptionalBundleArg(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.getBundleExtra(argName) }

// Extras Bundle
fun Activity.bindExtrasArg(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.intent.extras }

fun Activity.bindOptionalExtrasArg(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.intent.extras }


private class ArgLazy<in REF, out OUT : Any>(val argName: String, val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '$argName' from arguments. 2" }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

private class OptionalArgLazy<in REF, out OUT>(val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT?> {
    private object EMPTY

    var arg: Any? = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT? {
        if (arg == EMPTY) {
            arg = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

/**
 * 当 Bundle 中存在 Bundle，子 Bundle 中又存在自定义的 Parcelable 时 get Parcelable 的时候会报 ClassNotFound 异常
 * 这是因为子 Bundle 在反序列化的时候其 classLoader 丢失了，get Parcelable 的时候就用 BootClassLoader 代替了
 * BootClassLoader 只有 java 和 android 的类，因此这里给 Bundle 设置 PathClassLoader 才能正常反序列化我们自定义的 Parcelable
 */
fun Bundle?.setAppClassLoader(): Bundle? {
    this?.classLoader = ArgLazy::class.java.classLoader
    return this
}