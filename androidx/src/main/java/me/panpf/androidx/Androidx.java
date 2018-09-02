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
