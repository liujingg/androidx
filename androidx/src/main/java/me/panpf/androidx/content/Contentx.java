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

package me.panpf.androidx.content;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import me.panpf.javax.io.Streamx;

public class Contentx {

    private Contentx() {
    }

    @Nullable
    public static InputStream openInput(@NonNull Context context, @NonNull Uri uri) throws IOException {
        return context.getContentResolver().openInputStream(uri);
    }

    @Nullable
    public static byte[] readBytes(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? Streamx.readBytes(inputStream) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static String readText(@NonNull Context context, @NonNull Uri uri, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? Streamx.readText(Streamx.bufferedReader(inputStream, charset)) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static String readText(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? Streamx.readText(Streamx.bufferedReader(inputStream)) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static List<String> readLines(@NonNull Context context, @NonNull Uri uri, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? Streamx.readLines(Streamx.bufferedReader(inputStream, charset)) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static List<String> readLines(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? Streamx.readLines(Streamx.bufferedReader(inputStream)) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull Uri uri, @Nullable Rect outPadding,
                                    @Nullable BitmapFactory.Options options) throws FileNotFoundException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? BitmapFactory.decodeStream(inputStream, outPadding, options) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull Uri uri) throws FileNotFoundException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? BitmapFactory.decodeStream(inputStream) : null;
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }
}
