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

package me.panpf.androidx.test.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.panpf.androidx.util.Jsonx;
import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Base64x;
import me.panpf.javax.util.Premisex;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class JsonxTest {

    @Test
    public void testIsEmpty() {
        assertTrue(Jsonx.isEmpty(""));
        assertTrue(Jsonx.isEmpty("{}"));
        assertTrue(Jsonx.isEmpty("[]"));
        assertTrue(Jsonx.isEmpty("null"));
        assertTrue(Jsonx.isEmpty(null));
        assertFalse(Jsonx.isEmpty("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmpty(""));
        assertFalse(Jsonx.isNotEmpty("{}"));
        assertFalse(Jsonx.isNotEmpty("[]"));
        assertFalse(Jsonx.isNotEmpty("null"));
        assertFalse(Jsonx.isNotEmpty(null));
        assertTrue(Jsonx.isNotEmpty("{\"key\":\"value\"}"));
    }

    @Test
    public void testToJsonArray() {
        assertEquals("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]", Jsonx.toJsonArray(Collectionx.listOf("0", "1", "2", "3", "4", "5")).toString());
        assertEquals("[]", Jsonx.toJsonArray((List<String>) null).toString());
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJsonArray(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5)).toString());
        assertEquals("[]", Jsonx.toJsonArray((int[]) null).toString());
    }

    @Test
    public void testToJson() {
        assertEquals("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]", Jsonx.toJson(Collectionx.listOf("0", "1", "2", "3", "4", "5")));
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJson(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5)));
    }

    @Test
    public void testToListOrArray() throws JSONException {
        assertEquals(Collectionx.listOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringList(Jsonx.toJsonArray(Collectionx.listOf("0", "1", "2", "3", "4", "5"))));
        assertEquals(Collectionx.listOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringList("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"));
        assertEquals(new ArrayList<>(0), Jsonx.toStringList((JSONArray) null));
        assertEquals(new ArrayList<>(0), Jsonx.toStringList(new JSONArray()));
        assertEquals(new ArrayList<>(0), Jsonx.toStringList((String) null));

        assertArrayEquals(Arrayx.arrayOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringArray(Jsonx.toJsonArray(Collectionx.listOf("0", "1", "2", "3", "4", "5"))));
        assertArrayEquals(Arrayx.arrayOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringArray("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"));
        assertArrayEquals(new String[0], Jsonx.toStringArray((JSONArray) null));
        assertArrayEquals(new String[0], Jsonx.toStringArray(new JSONArray()));
        assertArrayEquals(new String[0], Jsonx.toStringArray((String) null));

        assertArrayEquals(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toIntArray(Jsonx.toJsonArray(Collectionx.listOf("0", "1", "2", "3", "4", "5"))));
        assertArrayEquals(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toIntArray("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"));
        assertArrayEquals(new int[0], Jsonx.toIntArray((JSONArray) null));
        assertArrayEquals(new int[0], Jsonx.toIntArray(new JSONArray()));
        assertArrayEquals(new int[0], Jsonx.toIntArray((String) null));
    }

    @Test
    public void testToBean() throws JSONException {
        Jsonx.BeanParser<Bean> parser = new Jsonx.BeanParser<Bean>() {
            @NonNull
            @Override
            public Bean parse(@NonNull JSONObject itemJsonObject) throws JSONException {
                return new Bean(itemJsonObject.getInt("age"), itemJsonObject.getString("name"));
            }
        };

        String source = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]";
        assertEquals(Collectionx.listOf(new Bean(20, "David"), new Bean(21, "Kevin"), new Bean(22, "Ruth")), Jsonx.toBeanList(new JSONArray(source), parser));
        assertEquals(Collectionx.listOf(new Bean(20, "David"), new Bean(21, "Kevin"), new Bean(22, "Ruth")), Jsonx.toBeanList(source, parser));

        String source2 = "{\"age\":20,\"name\":\"David\"}";
        assertEquals(new Bean(20, "David"), Jsonx.toBean(new JSONObject(source2), parser));
        assertEquals(new Bean(20, "David"), Jsonx.toBean(source2, parser));

        assertNull(Jsonx.toBean((JSONObject) null, parser));
        assertNull(Jsonx.toBean((String) null, parser));
        assertEquals(new ArrayList<>(0), Jsonx.toBeanList((JSONArray) null, parser));
        assertEquals(new ArrayList<>(0), Jsonx.toBeanList(new JSONArray(), parser));
        assertEquals(new ArrayList<>(0), Jsonx.toBeanList((String) null, parser));
        assertEquals(new ArrayList<>(0), Jsonx.toBeanList("[\"0\",\"1\"]", parser));
        assertEquals(new ArrayList<>(0), Jsonx.toBeanList(source, new Jsonx.BeanParser<Bean>() {
            @Nullable
            @Override
            public Bean parse(@NonNull JSONObject itemJsonObject) {
                return null;
            }
        }));
    }

    @Test
    public void testOpt() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age1", 11);
        jsonObject.put("name1", "name1");
        jsonObject.put("long1", 21L);
        jsonObject.put("boolean1", true);
        jsonObject.put("double1", 21.0);

        JSONObject childJsonObject1 = new JSONObject();
        childJsonObject1.put("child", "childValue");
        jsonObject.put("jsonObject1", childJsonObject1);

        JSONArray childJsonArray = new JSONArray();
        childJsonArray.put("childArrayValue");
        jsonObject.put("jsonArray1", childJsonArray);

        assertEquals(11, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age1")));
        assertEquals(0, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4")));
        assertEquals(9, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4"), 9));
        assertEquals(9, Jsonx.optInt(null, Arrayx.arrayOf("age", "年龄", "age4"), 9));

        assertEquals("name1", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")));
        assertEquals("", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4")));
        assertEquals("unknown", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4"), "unknown"));
        assertEquals("unknown", Jsonx.optString(null, Arrayx.arrayOf("age", "年龄", "name4"), "unknown"));

        assertEquals(21L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long1")));
        assertEquals(0L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4")));
        assertEquals(9L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4"), 9L));
        assertEquals(9L, Jsonx.optLong(null, Arrayx.arrayOf("long", "年龄", "long4"), 9L));

        assertTrue(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean1")));
        assertFalse(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4")));
        assertTrue(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4"), true));
        assertTrue(Jsonx.optBoolean(null, Arrayx.arrayOf("boolean", "年龄", "boolean4"), true));

        assertEquals(21.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double1")), 0.0);
        assertEquals(0.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4")), 0.0);
        assertEquals(9.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4"), 9.0), 0.0);
        assertEquals(9.0, Jsonx.optDouble(null, Arrayx.arrayOf("double", "年龄", "double4"), 9.0), 0.0);

        assertEquals("{\"child\":\"childValue\"}", Premisex.requireNotNull(Jsonx.optJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject1"))).toString());
        assertNull(Jsonx.optJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5")));
        assertEquals("[\"childArrayValue\"]", Premisex.requireNotNull(Jsonx.optJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray1"))).toString());
        assertNull(Jsonx.optJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4")));
        assertNull(Jsonx.optJSONArray(null, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4")));
    }

    @Test
    public void testGet() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("age1", 11);
            jsonObject.put("name1", "name1");
            jsonObject.put("long1", 21L);
            jsonObject.put("boolean1", true);
            jsonObject.put("double1", 21.0);

            JSONObject childJsonObject1 = new JSONObject();
            childJsonObject1.put("child", "childValue");
            jsonObject.put("jsonObject1", childJsonObject1);

            JSONArray childJsonArray = new JSONArray();
            childJsonArray.put("childArrayValue");
            jsonObject.put("jsonArray1", childJsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            assertEquals(11, Jsonx.getInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age1")));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getInt(null, Arrayx.arrayOf("age", "年龄", "age4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("name1", Jsonx.getString(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getString(null, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(21L, Jsonx.getLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long1")));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getLong(null, Arrayx.arrayOf("long", "年龄", "long4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertTrue(Jsonx.getBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean1")));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getBoolean(null, Arrayx.arrayOf("boolean", "年龄", "boolean4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(21.0, Jsonx.getDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double1")), 0.0);
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getDouble(null, Arrayx.arrayOf("double", "年龄", "double4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("{\"child\":\"childValue\"}", Jsonx.getJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject1")).toString());
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getJSONObject(null, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("[\"childArrayValue\"]", Jsonx.getJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray1")).toString());
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.getJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Jsonx.getJSONArray(null, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4"));
            fail();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFormat() throws JSONException {
        String sourceArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]";
        String sourceObject = "{\"age\":20,\"name\":\"David\"}";

        String sourceArrayFormatResultBase64 = "WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n";

        assertEquals(Base64x.decodeToString(sourceArrayFormatResultBase64), Jsonx.format(sourceArray));
        assertEquals(Base64x.decodeToString(sourceArrayFormatResultBase64), Jsonx.format(new JSONArray(sourceArray)));
        assertEquals("[]", Jsonx.format((JSONArray) null));
        assertEquals("[]", Jsonx.format(new JSONArray()));

        assertEquals(Base64x.decodeToString("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n"), Jsonx.format(sourceObject));
        assertEquals(Base64x.decodeToString("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n"), Jsonx.format(new JSONObject(sourceObject)));
        assertEquals("{}", Jsonx.format((JSONObject) null));
        assertEquals("{}", Jsonx.format((String) null));
        assertEquals("{}", Jsonx.format("{}"));
        assertEquals("{}", Jsonx.format("[]"));
        assertEquals("{}", Jsonx.format("null"));
    }

    public static class Bean {
        public String name;
        private int age;

        Bean(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return "Bean{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bean bean = (Bean) o;
            return age == bean.age && Stringx.equals(name, bean.name);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{age, name});
        }
    }
}
