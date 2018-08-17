@file:Suppress("unused")

package me.panpf.androidx.util

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/*
 * JSON 相关的扩展方法或属性
 */


fun String.isEmptyJson(): Boolean = this.trim().let { "" == it || "null".equals(it, ignoreCase = true) || "{}".equals(it, ignoreCase = true) || "[]" == it }

fun String.isNotEmptyJson(): Boolean = !isEmptyJson()


fun List<String>.toJsonArray(): JSONArray = JSONArray().apply { for (item in this@toJsonArray) put(item) }

fun List<String>.toJson(): String = this.toJsonArray().toString()


fun IntArray.toJsonArray(): JSONArray = JSONArray().apply { for (item in this@toJsonArray) put(item) }

fun IntArray.toJson(): String = this.toJsonArray().toString()


fun JSONArray.parseToStringList(): List<String> = (0 until this.length()).map { this.getString(it) }

fun JSONArray.parseToStringArray(): Array<String> = Array(this.length()) { "" }.apply {
    (0 until this@parseToStringArray.length()).forEach { index -> this[index] = this@parseToStringArray.getString(index) }
}

fun JSONArray.parseToIntArray(): IntArray = IntArray(this.length()).apply {
    (0 until this@parseToIntArray.length()).forEach { index -> this[index] = this@parseToIntArray.getInt(index) }
}

@Throws(JSONException::class)
fun <Bean> JSONArray.parseToBeanList(beanParser: (JSONObject) -> Bean): ArrayList<Bean> = ArrayList<Bean>(this.length()).apply {
    (0 until this@parseToBeanList.length()).forEach { index ->
        beanParser(this@parseToBeanList.getJSONObject(index))?.let { this += it }
    }
}

@Throws(JSONException::class)
fun <Bean> JSONObject.parseToBean(beanParser: (JSONObject) -> Bean): Bean = beanParser(this)


@Throws(JSONException::class)
fun String.parseJsonToStringList(): List<String>? = if (this.isNotEmptyJson()) JSONArray(this).parseToStringList() else null

@Throws(JSONException::class)
fun String.parseJsonToStringArray(): Array<String>? = if (this.isNotEmptyJson()) JSONArray(this).parseToStringArray() else null

@Throws(JSONException::class)
fun String.parseJsonToIntArray(): IntArray? = if (this.isNotEmptyJson()) JSONArray(this).parseToIntArray() else null

@Throws(JSONException::class)
fun <Bean> String.parseToBeanList(beanParser: (JSONObject) -> Bean): ArrayList<Bean>? = if (this.isNotEmptyJson()) JSONArray(this).parseToBeanList(beanParser) else null

@Throws(JSONException::class)
fun <Bean> String.parseToBean(beanParser: (JSONObject) -> Bean): Bean? = if (this.isNotEmptyJson()) JSONObject(this).parseToBean(beanParser) else null