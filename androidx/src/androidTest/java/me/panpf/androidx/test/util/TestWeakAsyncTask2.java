package me.panpf.androidx.test.util;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.panpf.androidx.util.WeakAsyncTask;

public class TestWeakAsyncTask2 extends WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer> {

    TestWeakAsyncTask2(@NonNull WeakAsyncTaskTest weakAsyncTaskTest) {
        super(weakAsyncTaskTest);
    }

    @Override
    @Nullable
    protected Integer doInBackground(@NotNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
        return null;
    }
}