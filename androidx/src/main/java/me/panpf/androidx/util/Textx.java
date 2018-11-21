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

package me.panpf.androidx.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import me.panpf.androidx.graphics.Paintx;

@SuppressWarnings("WeakerAccess")
public class Textx {

    private Textx() {
    }

    /**
     * Text to Bitmap
     *
     * @param compact If true, use FontMetrics.descent - ascent to calculate the bitmap height, otherwise use FontMetrics.bottom - top
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize, boolean compact, @Nullable Bitmap leftIcon) {
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);

        float textWidth = Paintx.getTextWidth(text, paint);
        float textHeight = compact ? Paintx.getTextHeightCompact(paint) : Paintx.getTextHeight(paint);

        int newBitmapWidth = textWidth % 1 == 0 ? (int) textWidth : (int) textWidth + 1;
        int newBitmapHeight = textHeight % 1 == 0 ? (int) textHeight : (int) textHeight + 1;

        if (leftIcon != null) {
            newBitmapWidth += leftIcon.getWidth();
            newBitmapHeight = leftIcon.getHeight() > newBitmapHeight ? leftIcon.getHeight() : newBitmapHeight;
        }

        Bitmap bitmap = Bitmap.createBitmap(newBitmapWidth, newBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        float y = compact ? Paintx.getDrawTextVerticalCenterBaseLineCompact(paint, 0, newBitmapHeight) : Paintx.getDrawTextVerticalCenterBaseLine(paint, 0, newBitmapHeight);
        if (leftIcon != null) {
            canvas.drawBitmap(leftIcon, 0, (newBitmapHeight - leftIcon.getHeight()) / 2, paint);
            canvas.drawText(text, leftIcon.getWidth(), y, paint);
        } else {
            canvas.drawText(text, 0, y, paint);
        }
        canvas.save();

        return bitmap;
    }

    /**
     * Text to Bitmap
     *
     * @param compact If true, use FontMetrics.descent - ascent to calculate the bitmap height, otherwise use FontMetrics.bottom - top
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize, boolean compact) {
        return textToBitmap(text, textColor, textSize, compact, null);
    }

    /**
     * Text to Bitmap
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize, @Nullable Bitmap leftIcon) {
        return textToBitmap(text, textColor, textSize, false, leftIcon);
    }

    /**
     * Text to Bitmap
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize) {
        return textToBitmap(text, textColor, textSize, false, null);
    }


    /**
     * Modify the display color of a string using Html
     */
    public static String changeColorByHtml(@NonNull String string, @NonNull String color) {
        return "<font color=\"" + color + "\">" + string + "</font>";
    }

    /**
     * Modify the display red color of a string using Html
     */
    public static String changeColorToRedByHtml(@NonNull String string) {
        return changeColorByHtml(string, "red");
    }


    /**
     * Use Html to modify the display color of all specified keywords in a string
     */
    public static String changeKeywordColorByHtml(@NonNull String sourceString, @NonNull String keyword, @NonNull String color) {
        return sourceString.replaceAll(keyword, "<font color=\"" + color + "\">" + keyword + "</font>");
    }

    /**
     * Use Html to modify the display color of all specified keywords in a string
     */
    public static String changeKeywordColorToRedByHtml(@NonNull String sourceString, @NonNull String keyword) {
        return changeKeywordColorByHtml(sourceString, keyword, "red");
    }


    /**
     * Use Spannable to modify the display color of a string
     */
    public static SpannableStringBuilder changeColorBySpannable(@NonNull String sourceString, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(sourceString);
        builder.setSpan(new ForegroundColorSpan(color), 0, sourceString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /**
     * Use Spannable to modify the display color of a string
     */
    public static SpannableStringBuilder changeColorToRedBySpannable(@NonNull String sourceString) {
        return changeColorBySpannable(sourceString, Color.RED);
    }


    /**
     * Use Spannable to modify the display color of all specified keywords in a string
     */
    public static SpannableStringBuilder changeKeywordColorBySpannable(@NonNull String sourceString, @NonNull String keyword, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(sourceString);
        int fromIndex = 0;
        while (fromIndex < sourceString.length()) {
            int index = sourceString.indexOf(keyword, fromIndex);
            if (index != -1) {
                int endIndex = index + keyword.length();
                builder.setSpan(new ForegroundColorSpan(color), index, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                fromIndex = endIndex;
            } else {
                break;
            }
        }
        return builder;
    }

    /**
     * Use Spannable to modify the display color of all specified keywords in a string
     */
    public static SpannableStringBuilder changeKeywordColorToRedBySpannable(@NonNull String sourceString, @NonNull String keyword) {
        return changeKeywordColorBySpannable(sourceString, keyword, Color.RED);
    }
}
