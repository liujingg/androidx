@file:Suppress("DEPRECATION")

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


/* ************************************* Byte ***************************************** */


fun Fragment.readByteArg(argName: String, defaultValue: Byte): Byte =
        this.arguments?.getByte(argName, defaultValue) ?: defaultValue


fun Fragment.readByteArrayArg(argName: String): ByteArray =
        requireNotNull(this.arguments?.getByteArray(argName)) { "Param '$argName' not found" }

fun Fragment.readByteArrayArg(argName: String, defaultValue: ByteArray): ByteArray =
        this.arguments?.getByteArray(argName) ?: defaultValue

fun Fragment.readOptionalByteArrayArg(argName: String): ByteArray? =
        this.arguments?.getByteArray(argName)


/* ************************************* Short ***************************************** */


fun Fragment.readShortArg(argName: String, defaultValue: Short): Short =
        this.arguments?.getShort(argName, defaultValue) ?: defaultValue


fun Fragment.readShortArrayArg(argName: String): ShortArray =
        requireNotNull(this.arguments?.getShortArray(argName)) { "Param '$argName' not found" }

fun Fragment.readShortArrayArg(argName: String, defaultValue: ShortArray): ShortArray =
        this.arguments?.getShortArray(argName) ?: defaultValue

fun Fragment.readOptionalShortArrayArg(argName: String): ShortArray? =
        this.arguments?.getShortArray(argName)


/* ************************************* Int ***************************************** */


fun Fragment.readIntArg(argName: String, defaultValue: Int): Int =
        this.arguments?.getInt(argName, defaultValue) ?: defaultValue


fun Fragment.readIntArrayArg(argName: String): IntArray? =
        requireNotNull(this.arguments?.getIntArray(argName)) { "Param '$argName' not found" }

fun Fragment.readIntArrayArg(argName: String, defaultValue: IntArray): IntArray =
        this.arguments?.getIntArray(argName) ?: defaultValue

fun Fragment.readOptionalIntArrayArg(argName: String): IntArray? =
        this.arguments?.getIntArray(argName)


fun Fragment.readIntArrayListArg(argName: String): ArrayList<Int> =
        requireNotNull(this.arguments?.getIntegerArrayList(argName)) { "Param '$argName' not found" }

fun Fragment.readIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        this.arguments?.getIntegerArrayList(argName) ?: defaultValue

fun Fragment.readOptionalIntArrayListArg(argName: String): ArrayList<Int>? =
        this.arguments?.getIntegerArrayList(argName)


/* ************************************* Long ***************************************** */



fun Fragment.readLongArg(argName: String, defaultValue: Long): Long =
        this.arguments?.getLong(argName, defaultValue) ?: defaultValue


fun Fragment.readLongArrayArg(argName: String): LongArray =
        requireNotNull(this.arguments?.getLongArray(argName)) { "Param '$argName' not found" }

fun Fragment.readLongArrayArg(argName: String, defaultValue: LongArray): LongArray =
        this.arguments?.getLongArray(argName) ?: defaultValue

fun Fragment.readOptionalLongArrayArg(argName: String): LongArray? =
        this.arguments?.getLongArray(argName)


/* ************************************* Float ***************************************** */



fun Fragment.readFloatArg(argName: String, defaultValue: Float): Float =
        this.arguments?.getFloat(argName, defaultValue) ?: defaultValue


fun Fragment.readFloatArrayArg(argName: String): FloatArray =
        requireNotNull(this.arguments?.getFloatArray(argName)) { "Param '$argName' not found" }

fun Fragment.readFloatArrayArg(argName: String, defaultValue: FloatArray): FloatArray =
        this.arguments?.getFloatArray(argName) ?: defaultValue

fun Fragment.readOptionalFloatArrayArg(argName: String): FloatArray? =
        this.arguments?.getFloatArray(argName)


/* ************************************* Double ***************************************** */



fun Fragment.readDoubleArg(argName: String, defaultValue: Double): Double =
        this.arguments?.getDouble(argName, defaultValue) ?: defaultValue


fun Fragment.readDoubleArrayArg(argName: String): DoubleArray =
        requireNotNull(this.arguments?.getDoubleArray(argName)) { "Param '$argName' not found" }

fun Fragment.readDoubleArrayArg(argName: String, defaultValue: DoubleArray): DoubleArray =
        this.arguments?.getDoubleArray(argName) ?: defaultValue

