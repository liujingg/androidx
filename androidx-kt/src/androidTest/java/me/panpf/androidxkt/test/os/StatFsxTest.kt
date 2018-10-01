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

package me.panpf.androidxkt.test.os

import android.os.Environment
import android.os.StatFs
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.os.availableBytesCompat
import me.panpf.androidxkt.os.freeBytesCompat
import me.panpf.androidxkt.os.totalBytesCompat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatFsxTest {

    @Test
    fun testBytes() {
        val statFs = StatFs(Environment.getExternalStorageDirectory().path)
        assertTrue(statFs.freeBytesCompat <= statFs.totalBytesCompat)
        assertTrue(statFs.availableBytesCompat <= statFs.totalBytesCompat)
        assertTrue(statFs.availableBytesCompat <= statFs.freeBytesCompat)
    }
}
