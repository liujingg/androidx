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

package me.panpf.androidx.test.content;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import me.panpf.androidx.content.LifecycleBroadcastReceiver;
import me.panpf.androidx.test.BuildConfig;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class LifecycleBroadcastReceiverTest {
    private static String INTENT_ACTION = BuildConfig.APPLICATION_ID + ".LifecycleBroadcastReceiverTest";

    @NonNull
    private final ActivityTestRule<TestFragmentActivity> activityTestRule = new ActivityTestRule<>(TestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestFragmentActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Test
    public void test() throws InterruptedException {
        TestFragmentActivity activity = activityTestRule.getActivity();
        TestFragment fragment = Premisex.requireNotNull(activity.getTestFragment());

        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "send1"));
        Thread.sleep(2000);
        Assert.assertEquals("[send1]", activity.createReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.startReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", fragment.createReceiver.getEventArrayString());

        activityTestRule.finishActivity();
        Thread.sleep(2000);
        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "send2"));
        Thread.sleep(2000);
        Assert.assertEquals("[send1]", activity.createReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.startReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", fragment.createReceiver.getEventArrayString());

        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "throwCrash"));
        Thread.sleep(2000);
    }

    public static class TestFragmentActivity extends FragmentActivity {

        public TestBroadcastReceiver createReceiver;
        public TestBroadcastReceiver startReceiver;
        public TestBroadcastReceiver resumeReceiver;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            createReceiver = new TestBroadcastReceiver(this);
            Assert.assertTrue(createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION)));
            Assert.assertFalse(createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION)));

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new TestFragment())
                    .commit();
        }

        @Override
        protected void onStart() {
            super.onStart();

            startReceiver = new TestBroadcastReceiver(this);
            Assert.assertTrue(startReceiver.registerStartStop(new IntentFilter(INTENT_ACTION)));
            Assert.assertFalse(startReceiver.registerStartStop(new IntentFilter(INTENT_ACTION)));
        }

        @Override
        protected void onResume() {
            super.onResume();

            resumeReceiver = new TestBroadcastReceiver(this);
            Assert.assertTrue(resumeReceiver.registerResumePause(new IntentFilter(INTENT_ACTION)));
            Assert.assertFalse(resumeReceiver.registerResumePause(new IntentFilter(INTENT_ACTION)));
        }

        @Nullable
        public TestFragment getTestFragment() {
            return (TestFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }
    }

    public static class TestFragment extends Fragment {

        public TestBroadcastReceiver createReceiver;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            createReceiver = new TestBroadcastReceiver(this);
            Assert.assertTrue(createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION)));
            Assert.assertFalse(createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION)));
        }
    }

    public static class TestBroadcastReceiver extends LifecycleBroadcastReceiver {
        private List<String> eventList = new LinkedList<>();

        public TestBroadcastReceiver(@NonNull Fragment fragment) {
            super(fragment);
        }

        public TestBroadcastReceiver(@NonNull FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String event = Stringx.orDefault(intent.getStringExtra("event"), "unknown");
            if (event.equals("throwCrash")) {
                throw new RuntimeException("Received a crash crash broadcast");
            } else {
                eventList.add(event);
            }
        }

        public String getEventArrayString() {
            return Collectionx.joinToArrayString(eventList);
        }
    }
}
