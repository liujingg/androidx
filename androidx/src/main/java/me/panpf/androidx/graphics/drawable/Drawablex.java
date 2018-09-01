package me.panpf.androidx.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Drawablex {
    public static Bitmap toBitmap(@NotNull Drawable drawable, @Nullable Bitmap.Config config, @Nullable Bitmap reuseBitmap) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        if (config == null) config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = reuseBitmap != null ? reuseBitmap : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), config);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
}
