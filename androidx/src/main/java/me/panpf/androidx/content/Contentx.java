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

import me.panpf.javax.io.IOStreamx;

public class Contentx {

    @Nullable
    public static InputStream openInput(@NonNull Context context, @NonNull Uri uri) throws IOException {
        return context.getContentResolver().openInputStream(uri);
    }

    @Nullable
    public static byte[] readBytes(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? IOStreamx.readBytes(inputStream) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static String readText(@NonNull Context context, @NonNull Uri uri, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? IOStreamx.readText(IOStreamx.bufferedReader(inputStream, charset)) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static String readText(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? IOStreamx.readText(IOStreamx.bufferedReader(inputStream)) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static List<String> readLines(@NonNull Context context, @NonNull Uri uri, @NonNull Charset charset) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? IOStreamx.readLines(IOStreamx.bufferedReader(inputStream, charset)) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static List<String> readLines(@NonNull Context context, @NonNull Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? IOStreamx.readLines(IOStreamx.bufferedReader(inputStream)) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull Uri uri, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) throws FileNotFoundException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? BitmapFactory.decodeStream(inputStream, outPadding, options) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }

    @Nullable
    public static Bitmap readBitmap(@NonNull Context context, @NonNull Uri uri) throws FileNotFoundException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        try {
            return inputStream != null ? BitmapFactory.decodeStream(inputStream) : null;
        } finally {
            IOStreamx.safeClose(inputStream);
        }
    }
}
