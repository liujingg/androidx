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

import android.arch.lifecycle.ViewModelStoreOwner;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Activityx;

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

        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                Activityx.convertToTranslucent(activity);
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                Activityx.convertFromTranslucent(activity);
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImpl() {
        ActivityxTestActivity activity = activityTestRule.getActivity();
        Assert.assertNotNull(Activityx.getImplWithParent(activity, ImplTestInterface.class));
        Assert.assertNull(Activityx.getImplWithParent(activity, ViewModelStoreOwner.class));

        ActivityxTestFragmentActivity activity2 = fragmentActivityTestRule.getActivity();
        Assert.assertNull(Activityx.getImplWithParent(activity2, ImplTestInterface.class));
        Assert.assertNotNull(Activityx.getImplWithParent(activity2, ViewModelStoreOwner.class));
    }
}
