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

package me.panpf.androidx.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Jsonx {

    private static final String INDENTATION = "    ";

    private Jsonx() {
    }

    public static boolean isEmpty(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "{}".equalsIgnoreCase(json) || "[]".equals(json);
    }

    public static boolean isNotEmpty(@Nullable String json) {
        return !isEmpty(json);
    }


    @NonNull
    public static JSONArray toJsonArray(@Nullable List<String> stringList) {
        JSONArray jsonArray = new JSONArray();
        if (stringList != null) {
            for (String item : stringList) {
                jsonArray.put(item);
            }
        }
        return jsonArray;
    }

    @NonNull
    public static JSONArray toJsonArray(@Nullable int[] ints) {
        JSONArray jsonArray = new JSONArray();
        if (ints != null) {
            for (int item : ints) {
                jsonArray.put(item);
            }
        }
        return jsonArray;
    }


    @NonNull
    public static String toJson(@Nullable List<String> stringList) {
        return toJsonArray(stringList).toString();
    }

    @NonNull
    public static String toJson(@Nullable int[] ints) {
        return toJsonArray(ints).toString();
    }


    @NonNull
    public static List<String> toStringList(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() <= 0) {
            return new ArrayList<>(0);
        }

        ArrayList<String> dataList = new ArrayList<>(jsonArray.length());
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataList.add(jsonArray.getString(i));
        }

        return dataList;
    }

    @NonNull
    public static List<String> toStringList(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringList(new JSONArray(json)) : new ArrayList<String>(0);
    }


    @NonNull
    public static String[] toStringArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return new String[0];
        }

        String[] dataStrings = new String[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataStrings[i] = jsonArray.getString(i);
        }

        return dataStrings;
    }

    @NonNull
    public static String[] toStringArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringArray(new JSONArray(json)) : new String[0];
    }


    @NonNull
    public static int[] toIntArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return new int[0];
        }

        int[] dataInts = new int[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getInt(i);
        }

        return dataInts;
    }

    @NonNull
    public static int[] toIntArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toIntArray(new JSONArray(json)) : new int[0];
    }


    @NonNull
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable JSONArray jsonArray, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return new ArrayList<>(0);
        }

        ArrayList<Bean> commentList = new ArrayList<>(jsonArray.length());
        JSONObject jsonObject;
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject != null) {
                Bean bean = parser.parse(jsonObject);
                if (bean != null) {
                    commentList.add(bean);
                }
            }
        }
        return commentList;
    }

    @NonNull
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable String jsonArrayString, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (isEmpty(jsonArrayString)) return new ArrayList<>(0);
        return toBeanList(new JSONArray(jsonArrayString), parser);
    }


    @Nullable
    public static <Bean> Bean toBean(@Nullable JSONObject jsonObject, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (jsonObject == null) return null;
        return parser.parse(jsonObject);
    }

    @Nullable
    public static <Bean> Bean toBean(@Nullable String jsonString, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (isEmpty(jsonString)) return null;
        return parser.parse(new JSONObject(jsonString));
    }


    @NonNull
    public static String optString(@NonNull JSONObject jsonObject, @NonNull String[] keys, @NonNull String defaultValue) {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                //noinspection ConstantConditions
                return value.toString();
            }
        }

        return defaultValue;
    }

    @NonNull
    public static String optString(@NonNull JSONObject jsonObject, @NonNull String[] keys) {
        return optString(jsonObject, keys, "");
    }

    public static int optInt(@NonNull JSONObject jsonObject, @NonNull String[] keys, int defaultValue) {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                //noinspection ConstantConditions
                return toInteger(value);
            }
        }

        return defaultValue;
    }

    public static int optInt(@NonNull JSONObject jsonObject, @NonNull String[] keys) {
        return optInt(jsonObject, keys, 0);
    }

    public static long optLong(@NonNull JSONObject jsonObject, @NonNull String[] keys, long defaultValue) {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                //noinspection ConstantConditions
                return toLong(value);
            }
        }

        return defaultValue;
    }

    public static long optLong(@NonNull JSONObject jsonObject, @NonNull String[] keys) {
        return optLong(jsonObject, keys, 0L);
    }

    @Nullable
    public static Integer toInteger(@Nullable Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return (int) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    @Nullable
    public static Long toLong(@Nullable Object value) {
        if (value instanceof Long) {
            return (Long) value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return (long) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    @NonNull
    public static String format(@Nullable JSONObject jsonObject) {
        if (jsonObject == null) return "{}";
        return appendJsonObject(new StringBuilder(), jsonObject, 0).toString();
    }

    @NonNull
    public static String format(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() <= 0) return "[]";
        return appendJsonArray(new StringBuilder(), jsonArray, 0).toString();
    }

    @NonNull
    public static String format(@Nullable String json) {
        if (isEmpty(json)) {
            return "{}";
        }

        try {
            return format(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return format(new JSONArray(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Invalid json: " + json);
    }

    private static StringBuilder appendJsonObject(StringBuilder builder, JSONObject jsonObject, int indentationCount) {
        builder.append("{");

        int newIndentationCount = indentationCount + 1;
        boolean hasData = false;

        Iterator keyIterator = jsonObject.keys();
        while (keyIterator.hasNext()) {
            hasData = true;
            String key = (String) keyIterator.next();
            Object value = jsonObject.opt(key);

            builder.append("\n");
            appendIndentation(builder, newIndentationCount);

            builder.append("\"").append(key).append("\"").append(":");

            if (value instanceof JSONArray) {
                appendJsonArray(builder, (JSONArray) value, newIndentationCount);
            } else if (value instanceof JSONObject) {
                appendJsonObject(builder, (JSONObject) value, newIndentationCount);
            } else if (value instanceof String) {
                builder.append("\"").append(value.toString()).append("\"");
            } else if (value != null) {
                builder.append(value.toString());
            }

            if (keyIterator.hasNext()) {
                builder.append(",");
            }
        }

        if (hasData) {
            builder.append("\n");
        }
        appendIndentation(builder, indentationCount);
        builder.append("}");

        return builder;
    }

    private static StringBuilder appendJsonArray(StringBuilder builder, JSONArray jsonArray, int indentationCount) {
        builder.append("[");

        int newIndentationCount = indentationCount + 1;
        boolean hasData = false;

        for (int w = 0, size = jsonArray.length(); w < size; w++) {
            hasData = true;
            Object item = jsonArray.opt(w);

            builder.append("\n");
            appendIndentation(builder, newIndentationCount);

            if (item instanceof JSONArray) {
                appendJsonArray(builder, (JSONArray) item, newIndentationCount);
            } else if (item instanceof JSONObject) {
                appendJsonObject(builder, (JSONObject) item, newIndentationCount);
            } else if (item instanceof String) {
                builder.append("\"").append(item.toString()).append("\"");
            } else if (item != null) {
                builder.append(item.toString());
            }

            if (w < size - 1) {
                builder.append(",");
            }
        }

        if (hasData) {
            builder.append("\n");
        }
        appendIndentation(builder, indentationCount);
        builder.append("]");

        return builder;
    }

    private static void appendIndentation(StringBuilder builder, int indentationCount) {
        for (int w = 0; w < indentationCount; w++) {
            builder.append(INDENTATION);
        }
    }

    public interface BeanParser<T> {
        @Nullable
        T parse(@NonNull JSONObject itemJsonObject) throws JSONException;
    }
}
