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

package me.panpf.androidx.test.app;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Activityx;
import me.panpf.androidx.test.app.activity.ActivityxNoRegisterTestActivity;
import me.panpf.androidx.test.app.activity.ActivityxTestActivity;
import me.panpf.androidx.test.app.activity.ActivityxTestFragmentActivity;
import me.panpf.androidx.test.app.activity.ImplTestInterface;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.lang.Throwablex;

@RunWith(AndroidJUnit4.class)
public class ActivityxTest {

    @NonNull
    private final ActivityTestRule<ActivityxTestActivity> activityTestRule = new ActivityTestRule<>(ActivityxTestActivity.class);

    @NonNull
    private final ActivityTestRule<ActivityxTestFragmentActivity> fragmentActivityTestRule = new ActivityTestRule<>(ActivityxTestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<ActivityxTestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<ActivityxTestFragmentActivity> getFragmentActivityTestRule() {
        return fragmentActivityTestRule;
    }

    @Test
    public void testActivityDestroyed() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        activityTestRule.finishActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testActivityNormal() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testFragmentActivityDestroyed() {
        ActivityxTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        fragmentActivityTestRule.finishActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testFragmentActivityNormal() {
        ActivityxTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testConvertTranslucent() {
        final ActivityxTestActivity activity = activityTestRule.getActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean result = Androidx.waitRunInUIResult(new ResultRunnable<Boolean>() {
            @NonNull
            @Override
            public Boolean run() {
                return Activityx.convertToTranslucentCompat(activity);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result);
        } else {
            Assert.assertFalse(result);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = Androidx.waitRunInUIResult(new ResultRunnable<Boolean>() {
            @NonNull
            @Override
            public Boolean run() {
                return Activityx.convertFromTranslucentCompat(activity);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result);
        } else {
            Assert.assertFalse(result);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetImplWithParent() {
        ActivityxTestActivity activity = activityTestRule.getActivity();
        Assert.assertNotNull(Activityx.getImplWithParent(activity, ImplTestInterface.class));
        Assert.assertNull(Activityx.getImplWithParent(activity, ViewModelStoreOwner.class));

        ActivityxTestFragmentActivity activity2 = fragmentActivityTestRule.getActivity();
        Assert.assertNull(Activityx.getImplWithParent(activity2, ImplTestInterface.class));
        Assert.assertNotNull(Activityx.getImplWithParent(activity2, ViewModelStoreOwner.class));
    }

    @Test
    public void testAppContext() {
        ActivityxTestActivity activity = activityTestRule.getActivity();
        Assert.assertTrue(Activityx.appContext(activity) instanceof Application);
        Assert.assertFalse(Activityx.appContext(activity) instanceof Activity);
    }

    @Test
    public void testCanStart() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertFalse(Activityx.canStart(context, new Intent(context, ActivityxTest.class)));
        Assert.assertTrue(Activityx.canStart(context, new Intent(context, ActivityxTestActivity.class)));
    }

    @Test
    public void testStartActivityByIntentActivity() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity, new Intent(activity, ActivityxTestActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(Activityx.appContext(activity), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.start(activity, ActivityxTestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity, ActivityxTestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(Activityx.appContext(activity), ActivityxNoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentSupportFragment() {
        ActivityxTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, ActivityxTestActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxTestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxTestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxNoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentOriginFragment() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, ActivityxTestActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxTestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxTestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), ActivityxNoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentView() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity.getView(), new Intent(activity, ActivityxTestActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.start(activity.getView(), ActivityxTestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), ActivityxTestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), ActivityxNoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSafeStartActivityByIntentActivity() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity, new Intent(activity, ActivityxTestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(Activityx.appContext(activity), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity, ActivityxTestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity, ActivityxTestActivity.class));
        Assert.assertFalse(Activityx.safeStart(Activityx.appContext(activity), ActivityxNoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentSupportFragment() {
        ActivityxTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), ActivityxTestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), ActivityxTestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), ActivityxNoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentOriginFragment() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), ActivityxTestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), ActivityxTestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), ActivityxNoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentView() {
        ActivityxTestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getView(), new Intent(activity, ActivityxTestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getView(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getView(), ActivityxTestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getView(), ActivityxTestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getView(), ActivityxNoRegisterTestActivity.class));
    }
}
