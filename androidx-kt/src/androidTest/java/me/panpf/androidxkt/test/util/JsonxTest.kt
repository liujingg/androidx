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

package me.panpf.androidxkt.test.util

import android.support.test.runner.AndroidJUnit4
import me.panpf.androidx.util.Jsonx
import me.panpf.androidxkt.util.*
import me.panpf.javaxkt.util.base64DecodeToString
import me.panpf.javaxkt.util.requireNotNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class JsonxTest {

    @Test
    fun testIsEmpty() {
        assertTrue("".isEmptyJson())
        assertTrue("{}".isEmptyJson())
        assertTrue("[]".isEmptyJson())
        assertTrue("null".isEmptyJson())
        assertTrue(null.isEmptyJson())
        assertFalse("{\"key\":\"value\"}".isEmptyJson())

        assertFalse("".isNotEmptyJson())
        assertFalse("{}".isNotEmptyJson())
        assertFalse("[]".isNotEmptyJson())
        assertFalse("null".isNotEmptyJson())
        assertFalse(null.isNotEmptyJson())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJson())
    }

    @Test
    fun testToJsonArray() {
        assertEquals("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]", listOf("0", "1", "2", "3", "4", "5").toJsonArray().toString())
        assertEquals("[]", (null as List<String>?).toJsonArray().toString())
        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJsonArray().toString())
        assertEquals("[]", (null as IntArray?).toJsonArray().toString())
    }

    @Test
    fun testToJson() {
        assertEquals("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]", listOf("0", "1", "2", "3", "4", "5").toJson())
        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJson())
    }

    @Test
    @Throws(JSONException::class)
    fun testToListOrArray() {
        assertEquals(listOf("0", "1", "2", "3", "4", "5"), listOf("0", "1", "2", "3", "4", "5").toJsonArray().toStringList())
        assertEquals(listOf("0", "1", "2", "3", "4", "5"), "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]".jsonToStringList())
        assertEquals(ArrayList<Any>(0), (null as JSONArray?).toStringList())
        assertEquals(ArrayList<Any>(0), JSONArray().toStringList())
        assertEquals(ArrayList<Any>(0), (null as String?).jsonToStringList())

        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), listOf("0", "1", "2", "3", "4", "5").toJsonArray().toStringArray())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]".jsonToStringArray())
        assertArrayEquals(arrayOfNulls<String>(0), (null as JSONArray?).toStringArray())
        assertArrayEquals(arrayOfNulls<String>(0), JSONArray().toStringArray())
        assertArrayEquals(arrayOfNulls<String>(0), (null as String?).jsonToStringArray())

        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, 5), listOf("0", "1", "2", "3", "4", "5").toJsonArray().toIntArray())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, 5), "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]".jsonToIntArray())
        assertArrayEquals(IntArray(0), (null as JSONArray?).toIntArray())
        assertArrayEquals(IntArray(0), JSONArray().toIntArray())
        assertArrayEquals(IntArray(0), (null as String?).jsonToIntArray())
    }

    @Test
    @Throws(JSONException::class)
    fun testToBean() {
        val parser = Jsonx.BeanParser { itemJsonObject -> Bean(itemJsonObject.getInt("age"), itemJsonObject.getString("name")) }
        val parser2: (JSONObject) -> Bean = { itemJsonObject -> Bean(itemJsonObject.getInt("age"), itemJsonObject.getString("name")) }

        val source = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), JSONArray(source).toBeanList(parser))
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), JSONArray(source).toBeanList(parser2))
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), source.jsonToBeanList(parser))
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), source.jsonToBeanList(parser2))

        val source2 = "{\"age\":20,\"name\":\"David\"}"
        assertEquals(Bean(20, "David"), JSONObject(source2).toBean(parser))
        assertEquals(Bean(20, "David"), JSONObject(source2).toBean(parser2))
        assertEquals(Bean(20, "David"), source2.jsonToBean(parser))
        assertEquals(Bean(20, "David"), source2.jsonToBean(parser2))

        assertNull((null as JSONObject?).toBean(parser))
        assertNull((null as String?).jsonToBean(parser))
        assertEquals(ArrayList<Any>(0), (null as JSONArray?).toBeanList(parser))
        assertEquals(ArrayList<Any>(0), JSONArray().toBeanList(parser))
        assertEquals(ArrayList<Any>(0), (null as String?).jsonToBeanList(parser))
        assertEquals(ArrayList<Any>(0), "[\"0\",\"1\"]".jsonToBeanList(parser))
        assertEquals(ArrayList<Any>(0), source.jsonToBeanList { null })
    }

    @Test
    @Throws(JSONException::class)
    fun testOpt() {
        val jsonObject = JSONObject()
        jsonObject.put("age1", 11)
        jsonObject.put("name1", "name1")
        jsonObject.put("long1", 21L)
        jsonObject.put("boolean1", true)
        jsonObject.put("double1", 21.0)

        val childJsonObject1 = JSONObject()
        childJsonObject1.put("child", "childValue")
        jsonObject.put("jsonObject1", childJsonObject1)

        val childJsonArray = JSONArray()
        childJsonArray.put("childArrayValue")
        jsonObject.put("jsonArray1", childJsonArray)

        assertEquals(11, jsonObject.optInt(arrayOf("age", "年龄", "age1")).toLong())
        assertEquals(0, jsonObject.optInt(arrayOf("age", "年龄", "age4")).toLong())
        assertEquals(9, jsonObject.optInt(arrayOf("age", "年龄", "age4"), 9).toLong())
        assertEquals(9, null.optInt(arrayOf("age", "年龄", "age4"), 9).toLong())

        assertEquals("name1", jsonObject.optString(arrayOf("age", "年龄", "name1")))
        assertEquals("", jsonObject.optString(arrayOf("age", "年龄", "name4")))
        assertEquals("unknown", jsonObject.optString(arrayOf("age", "年龄", "name4"), "unknown"))
        assertEquals("unknown", null.optString(arrayOf("age", "年龄", "name4"), "unknown"))

        assertEquals(21L, jsonObject.optLong(arrayOf("long", "年龄", "long1")))
        assertEquals(0L, jsonObject.optLong(arrayOf("long", "年龄", "long4")))
        assertEquals(9L, jsonObject.optLong(arrayOf("long", "年龄", "long4"), 9L))
        assertEquals(9L, null.optLong(arrayOf("long", "年龄", "long4"), 9L))

        assertTrue(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean1")))
        assertFalse(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean4")))
        assertTrue(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean4"), true))
        assertTrue(null.optBoolean(arrayOf("boolean", "年龄", "boolean4"), true))

        assertEquals(21.0, jsonObject.optDouble(arrayOf("double", "年龄", "double1")), 0.0)
        assertEquals(0.0, jsonObject.optDouble(arrayOf("double", "年龄", "double4")), 0.0)
        assertEquals(9.0, jsonObject.optDouble(arrayOf("double", "年龄", "double4"), 9.0), 0.0)
        assertEquals(9.0, null.optDouble(arrayOf("double", "年龄", "double4"), 9.0), 0.0)

        assertEquals("{\"child\":\"childValue\"}", jsonObject.optJSONObject(arrayOf("jsonObject", "年龄", "jsonObject1")).requireNotNull().toString())
        assertNull(jsonObject.optJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5")))
        assertEquals("[\"childArrayValue\"]", jsonObject.optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray1")).requireNotNull().toString())
        assertNull(jsonObject.optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4")))
        assertNull(null.optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4")))
    }

    @Test
    fun testGet() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("age1", 11)
            jsonObject.put("name1", "name1")
            jsonObject.put("long1", 21L)
            jsonObject.put("boolean1", true)
            jsonObject.put("double1", 21.0)

            val childJsonObject1 = JSONObject()
            childJsonObject1.put("child", "childValue")
            jsonObject.put("jsonObject1", childJsonObject1)

            val childJsonArray = JSONArray()
            childJsonArray.put("childArrayValue")
            jsonObject.put("jsonArray1", childJsonArray)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        try {
            assertEquals(11, jsonObject.getInt(arrayOf("age", "年龄", "age1")).toLong())
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getInt(arrayOf("age", "年龄", "age4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getInt(arrayOf("age", "年龄", "age4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertEquals("name1", jsonObject.getString(arrayOf("age", "年龄", "name1")))
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getString(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getString(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertEquals(21L, jsonObject.getLong(arrayOf("long", "年龄", "long1")))
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getLong(arrayOf("long", "年龄", "long4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getLong(arrayOf("long", "年龄", "long4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertTrue(jsonObject.getBoolean(arrayOf("boolean", "年龄", "boolean1")))
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getBoolean(arrayOf("boolean", "年龄", "boolean4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getBoolean(arrayOf("boolean", "年龄", "boolean4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertEquals(21.0, jsonObject.getDouble(arrayOf("double", "年龄", "double1")), 0.0)
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getDouble(arrayOf("double", "年龄", "double4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getDouble(arrayOf("double", "年龄", "double4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertEquals("{\"child\":\"childValue\"}", jsonObject.getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject1")).toString())
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            assertEquals("[\"childArrayValue\"]", jsonObject.getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray1")).toString())
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            jsonObject.getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            null.getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4"))
            fail()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    @Test
    @Throws(JSONException::class)
    fun testFormat() {
        val sourceArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val sourceObject = "{\"age\":20,\"name\":\"David\"}"

        val sourceArrayFormatResultBase64 = "WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n"

        assertEquals(sourceArrayFormatResultBase64.base64DecodeToString(), sourceArray.formatJson())
        assertEquals(sourceArrayFormatResultBase64.base64DecodeToString(), JSONArray(sourceArray).formatJson())
        assertEquals("[]", (null as JSONArray?).formatJson())
        assertEquals("[]", JSONArray().formatJson())

        assertEquals("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n".base64DecodeToString(), sourceObject.formatJson())
        assertEquals("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n".base64DecodeToString(), JSONObject(sourceObject).formatJson())
        assertEquals("{}", (null as JSONObject?).formatJson())
        assertEquals("{}", (null as String?).formatJson())
        assertEquals("{}", "{}".formatJson())
        assertEquals("{}", "[]".formatJson())
        assertEquals("{}", "null".formatJson())
    }

    class Bean internal constructor(private val age: Int, var name: String) {

        override fun toString(): String {
            return "Bean{" +
                    "age=" + age +
                    ", name='" + name + '\''.toString() +
                    '}'.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val bean = other as Bean?
            return age == bean!!.age && name == bean.name
        }

        override fun hashCode(): Int {
            return Objects.hash(age, name)
        }
    }
}
