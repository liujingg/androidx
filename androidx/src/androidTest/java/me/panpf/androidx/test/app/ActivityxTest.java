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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Activityx;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.lang.Throwablex;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class ActivityxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @NonNull
    private final ActivityTestRule<TestFragmentActivity> fragmentActivityTestRule = new ActivityTestRule<>(TestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<TestFragmentActivity> getFragmentActivityTestRule() {
        return fragmentActivityTestRule;
    }

    @Test
    public void testActivityDestroyed() {
        TestActivity activity = activityTestRule.getActivity();

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
        TestActivity activity = activityTestRule.getActivity();

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
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

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
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

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
        final TestActivity activity = activityTestRule.getActivity();

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
        TestActivity activity = activityTestRule.getActivity();
        Assert.assertNotNull(Activityx.getImplWithParent(activity, ImplTestInterface.class));
        Assert.assertNull(Activityx.getImplWithParent(activity, ViewModelStoreOwner.class));

        TestFragmentActivity activity2 = fragmentActivityTestRule.getActivity();
        Assert.assertNull(Activityx.getImplWithParent(activity2, ImplTestInterface.class));
        Assert.assertNotNull(Activityx.getImplWithParent(activity2, ViewModelStoreOwner.class));
    }

    @Test
    public void testAppContext() {
        TestActivity activity = activityTestRule.getActivity();
        Assert.assertTrue(Activityx.appContext(activity) instanceof Application);
        Assert.assertFalse(Activityx.appContext(activity) instanceof Activity);
    }

    @Test
    public void testCanStart() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertFalse(Activityx.canStart(context, new Intent(context, ActivityxTest.class)));
        Assert.assertTrue(Activityx.canStart(context, new Intent(context, TestActivity.class)));
    }

    @Test
    public void testStartActivityByIntentActivity() {
        TestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity, new Intent(activity, TestActivity.class));
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
            Activityx.start(activity, TestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity, TestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(Activityx.appContext(activity), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentSupportFragment() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, TestActivity.class));
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
            Activityx.start(activity.getFragment(), TestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), TestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentOriginFragment() {
        TestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, TestActivity.class));
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
            Activityx.start(activity.getFragment(), TestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), TestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentView() {
        TestActivity activity = activityTestRule.getActivity();

        try {
            Activityx.start(activity.getView(), new Intent(activity, TestActivity.class));
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
            Activityx.start(activity.getView(), TestActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), TestActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSafeStartActivityByIntentActivity() {
        TestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity, new Intent(activity, TestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(Activityx.appContext(activity), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity, TestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity, TestActivity.class));
        Assert.assertFalse(Activityx.safeStart(Activityx.appContext(activity), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentSupportFragment() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), TestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), TestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentOriginFragment() {
        TestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), TestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), TestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentView() {
        TestActivity activity = activityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getView(), new Intent(activity, TestActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getView(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStart(activity.getView(), TestActivity.class, null));
        Assert.assertTrue(Activityx.safeStart(activity.getView(), TestActivity.class));
        Assert.assertFalse(Activityx.safeStart(activity.getView(), NoRegisterTestActivity.class));
    }

    public interface ImplTestInterface {
    }

    public static class TestActivity extends Activity implements ImplTestInterface {
        public boolean finished;
        public boolean finishedActivity;
        public boolean finishedActivityFromChild;
        public boolean destoryed;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction().replace(android.R.id.content, new android.app.Fragment()).commit();
        }

        @Override
        public void finish() {
            super.finish();
            finished = true;
        }

        @Override
        public void finishActivity(int requestCode) {
            super.finishActivity(requestCode);
            finishedActivity = true;
        }

        @Override
        public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
            super.finishActivityFromChild(child, requestCode);
            finishedActivityFromChild = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            destoryed = true;
        }

        @NonNull
        public android.app.Fragment getFragment() {
            return getFragmentManager().findFragmentById(android.R.id.content);
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }

    public static class TestFragmentActivity extends FragmentActivity {
        public boolean finished;
        public boolean finishedActivity;
        public boolean finishedActivityFromChild;
        public boolean destoryed;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new android.support.v4.app.Fragment()).commit();
        }

        @Override
        public void finish() {
            super.finish();
            finished = true;
        }

        @Override
        public void finishActivity(int requestCode) {
            super.finishActivity(requestCode);
            finishedActivity = true;
        }

        @Override
        public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
            super.finishActivityFromChild(child, requestCode);
            finishedActivityFromChild = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            destoryed = true;
        }

        @NonNull
        public android.support.v4.app.Fragment getFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(android.R.id.content));
        }

//        public View getView() {
//            return findViewById(android.R.id.content);
//        }
    }

    public static class NoRegisterTestActivity extends Activity implements ImplTestInterface {
        public boolean finished;
        public boolean finishedActivity;
        public boolean finishedActivityFromChild;
        public boolean destoryed;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction().replace(android.R.id.content, new android.app.Fragment()).commit();
        }

        @Override
        public void finish() {
            super.finish();
            finished = true;
        }

        @Override
        public void finishActivity(int requestCode) {
            super.finishActivity(requestCode);
            finishedActivity = true;
        }

        @Override
        public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
            super.finishActivityFromChild(child, requestCode);
            finishedActivityFromChild = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            destoryed = true;
        }
    }
}
