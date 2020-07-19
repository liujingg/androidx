package me.panpf.androidx.test.app;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Dialogx;

@RunWith(AndroidJUnit4.class)
public class DialogxTest {

    @Rule
    public final ActivityTestRule<TestFragmentActivity> mFragmentActivityTestRule =
            new ActivityTestRule<>(TestFragmentActivity.class);

    @Test
    public void testSetClickButtonClosable() {
        Androidx.waitRunInUI(() -> {
            TestFragmentActivity activity = mFragmentActivityTestRule.getActivity();
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), true));
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), false));
        });
    }

    @Test
    public void testShowProgressDialog() {
        Androidx.waitRunInUI(() -> {
            TestFragmentActivity activity = mFragmentActivityTestRule.getActivity();
            androidx.fragment.app.Fragment supportFragment = mFragmentActivityTestRule.getActivity()
                    .getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);

            Assert.assertNotNull(Dialogx.showProgressDialog(activity, "by activity"));
            Assert.assertNotNull(Dialogx.showProgressDialog(activity, android.R.string.ok));

            assert supportFragment != null;
            Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, "by supportFragment"));
            Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, android.R.string.yes));
        });
    }

    public static class TestFragmentActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new androidx.fragment.app.Fragment())
                    .commit();
        }

        public Dialog getDialog() {
            return new Dialog(this);
        }
    }
}