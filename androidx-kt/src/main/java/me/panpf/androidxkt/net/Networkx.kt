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

package me.panpf.androidxkt.net

import android.Manifest
import android.content.Context
import android.support.annotation.RequiresPermission
import me.panpf.androidx.net.NetworkState
import me.panpf.androidx.net.Networkx

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkState(): NetworkState = Networkx.getState(this)

/**
 * Get the status of Wi-Fi
 *
 * @return The value is one of WIFI_STATE_ENABLED, WIFI_STATE_ENABLING, WIFI_STATE_DISABLED, WIFI_STATE_DISABLING, WIFI_STATE_UNKNOWN in WifiManager.
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getWifiState(): Int = Networkx.getWifiState(this)

/**
 * Return true if Wi-Fi is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.isWifiEnabled(): Boolean = Networkx.isWifiEnabled(this)

/**
 * Turn Wi-Fi on or off
 */
@RequiresPermission(anyOf = [(Manifest.permission.ACCESS_WIFI_STATE), (Manifest.permission.CHANGE_WIFI_STATE)])
inline fun Context.setWifiEnabled(enable: Boolean): Boolean = Networkx.setWifiEnabled(this, enable)

/**
 * Return true if mobile network is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isMobileEnabled(): Boolean = Networkx.isMobileEnabled(this)

/**
 * Turn mobile network on or off
 */
@RequiresPermission(Manifest.permission.CHANGE_NETWORK_STATE)
inline fun Context.setMobileEnabled(enabled: Boolean): Boolean = Networkx.setMobileEnabled(this, enabled)

/**
 * Get local IP address
 */
inline fun getLocalIpAddress(defaultIpAddress: String): String = Networkx.getLocalIpAddress(defaultIpAddress)

/**
 * Get local IP address
 */
inline fun getLocalIpAddress(): String? = Networkx.getLocalIpAddress()

/**
 * Get local IPV4 address
 */
inline fun getLocalIpV4Address(defaultIpAddress: String): String = Networkx.getLocalIpV4Address(defaultIpAddress)

/**
 * Get local IPV4 address
 */
inline fun getLocalIpV4Address(): String? = Networkx.getLocalIpV4Address()