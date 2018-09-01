package me.panpf.androidx.os;

import android.os.Looper;

/*
 * Thread related tool methods
 */
public class Threadx {

    /**
     * Is it the main thread?
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
