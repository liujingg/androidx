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

package me.panpf.androidxkt.provider

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresPermission

/*
 * System setup tool method
 */


/**
 * Return true if screen brightness auto mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.isScreenBrightnessModeAutomatic(): Boolean {
    try {
        return Settings.System.getInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
    } catch (e: Settings.SettingNotFoundException) {
        throw IllegalStateException(e)
    }

}

/**
 * Turn on or off the screen brightness auto mode
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setScreenBrightnessModeAutomatic(automatic: Boolean): Boolean {
    val newValue = if (automatic) Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC else Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
    return Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, newValue)
}

/**
 * Get system brightness, the range is 0-255
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.getScreenBrightness(): Int {
    try {
        return Settings.System.getInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
    } catch (e: Settings.SettingNotFoundException) {
        throw IllegalStateException(e)
    }

}

/**
 * Set the system brightness (only change the brightness attribute of the system, the current activity brightness does not change)
 *
 * @param brightness Brightness, the range is 0-255
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setScreenBrightness(brightness: Int): Boolean {
    return Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
}

/**
 * Set the brightness of the Activity window (you can see the effect, the brightness of the system will not change)
 *
 * @param brightness Brightness, the range is 0-255
 */
fun Activity.setWindowBrightness(brightness: Float) {
    val window = this.window
    val params = window.attributes
    params.screenBrightness = brightness
    window.attributes = params
}

/**
 * Get screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.getScreenOffTimeout(): Int {
    try {
        return Settings.System.getInt(this.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
    } catch (e: Settings.SettingNotFoundException) {
        throw IllegalStateException(e)
    }

}

/**
 * Set screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setScreenOffTimeout(millis: Int): Boolean {
    return Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, millis)
}

/**
 * Return true if airplane mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.isAirplaneModeOn(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Settings.Global.getInt(this.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1
    } else {
        Settings.System.getInt(this.contentResolver, Settings.System.AIRPLANE_MODE_ON, 0) == 1
    }
}

/**
 * Turn airplane mode on or off
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setAirplaneModeOn(enabled: Boolean): Boolean {
    val result: Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Settings.Global.putInt(this.contentResolver, Settings.Global.AIRPLANE_MODE_ON, if (enabled) 1 else 0)
    } else {
        Settings.System.putInt(this.contentResolver, Settings.System.AIRPLANE_MODE_ON, if (enabled) 1 else 0)
    }
    this.sendBroadcast(Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    return result
}

/**
 * Return true if Bluetooth is on or is being turned on
 */
@RequiresPermission(Manifest.permission.BLUETOOTH)
fun isBluetoothOn(): Boolean {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val state = bluetoothAdapter?.state ?: -1
    return state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON
}

/**
 * Turn Bluetooth on or off
 */
@RequiresPermission(allOf = [(Manifest.permission.BLUETOOTH), (Manifest.permission.BLUETOOTH_ADMIN)])
fun setBluetiithOn(enable: Boolean) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    if (bluetoothAdapter != null) {
        if (enable) {
            bluetoothAdapter.enable()
        } else {
            bluetoothAdapter.disable()
        }
    }
}

/**
 * Get the media volume, the value range is 0-15
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.getMediaVolume(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val audioManager = (this.getSystemService(Context.AUDIO_SERVICE)
                ?: throw IllegalStateException("AudioManager not found")) as AudioManager
        audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    } else {
        try {
            Settings.System.getInt(this.contentResolver, "volume_music")
        } catch (e: Settings.SettingNotFoundException) {
            throw IllegalStateException(e)
        }
    }
}

/**
 * Set the media volume, the value range is 0-15
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setMediaVolume(mediaVolume: Int): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val audioManager = (this.getSystemService(Context.AUDIO_SERVICE)
                ?: throw IllegalStateException("AudioManager not found")) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVolume, 0)
        true
    } else {
        Settings.System.putInt(this.contentResolver, "volume_music", mediaVolume)
    }
}

/**
 * Get the ringer volume, the range is 0-7
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.getRingVolume(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val audioManager = (this.getSystemService(Context.AUDIO_SERVICE)
                ?: throw IllegalStateException("AudioManager not found")) as AudioManager
        audioManager.getStreamVolume(AudioManager.STREAM_RING)
    } else {
        try {
            Settings.System.getInt(this.contentResolver, "volume_ring")
        } catch (e: Settings.SettingNotFoundException) {
            throw IllegalStateException(e)
        }

    }
}

/**
 * Set the ringer volume, the range is 0-7
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
fun Context.setRingVolume(ringVolume: Int): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val audioManager = (this.getSystemService(Context.AUDIO_SERVICE)
                ?: throw IllegalStateException("AudioManager not found")) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_RING, ringVolume, 0)
        true
    } else {
        Settings.System.putInt(this.contentResolver, "volume_ring", ringVolume)
    }
}
