package me.panpf.androidx;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import me.panpf.androidx.os.Threadx;

@SuppressWarnings("WeakerAccess")
public class Androidx {
    private static volatile Handler mainHandler = null;

    @NotNull
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

    public static void runInUI(@NotNull Runnable runnable) {
        if (Threadx.isMainThread()) {
            runnable.run();
        } else {
            getMainHandler().post(runnable);
        }
    }
}
