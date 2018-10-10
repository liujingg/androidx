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

package me.panpf.androidx.test.graphics;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Imagex;

@RunWith(AndroidJUnit4.class)
public class ImagexTest {

    @Test
    public void testMimeType() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals(Imagex.getMimeType(context.getResources(), me.panpf.androidx.test.R.drawable.ic_opera), "image/png");
        Assert.assertEquals(Imagex.getMimeType(context.getResources(), me.panpf.androidx.test.R.drawable.rect), "image/jpeg");
    }

    @Test
    public void testMimeSubType() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), me.panpf.androidx.test.R.drawable.ic_opera), "png");
        Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), me.panpf.androidx.test.R.drawable.rect), "jpeg");
    }
}
