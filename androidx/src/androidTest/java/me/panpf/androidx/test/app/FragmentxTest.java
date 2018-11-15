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
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Argsx;
import me.panpf.androidx.app.Fragmentx;
import me.panpf.androidx.os.BundleBuilder;
import me.panpf.androidx.test.R;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class FragmentxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);
    @NonNull
    private final ActivityTestRule<TestFindUserVisibleChildActivity> findUserVisibleChildActivityRule = new ActivityTestRule<>(TestFindUserVisibleChildActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<TestFindUserVisibleChildActivity> getFindUserVisibleChildActivityRule() {
        return findUserVisibleChildActivityRule;
    }

    @Test
    public void testDestroyed() throws InterruptedException {
        android.app.Fragment originFragment = activityTestRule.getActivity().getOriginFragment();
        android.support.v4.app.Fragment supportFragment = activityTestRule.getActivity().getSupportFragment();

        Assert.assertFalse(Fragmentx.isDestroyedCompat(originFragment));
        Assert.assertFalse(Fragmentx.isDestroyedCompat(supportFragment));

        activityTestRule.finishActivity();
        Thread.sleep(2000);

        Assert.assertTrue(Fragmentx.isDestroyedCompat(originFragment));
        Assert.assertTrue(Fragmentx.isDestroyedCompat(supportFragment));
    }

    @Test
    public void testGetImplWithParent() {
        TestImplOriginFragment originFragment = (TestImplOriginFragment) activityTestRule.getActivity().getOriginFragment();
        TestImplSupportFragment supportFragment = (TestImplSupportFragment) activityTestRule.getActivity().getSupportFragment();

        Assert.assertNotNull(Fragmentx.getImplWithParent(originFragment, ImplTestInterface.class));
        Assert.assertNotNull(Fragmentx.getImplWithParent(supportFragment, ImplTestInterface.class));

        if (Androidx.isAtLeast17()) {
            Assert.assertNotNull(Fragmentx.getImplWithParent(originFragment.getChildFragment(), ImplTestInterface.class));
        }
        Assert.assertNotNull(Fragmentx.getImplWithParent(supportFragment.getChildFragment(), ImplTestInterface.class));

        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().convertChildFragment();
            }
        });

        TestImplOriginFragment2 originFragment2 = Androidx.waitRunInUIResult(new ResultRunnable<TestImplOriginFragment2>() {
            @NonNull
            @Override
            public TestImplOriginFragment2 run() {
                return (TestImplOriginFragment2) activityTestRule.getActivity().getOriginFragment();
            }
        });
        TestImplSupportFragment2 supportFragment2 = Androidx.waitRunInUIResult(new ResultRunnable<TestImplSupportFragment2>() {
            @NonNull
            @Override
            public TestImplSupportFragment2 run() {
                return (TestImplSupportFragment2) activityTestRule.getActivity().getSupportFragment();
            }
        });

        Assert.assertNotNull(Fragmentx.getImplWithParent(originFragment2, ImplTestInterface.class));
        Assert.assertNotNull(Fragmentx.getImplWithParent(supportFragment2, ImplTestInterface.class));

        Assert.assertNull(Fragmentx.getImplWithParent(new TestImplOriginFragment2(), ImplTestInterface.class));
        Assert.assertNull(Fragmentx.getImplWithParent(new TestImplSupportFragment2(), ImplTestInterface.class));
    }

    @Test
    public void testInstantiate() {
        android.app.Fragment originFragment = Fragmentx.instantiateOrigin(android.app.Fragment.class, new BundleBuilder().putString("key", "testInstantiate").build());
        Assert.assertEquals(android.app.Fragment.class.getName(), originFragment.getClass().getName());
        Assert.assertEquals("testInstantiate", Argsx.readStringArg(originFragment, "key"));

        android.support.v4.app.Fragment supportFragment = Fragmentx.instantiate(android.support.v4.app.Fragment.class, new BundleBuilder().putString("key", "testInstantiate").build());
        Assert.assertEquals(android.support.v4.app.Fragment.class.getName(), supportFragment.getClass().getName());
        Assert.assertEquals("testInstantiate", Argsx.readStringArg(supportFragment, "key"));

        android.app.Fragment originFragment2 = Fragmentx.instantiateOrigin(android.app.Fragment.class);
        Assert.assertEquals(android.app.Fragment.class.getName(), originFragment2.getClass().getName());

        android.support.v4.app.Fragment supportFragment2 = Fragmentx.instantiate(android.support.v4.app.Fragment.class);
        Assert.assertEquals(android.support.v4.app.Fragment.class.getName(), supportFragment2.getClass().getName());
    }

    @Test
    public void testFindUserVisibleChildFragment() {
        TestFindUserVisibleChildActivity activity = findUserVisibleChildActivityRule.getActivity();
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity();
        // 此测试要求手机处于屏幕解锁状态

        android.support.v4.app.Fragment fragmentFromActivity = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(activity));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromActivity.getClass().getName());

        android.support.v4.app.Fragment fragmentFromList = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(activity.getSupportFragmentManager().getFragments()));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromList.getClass().getName());

        TestFindUserVisibleChildFragment fragmentFromActivity2 = (TestFindUserVisibleChildFragment) fragmentFromActivity;
        android.support.v4.app.Fragment fragmentFromChildFragment = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(fragmentFromActivity2));
        Assert.assertTrue(fragmentFromChildFragment.getTag(), Stringx.orEmpty(fragmentFromChildFragment.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromChildFragment.getTag()).endsWith(":3"));
    }

    @Test
    public void testFindFragmentByViewPagerCurrentItem() {
        TestFindUserVisibleChildActivity activity = findUserVisibleChildActivityRule.getActivity();
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity();
        // 此测试要求手机处于屏幕解锁状态

        android.support.v4.app.Fragment fragmentFromActivity = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(activity, 2));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromActivity.getClass().getName());
        Assert.assertTrue(fragmentFromActivity.getTag(), Stringx.orEmpty(fragmentFromActivity.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromActivity.getTag()).endsWith(":2"));

        android.support.v4.app.Fragment fragmentFromList = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(activity.getSupportFragmentManager().getFragments(), 2));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromList.getClass().getName());
        Assert.assertTrue(fragmentFromList.getTag(), Stringx.orEmpty(fragmentFromList.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromList.getTag()).endsWith(":2"));

        TestFindUserVisibleChildFragment fragmentFromActivity2 = (TestFindUserVisibleChildFragment) fragmentFromActivity;
        android.support.v4.app.Fragment fragmentFromChildFragment = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(fragmentFromActivity2, 3));
        Assert.assertTrue(fragmentFromChildFragment.getTag(), Stringx.orEmpty(fragmentFromChildFragment.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromChildFragment.getTag()).endsWith(":3"));
    }

    @Test
    public void testContext() throws InterruptedException {
        TestActivity activity = activityTestRule.getActivity();

        Assert.assertNotNull(Fragmentx.requireContext(activity.getOriginFragment()));

        Assert.assertNotNull(Fragmentx.requireAppContext(activity.getSupportFragment()));
        Assert.assertFalse(Fragmentx.requireAppContext(activity.getSupportFragment()) instanceof Activity);

        Assert.assertNotNull(Fragmentx.requireAppContext(activity.getOriginFragment()));
        Assert.assertFalse(Fragmentx.requireAppContext(activity.getOriginFragment()) instanceof Activity);

        activityTestRule.finishActivity();
        Thread.sleep(1000);

        try {
            Fragmentx.requireContext(activity.getOriginFragment());
            Assert.fail();
        } catch (Exception ignored) {
        }

        try {
            Fragmentx.requireAppContext(activity.getOriginFragment());
            Assert.fail();
        } catch (Exception ignored) {
        }

        try {
            Fragmentx.requireAppContext(activity.getSupportFragment());
            Assert.fail();
        } catch (Exception ignored) {
        }
    }

    public interface ImplTestInterface {
    }

    public static class TestActivity extends FragmentActivity implements ImplTestInterface {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);

            getFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, new TestImplOriginFragment())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new TestImplSupportFragment())
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

        public final void convertChildFragment() {
            getFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, new FragmentxTest.TestImplOriginFragment2())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new FragmentxTest.TestImplSupportFragment2())
                    .commit();
        }
    }

    public static final class TestImplOriginFragment extends android.app.Fragment implements FragmentxTest.ImplTestInterface {

        @Nullable
        public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater != null ? inflater.inflate(R.layout.at_test, container, false) : null;
        }

        public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (Androidx.isAtLeast17()) {
                this.getChildFragmentManager().beginTransaction().replace(R.id.testAt_frame, new FragmentxTest.TestImplOriginFragment2())
                        .commit();
            }
        }

        @NotNull
        public final android.app.Fragment getChildFragment() {
            return Premisex.requireNotNull(getChildFragmentManager().findFragmentById(R.id.testAt_frame));
        }
    }

    public static final class TestImplOriginFragment2 extends android.app.Fragment {
    }

    public static final class TestImplSupportFragment extends android.support.v4.app.Fragment implements FragmentxTest.ImplTestInterface {

        @Nullable
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.at_test, container, false);
        }

        public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            this.getChildFragmentManager().beginTransaction()
                    .replace(R.id.testAt_frame, new TestImplSupportFragment2())
                    .commit();
        }

        @NotNull
        public final android.support.v4.app.Fragment getChildFragment() {
            return Premisex.requireNotNull(this.getChildFragmentManager().findFragmentById(R.id.testAt_frame));
        }
    }

    public static final class TestImplSupportFragment2 extends android.support.v4.app.Fragment {

    }

    public static class TestFindUserVisibleChildActivity extends FragmentActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_view_pager);

            ViewPager viewPager = findViewById(R.id.viewPagerAt_viewPager);
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public android.support.v4.app.Fragment getItem(int p0) {
                    if (p0 == 2) {
                        return Fragmentx.instantiate(TestFindUserVisibleChildFragment.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                    } else {
                        return Fragmentx.instantiate(TestFindUserVisibleChildFragment2.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                    }
                }

                @Override
                public int getCount() {
                    return 5;
                }
            });
            viewPager.setCurrentItem(2);
        }
    }

    public static class TestFindUserVisibleChildFragment extends android.support.v4.app.Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.at_view_pager, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            ViewPager viewPager = view.findViewById(R.id.viewPagerAt_viewPager);
            viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                @Override
                public android.support.v4.app.Fragment getItem(int p0) {
                    return Fragmentx.instantiate(TestFindUserVisibleChildFragment2.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                }

                @Override
                public int getCount() {
                    return 5;
                }
            });
            viewPager.setCurrentItem(3);
        }
    }

    public static class TestFindUserVisibleChildFragment2 extends android.support.v4.app.Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return new TextView(getContext());
        }

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            ((TextView) view).setText("position: " + Argsx.readStringArg(this, "position"));
        }
    }
}
