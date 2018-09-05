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