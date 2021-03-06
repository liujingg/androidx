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

package me.panpf.androidx.test.util;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import me.panpf.androidx.test.R;
import me.panpf.androidx.util.Dimenx;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class DimenxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Test
    public void testContext() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(context, 10f));
        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(context, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(context, 100), 0f);

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(context, 10f));
        Assert.assertEquals((int) ((float) 10 * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(context, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(context, 100), 0f);

        Assert.assertEquals(10f * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(context, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
        Assert.assertEquals(((float) 10) * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(context, TypedValue.COMPLEX_UNIT_SP, 10), 0f);
    }

    @Test
    public void testFragment() {
        Context context = InstrumentationRegistry.getContext();


        android.app.Fragment originFragment = activityTestRule.getActivity().getOriginFragment();

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(originFragment, 10f));
        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(originFragment, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(originFragment, 100), 0f);

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(originFragment, 10f));
        Assert.assertEquals((int) ((float) 10 * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(originFragment, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(originFragment, 100), 0f);

        Assert.assertEquals(10f * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(originFragment, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
        Assert.assertEquals(((float) 10) * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(originFragment, TypedValue.COMPLEX_UNIT_SP, 10), 0f);


        Fragment supportFragment = activityTestRule.getActivity().getSupportFragment();

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(supportFragment, 10f));
        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(supportFragment, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(supportFragment, 100), 0f);

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(supportFragment, 10f));
        Assert.assertEquals((int) ((float) 10 * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(supportFragment, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(supportFragment, 100), 0f);

        Assert.assertEquals(10f * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(supportFragment, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
        Assert.assertEquals(((float) 10) * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(supportFragment, TypedValue.COMPLEX_UNIT_SP, 10), 0f);
    }

    @Test
    public void testView() {
        Context context = InstrumentationRegistry.getContext();

        View view = activityTestRule.getActivity().getView();

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(view, 10f));
        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(view, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(view, 100), 0f);

        Assert.assertEquals((int) (10f * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(view, 10f));
        Assert.assertEquals((int) ((float) 10 * context.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(view, 10));

        Assert.assertEquals(((float) 100) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(view, 100), 0f);

        Assert.assertEquals(10f * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(view, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
        Assert.assertEquals(((float) 10) * context.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(view, TypedValue.COMPLEX_UNIT_SP, 10), 0f);
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
                    .replace(R.id.multiFrameAt_frame2, new Fragment())
                    .commit();
        }

        @NonNull
        public android.app.Fragment getOriginFragment() {
            return getFragmentManager().findFragmentById(R.id.multiFrameAt_frame1);
        }

        @NonNull
        public Fragment getSupportFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        @NonNull
        public View getView() {
            return findViewById(android.R.id.content);
        }
    }
}
