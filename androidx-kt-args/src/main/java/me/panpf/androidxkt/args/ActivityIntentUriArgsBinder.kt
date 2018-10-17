package me.panpf.androidxkt.args

import android.app.Activity
import me.panpf.androidxkt.app.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun Activity.bindByteIntentUriArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteIntentUriArgOr(argName, defaultValue) }

fun Activity.bindShortIntentUriArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortIntentUriArgOr(argName, defaultValue) }

fun Activity.bindIntIntentUriArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntIntentUriArgOr(argName, defaultValue) }

fun Activity.bindLongIntentUriArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongIntentUriArgOr(argName, defaultValue) }

fun Activity.bindFloatIntentUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatIntentUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleIntentUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOr(argName, defaultValue) }

fun Activity.bindStringIntentUriArg(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArg(argName) }

fun Activity.bindStringIntentUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argName, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argName) }