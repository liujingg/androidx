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
import android.graphics.drawable.GradientDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.drawable.Drawablex;
import me.panpf.androidx.test.R;

@RunWith(AndroidJUnit4.class)
public class DrawablexTest {

    @Test
    public void testIntrinsic() {
        Context context = InstrumentationRegistry.getContext();

        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_opera);
        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);
        bitmap.recycle();
    }

    @Test
    public void testIntrinsicReuse() {
        Context context = InstrumentationRegistry.getContext();

        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_opera);

        Bitmap reuseBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable, reuseBitmap);
        bitmap.recycle();
    }

    @Test
    public void testIntrinsicConfig() {
        Context context = InstrumentationRegistry.getContext();

        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_opera);

        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable, Bitmap.Config.RGB_565);
        bitmap.recycle();
    }

    @Test
    public void testIntrinsicError() {
        GradientDrawable drawable = new GradientDrawable();

        Bitmap bitmap = null;
        try {
            bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNull(bitmap);
    }

    @Test
    public void testBounds() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));
        drawable.setBounds(0, 0, 100, 100);
        Bitmap bitmap = Drawablex.toBitmapWithBoundsSize(drawable);
        bitmap.recycle();
    }

    @Test
    public void testBoundsReuse() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));
        drawable.setBounds(0, 0, 100, 100);
        Bitmap reuseBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Drawablex.toBitmapWithBoundsSize(drawable, reuseBitmap);
        bitmap.recycle();
    }

    @Test
    public void testConfig() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));
        drawable.setBounds(0, 0, 100, 100);
        Bitmap bitmap = Drawablex.toBitmapWithBoundsSize(drawable, Bitmap.Config.RGB_565);
        bitmap.recycle();
    }

    @Test
    public void testError() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));

        Bitmap bitmap = null;
        try {
            bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNull(bitmap);
    }

    @Test
    public void testOther() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));
        drawable.setBounds(50, 50, 100, 100);
        Bitmap bitmap = Drawablex.toBitmapWithBoundsSize(drawable, Bitmap.Config.RGB_565);
        bitmap.recycle();
    }

    @Test
    public void testOther2() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#88FF0000"));
        drawable.setBounds(-50, -50, 100, 100);
        Bitmap bitmap = Drawablex.toBitmapWithBoundsSize(drawable, Bitmap.Config.RGB_565);
        bitmap.recycle();
    }

    @Test
    public void testChangeColor() {
        Context context = InstrumentationRegistry.getContext();

        Drawable drawable = Drawablex.changeColor(context.getResources().getDrawable(R.drawable.ic_opera), Color.parseColor("#0000FF"));
        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);
        bitmap.recycle();

        Drawable drawable2 = Drawablex.changeResDrawableColor(context, R.drawable.ic_opera, Color.parseColor("#0000FF"));
        Bitmap bitmap2 = Drawablex.toBitmapWithIntrinsicSize(drawable2);
        bitmap2.recycle();
    }
}
