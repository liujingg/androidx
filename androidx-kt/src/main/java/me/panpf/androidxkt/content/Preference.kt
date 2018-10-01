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

package me.panpf.androidxkt.content

import android.content.Context
import android.content.SharedPreferences
import me.panpf.androidx.content.Preferencex

inline fun Context.getPreference(name: String? = null): SharedPreferences = Preferencex.getPreference(this, name)


inline fun Context.putIntPreference(key: String, value: Int, name: String? = null) = Preferencex.putInt(this, key, value, name)

inline fun Context.putIntsPreference(dataMap: Map<String, Int>, name: String? = null) = Preferencex.putInts(this, dataMap, name)

inline fun Context.putLongPreference(key: String, value: Long, name: String? = null) = Preferencex.putLong(this, key, value, name)

inline fun Context.putLongsPreference(dataMap: Map<String, Long>, name: String? = null) = Preferencex.putLongs(this, dataMap, name)

inline fun Context.putBooleanPreference(key: String, value: Boolean, name: String? = null) = Preferencex.putBoolean(this, key, value, name)

inline fun Context.putBooleansPreference(dataMap: Map<String, Boolean>, name: String? = null) = Preferencex.putBooleans(this, dataMap, name)

inline fun Context.putFloatPreference(key: String, value: Float, name: String? = null) = Preferencex.putFloat(this, key, value, name)

inline fun Context.putFloatsPreference(dataMap: Map<String, Float>, name: String? = null) = Preferencex.putFloats(this, dataMap, name)

inline fun Context.putStringPreference(key: String, value: String?, name: String? = null) = Preferencex.putString(this, key, value, name)

inline fun Context.putStringsPreference(dataMap: Map<String, String>, name: String? = null) = Preferencex.putStrings(this, dataMap, name)

inline fun Context.putStringSetPreference(key: String, value: Set<String>?, name: String? = null) = Preferencex.putStringSet(this, key, value, name)

inline fun Context.putStringSetsPreference(dataMap: Map<String, Set<String>>, name: String? = null) = Preferencex.putStringSets(this, dataMap, name)


inline fun Context.getIntPreference(key: String, defValue: Int, name: String? = null): Int = Preferencex.getInt(this, key, defValue, name)

inline fun Context.getLongPreference(key: String, defValue: Long, name: String? = null): Long = Preferencex.getLong(this, key, defValue, name)

inline fun Context.getBooleanPreference(key: String, defValue: Boolean, name: String? = null): Boolean = Preferencex.getBoolean(this, key, defValue, name)

inline fun Context.getFloatPreference(key: String, defValue: Float, name: String? = null): Float = Preferencex.getFloat(this, key, defValue, name)

inline fun Context.getStringPreference(key: String, defValue: String?, name: String? = null): String? = Preferencex.getString(this, key, defValue, name)

inline fun Context.getStringSetPreference(key: String, defValue: Set<String>?, name: String? = null): Set<String>? = Preferencex.getStringSet(this, key, defValue, name)

inline fun Context.getAllPreference(name: String? = null): Map<String, *> = Preferencex.getAll(this, name)


inline fun Context.removePreference(keys: Array<String>, name: String? = null) = Preferencex.remove(this, keys, name)

inline fun Context.removePreference(key: String, name: String? = null) = Preferencex.remove(this, key, name)

inline fun Context.clearPreference(name: String? = null) = Preferencex.clear(this, name)
