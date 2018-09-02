package me.panpf.androidx.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Jsonx {

    public static boolean isEmpty(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "{}".equalsIgnoreCase(json) || "[]".equals(json);
    }

    public static boolean isNotEmpty(@Nullable String json) {
        return !isEmpty(json);
    }


    @NonNull
    public static JSONArray toJsonArray(@NonNull List<String> stringList) {
        JSONArray jsonArray = new JSONArray();
        //noinspection ConstantConditions
        if (stringList != null) {
            for (String item : stringList) {
                jsonArray.put(item);
            }
        }
        return jsonArray;
    }

    @NonNull
    public static String toJson(@NonNull List<String> stringList) {
        return toJsonArray(stringList).toString();
    }

    @NonNull
    public static JSONArray toJsonArray(@NonNull int[] ints) {
        JSONArray jsonArray = new JSONArray();
        //noinspection ConstantConditions
        if (ints != null) {
            for (int item : ints) {
                jsonArray.put(item);
            }
        }
        return jsonArray;
    }

    @NonNull
    public static String toJson(@NonNull int[] ints) {
        return toJsonArray(ints).toString();
    }


    @Nullable
    public static ArrayList<String> toStringList(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() <= 0) {
            return null;
        }

        ArrayList<String> dataList = new ArrayList<>(jsonArray.length());
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataList.add(jsonArray.getString(i));
        }

        return dataList;
    }

    @Nullable
    public static ArrayList<String> toStringList(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringList(new JSONArray(json)) : null;
    }

    @Nullable
    public static String[] toStringArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }

        String[] dataStrings = new String[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataStrings[i] = jsonArray.getString(i);
        }

        return dataStrings;
    }

    @Nullable
    public static String[] toStringArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toStringArray(new JSONArray(json)) : null;
    }

    @Nullable
    public static int[] toIntArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }

        int[] dataInts = new int[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getInt(i);
        }

        return dataInts;
    }

    @Nullable
    public static int[] toIntArray(@Nullable String json) throws JSONException {
        return !isEmpty(json) ? toIntArray(new JSONArray(json)) : null;
    }

    @Nullable
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable JSONArray jsonArray, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
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

    @Nullable
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable String jsonArrayString, @NonNull BeanParser<Bean> parser) throws JSONException {
        if (isEmpty(jsonArrayString)) return null;
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


    @Nullable
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

    @Nullable
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

    public interface BeanParser<T> {
        @Nullable
        T parse(@NonNull JSONObject itemJsonObject) throws JSONException;
    }
}
