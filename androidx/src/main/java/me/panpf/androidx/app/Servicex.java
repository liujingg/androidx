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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import me.panpf.androidx.content.Contextx;

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
     * Stop Service
     */
    public static void stop(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        context.stopService(new Intent(context, serviceClass));
    }

    // todo add isAccessibilityServiceEnabled
//    public static boolean isAccessibilityServiceEnabled(@NonNull Context context, @NonNull Class<? extends AccessibilityService> service, final @Nullable String packageName) {
//        try {
//            int enable = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, 0);
//            if (enable == 1) {
//                String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
//                if (!TextUtils.isEmpty(services)) {
//                    TextUtils.SimpleStringSplitter split = new TextUtils.SimpleStringSplitter(':');
//                    split.setString(services);
//                    final String finalPackageName = packageName != null ? packageName : context.getPackageName();
//                    final String serviceInfo = finalPackageName + "/" + service.getName();
//                    while (split.hasNext()) {
//                        if (split.next().equalsIgnoreCase(serviceInfo)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}