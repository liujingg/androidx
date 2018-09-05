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
import android.net.NetworkInfo
import android.os.Build
import android.support.annotation.RequiresPermission

/**
 * 判断网络状态的工具类，可一次性满足是否有网络以及什么网络类型
 */
class NetworkState @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
private constructor(context: Context) {

    private val connectivity: ConnectivityManager?

    private val networkInfo: NetworkInfo?

    /**
     * 是否有可用的网络连接
     */
    val isActivated: Boolean
        get() = networkInfo != null && networkInfo.isConnected

    /**
     * 是否有可用的 wifi 网络连接
     */
    val isWifiActivated: Boolean
        get() = networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI

    /**
     * 是否有可用的不计量 wifi 网络连接
     */
    val isNoMeteredWifiActivated: Boolean
        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        get() = isWifiActivated && !isMetered

    /**
     * 是否有可用的移动数据网络连接
     */
    val isMobileActivated: Boolean
        get() = networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_MOBILE

    /**
     * 是否有可用的蓝牙网络连接
     */
    val isBluetoothActivated: Boolean
        get() = networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_BLUETOOTH

    val type: Int
        get() = networkInfo?.type ?: -1

    /**
     * 是否有可用的蓝牙网络连接
     */
    val isVPNActivated: Boolean
        get() = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && networkInfo != null
                && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_VPN)

    /**
     * 当前网络是否是计量的，比如移动数据肯定是计量的，还有热点 wifi 也是计量的
     */
    val isMetered: Boolean
        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && connectivity != null && connectivity.isActiveNetworkMetered

    /**
     * 是否是漫游网络
     */
    val isRoaming: Boolean
        get() = networkInfo != null && networkInfo.isRoaming

    /**
     * 是否是故障转移网络
     */
    val isFailover: Boolean
        get() = networkInfo != null && networkInfo.isFailover

    /**
     * 获取网络类型名字
     */
    val typeName: String
        get() = if (networkInfo != null) networkInfo.typeName else "Unknown"

    /**
     * 获取子类型名字
     */
    val subtypeName: String
        get() = if (networkInfo != null) networkInfo.subtypeName else "Unknown"

    /**
     * 获取扩展信息
     */
    val extraInfo: String
        get() = if (networkInfo != null) networkInfo.extraInfo else "Unknown"

    init {
        this.connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        this.networkInfo = this.connectivity.activeNetworkInfo
    }

    companion object {

        /**
         * 获取网络状态
         *
         * @param context Context
         */
        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        operator fun get(context: Context): NetworkState {
            return NetworkState(context)
        }
    }
}
