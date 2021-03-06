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

import java.io.File;
import java.util.concurrent.CountDownLatch;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.panpf.androidx.content.Contextx;
import me.panpf.androidx.util.NullableResultRunnable;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Premisex;

public class Androidx {

    private Androidx() {
    }

    /**
     * Get the main thread Handler
     */
    @NonNull
    public static Handler getMainHandler() {
        return MainHandlerHolder.MAIN_HANDLER;
    }

    /**
     * Execute the specified code block in the main thread
     */
    public static void runInUI(@NonNull Runnable block) {
        if (isMainThread()) {
            block.run();
        } else {
            getMainHandler().post(block);
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    public static void waitRunInUI(@NonNull final Runnable block) {
        if (isMainThread()) {
            block.run();
        } else {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    block.run();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @NonNull
    public static <T> T waitRunInUIResult(@NonNull final ResultRunnable<T> block) {
        if (isMainThread()) {
            return block.run();
        } else {
            final Object[] results = new Object[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    results[0] = block.run();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //noinspection unchecked
            return Premisex.requireNotNull((T) results[0], "result");
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @Nullable
    public static <T> T waitRunInUINullableResult(@NonNull final NullableResultRunnable<T> block) {
        if (isMainThread()) {
            return block.run();
        } else {
            final Object[] results = new Object[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    results[0] = block.run();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //noinspection unchecked
            return (T) results[0];
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
        ActivityManager.RunningAppProcessInfo info = Collectionx.find(Contextx.activityManager(context).getRunningAppProcesses(), new Predicate<ActivityManager.RunningAppProcessInfo>() {
            @Override
            public boolean accept(@NonNull ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
                return runningAppProcessInfo.pid == myPid;
            }
        });
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


    public static boolean isAtLeastJ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeast16() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeast4_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeastJMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeast17() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeast4_2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeastJMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeast18() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeast4_3() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeastK() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeast19() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeast4_4() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeastKW() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeast20() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeast4_4_W() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeastL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeast21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeast5_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeastLMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeast22() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeast5_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeast23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeast6_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast24() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast7_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeast25() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeast7_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeast26() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeast8_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeast27() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeast8_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isAtLeast28() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isAtLeast9_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }


    @NonNull
    public static String getVersionName(int sdkVersion) {
        String name;
        switch (sdkVersion) {
            case Build.VERSION_CODES.BASE:
                name = "1.0.0";
                break;
            case Build.VERSION_CODES.BASE_1_1:
                name = "1.1.0";
                break;
            case Build.VERSION_CODES.CUPCAKE:
                name = "1.5.0";
                break;
            case Build.VERSION_CODES.DONUT:
                name = "1.6.0";
                break;
            case Build.VERSION_CODES.ECLAIR:
                name = "2.0.0";
                break;
            case Build.VERSION_CODES.ECLAIR_0_1:
                name = "2.0.1";
                break;
            case Build.VERSION_CODES.ECLAIR_MR1:
                name = "2.1.0";
                break;
            case Build.VERSION_CODES.FROYO:
                name = "2.2.0";
                break;
            case Build.VERSION_CODES.GINGERBREAD:
                name = "2.3.0";
                break;
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                name = "2.3.3";
                break;
            case Build.VERSION_CODES.HONEYCOMB:
                name = "3.0.0";
                break;
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                name = "3.1.0";
                break;
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                name = "3.2.0";
                break;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                name = "4.0.0";
                break;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                name = "4.0.3";
                break;
            case Build.VERSION_CODES.JELLY_BEAN:
                name = "4.1.0";
                break;
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                name = "4.2.0";
                break;
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                name = "4.3.0";
                break;
            case Build.VERSION_CODES.KITKAT:
                name = "4.4.0";
                break;
            case Build.VERSION_CODES.KITKAT_WATCH:
                name = "4.4W";
                break;
            case Build.VERSION_CODES.LOLLIPOP:
                name = "5.0.0";
                break;
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                name = "5.1.0";
                break;
            case Build.VERSION_CODES.M:
                name = "6.0.0";
                break;
            case Build.VERSION_CODES.N:
                name = "7.0.0";
                break;
            case Build.VERSION_CODES.N_MR1:
                name = "7.1.1";
                break;
            case Build.VERSION_CODES.O:
                name = "8.0.0";
                break;
            case Build.VERSION_CODES.O_MR1:
                name = "8.1.0";
                break;
            case Build.VERSION_CODES.P:
                name = "9.0.0";
                break;
            default:
                name = "Unknown(" + sdkVersion + ')';
        }

        return name;
    }

    @NonNull
    public static String getVersionName() {
        return getVersionName(Build.VERSION.SDK_INT);
    }

    @NonNull
    public static String getVersionCodeName(int sdkVersion) {
        switch (sdkVersion) {
            case Build.VERSION_CODES.BASE:
                return "Base";
            case Build.VERSION_CODES.BASE_1_1:
                return "Base1_1";
            case Build.VERSION_CODES.CUPCAKE:
                return "Cupcake";
            case Build.VERSION_CODES.DONUT:
                return "Donut";
            case Build.VERSION_CODES.ECLAIR:
                return "Eclair";
            case Build.VERSION_CODES.ECLAIR_0_1:
                return "Eclair01";
            case Build.VERSION_CODES.ECLAIR_MR1:
                return "EclairMR1";
            case Build.VERSION_CODES.FROYO:
                return "Froyo";
            case Build.VERSION_CODES.GINGERBREAD:
                return "Gingerbread";
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                return "GingerbreadMR1";
            case Build.VERSION_CODES.HONEYCOMB:
                return "Honeycomb";
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                return "HoneycombMR1";
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                return "HoneycombMR2";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                return "IceCreamSandwich";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return "IceCreamSandwichMR1";
            case Build.VERSION_CODES.JELLY_BEAN:
                return "JellyBean";
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                return "JellyBeanMR1";
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return "JellyBeanMR2";
            case Build.VERSION_CODES.KITKAT:
                return "KITKAT";
            case Build.VERSION_CODES.KITKAT_WATCH:
                return "KitkatWatch";
            case Build.VERSION_CODES.LOLLIPOP:
                return "Lollipop";
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return "LollipopMR1";
            case Build.VERSION_CODES.M:
                return "Marshmallow";
            case Build.VERSION_CODES.N:
                return "Nougat";
            case Build.VERSION_CODES.N_MR1:
                return "NougatMR1";
            case Build.VERSION_CODES.O:
                return "Oreo";
            case Build.VERSION_CODES.O_MR1:
                return "OreoMR1";
            case Build.VERSION_CODES.P:
                return "Pie";
            default:
                return "Unknown(" + sdkVersion + ')';
        }
    }

    @NonNull
    public static String getVersionCodeName() {
        return getVersionCodeName(Build.VERSION.SDK_INT);
    }


    private static class MainHandlerHolder {
        private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    }
}
