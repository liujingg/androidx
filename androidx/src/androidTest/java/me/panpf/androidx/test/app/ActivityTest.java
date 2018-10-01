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

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.app.Activityx;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @NonNull
    private final ActivityTestRule<ActivityTestActivity> activityTestRule = new ActivityTestRule<>(ActivityTestActivity.class);

    @NonNull
    private final ActivityTestRule<ActivityTestFragmentActivity> fragmentActivityTestRule = new ActivityTestRule<>(ActivityTestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<ActivityTestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<ActivityTestFragmentActivity> getFragmentActivityTestRule() {
        return fragmentActivityTestRule;
    }

    @Test
    public void testActivityDestroyed() {
        ActivityTestActivity activity = activityTestRule.getActivity();

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
        ActivityTestActivity activity = activityTestRule.getActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testFragmentActivityDestroyed() {
        ActivityTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

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
        ActivityTestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));
    }
}
