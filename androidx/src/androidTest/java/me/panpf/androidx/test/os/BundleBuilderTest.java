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

package me.panpf.androidx.test.os;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.test.runner.AndroidJUnit4;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.Arrays;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.os.BundleBuilder;
import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Premisex;

@SuppressWarnings("UnnecessaryBoxing")
@RunWith(AndroidJUnit4.class)
public class BundleBuilderTest {

    @Test
    public void testBase() {
        Bundle bundle1 = new Bundle();
        bundle1.putString("bundle1", "7");
        bundle1.putString("bundle2", "8");

        BundleBuilder builder = new BundleBuilder()
                .putByte("byte", (byte) 1)
                .putByteArray("byteArray", new byte[]{(byte) 1, (byte) 2})
                .putShort("short", (short) 1)
                .putShortArray("shortArray", new short[]{(short) 1, (short) 2})
                .putChar("char", (char) 1)
                .putCharArray("charArray", new char[]{(char) 1, (char) 2})
                .putInt("int", 1)
                .putIntArray("intArray", new int[]{1, 2})
                .putIntegerArrayList("intArrayList", Collectionx.arrayListOf(Integer.valueOf(1), Integer.valueOf(2)))
                .putLong("long", 1)
                .putLongArray("longArray", new long[]{1, 2})
                .putFloat("float", 1f)
                .putFloatArray("floatArray", new float[]{1f, 2f})
                .putDouble("double", 1f)
                .putDoubleArray("doubleArray", new double[]{1.0, 2.0})
                .putString("string", "1")
                .putStringArray("stringArray", new String[]{"1", "2"})
                .putStringArrayList("stringArrayList", Collectionx.arrayListOf("1", "2"))
                .putCharSequence("charSequence", "1")
                .putCharSequenceArray("charSequenceArray", new CharSequence[]{"1", "2"})
                .putCharSequenceArrayList("charSequenceArrayList", Collectionx.arrayListOf((CharSequence) "1", "2"))
                .putBoolean("boolean", true)
                .putBooleanArray("booleanArray", new boolean[]{false, true})
                .putBundle("bundle", new BundleBuilder().putString("key", "4").build())
                .putAll(bundle1);
        if (Androidx.isAtLeastL()) {
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("persistableBundle1", "5");
            persistableBundle.putString("persistableBundle2", "6");
            builder.putAll(persistableBundle);
        }
        Bundle bundle = builder.build();

        Assert.assertEquals(Androidx.isAtLeastL() ? 28 : 26, bundle.size());

        Assert.assertEquals((byte) 1, bundle.getByte("byte"));
        Assert.assertArrayEquals(new byte[]{(byte) 1, (byte) 2}, bundle.getByteArray("byteArray"));

        Assert.assertEquals((short) 1, bundle.getShort("short"));
        Assert.assertArrayEquals(new short[]{(short) 1, (short) 2}, bundle.getShortArray("shortArray"));

        Assert.assertEquals((char) 1, bundle.getChar("char"));
        Assert.assertArrayEquals(new char[]{(char) 1, (byte) 2}, bundle.getCharArray("charArray"));

        Assert.assertEquals(1, bundle.getInt("int"));
        Assert.assertArrayEquals(new int[]{1, 2}, bundle.getIntArray("intArray"));
        Assert.assertEquals(Collectionx.joinToArrayString(Collectionx.arrayListOf(Integer.valueOf(1), Integer.valueOf(2))), Collectionx.joinToArrayString(bundle.getIntegerArrayList("intArrayList")));

        Assert.assertEquals(1, bundle.getLong("long"));
        Assert.assertArrayEquals(new long[]{1, 2}, bundle.getLongArray("longArray"));

        Assert.assertEquals(1, bundle.getFloat("float"), 0f);
        Assert.assertArrayEquals(new float[]{1f, 2f}, bundle.getFloatArray("floatArray"), 0f);

        Assert.assertEquals(1, bundle.getDouble("double"), 0.0);
        Assert.assertArrayEquals(new double[]{1.0, 2.0}, bundle.getDoubleArray("doubleArray"), 0.0);

        Assert.assertEquals("1", bundle.getString("string"));
        Assert.assertArrayEquals(new String[]{"1", "2"}, bundle.getStringArray("stringArray"));
        Assert.assertEquals(Collectionx.joinToArrayString(Collectionx.arrayListOf("1", "2")), Collectionx.joinToArrayString(bundle.getStringArrayList("stringArrayList")));

        Assert.assertEquals("1", bundle.getCharSequence("charSequence"));
        Assert.assertArrayEquals(new CharSequence[]{"1", "2"}, bundle.getCharSequenceArray("charSequenceArray"));
        Assert.assertEquals(Collectionx.joinToArrayString(Collectionx.arrayListOf("1", "2")), Collectionx.joinToArrayString(bundle.getCharSequenceArrayList("charSequenceArrayList")));

        Assert.assertTrue(bundle.getBoolean("boolean"));
        Assert.assertArrayEquals(new boolean[]{false, true}, bundle.getBooleanArray("booleanArray"));

        Assert.assertEquals("4", Premisex.requireNotNull(bundle.getBundle("bundle")).getString("key"));
        if (Androidx.isAtLeastL()) {
            Assert.assertEquals("5", bundle.getString("persistableBundle1"));
            Assert.assertEquals("6", bundle.getString("persistableBundle2"));
        }
        Assert.assertEquals("7", bundle.getString("bundle1"));
        Assert.assertEquals("8", bundle.getString("bundle2"));
    }

