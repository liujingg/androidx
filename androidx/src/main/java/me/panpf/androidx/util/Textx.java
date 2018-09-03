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

import org.jetbrains.annotations.NotNull;

import me.panpf.androidx.graphics.Paintx;

@SuppressWarnings("WeakerAccess")
public class Textx {

    /**
     * 获取一张文字位图
     *
     * @param text       文字
     * @param textColor  文字颜色
     * @param textSize   文字大小
     * @param leftBitmap 可以在文字的左边放置一张图片
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize, @Nullable Bitmap leftBitmap) {
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);

        float textWidth = Paintx.getTextWidth(text, paint);
        float textHeight = Paintx.getTextHeight(paint);

        int newBitmapWidth = textWidth % 1 == 0 ? (int) textWidth : (int) textWidth + 1;
        int newBitmapHeight = textHeight % 1 == 0 ? (int) textHeight : (int) textHeight + 1;

        if (leftBitmap != null) {
            newBitmapWidth += leftBitmap.getWidth();
            newBitmapHeight = leftBitmap.getHeight() > newBitmapHeight ? leftBitmap.getHeight() : newBitmapHeight;
        }

        Bitmap bitmap = Bitmap.createBitmap(newBitmapWidth, newBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (leftBitmap != null) {
            canvas.drawBitmap(leftBitmap, 0, (newBitmapHeight - leftBitmap.getHeight()) / 2, paint);
            canvas.drawText(text, leftBitmap.getWidth(), (newBitmapHeight - textHeight) / 2 + Paintx.getTextLeading(paint), paint);
        } else {
            canvas.drawText(text, 0, (newBitmapHeight - textHeight) / 2 + Paintx.getTextLeading(paint), paint);
        }
        canvas.save();

        return bitmap;
    }

    /**
     * 获取一张文字位图
     *
     * @param text      文字
     * @param textColor 文字颜色
     * @param textSize  文字大小
     * @return 文字位图
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize) {
        return textToBitmap(text, textColor, textSize, null);
    }

    /**
     * 使用 Html 的方式给字符串添加颜色标记
     */
    public static String toHtmlColorFlag(@NotNull String string, @NonNull String color) {
        return "<font color=\"" + color + "\">" + string + "</font>";
    }

    /**
     * 使用 Html 的方式给字符串添加红色标记
     */
    public static String toHtmlRedFlag(@NotNull String string) {
        return toHtmlColorFlag(string, "red");
    }

    /**
     * 使用 Html 的方式将字符串中所有关键字标记颜色
     *
     * @param sourceString 字符串
     * @param keyword      关键字
     * @param color        html 支持的颜色
     */
    public static String keywordMadeColorByHtml(@NonNull String sourceString, @NonNull String keyword, @NonNull String color) {
        return sourceString.replaceAll(keyword, "<font color=\"" + color + "\">" + keyword + "</font>");
    }

    /**
     * 使用 Html 的方式将字符串中所有关键字标记成红色
     *
     * @param sourceString 字符串
     * @param keyword      关键字
     */
    public static String keywordMadeRedByHtml(@NonNull String sourceString, @NonNull String keyword) {
        return keywordMadeColorByHtml(sourceString, keyword, "red");
    }

    /**
     * 使用 Spannable 的方式将字符串中所有的关键字标记颜色
     *
     * @param sourceString 字符串
     * @param keyword      关键字
     * @param color        颜色
     */
    public static SpannableStringBuilder keywordMadeColorBySpannable(@NonNull String sourceString, @NonNull String keyword, int color) {
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
     * 使用 Spannable 的方式将字符串中所有的关键字标记成红色
     *
     * @param sourceString 字符串
     * @param keyword      关键字
     */
    public static SpannableStringBuilder keywordMadeRedBySpannable(@NonNull String sourceString, @NonNull String keyword) {
        return keywordMadeColorBySpannable(sourceString, keyword, Color.RED);
    }
}
