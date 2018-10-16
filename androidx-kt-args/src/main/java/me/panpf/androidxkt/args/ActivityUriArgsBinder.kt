package me.panpf.androidxkt.args

import android.app.Activity
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Activity.bindByteUriArg(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArg(argName) }

fun Activity.bindByteUriArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOr(argName, defaultValue) }

fun Activity.bindByteUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOrNull(argName) }


/* ************************************* Short ***************************************** */


fun Activity.bindShortUriArg(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArg(argName) }

fun Activity.bindShortUriArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOr(argName, defaultValue) }

fun Activity.bindShortUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOrNull(argName) }


/* ************************************* Int ***************************************** */


fun Activity.bindIntUriArg(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArg(argName) }

fun Activity.bindIntUriArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOr(argName, defaultValue) }

fun Activity.bindIntUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOrNull(argName) }


/* ************************************* Long ***************************************** */


fun Activity.bindLongUriArg(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArg(argName) }

fun Activity.bindLongUriArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOr(argName, defaultValue) }

fun Activity.bindLongUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOrNull(argName) }


/* ************************************* Float ***************************************** */


fun Activity.bindFloatUriArg(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArg(argName) }

fun Activity.bindFloatUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOr(argName, defaultValue) }

fun Activity.bindFloatUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argName) }


/* ************************************* Double ***************************************** */


fun Activity.bindDoubleUriArg(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArg(argName) }

fun Activity.bindDoubleUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argName) }


/* ************************************* Boolean ***************************************** */


fun Activity.bindBooleanUriArg(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArg(argName) }

fun Activity.bindBooleanUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argName) }


/* ************************************* String ***************************************** */


fun Activity.bindStringUriArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArg(argName) }

fun Activity.bindStringUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOr(argName, defaultValue) }

fun Activity.bindStringUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOrNull(argName) }