package me.panpf.androidxkt.args

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment as SupportFragment


/* ************************************* Byte ***************************************** */


fun Activity.readByteUriArg(paramName: String): Byte {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toByte() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readByteUriArg(paramName: String, defaultValue: Byte): Byte {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toByte() else return defaultValue
}

fun Activity.readOptionalByteUriArg(paramName: String): Byte? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toByte() else return null
}


/* ************************************* Short ***************************************** */


fun Activity.readShortUriArg(paramName: String): Short {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toShort() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readShortUriArg(paramName: String, defaultValue: Short): Short {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toShort() else return defaultValue
}

fun Activity.readOptionalShortUriArg(paramName: String): Short? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toShort() else return null
}


/* ************************************* Int ***************************************** */


fun Activity.readIntUriArg(paramName: String): Int {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toInt() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readIntUriArg(paramName: String, defaultValue: Int): Int {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toInt() else return defaultValue
}

fun Activity.readOptionalIntUriArg(paramName: String): Int? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toInt() else return null
}


/* ************************************* Long ***************************************** */


fun Activity.readLongUriArg(paramName: String): Long {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toLong() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readLongUriArg(paramName: String, defaultValue: Long): Long {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toLong() else return defaultValue
}

fun Activity.readOptionalLongUriArg(paramName: String): Long? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toLong() else return null
}


/* ************************************* Float ***************************************** */


fun Activity.readFloatUriArg(paramName: String): Float {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toFloat() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readFloatUriArg(paramName: String, defaultValue: Float): Float {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toFloat() else return defaultValue
}

fun Activity.readOptionalFloatUriArg(paramName: String): Float? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toFloat() else return null
}


/* ************************************* Double ***************************************** */


fun Activity.readDoubleUriArg(paramName: String): Double {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toDouble() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readDoubleUriArg(paramName: String, defaultValue: Double): Double {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toDouble() else return defaultValue
}

fun Activity.readOptionalDoubleUriArg(paramName: String): Double? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toDouble() else return null
}


/* ************************************* Boolean ***************************************** */


fun Activity.readBooleanUriArg(paramName: String): Boolean {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toBoolean() else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readBooleanUriArg(paramName: String, defaultValue: Boolean): Boolean {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toBoolean() else return defaultValue
}

fun Activity.readOptionalBooleanUriArg(paramName: String): Boolean? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value.toBoolean() else return null
}


/* ************************************* String ***************************************** */


fun Activity.readStringUriArg(paramName: String): String {
    require(intent.action == Intent.ACTION_VIEW) { "Intent action not VIEW" }
    val uri = requireNotNull(intent.data) { "Intent data is null" }
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value else throw IllegalArgumentException("Param $paramName not found: $uri")
}

fun Activity.readStringUriArg(paramName: String, defaultValue: String): String {
    if (intent.action != Intent.ACTION_VIEW) return defaultValue
    val uri = intent.data ?: return defaultValue
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value else return defaultValue
}

fun Activity.readOptionalStringUriArg(paramName: String): String? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.data ?: return null
    val value = uri.getQueryParameter(paramName)
    return if (value != null && value.trim() != "") value else return null
}