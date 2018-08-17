@file:Suppress("unused")

package me.panpf.androidx.res

import android.content.Context
import java.io.IOException

/*
 * Asset 资源相关的扩展方法或属性
 */

@Throws(IOException::class)
fun Context.readTextFromAsset(fileName: String): String {
    return this.assets.open(fileName).use { String(it.readBytes()) }
}