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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import me.panpf.androidx.util.WeakAsyncTask;

public class WeakAsyncTaskTestActivity extends Activity {

    public String result = "None";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new LoadDataTask(this).execute(0);
    }

    private static class LoadDataTask extends WeakAsyncTask<WeakAsyncTaskTestActivity, Integer, Integer, Integer> {

        LoadDataTask(@NonNull WeakAsyncTaskTestActivity weakAsyncTaskTestActivity) {
            super(weakAsyncTaskTestActivity);
        }

        @Override
        protected Integer doInBackground(@NotNull WeakAsyncTaskTestActivity weakAsyncTaskTestActivity, @NonNull Integer[] integers) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(@NotNull WeakAsyncTaskTestActivity weakAsyncTaskTestActivity, @Nullable Integer integer) {
            super.onPostExecute(weakAsyncTaskTestActivity, integer);
            weakAsyncTaskTestActivity.result = "onPostExecute";
        }
    }
}