package me.panpf.androidx.test.app;

import android.app.Fragment;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import me.panpf.androidx.app.Dialogx;
import me.panpf.androidx.test.app.dialog.DialogxTestActivity;
import me.panpf.androidx.test.app.dialog.DialogxTestFragmentActivity;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

  private final Instrumentation ins = InstrumentationRegistry.getInstrumentation();

  @Test
  public void testSetClickButtonClosable() {
    ins.runOnMainSync(new Runnable() {
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
    ins.runOnMainSync(new Runnable() {
      @Override
      public void run() {
        DialogxTestActivity activity = mActivityTestRule.getActivity();
        Fragment fragment = activity.getFragmentManager().findFragmentById(android.R.id.content);
        android.support.v4.app.Fragment supportFragment = mFragmentActivityTestRule.getActivity()
            .getSupportFragmentManager()
            .findFragmentById(android.R.id.content);

        Assert.assertNotNull(Dialogx.showProgressDialog(activity, "by activity"));
        //@StringRes limited ?
        Assert.assertNotNull(Dialogx.showProgressDialog(activity, android.R.string.ok));

        Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, "by supportFragment"));
        Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, android.R.string.yes));

        Assert.assertNotNull(Dialogx.showProgressDialog(fragment, "by fragment"));
        Assert.assertNotNull(Dialogx.showProgressDialog(fragment, android.R.string.yes));
      }
    });
  }


}