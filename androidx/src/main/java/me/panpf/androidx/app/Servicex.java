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
import android.support.annotation.Nullable;

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
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, @Nullable Bundle extras) {
        Intent intent = new Intent(context, serviceClass);
        if (extras != null) intent.putExtras(extras);
        context.startService(intent);
    }

    /**
     * Start Service
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        start(context, serviceClass, null);
    }

    /**
     * Start the service if the specified Class is not running.
     */
    public static boolean tryStart(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, @Nullable Bundle extras) {
        if (!isRunning(context, serviceClass)) {
            Intent intent = new Intent(context, serviceClass);
            if (extras != null) intent.putExtras(extras);
            context.startService(intent);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Start the service if the specified Class is not running.
     */
    public static boolean tryStart(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        return tryStart(context, serviceClass, null);
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
    public static boolean tryStop(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        if (isRunning(context, serviceClass)) {
            context.stopService(new Intent(context, serviceClass));
            return true;
        } else {
            return false;
        }
    }
}