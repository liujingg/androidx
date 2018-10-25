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


inline fun String?.isEmptyJson(): Boolean = Jsonx.isEmpty(this)

inline fun String?.isNotEmptyJson(): Boolean = Jsonx.isNotEmpty(this)


inline fun List<String>?.toJsonArray(): JSONArray = Jsonx.toJsonArray(this)

inline fun IntArray?.toJsonArray(): JSONArray = Jsonx.toJsonArray(this)

inline fun List<String>?.toJson(): String = Jsonx.toJson(this)

inline fun IntArray?.toJson(): String = Jsonx.toJson(this)


inline fun JSONArray.toStringList(): List<String> = Jsonx.toStringList(this)

@Throws(JSONException::class)
inline fun String?.jsonToStringList(): List<String> = Jsonx.toStringList(this)

inline fun JSONArray.toStringArray(): Array<String> = Jsonx.toStringArray(this)

@Throws(JSONException::class)
inline fun String?.jsonToStringArray(): Array<String> = Jsonx.toStringArray(this)


inline fun JSONArray.toIntArray(): IntArray = Jsonx.toIntArray(this)

@Throws(JSONException::class)
inline fun String?.jsonToIntArray(): IntArray = Jsonx.toIntArray(this)


@Throws(JSONException::class)
inline fun <Bean> JSONArray.toBeanList(parser: Jsonx.BeanParser<Bean>): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

@Throws(JSONException::class)
inline fun <Bean> JSONArray.toBeanList(noinline parser: (JSONObject) -> Bean): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBeanList(parser: Jsonx.BeanParser<Bean>): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBeanList(noinline parser: (JSONObject) -> Bean): ArrayList<Bean> = Jsonx.toBeanList(this, parser)

@Throws(JSONException::class)
inline fun <Bean> JSONObject.toBean(parser: Jsonx.BeanParser<Bean>): Bean? = Jsonx.toBean(this, parser)

@Throws(JSONException::class)
inline fun <Bean> JSONObject.toBean(noinline parser: (JSONObject) -> Bean): Bean? = Jsonx.toBean(this, parser)

@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBean(parser: Jsonx.BeanParser<Bean>): Bean? = Jsonx.toBean(this, parser)

@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBean(noinline parser: (JSONObject) -> Bean): Bean? = Jsonx.toBean(this, parser)


inline fun JSONObject.optString(keys: Array<String>, defaultValue: String): String = Jsonx.optString(this, keys, defaultValue)

inline fun JSONObject.optString(keys: Array<String>): String = Jsonx.optString(this, keys)

inline fun JSONObject.optInt(keys: Array<String>, defaultValue: Int): Int = Jsonx.optInt(this, keys, defaultValue)

inline fun JSONObject.optInt(keys: Array<String>): Int = Jsonx.optInt(this, keys)

inline fun JSONObject.optLong(keys: Array<String>, defaultValue: Long): Long = Jsonx.optLong(this, keys, defaultValue)

inline fun JSONObject.optLong(keys: Array<String>): Long = Jsonx.optLong(this, keys)


inline fun JSONObject?.formatJson(): String = Jsonx.format(this)

inline fun JSONArray?.formatJson(): String = Jsonx.format(this)

inline fun String?.formatJson(): String = Jsonx.format(this)