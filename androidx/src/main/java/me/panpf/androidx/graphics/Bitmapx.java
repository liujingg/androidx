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

package me.panpf.androidx.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.panpf.javax.io.Filex;
import me.panpf.javax.io.Streamx;

public class Bitmapx {

    private Bitmapx() {
    }


    /* ************************************** read ******************************************  */


    @Nullable
    public static Bitmap readBitmap(@NonNull File file, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull File file) {
        return BitmapFactory.decodeFile(file.getPath());
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(inputStream, outPadding, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, int offset, int length, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(data, offset, length, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, int offset, int length) {
        return BitmapFactory.decodeByteArray(data, offset, length);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull FileDescriptor fileDescriptor, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, outPadding, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Resources resources, @DrawableRes int resId, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Resources resources, @DrawableRes int resId) {
        return BitmapFactory.decodeResource(resources, resId);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Resources res, @Nullable TypedValue value, @Nullable InputStream is, @Nullable Rect pad, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResourceStream(res, value, is, pad, options);
    }


    /* ************************************** create ******************************************  */


    public static Bitmap createByColor(int width, int height, @ColorInt int color, Bitmap.Config config) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);
        return bitmap;
    }

    public static Bitmap createByColor(int width, int height, @ColorInt int color) {
        return createByColor(width, height, color, Bitmap.Config.ARGB_8888);
    }


    /* ************************************** save ******************************************  */


    public static void writeToFile(Bitmap bm, File file, Bitmap.CompressFormat format, int quality) throws IOException {
        Filex.createNewFileOrThrow(file);
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
            throw e;
        }
        try {
            bm.compress(format, quality, bos);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            Streamx.closeQuietly(bos);
        }
    }


    /* ************************************** to ******************************************  */


    public static byte[] toByteArray(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * Change the color of the bitmap
     *
     * @param bitmap    Source bitmap
     * @param resources setting initial target density based on the display metrics of the resources.
     */
    @NonNull
    public static BitmapDrawable toDrawableByColor(@NonNull Bitmap bitmap, @ColorInt int color, @Nullable Resources resources) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        if (resources == null) {
            bitmapDrawable.setTargetDensity(bitmap.getDensity());
        }
        bitmapDrawable.setColorFilter(Colorx.createMatrixColorFilter(color));
        return bitmapDrawable;
    }

    /**
     * Change the color of the bitmap
     *
     * @param bitmap Source bitmap
     */
    @NonNull
    public static BitmapDrawable toDrawableByColor(@NonNull Bitmap bitmap, @ColorInt int color) {
        return toDrawableByColor(bitmap, color, null);
    }


    /* ************************************** process ******************************************  */


    @NonNull
    public static Bitmap circularTo(@NonNull Bitmap srcBitmap, @NonNull Bitmap dstBitmap) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFF0000);

        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawARGB(0, 0, 0, 0);

        float newBitmapRadius = Math.min(dstBitmap.getWidth(), dstBitmap.getHeight()) / 2;
        canvas.drawCircle(newBitmapRadius, newBitmapRadius, newBitmapRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Resizex.Result result = Resizex.calculator(srcBitmap.getWidth(), srcBitmap.getHeight(),
                dstBitmap.getWidth(), dstBitmap.getHeight(), ImageView.ScaleType.CENTER_CROP, false);
        canvas.drawBitmap(srcBitmap, result.srcRect, result.destRect, paint);

        return dstBitmap;
    }

    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, int newSize, @NonNull Bitmap.Config config) {
        return circularTo(srcBitmap, Bitmap.createBitmap(newSize, newSize, config));
    }

    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, int newSize) {
        return circularTo(srcBitmap, Bitmap.createBitmap(newSize, newSize, Bitmap.Config.ARGB_8888));
    }

    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, @NonNull Bitmap.Config config) {
        final int newBitmapSize = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        return circularTo(srcBitmap, Bitmap.createBitmap(newBitmapSize, newBitmapSize, config));
    }

    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap) {
        return circular(srcBitmap, Bitmap.Config.ARGB_8888);
    }


    @NonNull
    public static Bitmap centerCropTo(@NonNull Bitmap srcBitmap, @NonNull Bitmap dstBitmap) {
        Paint paint = new Paint();
        Canvas canvas = new Canvas(dstBitmap);

        Resizex.Result result = Resizex.calculator(srcBitmap.getWidth(), srcBitmap.getHeight(),
                dstBitmap.getWidth(), dstBitmap.getHeight(), ImageView.ScaleType.CENTER_CROP, false);
        canvas.drawBitmap(srcBitmap, result.srcRect, result.destRect, paint);

        return dstBitmap;
    }

    @NonNull
    public static Bitmap centerCrop(@NonNull Bitmap srcBitmap, int newWidth, int newHeight, @NonNull Bitmap.Config config) {
        return centerCropTo(srcBitmap, Bitmap.createBitmap(newWidth, newHeight, config));
    }

    @NonNull
    public static Bitmap centerCrop(@NonNull Bitmap srcBitmap, int newWidth, int newHeight) {
        return centerCropTo(srcBitmap, Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888));
    }


    @NonNull
    public static Bitmap tint(@NonNull Bitmap srcBitmap, @ColorInt int color) {
        Paint mPaint = new Paint();
        mPaint.setColorFilter(Colorx.createMatrixColorFilter(color));

        Bitmap newBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(srcBitmap, new Matrix(), mPaint);

        return newBitmap;
    }


    /* ************************************** other ******************************************  */


    public static int calculateSamplingSize(int size, int inSampleSize) {
        return (int) Math.ceil(size / (float) inSampleSize);
    }

    public static int calculateSamplingSizeForRegion(int size, int inSampleSize) {
        return (int) Math.floor(size / (float) inSampleSize);
    }
}
