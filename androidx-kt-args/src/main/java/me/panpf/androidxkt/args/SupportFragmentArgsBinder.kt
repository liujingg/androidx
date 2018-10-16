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
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }


fun Fragment.bindByteArrayArg(argName: String): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArg(argName) }

fun Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


/* ************************************* Short ***************************************** */


fun Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }


fun Fragment.bindShortArrayArg(argName: String): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArg(argName) }

fun Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


/* ************************************* Int ***************************************** */


fun Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }


fun Fragment.bindIntArrayArg(argName: String): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArg(argName) }

fun Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun Fragment.bindIntArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArg(argName) }

fun Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


/* ************************************* Long ***************************************** */


fun Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }


fun Fragment.bindLongArrayArg(argName: String): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArg(argName) }

fun Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


/* ************************************* Float ***************************************** */


fun Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }


fun Fragment.bindFloatArrayArg(argName: String): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArg(argName) }

fun Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


/* ************************************* Double ***************************************** */


fun Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }


fun Fragment.bindDoubleArrayArg(argName: String): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArg(argName) }

fun Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


/* ************************************* Boolean ***************************************** */


fun Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }


fun Fragment.bindBooleanArrayArg(argName: String): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArg(argName) }

fun Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


/* ************************************* Char ***************************************** */


fun Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }


fun Fragment.bindCharArrayArg(argName: String): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArg(argName) }

fun Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


/* ************************************* CharSequence ***************************************** */


fun Fragment.bindCharSequenceArg(argName: String): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArg(argName) }

fun Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun Fragment.bindCharSequenceArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArg(argName) }

fun Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


/* ************************************* String ***************************************** */


fun Fragment.bindStringArg(argName: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArg(argName) }

fun Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun Fragment.bindStringArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArg(argName) }

fun Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun Fragment.bindStringArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArg(argName) }

fun Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Fragment.bindParcelableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> Fragment.bindParcelableArrayListArg(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArg(argName) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> Fragment.bindSparseParcelableArrayArg(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArg(argName) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArg(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArg(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


/* ************************************* Bundle ***************************************** */


fun Fragment.bindBundleArg(argName: String): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArg(argName) }

fun Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


/* ************************************* IBinder ***************************************** */


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArg(argName: String): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArg(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


/* ************************************* Size ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArg(argName: String): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


/* ************************************* SizeF ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArg(argName: String): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArg(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }