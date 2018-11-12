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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.app.Fragmentx;

@RunWith(AndroidJUnit4.class)
public class FragmentxTest {

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

    public static class TestActivity extends Activity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getFragmentManager().beginTransaction().replace(android.R.id.content, new android.app.Fragment()).commit();
        }
    }

    public static class TestFragmentActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new android.support.v4.app.Fragment()).commit();
        }

    }
}