fun Fragment.readOptionalDoubleArrayArg(argName: String): DoubleArray? =
        this.arguments?.getDoubleArray(argName)


/* ************************************* Boolean ***************************************** */



fun Fragment.readBooleanArg(argName: String, defaultValue: Boolean): Boolean =
        this.arguments?.getBoolean(argName, defaultValue) ?: defaultValue


fun Fragment.readBooleanArrayArg(argName: String): BooleanArray =
        requireNotNull(this.arguments?.getBooleanArray(argName)) { "Param '$argName' not found" }

fun Fragment.readBooleanArrayArg(argName: String, defaultValue: BooleanArray): BooleanArray =
        this.arguments?.getBooleanArray(argName) ?: defaultValue

fun Fragment.readOptionalBooleanArrayArg(argName: String): BooleanArray? =
        this.arguments?.getBooleanArray(argName)


/* ************************************* Char ***************************************** */



fun Fragment.readCharArg(argName: String, defaultValue: Char): Char =
        this.arguments?.getChar(argName, defaultValue) ?: defaultValue


fun Fragment.readCharArrayArg(argName: String): CharArray =
        requireNotNull(this.arguments?.getCharArray(argName)) { "Param '$argName' not found" }

fun Fragment.readCharArrayArg(argName: String, defaultValue: CharArray): CharArray =
        this.arguments?.getCharArray(argName) ?: defaultValue

fun Fragment.readOptionalCharArrayArg(argName: String): CharArray? =
        this.arguments?.getCharArray(argName)


/* ************************************* CharSequence ***************************************** */


fun Fragment.readCharSequenceArg(argName: String): CharSequence =
        requireNotNull(this.arguments?.getCharSequence(argName)) { "Param '$argName' not found" }

fun Fragment.readCharSequenceArg(argName: String, defaultValue: CharSequence): CharSequence =
        this.arguments?.getCharSequence(argName, defaultValue)?.takeIf { it.trim() != "" }
                ?: defaultValue

fun Fragment.readOptionalCharSequenceArg(argName: String): CharSequence? =
        this.arguments?.getCharSequence(argName)


fun Fragment.readCharSequenceArrayArg(argName: String): Array<CharSequence> =
        requireNotNull(this.arguments?.getCharSequenceArray(argName)) { "Param '$argName' not found" }

fun Fragment.readCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        this.arguments?.getCharSequenceArray(argName) ?: defaultValue

fun Fragment.readOptionalCharSequenceArrayArg(argName: String): Array<CharSequence>? =
        this.arguments?.getCharSequenceArray(argName)


/* ************************************* String ***************************************** */


fun Fragment.readStringArg(argName: String): String =
        requireNotNull(this.arguments?.getString(argName)) { "Param '$argName' not found" }

fun Fragment.readStringArg(argName: String, defaultValue: String): String =
        this.arguments?.getString(argName, defaultValue)?.takeIf { it.trim() != "" } ?: defaultValue

fun Fragment.readOptionalStringArg(argName: String): String? =
        this.arguments?.getString(argName)


fun Fragment.readStringArrayArg(argName: String): Array<String> =
        requireNotNull(this.arguments?.getStringArray(argName)) { "Param '$argName' not found" }

fun Fragment.readStringArrayArg(argName: String, defaultValue: Array<String>): Array<String> =
        this.arguments?.getStringArray(argName) ?: defaultValue

fun Fragment.readOptionalStringArrayArg(argName: String): Array<String>? =
        this.arguments?.getStringArray(argName)


fun Fragment.readStringArrayListArg(argName: String): ArrayList<String> =
        requireNotNull(this.arguments?.getStringArrayList(argName)) { "Param '$argName' not found" }

fun Fragment.readStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        this.arguments?.getStringArrayList(argName) ?: defaultValue

fun Fragment.readOptionalStringArrayListArg(argName: String): ArrayList<String>? =
        this.arguments?.getStringArrayList(argName)


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Fragment.readParcelableArg(argName: String): V =
        requireNotNull(this.arguments?.setAppClassLoader()?.getParcelable(argName)) { "Param '$argName' not found" }

fun <V : Parcelable> Fragment.readParcelableArg(argName: String, defaultValue: V): V =
        this.arguments?.setAppClassLoader()?.getParcelable(argName) ?: defaultValue

