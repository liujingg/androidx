package me.panpf.androidx.util;

import androidx.annotation.NonNull;

public interface ResultRunnable<T> {
    @NonNull
    T run();
}
