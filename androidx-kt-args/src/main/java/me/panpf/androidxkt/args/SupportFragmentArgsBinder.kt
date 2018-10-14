package me.panpf.androidxkt.args

import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Fragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArg(argName, defaultValue) }


fun Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArrayArg(argName) }

fun Fragment.bindByteArrayArg(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readByteArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalByteArrayArg(argName) }


/* ************************************* Short ***************************************** */


fun Fragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArg(argName, defaultValue) }


fun Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArrayArg(argName) }

fun Fragment.bindShortArrayArg(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readShortArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalShortArrayArg(argName) }


/* ************************************* Int ***************************************** */


fun Fragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArg(argName, defaultValue) }


fun Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayArg(argName) }

fun Fragment.bindIntArrayArg(argName: String, defaultValue: IntArray): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalIntArrayArg(argName) }


fun Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayListArg(argName) }

fun Fragment.bindIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readIntArrayListArg(argName, defaultValue) }

fun Fragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalIntArrayListArg(argName) }


/* ************************************* Long ***************************************** */


fun Fragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArg(argName, defaultValue) }


fun Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArrayArg(argName) }

fun Fragment.bindLongArrayArg(argName: String, defaultValue: LongArray): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readLongArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalLongArrayArg(argName) }


/* ************************************* Float ***************************************** */


fun Fragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArg(argName, defaultValue) }


fun Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArrayArg(argName) }

fun Fragment.bindFloatArrayArg(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readFloatArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalFloatArrayArg(argName) }


/* ************************************* Double ***************************************** */


fun Fragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArg(argName, defaultValue) }


fun Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArrayArg(argName) }

fun Fragment.bindDoubleArrayArg(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readDoubleArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalDoubleArrayArg(argName) }


/* ************************************* Boolean ***************************************** */


fun Fragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArg(argName, defaultValue) }


fun Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArrayArg(argName) }

fun Fragment.bindBooleanArrayArg(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBooleanArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalBooleanArrayArg(argName) }


/* ************************************* Char ***************************************** */


fun Fragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArg(argName, defaultValue) }


fun Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArrayArg(argName) }

fun Fragment.bindCharArrayArg(argName: String, defaultValue: CharArray): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharArrayArg(argName) }


/* ************************************* CharSequence ***************************************** */


fun Fragment.bindCharSequenceArg(argName: String): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArg(argName) }

fun Fragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArg(argName, defaultValue) }

fun Fragment.bindOptionalCharSequenceArg(argName: String): ReadOnlyProperty<Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharSequenceArg(argName) }


fun Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArrayArg(argName) }

fun Fragment.bindCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readCharSequenceArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalCharSequenceArrayArg(argName) }


/* ************************************* String ***************************************** */


fun Fragment.bindStringArg(argName: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArg(argName) }

fun Fragment.bindStringArg(argName: String, defaultValue: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArg(argName) }


fun Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayArg(argName) }

fun Fragment.bindStringArrayArg(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArrayArg(argName) }


fun Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayListArg(argName) }

fun Fragment.bindStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readStringArrayListArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalStringArrayListArg(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArg(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArg(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArg(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArrayArg(argName) }


fun <V : Parcelable> Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayListArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readParcelableArrayListArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalParcelableArrayListArg(argName) }


fun <V : Parcelable> Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSparseParcelableArrayArg(argName) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArg(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSparseParcelableArrayArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalSparseParcelableArrayArg(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArg(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSerializableArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalSerializableArg(argName) }


/* ************************************* Bundle ***************************************** */


fun Fragment.bindBundleArg(argName: String): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBundleArg(argName) }

fun Fragment.bindBundleArg(argName: String, defaultValue: Bundle): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBundleArg(argName, defaultValue) }

fun Fragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalBundleArg(argName) }


/* ************************************* IBinder ***************************************** */


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArg(argName: String): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBinderArg(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArg(argName: String, defaultValue: IBinder): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> readBinderArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalBinderArg(argName) }


/* ************************************* Size ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArg(argName: String): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSizeArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArg(argName: String, defaultValue: Size): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSizeArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalSizeArg(argName) }


/* ************************************* SizeF ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSizeFArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArg(argName: String, defaultValue: SizeF): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> readSizeFArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> readOptionalSizeFArg(argName) }