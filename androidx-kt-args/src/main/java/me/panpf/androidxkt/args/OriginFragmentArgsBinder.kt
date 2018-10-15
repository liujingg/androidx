package me.panpf.androidxkt.args

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Fragment.bindByteArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArg(argName, defaultValue) }


fun Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun Fragment.bindByteArrayArg(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalByteArrayArg(argName: String): ReadOnlyProperty<Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalByteArrayArg(argName) }


/* ************************************* Short ***************************************** */


fun Fragment.bindShortArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArg(argName, defaultValue) }


fun Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun Fragment.bindShortArrayArg(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalShortArrayArg(argName: String): ReadOnlyProperty<Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalShortArrayArg(argName) }


/* ************************************* Int ***************************************** */


fun Fragment.bindIntArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArg(argName, defaultValue) }


fun Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName) }

fun Fragment.bindIntArrayArg(argName: String, defaultValue: IntArray): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalIntArrayArg(argName: String): ReadOnlyProperty<Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalIntArrayArg(argName) }


fun Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName) }

fun Fragment.bindIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName, defaultValue) }

fun Fragment.bindOptionalIntArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalIntArrayListArg(argName) }


/* ************************************* Long ***************************************** */


fun Fragment.bindLongArg(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArg(argName, defaultValue) }


fun Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun Fragment.bindLongArrayArg(argName: String, defaultValue: LongArray): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalLongArrayArg(argName: String): ReadOnlyProperty<Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalLongArrayArg(argName) }


/* ************************************* Float ***************************************** */


fun Fragment.bindFloatArg(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArg(argName, defaultValue) }


fun Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun Fragment.bindFloatArrayArg(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalFloatArrayArg(argName: String): ReadOnlyProperty<Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalFloatArrayArg(argName) }


/* ************************************* Double ***************************************** */


fun Fragment.bindDoubleArg(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArg(argName, defaultValue) }


fun Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun Fragment.bindDoubleArrayArg(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalDoubleArrayArg(argName: String): ReadOnlyProperty<Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalDoubleArrayArg(argName) }


/* ************************************* Boolean ***************************************** */


fun Fragment.bindBooleanArg(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArg(argName, defaultValue) }


fun Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun Fragment.bindBooleanArrayArg(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalBooleanArrayArg(argName: String): ReadOnlyProperty<Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalBooleanArrayArg(argName) }


/* ************************************* Char ***************************************** */


fun Fragment.bindCharArg(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArg(argName, defaultValue) }


fun Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun Fragment.bindCharArrayArg(argName: String, defaultValue: CharArray): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalCharArrayArg(argName: String): ReadOnlyProperty<Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalCharArrayArg(argName) }


/* ************************************* CharSequence ***************************************** */


fun Fragment.bindCharSequenceArg(argName: String): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName) }

fun Fragment.bindCharSequenceArg(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName, defaultValue) }

fun Fragment.bindOptionalCharSequenceArg(argName: String): ReadOnlyProperty<Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalCharSequenceArg(argName) }


fun Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName) }

fun Fragment.bindCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalCharSequenceArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalCharSequenceArrayArg(argName) }


/* ************************************* String ***************************************** */


fun Fragment.bindStringArg(argName: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName) }

fun Fragment.bindStringArg(argName: String, defaultValue: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArg(argName: String): ReadOnlyProperty<Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalStringArg(argName) }


fun Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName) }

fun Fragment.bindStringArrayArg(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalStringArrayArg(argName) }


fun Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName) }

fun Fragment.bindStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName, defaultValue) }

fun Fragment.bindOptionalStringArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalStringArrayListArg(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArg(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalParcelableArg(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalParcelableArg(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArg(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindOptionalParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalParcelableArrayArg(argName) }


fun <V : Parcelable> Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalParcelableArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalParcelableArrayListArg(argName) }


fun <V : Parcelable> Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argName) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArg(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindOptionalSparseParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalSparseParcelableArrayArg(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArg(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindOptionalSerializableArg(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalSerializableArg(argName) }


/* ************************************* Bundle ***************************************** */


fun Fragment.bindBundleArg(argName: String): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName) }

fun Fragment.bindBundleArg(argName: String, defaultValue: Bundle): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName, defaultValue) }

fun Fragment.bindOptionalBundleArg(argName: String): ReadOnlyProperty<Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalBundleArg(argName) }


/* ************************************* IBinder ***************************************** */


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArg(argName: String): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArg(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArg(argName: String, defaultValue: IBinder): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindOptionalBinderArg(argName: String): ReadOnlyProperty<Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalBinderArg(argName) }


/* ************************************* Size ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArg(argName: String): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArg(argName: String, defaultValue: Size): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindOptionalSizeArg(argName: String): ReadOnlyProperty<Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalSizeArg(argName) }


/* ************************************* SizeF ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArg(argName: String, defaultValue: SizeF): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArg(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindOptionalSizeFArg(argName: String): ReadOnlyProperty<Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readOptionalSizeFArg(argName) }