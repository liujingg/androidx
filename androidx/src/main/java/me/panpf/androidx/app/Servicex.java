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

package me.panpf.androidx.app;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import me.panpf.androidx.content.Contextx;

@SuppressWarnings("WeakerAccess")
public class Servicex {

    private Servicex() {
    }

    /**
     * Return true if the service specifying Class is running
     */
    public static boolean isRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        try {
            String serviceClassName = serviceClass.getName();

            ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)
                    Contextx.activityManager(context).getRunningServices(Integer.MAX_VALUE);
            if (runningService != null) {
                for (int i = 0, size = runningService.size(); i < size; i++) {
                    if (runningService.get(i).service.getClassName().equals(serviceClassName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Start Service
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, @NonNull Bundle extras) {
        Intent intent = new Intent(context, serviceClass);
        intent.putExtras(extras);
        context.startService(intent);
    }

    /**
     * Start Service
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        context.startService(new Intent(context, serviceClass));
    }

    /**
     * Start the service if the specified Class is not running.
     */
    public static boolean startIfNoRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, @NonNull Bundle extras) {
        if (isRunning(context, serviceClass)) return false;
        Intent intent = new Intent(context, serviceClass);
        intent.putExtras(extras);
        context.startService(intent);
        return true;
    }

    /**
     * Start the service if the specified Class is not running.
     */
    public static boolean startIfNoRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        if (isRunning(context, serviceClass)) return false;
        context.startService(new Intent(context, serviceClass));
        return true;
    }

    /**
     * Stop Service
     */
    public static void stop(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        context.stopService(new Intent(context, serviceClass));
    }

    /**
     * Stop it if the Service for the specified Class is running
     */
    public static boolean stopIfRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        if (!isRunning(context, serviceClass)) return false;
        context.stopService(new Intent(context, serviceClass));
        return true;
    }
}