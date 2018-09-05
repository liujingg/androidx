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