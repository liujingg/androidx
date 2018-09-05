/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.panpf.javax.io.Filex;
import me.panpf.javax.io.IOStreamx;

public class Bitmapx {

    public static Bitmap centerCrop(Bitmap srcBitmap, int outWidth, int outHeight, Bitmap.Config outConfig) {
        float widthScale = (float) outWidth / srcBitmap.getWidth();
        float heightScale = (float) outHeight / srcBitmap.getHeight();
        float finalScale = Math.max(widthScale, heightScale);
        float dx = (srcBitmap.getWidth() * finalScale - outWidth) / 2;
        float dy = (srcBitmap.getHeight() * finalScale - outHeight) / 2;

        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        matrix.postScale(finalScale, finalScale);
        matrix.postTranslate(-dx, -dy);

        Bitmap newBitmap = Bitmap.createBitmap(outWidth, outHeight, outConfig);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(srcBitmap, matrix, paint);

        return newBitmap;
    }

    public static Bitmap tint(Bitmap bitmap, int color) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        Paint mPaint = new Paint();
        float mRed = Color.red(color);
        float mGreen = Color.green(color);
        float mBlue = Color.blue(color);
        float[] src = new float[]{
                0, 0, 0, 0, mRed,
                0, 0, 0, 0, mGreen,
                0, 0, 0, 0, mBlue,
                0, 0, 0, 1, 0};
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(src);
        mPaint.setColorFilter(new ColorMatrixColorFilter(src));
        canvas.drawBitmap(bitmap, new Matrix(), mPaint);
        return newBitmap;
    }

    public static Bitmap makeBitmapByColor(int color, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);
        return bitmap;
    }

    public static byte[] toByteArray(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, outputStream);
        return outputStream.toByteArray();
    }

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
            IOStreamx.safeClose(bos);
        }
    }

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
    public static Bitmap readBitmap(@NonNull byte[] data, int offset, int length) {
        return BitmapFactory.decodeByteArray(data, offset, length);
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
    public static Bitmap readBitmap(@NonNull Resources resources, int resId, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Resources resources, int resId) {
        return BitmapFactory.decodeResource(resources, resId);
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Resources res, @Nullable TypedValue value, @Nullable InputStream is, @Nullable Rect pad, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResourceStream(res, value, is, pad, options);
    }
}
