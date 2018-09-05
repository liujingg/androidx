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

package me.panpf.androidxkt.net

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.support.annotation.RequiresPermission
import me.panpf.javaxkt.lang.callMethod

import java.net.NetworkInterface

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.getNetworkState(): NetworkState {
    return NetworkState[this]
}

/**
 * Get the status of Wi-Fi
 *
 * @return The value is one of WIFI_STATE_ENABLED, WIFI_STATE_ENABLING, WIFI_STATE_DISABLED, WIFI_STATE_DISABLING, WIFI_STATE_UNKNOWN in WifiManager.
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
fun Context.getWifiState(): Int {
    val manager = (this.applicationContext.getSystemService(Context.WIFI_SERVICE)
            ?: throw IllegalStateException("WifiManager not found")) as WifiManager
    return manager.wifiState
}

/**
 * Return true if Wi-Fi is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
fun Context.isWifiEnabled(): Boolean {
    val state = this.getWifiState();
    return state == WifiManager.WIFI_STATE_ENABLED || state == WifiManager.WIFI_STATE_ENABLING
}

/**
 * Turn Wi-Fi on or off
 */
@RequiresPermission(anyOf = [(Manifest.permission.ACCESS_WIFI_STATE), (Manifest.permission.CHANGE_WIFI_STATE)])
fun Context.setWifiEnabled(enable: Boolean): Boolean {
    val manager = (this.applicationContext.getSystemService(Context.WIFI_SERVICE)
            ?: throw IllegalStateException("WifiManager not found")) as WifiManager
    return manager.setWifiEnabled(enable)
}

/**
 * Return true if mobile network is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isMobileEnabled(): Boolean {
    val manager = (this.getSystemService(Context.CONNECTIVITY_SERVICE)
            ?: throw IllegalStateException("ConnectivityManager not found")) as ConnectivityManager
    return manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.isConnected ?: false
}

/**
 * Turn mobile network on or off
 */
@RequiresPermission(Manifest.permission.CHANGE_NETWORK_STATE)
fun Context.setMobileEnabled(enabled: Boolean): Boolean {
    val manager = (this.getSystemService(Context.CONNECTIVITY_SERVICE)
            ?: throw IllegalStateException("ConnectivityManager not found")) as ConnectivityManager
    return try {
        manager.callMethod("setMobileDataEnabled", enabled)
        true
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
        false
    }
}

/**
 * Get an IP address
 */
fun getIpAddress(): String? {
    try {
        val en = NetworkInterface.getNetworkInterfaces()
        while (en.hasMoreElements()) {
            val networkInterface = en.nextElement()
            val inetAddresses = networkInterface.inetAddresses
            while (inetAddresses.hasMoreElements()) {
                val inetAddress = inetAddresses.nextElement()
                if (!inetAddress.isLoopbackAddress) {
                    return inetAddress.hostAddress
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}