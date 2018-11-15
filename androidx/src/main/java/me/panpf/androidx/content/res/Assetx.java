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

package me.panpf.androidx.content.res;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import me.panpf.javax.io.Streamx;

/**
 * Asset resource-related tool methods
 */
public class Assetx {

    private Assetx() {
    }

    @NonNull
    public static InputStream openInput(@NonNull Context context, @NonNull String fileName) throws IOException {
        return context.getAssets().open(fileName);
    }

    @NonNull
    public static byte[] readBytes(@NonNull Context context, @NonNull String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return Streamx.readBytes(inputStream);
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @NonNull
    public static String readText(@NonNull Context context, @NonNull String fileName, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return Streamx.readText(Streamx.bufferedReader(inputStream, charset));
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @NonNull
    public static String readText(@NonNull Context context, @NonNull String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return Streamx.readText(Streamx.bufferedReader(inputStream));
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @NonNull
    public static List<String> readLines(@NonNull Context context, @NonNull String fileName, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return Streamx.readLines(Streamx.bufferedReader(inputStream, charset));
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @NonNull
    public static List<String> readLines(@NonNull Context context, @NonNull String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return Streamx.readLines(Streamx.bufferedReader(inputStream));
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull String fileName, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return BitmapFactory.decodeStream(inputStream, outPadding, options);
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        try {
            return BitmapFactory.decodeStream(inputStream);
        } finally {
            Streamx.closeQuietly(inputStream);
        }
    }
}