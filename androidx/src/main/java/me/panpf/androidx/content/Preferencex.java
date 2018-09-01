package me.panpf.androidx.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class Preferencex {

    public static SharedPreferences getPreference(@NotNull Context context, @Nullable String name) {
        if (name == null) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    public static void putInt(@NotNull Context context, @NotNull String key, int value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putInt(@NotNull Context context, @NotNull String key, int value) {
        putInt(context, key, value, null);
    }

    public static void putInts(@NotNull Context context, @NotNull Map<String, Integer> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            if (value != null) {
                editor.putInt(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putInts(@NotNull Context context, @NotNull Map<String, Integer> dataMap) {
        putInts(context, dataMap, null);
    }

    public static void putLong(@NotNull Context context, @NotNull String key, long value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putLong(@NotNull Context context, @NotNull String key, long value) {
        putLong(context, key, value, null);
    }

    public static void putLongs(@NotNull Context context, @NotNull Map<String, Long> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            Long value = dataMap.get(key);
            if (value != null) {
                editor.putLong(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putLongs(@NotNull Context context, @NotNull Map<String, Long> dataMap) {
        putLongs(context, dataMap, null);
    }

    public static void putBoolean(@NotNull Context context, @NotNull String key, boolean value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putBoolean(@NotNull Context context, @NotNull String key, boolean value) {
        putBoolean(context, key, value, null);
    }

    public static void putBooleans(@NotNull Context context, @NotNull Map<String, Boolean> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            Boolean value = dataMap.get(key);
            if (value != null) {
                editor.putBoolean(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putBooleans(@NotNull Context context, @NotNull Map<String, Boolean> dataMap) {
        putBooleans(context, dataMap, null);
    }

    public static void putFloat(@NotNull Context context, @NotNull String key, float value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void putFloat(@NotNull Context context, @NotNull String key, float value) {
        putFloat(context, key, value, null);
    }

    public static void putFloats(@NotNull Context context, @NotNull Map<String, Float> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            Float value = dataMap.get(key);
            if (value != null) {
                editor.putFloat(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putFloats(@NotNull Context context, @NotNull Map<String, Float> dataMap) {
        putFloats(context, dataMap, null);
    }

    public static void putString(@NotNull Context context, @NotNull String key, @Nullable String value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void putString(@NotNull Context context, @NotNull String key, @Nullable String value) {
        putString(context, key, value, null);
    }

    public static void putStrings(@NotNull Context context, @NotNull Map<String, String> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            String value = dataMap.get(key);
            if (value != null) {
                editor.putString(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putStrings(@NotNull Context context, @NotNull Map<String, String> dataMap) {
        putStrings(context, dataMap, null);
    }

    public static void putStringSet(@NotNull Context context, @NotNull String key, @Nullable Set<String> value, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static void putStringSet(@NotNull Context context, @NotNull String key, @Nullable Set<String> value) {
        putStringSet(context, key, value, null);
    }

    public static void putStringSets(@NotNull Context context, @NotNull Map<String, Set<String>> dataMap, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : dataMap.keySet()) {
            Set<String> value = dataMap.get(key);
            if (value != null) {
                editor.putStringSet(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    public static void putStringSets(@NotNull Context context, @NotNull Map<String, Set<String>> dataMap) {
        putStringSets(context, dataMap, null);
    }


    public static int getInt(@NotNull Context context, @NotNull String key, int defValue, @Nullable String name) {
        return getPreference(context, name).getInt(key, defValue);
    }

    public static int getInt(@NotNull Context context, @NotNull String key, int defValue) {
        return getInt(context, key, defValue, null);
    }

    public static long getLong(@NotNull Context context, @NotNull String key, long defValue, @Nullable String name) {
        return getPreference(context, name).getLong(key, defValue);
    }

    public static long getLong(@NotNull Context context, @NotNull String key, long defValue) {
        return getLong(context, key, defValue, null);
    }

    public static boolean getBoolean(@NotNull Context context, @NotNull String key, boolean defValue, @Nullable String name) {
        return getPreference(context, name).getBoolean(key, defValue);
    }

    public static boolean getBoolean(@NotNull Context context, @NotNull String key, boolean defValue) {
        return getBoolean(context, key, defValue, null);
    }

    public static float getFloat(@NotNull Context context, @NotNull String key, float defValue, @Nullable String name) {
        return getPreference(context, name).getFloat(key, defValue);
    }

    public static float getFloat(@NotNull Context context, @NotNull String key, float defValue) {
        return getFloat(context, key, defValue, null);
    }

    public static String getString(@NotNull Context context, @NotNull String key, @Nullable String defValue, @Nullable String name) {
        return getPreference(context, name).getString(key, defValue);
    }

    public static String getString(@NotNull Context context, @NotNull String key, @Nullable String defValue) {
        return getString(context, key, defValue, null);
    }

    public static Set<String> getStringSet(@NotNull Context context, @NotNull String key, @Nullable Set<String> defValue, @Nullable String name) {
        return getPreference(context, name).getStringSet(key, defValue);
    }

    public static Set<String> getStringSet(@NotNull Context context, @NotNull String key, @Nullable Set<String> defValue) {
        return getStringSet(context, key, defValue, null);
    }

    public static Map<String, ?> getAll(@NotNull Context context, @Nullable String name) {
        return getPreference(context, name).getAll();
    }

    public static Map<String, ?> getAll(@NotNull Context context) {
        return getAll(context, null);
    }


    public static void remove(@NotNull Context context, @NotNull String[] keys, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }

    public static void remove(@NotNull Context context, String[] keys) {
        remove(context, keys, null);
    }

    public static void clear(@NotNull Context context, @Nullable String name) {
        SharedPreferences.Editor editor = getPreference(context, name).edit();
        editor.clear();
        editor.apply();
    }

    public static void clear(@NotNull Context context) {
        clear(context, null);
    }
}
