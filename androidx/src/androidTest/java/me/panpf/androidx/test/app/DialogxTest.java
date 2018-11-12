package me.panpf.androidx.test.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Dialogx;
import me.panpf.androidx.test.app.fragment.TestOriginFragment;
import me.panpf.androidx.test.app.fragment.TestSupportFragment;

/**
 * <P>Created by Vincent on 2018/11/9.</P>
 */
@RunWith(AndroidJUnit4.class)
public class DialogxTest {

    @Rule
    public final ActivityTestRule<DialogxTestActivity> mActivityTestRule = new ActivityTestRule<>
            (DialogxTestActivity.class);

    @Rule
    public final ActivityTestRule<DialogxTestFragmentActivity> mFragmentActivityTestRule =
            new ActivityTestRule<>(DialogxTestFragmentActivity.class);

    @Test
    public void testSetClickButtonClosable() {
        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                DialogxTestActivity activity = mActivityTestRule.getActivity();
                Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), true));
                Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), false));
            }
        });
    }

    @Test
    public void testShowProgressDialog() {
        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                DialogxTestActivity activity = mActivityTestRule.getActivity();
                Fragment fragment = activity.getFragmentManager().findFragmentById(android.R.id.content);
                android.support.v4.app.Fragment supportFragment = mFragmentActivityTestRule.getActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(android.R.id.content);

                Assert.assertNotNull(Dialogx.showProgressDialog(activity, "by activity"));
                Assert.assertNotNull(Dialogx.showProgressDialog(activity, android.R.string.ok));

                assert supportFragment != null;
                Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, "by supportFragment"));
                Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, android.R.string.yes));

                Assert.assertNotNull(Dialogx.showProgressDialog(fragment, "by fragment"));
                Assert.assertNotNull(Dialogx.showProgressDialog(fragment, android.R.string.yes));
            }
        });
    }

    public static class DialogxTestActivity extends Activity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new TestOriginFragment())
                    .commit();
        }

        public Dialog getDialog() {
            return new Dialog(this);
        }
    }

    public static class DialogxTestFragmentActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new TestSupportFragment())
                    .commit();
        }

    }
}