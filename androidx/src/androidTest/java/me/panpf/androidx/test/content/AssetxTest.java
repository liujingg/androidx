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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import me.panpf.androidx.content.res.Assetx;
import me.panpf.androidx.graphics.Bitmapx;
import me.panpf.javax.io.Streamx;
import me.panpf.javax.collections.Collectionx;

@RunWith(AndroidJUnit4.class)
public class AssetxTest {

    private static final String TEST_TXT_CONTENT = "1\n2\n3\n4\n5\n6\n7\n8\n9";

    @Test
    public void testOpenInput() {
        Context context = InstrumentationRegistry.getContext();

        InputStream inputStream = null;
        try {
            inputStream = Assetx.openInput(context, "test.txt");
            Assert.assertNotNull(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            Streamx.closeQuietly(inputStream);
        }

        try {
            Assetx.openInput(context, "test_no_file.txt");
            Assert.fail();
        } catch (IOException ignored) {
        }
    }

    @Test
    public void testReadBytes() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals(TEST_TXT_CONTENT, new String(Assetx.readBytes(context, "test.txt")));
    }

    @Test
    public void testReadTest() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals(TEST_TXT_CONTENT, Assetx.readText(context, "test.txt"));
        Assert.assertEquals(TEST_TXT_CONTENT, Assetx.readText(context, "test.txt", Charset.defaultCharset()));
    }

    @Test
    public void testReadLines() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals(TEST_TXT_CONTENT.replace("\n", ","), Collectionx.joinToString(Assetx.readLines(context, "test.txt"), ","));
        Assert.assertEquals(TEST_TXT_CONTENT.replace("\n", ","), Collectionx.joinToString(Assetx.readLines(context, "test.txt", Charset.defaultCharset()), ","));
    }

    @Test
    public void testReadBitmap() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        Bitmap bitmap = null;
        try {
            bitmap = Assetx.readBitmap(context, "test.png");
            Assert.assertNotNull(bitmap);
            Assert.assertFalse(bitmap.isRecycled());
            Assert.assertEquals(128, bitmap.getWidth());
            Assert.assertEquals(128, bitmap.getHeight());
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }

        Bitmap bitmap2 = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Rect outPadding = new Rect(0, 0, 0, 0);
            bitmap2 = Assetx.readBitmap(context, "test.png", outPadding, options);
            Assert.assertNotNull(bitmap2);
            Assert.assertFalse(bitmap2.isRecycled());
            Assert.assertEquals(Bitmapx.calculateSamplingSize(128, options.inSampleSize), bitmap2.getWidth());
            Assert.assertEquals(Bitmapx.calculateSamplingSize(128, options.inSampleSize), bitmap2.getHeight());
            Assert.assertEquals(new Rect(-1, -1, -1, -1).toShortString(), outPadding.toShortString());
        } finally {
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
        }
    }
}
