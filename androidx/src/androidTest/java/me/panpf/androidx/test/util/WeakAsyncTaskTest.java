package me.panpf.androidx.test.util;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.util.WeakAsyncTask;

@RunWith(AndroidJUnit4.class)
public class WeakAsyncTaskTest {

    @NonNull
    private final ActivityTestRule<WeakAsyncTaskTestActivity> activityTestRule = new ActivityTestRule<>(WeakAsyncTaskTestActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<WeakAsyncTaskTestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Test
    public void testError() {
        String result;
        try {
            new WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer>(this) {
                @Override
                protected Integer doInBackground(@NotNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
                    return null;
                }
            };
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "failed");

        try {
            new TestWeakAsyncTask(this);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "success");

        try {
            new TestWeakAsyncTask2(this);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "success");
    }

    @Test
    public void testDestroyed() {
        WeakAsyncTaskTestActivity activity = activityTestRule.getActivity();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        activityTestRule.finishActivity();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(activity.result, "None");
    }

    @Test
    public void testNormal() {
        WeakAsyncTaskTestActivity activity = activityTestRule.getActivity();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(activity.result, "onPostExecute");
    }

    private static class TestWeakAsyncTask extends WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer> {

        TestWeakAsyncTask(@NonNull WeakAsyncTaskTest weakAsyncTaskTest) {
            super(weakAsyncTaskTest);
        }

        @Override
        protected Integer doInBackground(@NotNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
            return null;
        }
    }
}
