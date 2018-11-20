/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.util

import me.panpf.androidx.util.Jsonx
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/*
 * JSON related extension methods
 */


/* ************************************* check ***************************************** */


/**
 * If the given json string is empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isEmptyJson(): Boolean = Jsonx.isEmpty(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isNotEmptyJson(): Boolean = Jsonx.isNotEmpty(this)


/* ************************************* toJson ***************************************** */


/**
 * Convert a list of strings to a JSONArray
 */
inline fun List<String>?.toJsonArray(): JSONArray = Jsonx.toJsonArray(this)

/**
 * Convert a array of int to a JSONArray
 */
inline fun IntArray?.toJsonArray(): JSONArray = Jsonx.toJsonArray(this)

/**
 * Convert a list of strings to a json
 */
inline fun List<String>?.toJson(): String = Jsonx.toJson(this)

/**
 * Convert a list of strings to a json
 */
inline fun IntArray?.toJson(): String = Jsonx.toJson(this)


/* ************************************* parse ***************************************** */


/**
 * Convert a JSONArray to a String list
 */
inline fun JSONArray?.toStringList(): List<String> = Jsonx.toStringList(this)

/**
 * Convert a json to a String list
 */
@Throws(JSONException::class)
inline fun String?.jsonToStringList(): List<String> = Jsonx.toStringList(this)

/**
 * Convert a JSONArray to a String array
 */
inline fun JSONArray?.toStringArray(): Array<String> = Jsonx.toStringArray(this)

/**
 * Convert a json to a String array
 */
@Throws(JSONException::class)
inline fun String?.jsonToStringArray(): Array<String> = Jsonx.toStringArray(this)

/**
 * Convert a JSONArray to a int array
 */
inline fun JSONArray?.toIntArray(): IntArray = Jsonx.toIntArray(this)

/**
 * Convert a json to a int array
 */
@Throws(JSONException::class)
inline fun String?.jsonToIntArray(): IntArray = Jsonx.toIntArray(this)


/**
 * Convert JSONArray to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> JSONArray?.toBeanList(parser: Jsonx.BeanParser<Bean>): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

/**
 * Convert JSONArray to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> JSONArray?.toBeanList(noinline parser: (JSONObject) -> Bean): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

/**
 * Convert json to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBeanList(parser: Jsonx.BeanParser<Bean>): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

/**
 * Convert json to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBeanList(noinline parser: (JSONObject) -> Bean): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

/**
 * Convert JSONArray to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> JSONObject?.toBean(parser: Jsonx.BeanParser<Bean>): Bean? = Jsonx.toBean(this, parser)

/**
 * Convert JSONArray to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> JSONObject?.toBean(noinline parser: (JSONObject) -> Bean): Bean? = Jsonx.toBean(this, parser)

/**
 * Convert json to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBean(parser: Jsonx.BeanParser<Bean>): Bean? = Jsonx.toBean(this, parser)

/**
 * Convert json to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBean(noinline parser: (JSONObject) -> Bean): Bean? = Jsonx.toBean(this, parser)


/* ************************************* opt and get ***************************************** */


/**
 * Returns the value mapped by keys, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optString(keys: Array<String>, defaultValue: String): String = Jsonx.optString(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or empty string if no such mapping exists.
 */
inline fun JSONObject?.optString(keys: Array<String>): String = Jsonx.optString(this, keys)

/**
 * Returns the value mapped by keys, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optInt(keys: Array<String>, defaultValue: Int): Int = Jsonx.optInt(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or 0 if no such mapping exists.
 */
inline fun JSONObject?.optInt(keys: Array<String>): Int = Jsonx.optInt(this, keys)

/**
 * Returns the value mapped by keys, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optLong(keys: Array<String>, defaultValue: Long): Long = Jsonx.optLong(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or 0L if no such mapping exists.
 */
inline fun JSONObject?.optLong(keys: Array<String>): Long = Jsonx.optLong(this, keys)

/**
 * Returns the value mapped by keys, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optBoolean(keys: Array<String>, defaultValue: Boolean): Boolean = Jsonx.optBoolean(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or false if no such mapping exists.
 */
inline fun JSONObject?.optBoolean(keys: Array<String>): Boolean = Jsonx.optBoolean(this, keys)

/**
 * Returns the value mapped by keys, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optDouble(keys: Array<String>, defaultValue: Double): Double = Jsonx.optDouble(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or 0.0 if no such mapping exists.
 */
inline fun JSONObject?.optDouble(keys: Array<String>): Double = Jsonx.optDouble(this, keys)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONObject(keys: Array<String>): JSONObject? = Jsonx.optJSONObject(this, keys)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONArray(keys: Array<String>): JSONArray? = Jsonx.optJSONArray(this, keys)


/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getString(keys: Array<String>): String = Jsonx.getString(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getInt(keys: Array<String>): Int = Jsonx.getInt(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getLong(keys: Array<String>): Long = Jsonx.getLong(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getBoolean(keys: Array<String>): Boolean = Jsonx.getBoolean(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getDouble(keys: Array<String>): Double = Jsonx.getDouble(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getJSONArray(keys: Array<String>): JSONArray = Jsonx.getJSONArray(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getJSONObject(keys: Array<String>): JSONObject = Jsonx.getJSONObject(this, keys)


/* ************************************* format ***************************************** */


/**
 * Formatted output for easy viewing
 */
inline fun JSONObject?.formatJson(): String = Jsonx.format(this)

/**
 * Formatted output for easy viewing
 */
inline fun JSONArray?.formatJson(): String = Jsonx.format(this)

/**
 * Formatted output for easy viewing
 */
inline fun String?.formatJson(): String = Jsonx.format(this)