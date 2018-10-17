package me.panpf.androidxkt.args

import android.app.Activity
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun Activity.bindByteUriIntentArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriIntentArgOr(argName, defaultValue) }

fun Activity.bindShortUriIntentArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriIntentArgOr(argName, defaultValue) }

fun Activity.bindIntUriIntentArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriIntentArgOr(argName, defaultValue) }

fun Activity.bindLongUriIntentArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriIntentArgOr(argName, defaultValue) }

fun Activity.bindFloatUriIntentArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriIntentArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriIntentArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOr(argName, defaultValue) }

fun Activity.bindStringUriIntentArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArg(argName) }

fun Activity.bindStringUriIntentArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argName, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argName) }