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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Jsonx {

    private static final String INDENTATION = "    ";

    private Jsonx() {
    }


    /* ************************************* check ***************************************** */


    /**
     * If the given json string is empty, for empty example: ' ', 'null', '{}', '[]' returns true
     */
    public static boolean isEmpty(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "{}".equalsIgnoreCase(json) || "[]".equals(json);
    }

    /**
     * If the given json string is not empty, for empty example: ' ', 'null', '{}', '[]' returns true
     */
    public static boolean isNotEmpty(@Nullable String json) {
        return !isEmpty(json);
    }


    /* ************************************* toJson ***************************************** */


    /**
     * Convert a list of strings to a JSONArray
     */
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

    /**
     * Convert a array of int to a JSONArray
     */
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


    /**
     * Convert a list of strings to a json
     */
    @NonNull
    public static String toJson(@Nullable List<String> stringList) {
        return toJsonArray(stringList).toString();
    }

    /**
     * Convert a list of strings to a json
     */
    @NonNull
    public static String toJson(@Nullable int[] ints) {
        return toJsonArray(ints).toString();
    }


    /* ************************************* parse ***************************************** */


    /**
     * Convert a JSONArray to a String list
     */
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

    /**
     * Convert a json to a String list
     */
    @NonNull
    public static List<String> toStringList(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringList(new JSONArray(json)) : new ArrayList<String>(0);
    }


    /**
     * Convert a JSONArray to a String array
     */
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

    /**
     * Convert a json to a String array
     */
    @NonNull
    public static String[] toStringArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringArray(new JSONArray(json)) : new String[0];
    }


    /**
     * Convert a JSONArray to a int array
     */
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

    /**
     * Convert a json to a int array
     */
    @NonNull
    public static int[] toIntArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toIntArray(new JSONArray(json)) : new int[0];
    }


    /**
     * Convert JSONArray to the Bean list
     */
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

    /**
     * Convert json to the Bean list
     */
    @NonNull
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable String jsonArrayString, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (isEmpty(jsonArrayString)) return new ArrayList<>(0);
        return toBeanList(new JSONArray(jsonArrayString), parser);
    }


    /**
     * Convert JSONArray to the Bean
     */
    @Nullable
    public static <Bean> Bean toBean(@Nullable JSONObject jsonObject, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (jsonObject == null) return null;
        return parser.parse(jsonObject);
    }

    /**
     * Convert json to the Bean
     */
    @Nullable
    public static <Bean> Bean toBean(@Nullable String jsonString, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (isEmpty(jsonString)) return null;
        return parser.parse(new JSONObject(jsonString));
    }


    /* ************************************* opt and get ***************************************** */


    /**
     * Returns the value mapped by keys, or defaultValue if no such mapping exists.
     */
    @NonNull
    public static String optString(@Nullable JSONObject jsonObject, @NonNull String[] keys, @NonNull String defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    //noinspection ConstantConditions
                    return value.toString();
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys, or empty string if no such mapping exists.
     */
    @NonNull
    public static String optString(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        return optString(jsonObject, keys, "");
    }

    /**
     * Returns the value mapped by keys, or defaultValue if no such mapping exists.
     */
    public static int optInt(@Nullable JSONObject jsonObject, @NonNull String[] keys, int defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Integer integerValue = toInteger(value);
                    if (integerValue != null) return integerValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys, or 0 if no such mapping exists.
     */
    public static int optInt(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        return optInt(jsonObject, keys, 0);
    }

    /**
     * Returns the value mapped by keys, or defaultValue if no such mapping exists.
     */
    public static long optLong(@Nullable JSONObject jsonObject, @NonNull String[] keys, long defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Long longValue = toLong(value);
                    if (longValue != null) return longValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys, or 0L if no such mapping exists.
     */
    public static long optLong(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        return optLong(jsonObject, keys, 0L);
    }

    /**
     * Returns the value mapped by keys, or defaultValue if no such mapping exists.
     */
    public static boolean optBoolean(@Nullable JSONObject jsonObject, @NonNull String[] keys, boolean defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Boolean booleanValue = toBoolean(value);
                    if (booleanValue != null) return booleanValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys, or false if no such mapping exists.
     */
    public static boolean optBoolean(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        return optBoolean(jsonObject, keys, false);
    }

    /**
     * Returns the value mapped by keys, or defaultValue if no such mapping exists.
     */
    public static double optDouble(@Nullable JSONObject jsonObject, @NonNull String[] keys, double defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Double doubleValue = toDouble(value);
                    if (doubleValue != null) return doubleValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys, or 0.0 if no such mapping exists.
     */
    public static double optDouble(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        return optDouble(jsonObject, keys, 0.0);
    }

    /**
     * Returns the value mapped by keys, or null if no such mapping exists.
     */
    @Nullable
    public static JSONObject optJSONObject(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        if (jsonObject != null) {
            JSONObject value;
            for (String key : keys) {
                value = jsonObject.optJSONObject(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        return null;
    }

    /**
     * Returns the value mapped by keys, or null if no such mapping exists.
     */
    @Nullable
    public static JSONArray optJSONArray(@Nullable JSONObject jsonObject, @NonNull String[] keys) {
        if (jsonObject != null) {
            JSONArray value;
            for (String key : keys) {
                value = jsonObject.optJSONArray(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        return null;
    }


    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NonNull
    public static String getString(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static int getInt(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getInt(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static long getLong(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getLong(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static boolean getBoolean(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getBoolean(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static double getDouble(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getDouble(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NonNull
    public static JSONArray getJSONArray(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getJSONArray(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NonNull
    public static JSONObject getJSONObject(@Nullable JSONObject jsonObject, @NonNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            for (String key : keys) {
                try {
                    return jsonObject.getJSONObject(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Object to Integer
     */
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

    /**
     * Object to Long
     */
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

    /**
     * Object to Boolean
     */
    @Nullable
    public static Boolean toBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            String stringValue = (String) value;
            if ("true".equalsIgnoreCase(stringValue)) {
                return true;
            } else if ("false".equalsIgnoreCase(stringValue)) {
                return false;
            }
        }
        return null;
    }

    /**
     * Object to Double
     */
    @Nullable
    public static Double toDouble(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.valueOf((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    /**
     * Object to String
     */
    @Nullable
    public static String toString(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }


    /* ************************************* format ***************************************** */


    /**
     * Formatted output for easy viewing
     */
    @NonNull
    public static String format(@Nullable JSONObject jsonObject) {
        if (jsonObject == null) return "{}";
        return appendJsonObject(new StringBuilder(), jsonObject, 0).toString();
    }

    /**
     * Formatted output for easy viewing
     */
    @NonNull
    public static String format(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() <= 0) return "[]";
        return appendJsonArray(new StringBuilder(), jsonArray, 0).toString();
    }

    /**
     * Formatted output for easy viewing
     */
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
