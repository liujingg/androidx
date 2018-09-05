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

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import me.panpf.androidx.os.Threadx;

@SuppressWarnings("WeakerAccess")
public class Androidx {
    private static volatile Handler mainHandler = null;

    @NonNull
    public static Handler getMainHandler() {
        if (mainHandler == null) {
            synchronized (Androidx.class) {
                if (mainHandler == null) {
                    mainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mainHandler;
    }

    public static void runInUI(@NonNull Runnable runnable) {
        if (Threadx.isMainThread()) {
            runnable.run();
        } else {
            getMainHandler().post(runnable);
        }
    }
}
