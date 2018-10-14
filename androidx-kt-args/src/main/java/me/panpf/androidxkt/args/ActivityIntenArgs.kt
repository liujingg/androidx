package me.panpf.androidxkt.args

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment as SupportFragment


/* ************************************* Byte ***************************************** */


fun Activity.readByteArg(argName: String, defaultValue: Byte): Byte =
        this.intent.getByteExtra(argName, defaultValue)


fun Activity.readByteArrayArg(argName: String): ByteArray =
        requireNotNull(this.intent.getByteArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readByteArrayArg(argName: String, defaultValue: ByteArray): ByteArray =
        this.intent.getByteArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalByteArrayArg(argName: String): ByteArray? =
        this.intent.getByteArrayExtra(argName)


/* ************************************* Short ***************************************** */


fun Activity.readShortArg(argName: String, defaultValue: Short): Short =
        this.intent.getShortExtra(argName, defaultValue)


fun Activity.readShortArrayArg(argName: String): ShortArray =
        requireNotNull(this.intent.getShortArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readShortArrayArg(argName: String, defaultValue: ShortArray): ShortArray =
        this.intent.getShortArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalShortArrayArg(argName: String): ShortArray? =
        this.intent.getShortArrayExtra(argName)


/* ************************************* Int ***************************************** */


fun Activity.readIntArg(argName: String, defaultValue: Int): Int =
        this.intent.getIntExtra(argName, defaultValue)


fun Activity.readIntArrayArg(argName: String): IntArray =
        requireNotNull(this.intent.getIntArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readIntArrayArg(argName: String, defaultValue: IntArray): IntArray =
        this.intent.getIntArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalIntArrayArg(argName: String): IntArray? =
        this.intent.getIntArrayExtra(argName)


fun Activity.readIntArrayListArg(argName: String): ArrayList<Int> =
        requireNotNull(this.intent.getIntegerArrayListExtra(argName)) { "Param '$argName' not found" }


fun Activity.readIntArrayListArg(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> =
        this.intent.getIntegerArrayListExtra(argName) ?: defaultValue

fun Activity.readOptionalIntArrayListArg(argName: String): ArrayList<Int>? =
        this.intent.getIntegerArrayListExtra(argName)


/* ************************************* Long ***************************************** */


fun Activity.readLongArg(argName: String, defaultValue: Long): Long =
        this.intent.getLongExtra(argName, defaultValue)


fun Activity.readLongArrayArg(argName: String): LongArray =
        requireNotNull(this.intent.getLongArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readLongArrayArg(argName: String, defaultValue: LongArray): LongArray =
        this.intent.getLongArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalLongArrayArg(argName: String): LongArray? =
        this.intent.getLongArrayExtra(argName)


/* ************************************* Float ***************************************** */


fun Activity.readFloatArg(argName: String, defaultValue: Float): Float =
        this.intent.getFloatExtra(argName, defaultValue)


fun Activity.readFloatArrayArg(argName: String): FloatArray =
        requireNotNull(this.intent.getFloatArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readFloatArrayArg(argName: String, defaultValue: FloatArray): FloatArray =
        this.intent.getFloatArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalFloatArrayArg(argName: String): FloatArray? =
        this.intent.getFloatArrayExtra(argName)


/* ************************************* Double ***************************************** */


fun Activity.readDoubleArg(argName: String, defaultValue: Double): Double =
        this.intent.getDoubleExtra(argName, defaultValue)


fun Activity.readDoubleArrayArg(argName: String): DoubleArray =
        requireNotNull(this.intent.getDoubleArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readDoubleArrayArg(argName: String, defaultValue: DoubleArray): DoubleArray =
        this.intent.getDoubleArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalDoubleArrayArg(argName: String): DoubleArray? =
        this.intent.getDoubleArrayExtra(argName)


/* ************************************* Boolean ***************************************** */


fun Activity.readBooleanArg(argName: String, defaultValue: Boolean): Boolean =
        this.intent.getBooleanExtra(argName, defaultValue)


fun Activity.readBooleanArrayArg(argName: String): BooleanArray =
        requireNotNull(this.intent.getBooleanArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readBooleanArrayArg(argName: String, defaultValue: BooleanArray): BooleanArray =
        this.intent.getBooleanArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalBooleanArrayArg(argName: String): BooleanArray? =
        this.intent.getBooleanArrayExtra(argName)


/* ************************************* Char ***************************************** */


fun Activity.readCharArg(argName: String, defaultValue: Char): Char =
        this.intent.getCharExtra(argName, defaultValue)


fun Activity.readCharArrayArg(argName: String): CharArray =
        requireNotNull(this.intent.getCharArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readCharArrayArg(argName: String, defaultValue: CharArray): CharArray =
        this.intent.getCharArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalCharArrayArg(argName: String): CharArray? =
        this.intent.getCharArrayExtra(argName)


/* ************************************* CharSequence ***************************************** */


fun Activity.readCharSequenceArg(argName: String): CharSequence =
        requireNotNull(this.intent.getCharSequenceExtra(argName)) { "Param '$argName' not found" }

fun Activity.readCharSequenceArg(argName: String, defaultValue: CharSequence): CharSequence =
        this.intent.getCharSequenceExtra(argName)?.takeIf { it.trim() != "" } ?: defaultValue

fun Activity.readOptionalCharSequenceArg(argName: String): CharSequence? =
        this.intent.getCharSequenceExtra(argName)


fun Activity.readCharSequenceArrayArg(argName: String): Array<CharSequence> =
        requireNotNull(this.intent.getCharSequenceArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readCharSequenceArrayArg(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> =
        this.intent.getCharSequenceArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalCharSequenceArrayArg(argName: String): Array<CharSequence>? =
        this.intent.getCharSequenceArrayExtra(argName)


/* ************************************* String ***************************************** */


fun Activity.readStringArg(argName: String): String =
        requireNotNull(this.intent.getStringExtra(argName)) { "Param '$argName' not found" }

fun Activity.readStringArg(argName: String, defaultValue: String): String =
        this.intent.getStringExtra(argName)?.takeIf { it.trim() != "" } ?: defaultValue

fun Activity.readOptionalStringArg(argName: String): String? =
        this.intent.getStringExtra(argName)


fun Activity.readStringArrayArg(argName: String): Array<String> =
        requireNotNull(this.intent.getStringArrayExtra(argName)) { "Param '$argName' not found" }

fun Activity.readStringArrayArg(argName: String, defaultValue: Array<String>): Array<String> =
        this.intent.getStringArrayExtra(argName) ?: defaultValue

fun Activity.readOptionalStringArrayArg(argName: String): Array<String>? =
        this.intent.getStringArrayExtra(argName)


fun Activity.readStringArrayListArg(argName: String): ArrayList<String> =
        requireNotNull(this.intent.getStringArrayListExtra(argName)) { "Param '$argName' not found" }

fun Activity.readStringArrayListArg(argName: String, defaultValue: ArrayList<String>): ArrayList<String> =
        this.intent.getStringArrayListExtra(argName) ?: defaultValue

fun Activity.readOptionalStringArrayListArg(argName: String): ArrayList<String>? =
        this.intent.getStringArrayListExtra(argName)


/* ************************************* Parcelable ***************************************** */


fun <V : Parcelable> Activity.readParcelableArg(argName: String): V =
        requireNotNull(this.intent.getParcelableExtra(argName)) { "Param '$argName' not found" }


fun <V : Parcelable> Activity.readParcelableArg(argName: String, defaultValue: V): V =
        this.intent.getParcelableExtra(argName) ?: defaultValue

fun <V : Parcelable> Activity.readOptionalParcelableArg(argName: String): V? =
        this.intent.getParcelableExtra(argName)


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String): Array<V> =
        requireNotNull(this.intent.getParcelableArrayExtra(argName) as Array<V>?) { "Param '$argName' not found" }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.readParcelableArrayArg(argName: String, defaultValue: Array<V>): Array<V> =
        this.intent.getParcelableArrayExtra(argName) as Array<V>? ?: defaultValue

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.readOptionalParcelableArrayArg(argName: String): Array<V>? =
        this.intent.getParcelableArrayExtra(argName) as Array<V>?


fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String): ArrayList<V> =
        requireNotNull(this.intent.getParcelableArrayListExtra(argName)) { "Param '$argName' not found" }

fun <V : Parcelable> Activity.readParcelableArrayListArg(argName: String, defaultValue: ArrayList<V>): ArrayList<V> =
        this.intent.getParcelableArrayListExtra(argName) ?: defaultValue

fun <V : Parcelable> Activity.readOptionalParcelableArrayListArg(argName: String): ArrayList<V>? =
        this.intent.getParcelableArrayListExtra(argName)


/* ************************************* Serializable ***************************************** */


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.readSerializableArg(argName: String): V =
        requireNotNull(this.intent.getSerializableExtra(argName) as V?) { "Param '$argName' not found" }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.readSerializableArg(argName: String, defaultValue: V): V =
        this.intent.getSerializableExtra(argName) as V? ?: defaultValue

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.readOptionalSerializableArg(argName: String): V? =
        this.intent.getSerializableExtra(argName) as V?


/* ************************************* Bundle ***************************************** */


fun Activity.readBundleArg(argName: String): Bundle =
        requireNotNull(this.intent.getBundleExtra(argName)) { "Param '$argName' not found" }

fun Activity.readBundleArg(argName: String, defaultValue: Bundle): Bundle =
        this.intent.getBundleExtra(argName) ?: defaultValue

fun Activity.readOptionalBundleArg(argName: String): Bundle? =
        this.intent.getBundleExtra(argName)


fun Activity.readExtrasArg(): Bundle =
        requireNotNull(this.intent.extras) { "Not found 'extras'" }

fun Activity.readExtrasArg(defaultValue: Bundle): Bundle =
        this.intent.extras ?: defaultValue

fun Activity.readOptionalExtrasArg(): Bundle? =
        this.intent.extras