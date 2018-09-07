package me.panpf.androidx.test.graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Bitmapx;
import me.panpf.androidx.graphics.drawable.Drawablex;

@RunWith(AndroidJUnit4.class)
public class BitmapTest {

    @Test
    public void testModifyColorBitmap() {
        Bitmap sourceBitmap = Bitmapx.createByColor(100, 100, Color.parseColor("#FF0000"));

        Drawable drawable = Bitmapx.toDrawableByColor(sourceBitmap, Color.parseColor("#0000FF"));
        Bitmap bitmap = Drawablex.toBitmapWithIntrinsicSize(drawable);

        sourceBitmap.recycle();
        bitmap.recycle();
    }
}
