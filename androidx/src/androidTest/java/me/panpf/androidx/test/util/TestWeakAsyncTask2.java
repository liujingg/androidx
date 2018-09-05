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