fun <V : Parcelable> Fragment.readOptionalParcelableArg(argName: String): V? =
        this.arguments?.setAppClassLoader()?.getParcelable(argName)


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.readParcelableArrayArg(argName: String): Array<V> =
        requireNotNull(this.arguments?.setAppClassLoader()?.getParcelableArray(argName) as Array<V>?) { "Param '$argName' not found" }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.readParcelableArrayArg(argName: String, defaultValue: Array<V>): Array<V> =
        this.arguments?.setAppClassLoader()?.getParcelableArray(argName) as Array<V>?
                ?: defaultValue

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.readOptionalParcelableArrayArg(argName: String): Array<V>? =
        this.arguments?.setAppClassLoader()?.getParcelableArray(argName) as Array<V>?


fun <V : Parcelable> Fragment.readParcelableArrayListArg(argName: String): ArrayList<V> =
        requireNotNull(this.arguments?.setAppClassLoader()?.getParcelableArrayList(argName)) { "Param '$argName' not found" }

fun <V : Parcelable> Fragment.readParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        this.arguments?.setAppClassLoader()?.getParcelableArrayList(argName) ?: defaultValue

fun <V : Parcelable> Fragment.readOptionalParcelableArrayListArg(argName: String): ArrayList<V>? =
        this.arguments?.setAppClassLoader()?.getParcelableArrayList(argName)


fun <V : Parcelable> Fragment.readSparseParcelableArrayArg(argName: String): SparseArray<V> =
        requireNotNull(this.arguments?.setAppClassLoader()?.getSparseParcelableArray(argName)) { "Param '$argName' not found" }

fun <V : Parcelable> Fragment.readSparseParcelableArrayArg(argName: String, defaultValue: SparseArray<V>): SparseArray<V> =
        this.arguments?.setAppClassLoader()?.getSparseParcelableArray(argName) ?: defaultValue

fun <V : Parcelable> Fragment.readOptionalSparseParcelableArrayArg(argName: String): SparseArray<V>? =
        this.arguments?.setAppClassLoader()?.getSparseParcelableArray(argName)


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.readSerializableArg(argName: String): V =
        requireNotNull(this.arguments?.getSerializable(argName) as V?) { "Param '$argName' not found" }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.readSerializableArg(argName: String, defaultValue: V): V =
        this.arguments?.getSerializable(argName) as V? ?: defaultValue

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.readOptionalSerializableArg(argName: String): V? =
        this.arguments?.getSerializable(argName) as V?


/* ************************************* Bundle ***************************************** */


fun Fragment.readBundleArg(argName: String): Bundle =
        requireNotNull(this.arguments?.getBundle(argName)) { "Param '$argName' not found" }

fun Fragment.readBundleArg(argName: String, defaultValue: Bundle): Bundle =
        this.arguments?.getBundle(argName) ?: defaultValue

fun Fragment.readOptionalBundleArg(argName: String): Bundle? =
        this.arguments?.getBundle(argName)


/* ************************************* IBinder ***************************************** */


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.readBinderArg(argName: String): IBinder =
        requireNotNull(this.arguments?.getBinder(argName)) { "Param '$argName' not found" }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.readBinderArg(argName: String, defaultValue: IBinder): IBinder =
        this.arguments?.getBinder(argName) ?: defaultValue

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.readOptionalBinderArg(argName: String): IBinder? =
        this.arguments?.getBinder(argName)


/* ************************************* Size ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readSizeArg(argName: String): Size =
        requireNotNull(this.arguments?.getSize(argName)) { "Param '$argName' not found" }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readSizeArg(argName: String, defaultValue: Size): Size =
        this.arguments?.getSize(argName) ?: defaultValue

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readOptionalSizeArg(argName: String): Size? =
        this.arguments?.getSize(argName)


/* ************************************* SizeF ***************************************** */


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readSizeFArg(argName: String): SizeF =
        requireNotNull(this.arguments?.getSizeF(argName)) { "Param '$argName' not found" }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readSizeFArg(argName: String, defaultValue: SizeF): SizeF =
        this.arguments?.getSizeF(argName) ?: defaultValue

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.readOptionalSizeFArg(argName: String): SizeF? =
        this.arguments?.getSizeF(argName)