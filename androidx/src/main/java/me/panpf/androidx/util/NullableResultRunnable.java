package me.panpf.androidx.util;

import androidx.annotation.Nullable;

public interface NullableResultRunnable<T> {
    @Nullable
    T run();
}
