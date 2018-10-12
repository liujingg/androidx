package me.panpf.androidx.util;

import android.support.annotation.Nullable;

public interface NullableResultRunnable<T> {
    @Nullable
    T run();
}
