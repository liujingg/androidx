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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Bitmapx;
import me.panpf.androidx.util.Dimenx;
import me.panpf.androidx.util.Textx;

@RunWith(AndroidJUnit4.class)
public class TextxTest {

    @Test
    public void testTextToBitmap() {
        Context context = InstrumentationRegistry.getContext();
        Bitmap bitmap = null;
        Bitmap bitmapCompact = null;
        Bitmap icon = null;
        Bitmap bitmapIcon = null;
        Bitmap bitmapIconCompact = null;
        try {
            bitmap = Textx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14));
            Assert.assertFalse(bitmap.isRecycled());

            bitmapCompact = Textx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), true);
            Assert.assertFalse(bitmapCompact.isRecycled());

            icon = Bitmapx.readBitmap(context.getResources(), me.panpf.androidx.test.R.drawable.ic_opera);
            bitmapIcon = Textx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), icon);
            Assert.assertFalse(bitmapIcon.isRecycled());

            bitmapIconCompact = Textx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), true, icon);
            Assert.assertFalse(bitmapIconCompact.isRecycled());

            Assert.assertTrue(bitmap.getHeight() > bitmapCompact.getHeight());
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (bitmapCompact != null) {
                bitmapCompact.recycle();
            }
            if (icon != null) {
                icon.recycle();
            }
            if (bitmapIcon != null) {
                bitmapIcon.recycle();
            }
            if (bitmapIconCompact != null) {
                bitmapIconCompact.recycle();
            }
        }
    }

    @Test
    public void testChangeColor() {
        Assert.assertEquals("<font color=\"green\">警告</font>", Textx.changeColorByHtml("警告", "green"));
        Assert.assertEquals("<font color=\"red\">警告</font>", Textx.changeColorToRedByHtml("警告"));

        Assert.assertEquals("郑重<font color=\"green\">警告</font>如下郑重<font color=\"green\">警告</font>如下", Textx.changeKeywordColorByHtml("郑重警告如下郑重警告如下", "警告", "green"));
        Assert.assertEquals("郑重<font color=\"red\">警告</font>如下郑重<font color=\"red\">警告</font>如下", Textx.changeKeywordColorToRedByHtml("郑重警告如下郑重警告如下", "警告"));

        Assert.assertNotNull(Textx.changeColorBySpannable("警告", Color.GREEN));
        Assert.assertNotNull(Textx.changeColorToRedBySpannable("警告"));

        Assert.assertNotNull(Textx.changeKeywordColorBySpannable("郑重警告如下郑重警告如下", "警告", Color.GREEN));
        Assert.assertNotNull(Textx.changeKeywordColorToRedBySpannable("郑重警告如下郑重警告如下", "警告"));
    }
}
