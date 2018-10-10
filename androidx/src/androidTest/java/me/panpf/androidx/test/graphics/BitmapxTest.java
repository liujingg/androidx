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

package me.panpf.androidx.test.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Bitmapx;
import me.panpf.androidx.graphics.Colorx;
import me.panpf.androidx.graphics.drawable.Drawablex;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class BitmapxTest {

    @Test
    public void testModifyColorBitmap() {
        Bitmap sourceBitmap = Bitmapx.createByColor(100, 100, Color.parseColor("#FF0000"));

        Drawable drawable = Bitmapx.toDrawableByColor(sourceBitmap, Color.parseColor("#0000FF"));
        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);

        sourceBitmap.recycle();
        bitmap.recycle();
    }

    @Test
    public void testCircular() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap rectBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), me.panpf.androidx.test.R.drawable.rect));

        Bitmap circular1Bitmap = Bitmapx.circular(rectBitmap);
        circular1Bitmap.recycle();

        Bitmap circular2Bitmap = Bitmapx.circular(rectBitmap, Bitmap.Config.RGB_565);
        circular2Bitmap.recycle();

        Bitmap circular3Bitmap = Bitmapx.circular(rectBitmap, rectBitmap.getHeight() / 2);
        circular3Bitmap.recycle();

        Bitmap circular4Bitmap = Bitmapx.circular(rectBitmap, rectBitmap.getHeight() / 2, Bitmap.Config.RGB_565);
        circular4Bitmap.recycle();

        Bitmap circular5Bitmap = Bitmapx.circularTo(rectBitmap, Bitmap.createBitmap(rectBitmap.getHeight() / 2, rectBitmap.getHeight() / 2, Bitmap.Config.RGB_565));
        circular5Bitmap.recycle();

        rectBitmap.recycle();
    }

    @Test
    public void testCenterCrop() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap rectBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), me.panpf.androidx.test.R.drawable.rect));

        Bitmap centerCrop1Bitmap = Bitmapx.centerCrop(rectBitmap, rectBitmap.getHeight() / 2, rectBitmap.getHeight());
        centerCrop1Bitmap.recycle();

        Bitmap centerCrop2Bitmap = Bitmapx.centerCrop(rectBitmap, rectBitmap.getHeight() / 2, rectBitmap.getHeight(), Bitmap.Config.RGB_565);
        centerCrop2Bitmap.recycle();

        Bitmap centerCrop3Bitmap = Bitmapx.centerCropTo(rectBitmap, Bitmap.createBitmap(rectBitmap.getHeight() / 2, rectBitmap.getHeight(), Bitmap.Config.RGB_565));
        centerCrop3Bitmap.recycle();

        rectBitmap.recycle();
    }

    @Test
    public void testTint() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap operaBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), me.panpf.androidx.test.R.drawable.ic_opera));

        Bitmap centerCrop1Bitmap = Bitmapx.tint(operaBitmap, Colorx.YELLOW);
        centerCrop1Bitmap.recycle();

        operaBitmap.recycle();
    }
}
