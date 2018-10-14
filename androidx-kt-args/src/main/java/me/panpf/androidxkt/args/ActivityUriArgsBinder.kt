package me.panpf.androidxkt.args

import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/* ************************************* Byte ***************************************** */


fun Activity.bindByteUriArg(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readByteUriArg(argName)
        }

fun Activity.bindByteUriArg(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readByteUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalByteUriArg(argName: String): ReadOnlyProperty<Activity, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalByteUriArg(argName)
        }


/* ************************************* Short ***************************************** */


fun Activity.bindShortUriArg(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readShortUriArg(argName)
        }

fun Activity.bindShortUriArg(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readShortUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalShortUriArg(argName: String): ReadOnlyProperty<Activity, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalShortUriArg(argName)
        }


/* ************************************* Int ***************************************** */


fun Activity.bindIntUriArg(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readIntUriArg(argName)
        }

fun Activity.bindIntUriArg(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readIntUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalIntUriArg(argName: String): ReadOnlyProperty<Activity, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalIntUriArg(argName)
        }


/* ************************************* Long ***************************************** */


fun Activity.bindLongUriArg(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readLongUriArg(argName)
        }

fun Activity.bindLongUriArg(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readLongUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalLongUriArg(argName: String): ReadOnlyProperty<Activity, Long?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalLongUriArg(argName)
        }


/* ************************************* Float ***************************************** */


fun Activity.bindFloatUriArg(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readFloatUriArg(argName)
        }

fun Activity.bindFloatUriArg(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readFloatUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalFloatUriArg(argName: String): ReadOnlyProperty<Activity, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalFloatUriArg(argName)
        }


/* ************************************* Double ***************************************** */


fun Activity.bindDoubleUriArg(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readDoubleUriArg(argName)
        }

fun Activity.bindDoubleUriArg(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readDoubleUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalDoubleUriArg(argName: String): ReadOnlyProperty<Activity, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalDoubleUriArg(argName)
        }


/* ************************************* Boolean ***************************************** */


fun Activity.bindBooleanUriArg(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readBooleanUriArg(argName)
        }

fun Activity.bindBooleanUriArg(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readBooleanUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalBooleanUriArg(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalBooleanUriArg(argName)
        }


/* ************************************* String ***************************************** */


fun Activity.bindStringUriArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readStringUriArg(argName)
        }

fun Activity.bindStringUriArg(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readStringUriArg(argName, defaultValue)
        }

fun Activity.bindOptionalStringUriArg(argName: String): ReadOnlyProperty<Activity, String?> =
        ArgLazy(argName) { _, _: KProperty<*> ->
            this.readOptionalStringUriArg(argName)
        }