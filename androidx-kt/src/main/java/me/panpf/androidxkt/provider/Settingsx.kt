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

package me.panpf.androidxkt.provider

import android.Manifest
import android.app.Activity
import android.content.Context
import android.support.annotation.FloatRange
import android.support.annotation.IntRange
import android.support.annotation.RequiresPermission
import me.panpf.androidx.provider.Settingsx

/*
 * System setup tool method
 */


/**
 * Return true if screen brightness auto mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.isScreenBrightnessModeAutomatic(): Boolean = Settingsx.isScreenBrightnessModeAutomatic(this)

/**
 * Turn on or off the screen brightness auto mode
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenBrightnessModeAutomatic(automatic: Boolean): Boolean = Settingsx.setScreenBrightnessModeAutomatic(this, automatic)

/**
 * Get system brightness, the range is 0-255
 */
@IntRange(from = 0, to = 255)
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getScreenBrightness(): Int = Settingsx.getScreenBrightness(this)

/**
 * Set the system brightness (only change the brightness attribute of the system, the current activity brightness does not change)
 *
 * @param brightness Brightness, the range is 0-255
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenBrightness(@IntRange(from = 0, to = 255) brightness: Int): Boolean = Settingsx.setScreenBrightness(this, brightness)

/**
 * This can be used to override the user's preferred brightness of the screen.
 * A value of less than 0, the default, means to use the preferred screen brightness.
 * 0 to 1 adjusts the brightness from dark to full bright.
 */
@FloatRange(from = -1.0, to = 1.0)
inline fun Activity.getWindowBrightness(): Float = Settingsx.getWindowBrightness(this)

/**
 * Set the brightness of the Activity window (you can see the effect, the brightness of the system will not change)
 *
 * @param brightness This can be used to override the user's preferred brightness of the screen.
 * A value of less than 0, the default, means to use the preferred screen brightness.
 * 0 to 1 adjusts the brightness from dark to full bright.
 */
inline fun Activity.setWindowBrightness(@FloatRange(from = -1.0, to = 1.0) brightness: Float) = Settingsx.setWindowBrightness(this, brightness)

/**
 * Return true if the current window use the preferred screen brightness.
 */
inline fun Activity.isWindowBrightnessFlowSystem(): Boolean = Settingsx.isWindowBrightnessFlowSystem(this)

/**
 * Get screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getScreenOffTimeout(): Int = Settingsx.getScreenOffTimeout(this)

/**
 * Set screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenOffTimeout(millis: Int): Boolean = Settingsx.setScreenOffTimeout(this, millis)

/**
 * Return true if airplane mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.isAirplaneModeOn(): Boolean = Settingsx.isAirplaneModeOn(this)

/**
 * Turn airplane mode on or off
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setAirplaneModeOn(enable: Boolean): Boolean = Settingsx.setAirplaneModeOn(this, enable)

/**
 * Return true if Bluetooth is on or is being turned on
 */
@RequiresPermission(Manifest.permission.BLUETOOTH)
inline fun isBluetoothOn(): Boolean = Settingsx.isBluetoothOn()

/**
 * Turn Bluetooth on or off
 */
@RequiresPermission(allOf = [(Manifest.permission.BLUETOOTH), (Manifest.permission.BLUETOOTH_ADMIN)])
inline fun setBluetiithOn(enable: Boolean) = Settingsx.setBluetoothOn(enable)

/**
 * Get the media volume, the value range is 0-15
 */
@IntRange(from = 0, to = 15)
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getMediaVolume(): Int = Settingsx.getMediaVolume(this)

/**
 * Set the media volume, the value range is 0-15
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setMediaVolume(@IntRange(from = 0, to = 15) mediaVolume: Int): Boolean = Settingsx.setMediaVolume(this, mediaVolume)

/**
 * Get the ringer volume, the range is 0-7
 */
@IntRange(from = 0, to = 7)
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getRingVolume(): Int = Settingsx.getRingVolume(this)

/**
 * Set the ringer volume, the range is 0-7
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setRingVolume(@IntRange(from = 0, to = 7) ringVolume: Int): Boolean = Settingsx.setRingVolume(this, ringVolume)
