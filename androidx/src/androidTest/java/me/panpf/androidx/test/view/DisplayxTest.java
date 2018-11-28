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

package me.panpf.androidx.test.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;
import android.view.Surface;
import android.view.View;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.content.Contextx;
import me.panpf.androidx.test.R;
import me.panpf.androidx.view.Displayx;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class DisplayxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testGetScreenSize() {
        Context context = InstrumentationRegistry.getContext();
        Point point = Displayx.getScreenSize(context);

        Assert.assertTrue(point.x > 0);
        Assert.assertTrue(point.y > 0);
        if (Displayx.isOrientationPortrait(context)) {
            Assert.assertTrue(point.y > point.x);
        } else {
            Assert.assertTrue(point.x > point.y);
        }

        Assert.assertEquals(point.x, Displayx.getScreenWidth(context));
        Assert.assertEquals(point.y, Displayx.getScreenHeight(context));
    }

    @Test
    public void testGetMetrics() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Displayx.getMetrics(context));
    }

    @Test
    public void testGetDensity() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertEquals(context.getResources().getDisplayMetrics().density, Displayx.getDensity(context), 0f);
        Assert.assertEquals(context.getResources().getDisplayMetrics().densityDpi, Displayx.getDensityDpi(context), 0f);
    }

    @Test
    public void testGetRotation() {
        TestActivity activity = activityRule.getActivity();

        switch (Contextx.windowManager(activity).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity));
                break;
        }

        switch (Contextx.windowManager(activity).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity.getOriginFragment()));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity.getOriginFragment()));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity.getOriginFragment()));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity.getOriginFragment()));
                break;
        }

        switch (Contextx.windowManager(activity).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity.getSupportFragment()));
                break;
        }

        switch (Contextx.windowManager(activity).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity.getView()));
                break;
        }
    }

    @Test
    public void testGetOrientation() {
        TestActivity activity = activityRule.getActivity();

        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Assert.assertTrue(Displayx.isOrientationPortrait(activity));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity.getOriginFragment()));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getView()));
        } else if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Assert.assertFalse(Displayx.isOrientationPortrait(activity));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity.getOriginFragment()));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getView()));
        } else {
            Assert.assertFalse(Displayx.isOrientationPortrait(activity));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getOriginFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity.getOriginFragment()));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity.getView()));
        }
    }

    @Test
    public void testStatusBar() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertTrue(Displayx.getStatusBarHeight(context) > 0);
    }

    @Test
    public void testNavigationBar() {
        Context context = InstrumentationRegistry.getContext();
        if (Displayx.hasNavigationBar(context)) {
            if (Displayx.isOrientationPortrait(context)) {
                Assert.assertTrue(Displayx.getNavigationBarHeight(context) > 0);
            } else {
                Assert.assertTrue(Displayx.getNavigationBarWidth(context) > 0);
            }
        } else {
            Assert.assertEquals(0, Displayx.getNavigationBarWidth(context));
            Assert.assertEquals(0, Displayx.getNavigationBarHeight(context));
        }
    }

    public static class TestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);

            getFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, new android.app.Fragment())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new android.support.v4.app.Fragment())
                    .commit();
        }

        @NonNull
        public android.app.Fragment getOriginFragment() {
            return getFragmentManager().findFragmentById(R.id.multiFrameAt_frame1);
        }

        @NonNull
        public android.support.v4.app.Fragment getSupportFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }
}
