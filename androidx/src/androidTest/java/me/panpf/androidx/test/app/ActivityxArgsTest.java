///*
// * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *   http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package me.panpf.androidx.test.app;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.support.annotation.NonNull;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Assert;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//
//import me.panpf.javax.util.Collectionx;
//
//@RunWith(AndroidJUnit4.class)
//public class ActivityxArgsTest {
//
//    @NonNull
//    private final ActivityTestRule<ActivityxArgsTestActivity> activityArgsTestRule = new ActivityTestRule<ActivityxArgsTestActivity>(ActivityxArgsTestActivity.class) {
//        @Override
//        protected Intent getActivityIntent() {
//            Intent intent = new Intent(InstrumentationRegistry.getContext(), ActivityxArgsTestActivity.class);
//            intent.putExtra("byteRequired", (byte) 2);
//            intent.putExtra("byteArrayRequired", new byte[]{(byte) 2, (byte) -2});
//            intent.putExtra("byteArrayOptional", new byte[]{(byte) -2, (byte) 2});
//            intent.putExtra("shortRequired", (short) 3);
//            intent.putExtra("shortArrayRequired", new short[]{(short) 3, (short) -3});
//            intent.putExtra("shortArrayOptional", new short[]{(short) -3, (short) 3});
//            intent.putExtra("intRequired", 500);
//            intent.putExtra("intArrayRequired", new int[]{500, -500});
//            intent.putExtra("intArrayOptional", new int[]{-500, 500});
//            intent.putIntegerArrayListExtra("intArrayListRequired", (ArrayList<Integer>) Collectionx.listOf(500, -500));
//            intent.putIntegerArrayListExtra("intArrayListOptional", (ArrayList<Integer>) Collectionx.listOf(-500, 500));
//            intent.putExtra("longRequired", 1000L);
//            intent.putExtra("longArrayRequired", new long[]{1000L, -1000L});
//            intent.putExtra("longArrayOptional", new long[]{-1000L, 1000L});
//            intent.putExtra("floatRequired", 4.0F);
//            intent.putExtra("floatArrayRequired", new float[]{4.0F, -4.0F});
//            intent.putExtra("floatArrayOptional", new float[]{-4.0F, 4.0F});
//            intent.putExtra("doubleRequired", (double) 6);
//            intent.putExtra("doubleArrayRequired", new double[]{(double) 6, (double) -6});
//            intent.putExtra("doubleArrayOptional", new double[]{(double) -6, (double) 6});
//            intent.putExtra("booleanRequired", true);
//            intent.putExtra("booleanArrayRequired", new boolean[]{true, false});
//            intent.putExtra("booleanArrayOptional", new boolean[]{false, true});
//            intent.putExtra("charRequired", 'a');
//            intent.putExtra("charArrayRequired", new char[]{'a', 'b'});
//            intent.putExtra("charArrayOptional", new char[]{'b', 'a'});
//            intent.putExtra("stringRequired", "stringRequired");
//            intent.putExtra("stringOptional", "stringOptional");
//            intent.putExtra("stringArrayRequired", new String[]{"stringRequired", "stringOptional"});
//            intent.putExtra("stringArrayOptional", new String[]{"stringOptional", "stringRequired"});
//            intent.putStringArrayListExtra("stringArrayListRequired", (ArrayList<String>) Collectionx.listOf("stringRequired", "stringOptional"));
//            intent.putStringArrayListExtra("stringArrayListOptional", (ArrayList<String>) Collectionx.listOf("stringOptional", "stringRequired"));
//            intent.putExtra("charSequenceRequired", "stringRequired");
//            intent.putExtra("charSequenceOptional", "stringOptional");
//            intent.putExtra("charSequenceArrayRequired", new String[]{"stringRequired", "stringOptional"});
//            intent.putExtra("charSequenceArrayOptional", new String[]{"stringOptional", "stringRequired"});
//            intent.putExtra("parcelableRequired", new TestParcelable("parcelableRequired"));
//            intent.putExtra("parcelableOptional", new TestParcelable("parcelableOptional"));
//            intent.putExtra("parcelableArrayRequired", new TestParcelable[]{new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")});
//            intent.putExtra("parcelableArrayOptional", new TestParcelable[]{new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")});
//            intent.putParcelableArrayListExtra("parcelableArrayListRequired", (ArrayList<? extends Parcelable>) Collectionx.listOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
//            intent.putParcelableArrayListExtra("parcelableArrayListOptional", (ArrayList<? extends Parcelable>) Collectionx.listOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));
//            intent.putExtra("serializableRequired", new TestSerializable("serializableRequired"));
//            intent.putExtra("serializableOptional", new TestSerializable("serializableOptional"));
//
//            Bundle bundle = new Bundle();
//            bundle.putString("bundle", "bundleRequired");
//            intent.putExtra("bundleRequired", bundle);
//
//            Bundle bundle2 = new Bundle();
//            bundle2.putString("bundle", "bundleOptional");
//            intent.putExtra("bundleOptional", bundle2);
//            return intent;
//        }
//    };
//
//    @Rule
//    @NonNull
//    public ActivityTestRule<ActivityxArgsTestActivity> getActivityArgsTestRule() {
//        return activityArgsTestRule;
//    }
//
//    @Test
//    public void testActivityReadArg() {
//        ActivityxArgsTestActivity activity = getActivityArgsTestRule().getActivity();
//
//        Assert.assertTrue(activity.booleanRequired)
//        Assert.assertTrue(activity.booleanArrayRequired[0] && !activity.booleanArrayRequired[1])
//        Assert.assertTrue(!(activity.booleanArrayOptional?.get(0) == true)
//                && activity.booleanArrayOptional?.get(1) ?: false)
//
//        Assert.assertTrue(activity.byteRequired == 2.toByte())
//        Assert.assertTrue(activity.byteArrayRequired[0] == 2.toByte()
//                && activity.byteArrayRequired[1] == (-2).toByte())
//        Assert.assertTrue(activity.byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
//                && activity.byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())
//
//        Assert.assertTrue(activity.charRequired == 'a')
//        Assert.assertTrue(activity.charArrayRequired[0] == 'a'
//                && activity.charArrayRequired[1] == 'b')
//        Assert.assertTrue(activity.charArrayOptional?.get(0) ?: 0.toByte() == 'b'
//                && activity.charArrayOptional?.get(1) ?: 0.toByte() == 'a')
//
//        Assert.assertTrue(activity.shortRequired == 3.toShort())
//        Assert.assertTrue(activity.shortArrayRequired[0] == 3.toShort()
//                && activity.shortArrayRequired[1] == (-3).toShort())
//        Assert.assertTrue(activity.shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
//                && activity.shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())
//
//        Assert.assertTrue(activity.floatRequired == 4f)
//        Assert.assertTrue(activity.floatArrayRequired[0] == 4f
//                && activity.floatArrayRequired[1] == (-4f))
//        Assert.assertTrue(activity.floatArrayOptional?.get(0) ?: 0f == (-4f)
//                && activity.floatArrayOptional?.get(1) ?: 0f == 4f)
//
//        Assert.assertTrue(activity.longRequired == 1000L)
//        Assert.assertTrue(activity.longArrayRequired[0] == 1000L
//                && activity.longArrayRequired[1] == (-1000L))
//        Assert.assertTrue(activity.longArrayOptional?.get(0) ?: 0L == (-1000L)
//                && activity.longArrayOptional?.get(1) ?: 0L == 1000L)
//
//        Assert.assertTrue(activity.intRequired == 500)
//        Assert.assertTrue(activity.intArrayRequired[0] == 500
//                && activity.intArrayRequired[1] == (-500))
//        Assert.assertTrue(activity.intArrayOptional?.get(0) ?: 0 == (-500)
//                && activity.intArrayOptional?.get(1) ?: 0 == 500)
//        Assert.assertTrue(activity.intArrayListRequired[0] == 500
//                && activity.intArrayListRequired[1] == (-500))
//        Assert.assertTrue(activity.intArrayListOptional?.get(0) ?: 0 == (-500)
//                && activity.intArrayListOptional?.get(1) ?: 0 == 500)
//
//        Assert.assertTrue(activity.doubleRequired == 6.toDouble())
//        Assert.assertTrue(activity.doubleArrayRequired[0] == 6.toDouble()
//                && activity.doubleArrayRequired[1] == (-6).toDouble())
//        Assert.assertTrue(activity.doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
//                && activity.doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())
//
//        Assert.assertTrue(activity.stringRequired == "stringRequired")
//        Assert.assertTrue(activity.stringOptional == "stringOptional")
//        Assert.assertTrue(activity.stringArrayRequired[0] == "stringRequired"
//                && activity.stringArrayRequired[1] == "stringOptional")
//        Assert.assertTrue(activity.stringArrayOptional?.get(0) == "stringOptional"
//                && activity.stringArrayOptional?.get(1) == "stringRequired")
//        Assert.assertTrue(activity.stringArrayListRequired[0] == "stringRequired"
//                && activity.stringArrayListRequired[1] == "stringOptional")
//        Assert.assertTrue(activity.stringArrayListOptional?.get(0) == "stringOptional"
//                && activity.stringArrayListOptional?.get(1) == "stringRequired")
//
//        Assert.assertTrue(activity.charSequenceRequired == "stringRequired")
//        Assert.assertTrue(activity.charSequenceOptional == "stringOptional")
//        Assert.assertTrue(activity.charSequenceArrayRequired[0] == "stringRequired"
//                && activity.stringArrayRequired[1] == "stringOptional")
//        Assert.assertTrue(activity.charSequenceArrayOptional?.get(0) == "stringOptional"
//                && activity.stringArrayOptional?.get(1) == "stringRequired")
//
//        Assert.assertTrue(activity.parcelableRequired == TestParcelable("parcelableRequired"))
//        Assert.assertTrue(activity.parcelableOptional == TestParcelable("parcelableOptional"))
//        Assert.assertTrue(activity.parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
//                && activity.parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
//        Assert.assertTrue(activity.parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
//                && activity.parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
//        Assert.assertTrue(activity.parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
//                && activity.parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
//        Assert.assertTrue(activity.parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
//                && activity.parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))
//
//        Assert.assertTrue(activity.serializableRequired == TestSerializable("serializableRequired"))
//        Assert.assertTrue(activity.serializableOptional == TestSerializable("serializableOptional"))
//
//        Assert.assertTrue(activity.bundleRequired.getString("bundle") == "bundleRequired")
//        Assert.assertTrue(activity.bundleOptional?.getString("bundle") == "bundleOptional")
//    }
//}
