package me.panpf.androidxkt.os

import android.os.Build
import android.os.StatFs

/*
 * StatFs related extension methods or properties
 */

val StatFs.compatAvailableBytes: Long
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            this.availableBytes
        } else {
            this.availableBlocks.toLong() * this.blockSize.toLong()
        }
    }

val StatFs.compatFreeBytes: Long
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            this.freeBytes
        } else {
            this.freeBlocks.toLong() * this.blockSize.toLong()
        }
    }

val StatFs.compatTotalBytes: Long
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            this.totalBytes
        } else {
            this.blockCount.toLong() * this.blockSize.toLong()
        }
    }