    @Test
    public void testSize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = new BundleBuilder()
                    .putSize("size", new Size(1, 2))
                    .putSizeF("sizeF", new SizeF(1f, 2f))
                    .build();

            Assert.assertEquals("1x2", Premisex.requireNotNull(bundle.getSize("size")).toString());
            Assert.assertEquals("1.0x2.0", Premisex.requireNotNull(bundle.getSizeF("sizeF")).toString());
        }
    }

    @Test
    public void testParcelableAndSerializable() {
        SparseArray<TestParcelable> sparseArray = new SparseArray<>();
        sparseArray.put(1, new TestParcelable("1"));
        sparseArray.put(2, new TestParcelable("2"));
        Bundle bundle = new BundleBuilder()
                .putParcelable("parcelable", new TestParcelable("1"))
                .putParcelableArray("parcelableArray", Arrayx.arrayOf(new TestParcelable("1"), new TestParcelable("2")))
                .putParcelableArrayList("parcelableArrayList", Collectionx.arrayListOf(new TestParcelable("1"), new TestParcelable("2")))
                .putSparseParcelableArray("sparseParcelableArray", sparseArray)
                .putSerializable("serializable", new TestParcelable("3"))
                .build();
        Assert.assertEquals("1", Premisex.requireNotNull((TestParcelable) bundle.getParcelable("parcelable")).getKey());
        Assert.assertArrayEquals(Arrayx.arrayOf(new TestParcelable("1"), new TestParcelable("2")), bundle.getParcelableArray("parcelableArray"));
        Assert.assertEquals(Collectionx.joinToArrayString(Collectionx.arrayListOf(new TestParcelable("1"), new TestParcelable("2"))),
                Collectionx.joinToArrayString(bundle.getParcelableArrayList("parcelableArrayList")));

        SparseArray<TestParcelable> sparseArray2 = bundle.getSparseParcelableArray("sparseParcelableArray");
        Assert.assertNotNull(sparseArray2);
        Assert.assertEquals("1", sparseArray2.get(1).getKey());
        Assert.assertEquals("2", sparseArray2.get(2).getKey());

        Assert.assertEquals("3", Premisex.requireNotNull((TestParcelable) bundle.getSerializable("serializable")).getKey());
    }

    @Test
    public void testRepeatBuild() {

    }

    @Test
    public void testRemove() {
        Bundle bundle = new BundleBuilder().putString("string", "1").build();
        Assert.assertNotNull(bundle.getString("string"));

        Bundle bundle2 = new BundleBuilder().putString("string", "1").remove("string").build();
        Assert.assertNull(bundle2.getString("string"));
    }

    @Test
    public void testSetClassLoader() {
        Bundle bundle = new BundleBuilder().build();
        Assert.assertEquals(Bundle.class.getClassLoader().getClass().getName(), bundle.getClassLoader().getClass().getName());

        Bundle bundle2 = new BundleBuilder().setClassLoader(BundleBuilderTest.class.getClassLoader()).build();
        Assert.assertEquals(BundleBuilderTest.class.getClassLoader().getClass().getName(), bundle2.getClassLoader().getClass().getName());
    }

    public static class TestParcelable implements Parcelable, Serializable {
        public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
            @Override
            public TestParcelable createFromParcel(Parcel in) {
                return new TestParcelable(in);
            }

            @Override
            public TestParcelable[] newArray(int size) {
                return new TestParcelable[size];
            }
        };
        private String key;

        public TestParcelable(String key) {
            this.key = key;
        }

        protected TestParcelable(Parcel in) {
            key = in.readString();
        }

        public String getKey() {
            return key;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(key);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestParcelable that = (TestParcelable) o;
            return Stringx.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new String[]{key});
        }
    }
}
