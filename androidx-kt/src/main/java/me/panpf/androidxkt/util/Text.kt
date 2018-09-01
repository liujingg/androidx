package me.panpf.androidxkt.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import me.panpf.androidxkt.graphics.getTextHeight
import me.panpf.androidxkt.graphics.getTextLeading
import me.panpf.androidxkt.graphics.getTextWidth


/**
 * 获取一张文字位图
 *
 * @param text       文字
 * @param textColor  文字颜色
 * @param textSize   文字大小
 * @param leftBitmap 可以在文字的左边放置一张图片
 * @return 文字位图
 */
@JvmOverloads
fun textToBitmap(text: String, textColor: Int, textSize: Float, leftBitmap: Bitmap? = null): Bitmap {
    // 创建并初始化画笔
    val paint = Paint()
    paint.color = textColor
    paint.textSize = textSize
    paint.isAntiAlias = true   //  去除锯齿
    paint.isFilterBitmap = true    //  对文字进行滤波处理，增强绘制效果

    // 计算要绘制的文字的宽和高
    val textWidth = text.getTextWidth(paint)
    val textHeight = paint.getTextHeight()

    // 计算图片的宽高
    var newBimapWidth = if (textWidth % 1 == 0f) textWidth.toInt() else textWidth.toInt() + 1
    var newBimapHeight = if (textHeight % 1 == 0f) textHeight.toInt() else textHeight.toInt() + 1

    if (leftBitmap != null) {
        newBimapWidth += leftBitmap.width
        newBimapHeight = if (leftBitmap.height > newBimapHeight) leftBitmap.height else newBimapHeight
    }

    val bitmap = Bitmap.createBitmap(newBimapWidth, newBimapHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    if (leftBitmap != null) {
        canvas.drawBitmap(leftBitmap, 0f, ((newBimapHeight - leftBitmap.height) / 2).toFloat(), paint)
        canvas.drawText(text, leftBitmap.width.toFloat(), (newBimapHeight - textHeight) / 2 + paint.getTextLeading(), paint)
    } else {
        canvas.drawText(text, 0f, (newBimapHeight - textHeight) / 2 + paint.getTextLeading(), paint)
    }
    canvas.save()

    return bitmap
}

/**
 * 使用 Html 的方式给字符串添加红色标记
 */
fun String.toHtmlRedFlag(): String {
    return "<font color=\"red\">$this</font>"
}

/**
 * 使用 Html 的方式将给定的字符串中所有给定的关键字标红
 *
 * @receiver 给定的字符串
 * @param keyword      给定的关键字
 */
fun String.keywordMadeRedByHtml(keyword: String): String {
    return this.replace(keyword.toRegex(), "<font color=\"red\">$keyword</font>")
}