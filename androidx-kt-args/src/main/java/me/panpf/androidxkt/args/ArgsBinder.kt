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

/* ************************************* SupportFragment ***************************************** */

// Boolean
fun SupportFragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<SupportFragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBoolean(argName, defaultValue) }

fun SupportFragment.bindOptionalBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<SupportFragment, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBoolean(argName, defaultValue) }

// BooleanArray
fun SupportFragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<SupportFragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBooleanArray(argName) }

fun SupportFragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<SupportFragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBooleanArray(argName) }


// Byte
fun SupportFragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<SupportFragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByte(argName, defaultValue) }

fun SupportFragment.bindOptionalByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<SupportFragment, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByte(argName, defaultValue) }

// ByteArray
fun SupportFragment.bindByteArrayArg(argName: String): ReadOnlyProperty<SupportFragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getByteArray(argName) }

fun SupportFragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<SupportFragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getByteArray(argName) }


// Char
fun SupportFragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<SupportFragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getChar(argName, defaultValue) }

fun SupportFragment.bindOptionalCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<SupportFragment, Char?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getChar(argName, defaultValue) }

// CharArray
fun SupportFragment.bindCharArrayArg(argName: String): ReadOnlyProperty<SupportFragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharArray(argName) }

fun SupportFragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<SupportFragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharArray(argName) }


// Short
fun SupportFragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<SupportFragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShort(argName, defaultValue) }

fun SupportFragment.bindOptionalShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<SupportFragment, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShort(argName, defaultValue) }

// ShortArray
fun SupportFragment.bindShortArrayArg(argName: String): ReadOnlyProperty<SupportFragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getShortArray(argName) }

fun SupportFragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<SupportFragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getShortArray(argName) }


// Float
fun SupportFragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<SupportFragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloat(argName, defaultValue) }

fun SupportFragment.bindOptionalFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<SupportFragment, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloat(argName, defaultValue) }

// FloatArray
fun SupportFragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<SupportFragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getFloatArray(argName) }

fun SupportFragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<SupportFragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getFloatArray(argName) }


// Int
fun SupportFragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<SupportFragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getInt(argName, defaultValue) }

fun SupportFragment.bindOptionalIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<SupportFragment, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getInt(argName, defaultValue) }

// IntArray
fun SupportFragment.bindIntArrayArg(argName: String): ReadOnlyProperty<SupportFragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntArray(argName) }

fun SupportFragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<SupportFragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntArray(argName) }

// ArrayList<Int>
fun SupportFragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getIntegerArrayList(argName) }

fun SupportFragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getIntegerArrayList(argName) }


// Double
fun SupportFragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<SupportFragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDouble(argName, defaultValue) }

fun SupportFragment.bindOptionalDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<SupportFragment, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDouble(argName, defaultValue) }

// DoubleArray
fun SupportFragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<SupportFragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getDoubleArray(argName) }

fun SupportFragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<SupportFragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getDoubleArray(argName) }


// Long
fun SupportFragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<SupportFragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLong(argName, defaultValue) }

fun SupportFragment.bindOptionalLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<SupportFragment, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLong(argName, defaultValue) }

// LongArray
fun SupportFragment.bindLongArrayArg(argName: String): ReadOnlyProperty<SupportFragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getLongArray(argName) }

fun SupportFragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<SupportFragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getLongArray(argName) }


// CharSequence
fun SupportFragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<SupportFragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequence(argName, defaultValue) }

fun SupportFragment.bindOptionalCharSequenceArg(argName: String, defaultValue: CharSequence? = null): ReadOnlyProperty<SupportFragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequence(argName, defaultValue) }

// Array<CharSequence>
fun SupportFragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getCharSequenceArray(argName) }

fun SupportFragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getCharSequenceArray(argName) }


// String
fun SupportFragment.bindStringArg(argName: String): ReadOnlyProperty<SupportFragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getString(argName) }

fun SupportFragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<SupportFragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getString(argName) }

// Array<String>
fun SupportFragment.bindStringArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArray(argName) }

fun SupportFragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArray(argName) }

// ArrayList<String>
fun SupportFragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getStringArrayList(argName) }

fun SupportFragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getStringArrayList(argName) }


// Parcelable
fun <V : Parcelable> SupportFragment.bindParcelableArg(argName: String): ReadOnlyProperty<SupportFragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelable(argName) }

fun <V : Parcelable> SupportFragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<SupportFragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelable(argName) }

// Array<Parcelable>
@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> SupportFragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArray(argName) as Array<V> }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> SupportFragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<SupportFragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArray(argName) as Array<V>? }

// ArrayList<Parcelable>
fun <V : Parcelable> SupportFragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getParcelableArrayList(argName) }

fun <V : Parcelable> SupportFragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<SupportFragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getParcelableArrayList(argName) }

// SparseArray<Parcelable>
fun <V : Parcelable> SupportFragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<SupportFragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments.setAppClassLoader()) { "arguments is null" }.getSparseParcelableArray(argName) }

fun <V : Parcelable> SupportFragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<SupportFragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments.setAppClassLoader()?.getSparseParcelableArray(argName) }

// Serializable
@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> SupportFragment.bindSerializableArg(argName: String): ReadOnlyProperty<SupportFragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSerializable(argName) as V }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> SupportFragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<SupportFragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSerializable(argName) as V? }


// IBinder
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun SupportFragment.bindBinderArg(argName: String): ReadOnlyProperty<SupportFragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBinder(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun SupportFragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<SupportFragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBinder(argName) }


// Bundle
fun SupportFragment.bindBundleArg(argName: String): ReadOnlyProperty<SupportFragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getBundle(argName) }

fun SupportFragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<SupportFragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getBundle(argName) }


// Size
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun SupportFragment.bindSizeArg(argName: String): ReadOnlyProperty<SupportFragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSize(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun SupportFragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<SupportFragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.arguments?.getSize(argName) }


// SizeF
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun SupportFragment.bindSizeFArg(argName: String): ReadOnlyProperty<SupportFragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> requireNotNull(this.arguments) { "arguments is null" }.getSizeF(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun SupportFragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<SupportFragment, SizeF?> =
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