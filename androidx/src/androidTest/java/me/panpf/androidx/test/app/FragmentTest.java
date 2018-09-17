package me.panpf.androidx.test.app;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.app.Fragmentx;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {

    @NonNull
    private final ActivityTestRule<FragmentTestActivity> activityTestRule = new ActivityTestRule<>(FragmentTestActivity.class);

    @NonNull
    private final ActivityTestRule<FragmentTestFragmentActivity> fragmentActivityTestRule = new ActivityTestRule<>(FragmentTestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<FragmentTestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<FragmentTestFragmentActivity> getFragmentActivityTestRule() {
        return fragmentActivityTestRule;
    }

    @Test
    public void testActivityDestroyed() {
        android.app.Fragment fragment = activityTestRule.getActivity().getFragmentManager().findFragmentById(android.R.id.content);

        activityTestRule.finishActivity();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Fragmentx.isDestroyedCompat(fragment));
    }

    @Test
    public void testActivityNormal() {
        android.app.Fragment fragment = activityTestRule.getActivity().getFragmentManager().findFragmentById(android.R.id.content);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Fragmentx.isDestroyedCompat(fragment));
    }

    @Test
    public void testFragmentActivityDestroyed() {
        android.support.v4.app.Fragment fragment = fragmentActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(android.R.id.content);

        fragmentActivityTestRule.finishActivity();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Fragmentx.isDestroyedCompat(fragment));
    }

    @Test
    public void testFragmentActivityNormal() {
        android.support.v4.app.Fragment fragment = fragmentActivityTestRule.getActivity().getSupportFragmentManager().findFragmentById(android.R.id.content);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Fragmentx.isDestroyedCompat(fragment));
    }
}
