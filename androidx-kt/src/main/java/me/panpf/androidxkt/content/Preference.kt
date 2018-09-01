package me.panpf.androidxkt.content

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

fun Context.getPreference(name: String? = null): SharedPreferences {
    return if (name == null) {
        PreferenceManager.getDefaultSharedPreferences(this)
    } else {
        this.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}

fun Context.putInt(key: String, value: Int, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putInt(key, value)
    editor.apply()
}

fun Context.putInts(dataMap: Map<String, Int>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putInt(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}

fun Context.putLong(key: String, value: Long, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putLong(key, value)
    editor.apply()
}

fun Context.putLongs(dataMap: Map<String, Long>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putLong(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}

fun Context.putBoolean(key: String, value: Boolean, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putBoolean(key, value)
    editor.apply()
}

fun Context.putBooleans(dataMap: Map<String, Boolean>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putBoolean(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}

fun Context.putFloat(key: String, value: Float, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putFloat(key, value)
    editor.apply()
}

fun Context.putFloats(dataMap: Map<String, Float>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putFloat(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}

fun Context.putString(key: String, value: String?, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putString(key, value)
    editor.apply()
}

fun Context.putStrings(dataMap: Map<String, String>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putString(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}

fun Context.putStringSet(key: String, value: Set<String>?, name: String? = null) {
    val editor = getPreference(name).edit()
    editor.putStringSet(key, value)
    editor.apply()
}

fun Context.putStringSets(dataMap: Map<String, Set<String>>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in dataMap.keys) {
        val value = dataMap[key]
        if (value != null) {
            editor.putStringSet(key, value)
        } else {
            editor.remove(key)
        }
    }
    editor.apply()
}


fun Context.getInt(key: String, defValue: Int, name: String? = null): Int {
    return getPreference(name).getInt(key, defValue)
}

fun Context.getLong(key: String, defValue: Long, name: String? = null): Long {
    return getPreference(name).getLong(key, defValue)
}

fun Context.getBoolean(key: String, defValue: Boolean, name: String? = null): Boolean {
    return getPreference(name).getBoolean(key, defValue)
}

fun Context.getFloat(key: String, defValue: Float, name: String? = null): Float {
    return getPreference(name).getFloat(key, defValue)
}

fun Context.getString(key: String, defValue: String?, name: String? = null): String? {
    return getPreference(name).getString(key, defValue)
}

fun Context.getStringSet(key: String, defValue: Set<String>?, name: String? = null): Set<String>? {
    return getPreference(name).getStringSet(key, defValue)
}

fun Context.getAll(name: String? = null): Map<String, *> {
    return getPreference(name).all
}


fun Context.remove(keys: Array<String>, name: String? = null) {
    val editor = getPreference(name).edit()
    for (key in keys) {
        editor.remove(key)
    }
    editor.apply()
}

fun Context.clear(name: String? = null) {
    val editor = getPreference(name).edit()
    editor.clear()
    editor.apply()
}
