@file:Suppress("unused")

package me.panpf.androidxkt.res

import android.content.Context
import java.io.IOException
import java.io.InputStream

/*
 * Asset resource-related extension methods or properties
 */

@Throws(IOException::class)
fun Context.readTextFromAsset(fileName: String): String {
    return this.assets.open(fileName).bufferedReader().use { it.readText() }
}

@Throws(IOException::class)
fun Context.readBytesFromAsset(fileName: String): ByteArray {
    return this.assets.open(fileName).use { it.readBytes() }
}

@Throws(IOException::class)
fun Context.readLinesFromAsset(fileName: String): List<String> {
    return this.assets.open(fileName).bufferedReader().use { it.readLines() }
}

@Throws(IOException::class)
fun Context.openInputFromAsset(fileName: String): InputStream {
    return this.assets.open(fileName)
}