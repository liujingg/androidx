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

package me.panpf.androidx.os;

import android.os.Build;
import android.os.StatFs;
import android.support.annotation.NonNull;

/*
 * StatFs related extension methods or properties
 */
public class StatFsx {

    public static long getCompatAvailableBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBytes() : (long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize();
    }

    public static long getCompatFreeBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getFreeBytes() : (long) statFs.getFreeBlocks() * (long) statFs.getBlockSize();
    }

    public static long getCompatTotalBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getTotalBytes() : (long) statFs.getBlockCount() * (long) statFs.getBlockSize();
    }
}