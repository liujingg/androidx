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

package me.panpf.androidxkt.util

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/*
 * JSON related extension methods
 */


fun String?.isEmptyJson(): Boolean = this == null || this.trim().let { "" == it || "null".equals(it, ignoreCase = true) || "{}".equals(it, ignoreCase = true) || "[]" == it }

fun String?.isNotEmptyJson(): Boolean = !isEmptyJson()


fun List<String>.toJsonArray(): JSONArray = JSONArray().apply { for (item in this@toJsonArray) put(item) }

fun List<String>.toJson(): String = this.toJsonArray().toString()

fun IntArray.toJsonArray(): JSONArray = JSONArray().apply { for (item in this@toJsonArray) put(item) }

fun IntArray.toJson(): String = this.toJsonArray().toString()


fun JSONArray.toStringList(): List<String> = (0 until this.length()).map { this.getString(it) }

@Throws(JSONException::class)
fun String.jsonToStringList(): List<String>? = if (this.isNotEmptyJson()) JSONArray(this).toStringList() else null

fun JSONArray.toStringArray(): Array<String> = Array(this.length()) { "" }.apply {
    (0 until this@toStringArray.length()).forEach { index -> this[index] = this@toStringArray.getString(index) }
}

@Throws(JSONException::class)
fun String.jsonToStringArray(): Array<String>? = if (this.isNotEmptyJson()) JSONArray(this).toStringArray() else null

fun JSONArray.toIntArray(): IntArray = IntArray(this.length()).apply {
    (0 until this@toIntArray.length()).forEach { index -> this[index] = this@toIntArray.getInt(index) }
}

@Throws(JSONException::class)
fun String.jsonToIntArray(): IntArray? = if (this.isNotEmptyJson()) JSONArray(this).toIntArray() else null

@Throws(JSONException::class)
fun <Bean> JSONArray.toBeanList(beanParser: (JSONObject) -> Bean): ArrayList<Bean> = ArrayList<Bean>(this.length()).apply {
    (0 until this@toBeanList.length()).forEach { index ->
        beanParser(this@toBeanList.getJSONObject(index))?.let { this += it }
    }
}

@Throws(JSONException::class)
fun <Bean> String.jsonToBeanList(beanParser: (JSONObject) -> Bean): ArrayList<Bean>? = if (this.isNotEmptyJson()) JSONArray(this).toBeanList(beanParser) else null

@Throws(JSONException::class)
fun <Bean> JSONObject.toBean(beanParser: (JSONObject) -> Bean): Bean = beanParser(this)

@Throws(JSONException::class)
fun <Bean> String.jsonToBean(beanParser: (JSONObject) -> Bean): Bean? = if (this.isNotEmptyJson()) JSONObject(this).toBean(beanParser) else null


private fun Any?.toInteger(): Int? {
    when (this) {
        is Int -> return this
        is Number -> return this.toInt()
        is String -> try {
            return java.lang.Double.parseDouble((this as String?)!!).toInt()
        } catch (ignored: NumberFormatException) {
        }
    }
    return null
}

private fun Any?.toLong(): Long? {
    when (this) {
        is Long -> return this
        is Number -> return this.toLong()
        is String -> try {
            return java.lang.Double.parseDouble((this as String?)!!).toLong()
        } catch (ignored: NumberFormatException) {
        }
    }
    return null
}

fun JSONObject.optString(keys: Array<String>, defaultValue: String = ""): String? {
    var value: Any?
    for (key in keys) {
        value = this.opt(key)
        if (value !== null && value !== JSONObject.NULL) {
            return value.toString()
        }
    }

    return defaultValue
}

fun JSONObject.optInt(keys: Array<String>, defaultValue: Int = 0): Int {
    var value: Any?
    for (key in keys) {
        value = this.opt(key)
        if (value !== null && value !== JSONObject.NULL) {

            return value.toInteger()!!
        }
    }

    return defaultValue
}

fun JSONObject.optLong(keys: Array<String>, defaultValue: Long = 0L): Long {
    var value: Any?
    for (key in keys) {
        value = this.opt(key)
        if (value !== null && value !== JSONObject.NULL) {

            return value.toLong()!!
        }
    }

    return defaultValue
}