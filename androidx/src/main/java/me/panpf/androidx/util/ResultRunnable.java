package me.panpf.androidx.util;

import android.support.annotation.NonNull;

public interface ResultRunnable<T> {
    @NonNull
    T run();
}
