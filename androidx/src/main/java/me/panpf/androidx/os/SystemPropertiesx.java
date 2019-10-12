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

package me.panpf.androidx.os;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;

import me.panpf.javax.lang.Classx;
import me.panpf.javax.lang.Stringx;

public class SystemPropertiesx {

    private SystemPropertiesx() {
    }

    /**
     * Get system properties based on the specified key
     */
    @NonNull
    public static String get(@NonNull String key) {
        if (GetStringMethodHolder.METHOD == null) return "";
        return Stringx.orEmpty((String) Classx.callStaticMethod(GetStringMethodHolder.METHOD, key));
    }

    /**
     * Get system properties based on the specified key
     */
    @NonNull
    public static String getOr(@NonNull String key, @NonNull String defaultValue) {
        if (GetStringDefMethodHolder.METHOD == null) return defaultValue;
        return Stringx.orEmpty((String) Classx.callStaticMethod(GetStringDefMethodHolder.METHOD, key, defaultValue));
    }


    /**
     * Get the system property according to the specified key and convert it to int
     */
    public static int getIntOr(@NonNull String key, int defaultValue) {
        if (GetIntMethodHolder.METHOD == null) return defaultValue;
        return (int) Classx.callStaticMethod(GetIntMethodHolder.METHOD, key, defaultValue);
    }

    /**
     * Get the system property according to the specified key and convert it to long
     */
    public static long getLongOr(@NonNull String key, long defaultValue) {
        if (GetLongMethodHolder.METHOD == null) return defaultValue;
        return (long) Classx.callStaticMethod(GetLongMethodHolder.METHOD, key, defaultValue);
    }

    /**
     * Get the system property according to the specified key and convert it to boolean
     */
    public static boolean getBooleanOr(@NonNull String key, boolean defaultValue) {
        if (GetBooleanMethodHolder.METHOD == null) return defaultValue;
        return (boolean) Classx.callStaticMethod(GetBooleanMethodHolder.METHOD, key, defaultValue);
    }

    /**
     * Modify system properties based on the specified key
     */
    @SuppressWarnings("unused")
    public static void set(@NonNull String key, @NonNull String value) {
        if (SetMethodHolder.METHOD == null) return;
        Classx.callStaticMethod(SetMethodHolder.METHOD, key, value);
    }

    /**
     * Add system property change callback
     */
    @SuppressWarnings("unused")
    public static void addChangeCallback(@NonNull Runnable runnable) {
        if (AddChangedCallbackMethodHolder.METHOD == null) return;
        Classx.callStaticMethod(AddChangedCallbackMethodHolder.METHOD, runnable);
    }

    /**
     * Callback all system property change callbacks
     */
    @SuppressWarnings("unused")
    public static void callChangeCallbacks() {
        if (CallChangeCallbacksMethodHolder.METHOD == null) return;
        Classx.callStaticMethod(CallChangeCallbacksMethodHolder.METHOD);
    }

    private static class GetStringMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            /*
             * 为何不采用读取 /root/build.prop 文件的方式？
             * 因为在 MIUI 上没有读取这个文件的权限，在华为 EMUI 上读取的属性不全
             */
            Method getMethod = null;
            try {
                getMethod = Classx.getMethodWithParent("android.os.SystemProperties", "get", String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getMethod;
        }
    }

    private static class GetStringDefMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method getDefMethod = null;
            try {
                getDefMethod = Classx.getMethodWithParent("android.os.SystemProperties", "get", String.class, String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getDefMethod;
        }
    }

    private static class GetIntMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method getIntMethod = null;
            try {
                getIntMethod = Classx.getMethodWithParent("android.os.SystemProperties", "getInt", String.class, int.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getIntMethod;
        }
    }

    private static class GetLongMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method getLongMethod = null;
            try {
                getLongMethod = Classx.getMethodWithParent("android.os.SystemProperties", "getLong", String.class, long.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getLongMethod;
        }
    }

    private static class GetBooleanMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method getBooleanMethod = null;
            try {
                getBooleanMethod = Classx.getMethodWithParent("android.os.SystemProperties", "getBoolean", String.class, boolean.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getBooleanMethod;
        }
    }

    private static class SetMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method getDefMethod = null;
            try {
                getDefMethod = Classx.getMethodWithParent("android.os.SystemProperties", "set", String.class, String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getDefMethod;
        }
    }

    private static class AddChangedCallbackMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method getDefMethod = null;
            try {
                getDefMethod = Classx.getMethodWithParent("android.os.SystemProperties", "addChangeCallback", Runnable.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getDefMethod;
        }
    }

    private static class CallChangeCallbacksMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method getDefMethod = null;
            try {
                getDefMethod = Classx.getMethodWithParent("android.os.SystemProperties", "callChangeCallbacks");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = getDefMethod;
        }
    }
}
