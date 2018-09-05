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

package me.panpf.androidx.net;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

/**
 * 判断网络状态的工具类，可一次性满足是否有网络以及什么网络类型
 */
@SuppressWarnings("WeakerAccess")
public class NetworkState {

    @Nullable
    private ConnectivityManager connectivity;

    @Nullable
    private NetworkInfo networkInfo;

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private NetworkState(@NonNull Context context) {
        this.connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.networkInfo = this.connectivity != null ? this.connectivity.getActiveNetworkInfo() : null;
    }

    /**
     * 获取网络状态
     *
     * @param context Context
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkState get(@NonNull Context context) {
        return new NetworkState(context);
    }

    /**
     * 是否有可用的网络连接
     */
    public boolean isActivated() {
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 是否有可用的 wifi 网络连接
     */
    public boolean isWifiActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 是否有可用的不计量 wifi 网络连接
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public boolean isNoMeteredWifiActivated() {
        return isWifiActivated() && !isMetered();
    }

    /**
     * 是否有可用的移动数据网络连接
     */
    public boolean isMobileActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 是否有可用的蓝牙网络连接
     */
    public boolean isBluetoothActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH;
    }

    public int getType(){
        return networkInfo != null ? networkInfo.getType() : -1;
    }

    /**
     * 是否有可用的蓝牙网络连接
     */
    public boolean isVPNActivated() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && networkInfo != null
                && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_VPN;
    }

    /**
     * 当前网络是否是计量的，比如移动数据肯定是计量的，还有热点 wifi 也是计量的
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public boolean isMetered() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && connectivity != null && connectivity.isActiveNetworkMetered();
    }

    /**
     * 是否是漫游网络
     */
    public boolean isRoaming() {
        return networkInfo != null && networkInfo.isRoaming();
    }

    /**
     * 是否是故障转移网络
     */
    public boolean isFailover() {
        return networkInfo != null && networkInfo.isFailover();
    }

    /**
     * 获取网络类型名字
     */
    @NonNull
    public String getTypeName() {
        return networkInfo != null ? networkInfo.getTypeName() : "Unknown";
    }

    /**
     * 获取子类型名字
     */
    @NonNull
    public String getSubtypeName() {
        return networkInfo != null ? networkInfo.getSubtypeName() : "Unknown";
    }

    /**
     * 获取扩展信息
     */
    @NonNull
    public String getExtraInfo() {
        return networkInfo != null ? networkInfo.getExtraInfo() : "Unknown";
    }

    @Nullable
    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    @Nullable
    public ConnectivityManager getConnectivity() {
        return connectivity;
    }
}
