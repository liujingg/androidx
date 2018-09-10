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

package me.panpf.androidx;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Collectionx;
import me.panpf.javax.util.Predicate;

@SuppressWarnings("WeakerAccess")
public class Androidx {

    /**
     * Get the main thread Handler
     */
    @NonNull
    public static Handler getMainHandler() {
        return MainHandlerHolder.mainHandler;
    }

    /**
     * Execute the specified code block in the main thread
     */
    public static void runInUI(@NonNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            getMainHandler().post(runnable);
        }
    }

    /**
     * Return true if ROOT is already
     */
    @AnyThread
    public static boolean isRooted() {
        return new File("/system/bin/su").exists() || new File("/system/xbin/su").exists()
                || new File("/sbin/su").exists() || new File("/su").exists();
    }

    /**
     * Is it the main thread?
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * Get the name of the current process
     */
    @Nullable
    public static String getInProcessName(@NonNull Context context) {
        final int myPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningAppProcessInfo info = activityManager != null ? Collectionx.find(activityManager.getRunningAppProcesses(), new Predicate<ActivityManager.RunningAppProcessInfo>() {
            @Override
            public boolean accept(@NonNull ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
                return runningAppProcessInfo.pid == myPid;
            }
        }) : null;
        return info != null ? info.processName : null;
    }

    /**
     * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
     */
    @Nullable
    public static String getInProcessNameSuffix(@NonNull Context $receiver) {
        String processName = getInProcessName($receiver);
        if (processName == null) return null;
        String packageName = $receiver.getPackageName();
        int lastIndex = Stringx.lastIndexOf(processName, packageName, 0, false);
        return lastIndex != -1 ? processName.substring(lastIndex + packageName.length()) : null;
    }

    /**
     * Is in the main process?
     */
    public static boolean isMainProcess(@NonNull Context context) {
        return context.getPackageName().equals(getInProcessName(context));
    }

    public static boolean isAtLeastI() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeast14() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeast4_0() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isAtLeastIMR1() {
        return Build.VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeast15() {
        return Build.VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeast4_0_3() {
        return Build.VERSION.SDK_INT >= 15;
    }

    public static boolean isAtLeastJ() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeast16() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeast4_1() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean isAtLeastJMR1() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeast17() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeast4_2() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeastJMR2() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeast18() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeast4_3() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeastK() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeast19() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeast4_4() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeastKW() {
        return Build.VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeast20() {
        return Build.VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeast4_4_W() {
        return Build.VERSION.SDK_INT >= 20;
    }

    public static boolean isAtLeastL() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeast21() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeast5_0() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeastLMR1() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeast22() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeast5_1() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeast23() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeast6_0() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeast24() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeast7_0() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeast25() {
        return Build.VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeast7_1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeast26() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeast8_0() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeast27() {
        return Build.VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeast8_1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isAtLeast28() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isAtLeast9_0() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @NonNull
    public static String getSdkVersionName(int sdkVersion) {
        String name;
        switch (sdkVersion) {
            case 1:
                name = "1.0";
                break;
            case 2:
                name = "1.1";
                break;
            case 3:
                name = "1.5";
                break;
            case 4:
                name = "1.6";
                break;
            case 5:
                name = "2.0";
                break;
            case 6:
                name = "2.0.1";
                break;
            case 7:
                name = "2.1";
                break;
            case 8:
                name = "2.2";
                break;
            case 9:
                name = "2.3";
                break;
            case 10:
                name = "2.3.3";
                break;
            case 11:
                name = "3.0";
                break;
            case 12:
                name = "3.1";
                break;
            case 13:
                name = "3.2";
                break;
            case 14:
                name = "4.0";
                break;
            case 15:
                name = "4.0.3";
                break;
            case 16:
                name = "4.1";
                break;
            case 17:
                name = "4.2";
                break;
            case 18:
                name = "4.3";
                break;
            case 19:
                name = "4.4";
                break;
            case 20:
                name = "4.4W";
                break;
            case 21:
                name = "5.0";
                break;
            case 22:
                name = "5.1";
                break;
            case 23:
                name = "6.0";
                break;
            case 24:
                name = "7.0";
                break;
            case 25:
                name = "7.1.1";
                break;
            case 26:
                name = "8.0";
                break;
            case 27:
                name = "8.1";
                break;
            case 28:
                name = "9.0";
                break;
            default:
                name = "Unknown(" + sdkVersion + ')';
        }

        return name;
    }

    private static class MainHandlerHolder {
        private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    }
}
