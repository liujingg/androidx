package me.panpf.androidxkt.test.graphics

import android.graphics.Color
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.createBitmapByColor
import me.panpf.androidxkt.graphics.drawable.toBitmapWithIntrinsicSize
import me.panpf.androidxkt.graphics.toDrawableByColor
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapTest {

    @Test
    fun testModifyColorBitmap() {
        val sourceBitmap = createBitmapByColor(100, 100, Color.parseColor("#FF0000"))

        val drawable = sourceBitmap.toDrawableByColor(Color.parseColor("#0000FF"))
        val bitmap = drawable.toBitmapWithIntrinsicSize()

        sourceBitmap.recycle()
        bitmap.recycle()
    }
}
