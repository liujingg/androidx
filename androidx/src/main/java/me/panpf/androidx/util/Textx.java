package me.panpf.androidx.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
     * @return 文字位图
     */
    @NonNull
    public static Bitmap textToBitmap(@NonNull String text, int textColor, float textSize, @Nullable Bitmap leftBitmap) {
        // 创建并初始化画笔
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);   //  去除锯齿
        paint.setFilterBitmap(true);    //  对文字进行滤波处理，增强绘制效果

        // 计算要绘制的文字的宽和高
        float textWidth = Paintx.getTextWidth(text, paint);
        float textHeight = Paintx.getTextHeight(paint);

        // 计算图片的宽高
        int newBimapWidth = textWidth % 1 == 0 ? (int) textWidth : (int) textWidth + 1;
        int newBimapHeight = textHeight % 1 == 0 ? (int) textHeight : (int) textHeight + 1;

        if (leftBitmap != null) {
            newBimapWidth += leftBitmap.getWidth();
            newBimapHeight = leftBitmap.getHeight() > newBimapHeight ? leftBitmap.getHeight() : newBimapHeight;
        }

        Bitmap bitmap = Bitmap.createBitmap(newBimapWidth, newBimapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (leftBitmap != null) {
            canvas.drawBitmap(leftBitmap, 0, (newBimapHeight - leftBitmap.getHeight()) / 2, paint);
            canvas.drawText(text, leftBitmap.getWidth(), (newBimapHeight - textHeight) / 2 + Paintx.getTextLeading(paint), paint);
        } else {
            canvas.drawText(text, 0, (newBimapHeight - textHeight) / 2 + Paintx.getTextLeading(paint), paint);
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
     * 使用 Html 的方式给字符串添加红色标记
     */
    public static String toHtmlRedFlag(String string) {
        return "<font color=\"red\">" + string + "</font>";
    }

    /**
     * 使用 Html 的方式将给定的字符串中所有给定的关键字标红
     *
     * @param sourceString 给定的字符串
     * @param keyword      给定的关键字
     */
    public static String keywordMadeRedByHtml(@NonNull String sourceString, @NonNull String keyword) {
        return sourceString.replaceAll(keyword, "<font color=\"red\">" + keyword + "</font>");
    }
}
