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

package me.panpf.androidx.test;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.SparseArray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.util.NullableResultRunnable;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.ranges.Rangex;

@RunWith(AndroidJUnit4.class)
public class AndroidxText {

    @Test
    public void testGetMainHandler() {
        Assert.assertNotNull(Androidx.getMainHandler());
    }

    @Test
    public void testRunInUI() {
        final String[] results = new String[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                results[0] = Androidx.isMainThread() ? "MainThread1" : "NoMainThread1";
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(results[0], "MainThread1");

        Androidx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                results[0] = Androidx.isMainThread() ? "MainThread2" : "NoMainThread2";
            }
        });
        Assert.assertEquals(results[0], "MainThread2");

        results[0] = Androidx.waitRunInUIResult(new ResultRunnable<String>() {
            @NonNull
            @Override
            public String run() {
                return Androidx.isMainThread() ? "MainThread3" : "NoMainThread3";
            }
        });
        Assert.assertEquals(results[0], "MainThread3");

        results[0] = Androidx.waitRunInUINullableResult(new NullableResultRunnable<String>() {
            @Nullable
            @Override
            public String run() {
                return Androidx.isMainThread() ? "MainThread4" : null;
            }
        });
        Assert.assertEquals(results[0], "MainThread4");
    }

    @Test
    public void testIsRoot() {
        Assert.assertNotNull(String.valueOf(Androidx.isRooted()));
    }

    @Test
    public void testIsMainThread() {
        Assert.assertFalse(Androidx.isMainThread());
    }

    @Test
    public void testIsMainProcess() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertTrue(Androidx.isMainProcess(context));
    }

    @Test
    public void testInProcessName() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Androidx.getInProcessName(context));
        Assert.assertEquals(Androidx.getInProcessNameSuffix(context), "");
    }

    @Test
    public void testIsAtLeast() {
        SparseArray<List<Predicate<String>>> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.JELLY_BEAN, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastJ();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast16();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast4_1();
            }
        }));
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastJMR1();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast17();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast4_2();
            }
        }));
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastJMR2();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast18();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast4_3();
            }
        }));
        array.append(Build.VERSION_CODES.KITKAT, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastK();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast19();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast4_4();
            }
        }));
        array.append(Build.VERSION_CODES.KITKAT_WATCH, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastKW();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast20();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast4_4_W();
            }
        }));
        array.append(Build.VERSION_CODES.LOLLIPOP, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastL();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast21();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast5_0();
            }
        }));
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastLMR1();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast22();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast5_1();
            }
        }));
        array.append(Build.VERSION_CODES.M, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastM();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast23();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast6_0();
            }
        }));
        array.append(Build.VERSION_CODES.N, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastN();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast24();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast7_0();
            }
        }));
        array.append(Build.VERSION_CODES.N_MR1, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastNMR1();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast25();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast7_1();
            }
        }));
        array.append(Build.VERSION_CODES.O, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastO();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast26();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast8_0();
            }
        }));
        array.append(Build.VERSION_CODES.O_MR1, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastOMR1();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast27();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast8_1();
            }
        }));
        array.append(Build.VERSION_CODES.P, Collectionx.mutableListOf(new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeastP();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast28();
            }
        }, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Androidx.isAtLeast9_0();
            }
        }));

        for (int index : Rangex.until(0, array.size())) {
            int key = array.keyAt(index);
            for (Predicate<String> predicate : array.get(key)) {
                if (Build.VERSION.SDK_INT >= key) {
                    Assert.assertTrue("sdkInt=" + Build.VERSION.SDK_INT + ", key=" + key, predicate.accept(""));
                } else {
                    Assert.assertFalse("sdkInt=" + Build.VERSION.SDK_INT + ", key=" + key, predicate.accept(""));
                }
            }
        }
    }

    @Test
    public void testGetVersionName() {
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.BASE), "1.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.BASE_1_1), "1.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.CUPCAKE), "1.5.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.DONUT), "1.6.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.ECLAIR), "2.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.ECLAIR_0_1), "2.0.1");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.ECLAIR_MR1), "2.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.FROYO), "2.2.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.GINGERBREAD), "2.3.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.GINGERBREAD_MR1), "2.3.3");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.HONEYCOMB), "3.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.HONEYCOMB_MR1), "3.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.HONEYCOMB_MR2), "3.2.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.ICE_CREAM_SANDWICH), "4.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1), "4.0.3");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.JELLY_BEAN), "4.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.JELLY_BEAN_MR1), "4.2.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.JELLY_BEAN_MR2), "4.3.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.KITKAT), "4.4.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.KITKAT_WATCH), "4.4W");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.LOLLIPOP), "5.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.LOLLIPOP_MR1), "5.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.M), "6.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.N), "7.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.N_MR1), "7.1.1");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.O), "8.0.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.O_MR1), "8.1.0");
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION_CODES.P), "9.0.0");

        SparseArray<String> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.BASE, "1.0.0");
        array.append(Build.VERSION_CODES.BASE_1_1, "1.1.0");
        array.append(Build.VERSION_CODES.CUPCAKE, "1.5.0");
        array.append(Build.VERSION_CODES.DONUT, "1.6.0");
        array.append(Build.VERSION_CODES.ECLAIR, "2.0.0");
        array.append(Build.VERSION_CODES.ECLAIR_0_1, "2.0.1");
        array.append(Build.VERSION_CODES.ECLAIR_MR1, "2.1.0");
        array.append(Build.VERSION_CODES.FROYO, "2.2.0");
        array.append(Build.VERSION_CODES.GINGERBREAD, "2.3.0");
        array.append(Build.VERSION_CODES.GINGERBREAD_MR1, "2.3.3");
        array.append(Build.VERSION_CODES.HONEYCOMB, "3.0.0");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR1, "3.1.0");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR2, "3.2.0");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH, "4.0.0");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, "4.0.3");
        array.append(Build.VERSION_CODES.JELLY_BEAN, "4.1.0");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, "4.2.0");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, "4.3.0");
        array.append(Build.VERSION_CODES.KITKAT, "4.4.0");
        array.append(Build.VERSION_CODES.KITKAT_WATCH, "4.4W");
        array.append(Build.VERSION_CODES.LOLLIPOP, "5.0.0");
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, "5.1.0");
        array.append(Build.VERSION_CODES.M, "6.0.0");
        array.append(Build.VERSION_CODES.N, "7.0.0");
        array.append(Build.VERSION_CODES.N_MR1, "7.1.1");
        array.append(Build.VERSION_CODES.O, "8.0.0");
        array.append(Build.VERSION_CODES.O_MR1, "8.1.0");
        array.append(Build.VERSION_CODES.P, "9.0.0");

        Assert.assertEquals(Androidx.getVersionName(), array.get(Build.VERSION.SDK_INT));
        Assert.assertEquals(Androidx.getVersionName(Build.VERSION.SDK_INT), array.get(Build.VERSION.SDK_INT));
    }

    @Test
    public void testGetVersionCodeName() {
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.BASE), "Base");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.BASE_1_1), "Base1_1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.CUPCAKE), "Cupcake");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.DONUT), "Donut");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.ECLAIR), "Eclair");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.ECLAIR_0_1), "Eclair01");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.ECLAIR_MR1), "EclairMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.FROYO), "Froyo");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.GINGERBREAD), "Gingerbread");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.GINGERBREAD_MR1), "GingerbreadMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB), "Honeycomb");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB_MR1), "HoneycombMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB_MR2), "HoneycombMR2");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.ICE_CREAM_SANDWICH), "IceCreamSandwich");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1), "IceCreamSandwichMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN), "JellyBean");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN_MR1), "JellyBeanMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN_MR2), "JellyBeanMR2");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.KITKAT), "KITKAT");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.KITKAT_WATCH), "KitkatWatch");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.LOLLIPOP), "Lollipop");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.LOLLIPOP_MR1), "LollipopMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.M), "Marshmallow");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.N), "Nougat");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.N_MR1), "NougatMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.O), "Oreo");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.O_MR1), "OreoMR1");
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION_CODES.P), "Pie");

        SparseArray<String> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.BASE, "Base");
        array.append(Build.VERSION_CODES.BASE_1_1, "Base1_1");
        array.append(Build.VERSION_CODES.CUPCAKE, "Cupcake");
        array.append(Build.VERSION_CODES.DONUT, "Donut");
        array.append(Build.VERSION_CODES.ECLAIR, "Eclair");
        array.append(Build.VERSION_CODES.ECLAIR_0_1, "Eclair01");
        array.append(Build.VERSION_CODES.ECLAIR_MR1, "EclairMR1");
        array.append(Build.VERSION_CODES.FROYO, "Froyo");
        array.append(Build.VERSION_CODES.GINGERBREAD, "Gingerbread");
        array.append(Build.VERSION_CODES.GINGERBREAD_MR1, "GingerbreadMR1");
        array.append(Build.VERSION_CODES.HONEYCOMB, "Honeycomb");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR1, "HoneycombMR1");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR2, "HoneycombMR2");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH, "IceCreamSandwich");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, "IceCreamSandwichMR1");
        array.append(Build.VERSION_CODES.JELLY_BEAN, "JellyBean");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, "JellyBeanMR1");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, "JellyBeanMR2");
        array.append(Build.VERSION_CODES.KITKAT, "KITKAT");
        array.append(Build.VERSION_CODES.KITKAT_WATCH, "KitkatWatch");
        array.append(Build.VERSION_CODES.LOLLIPOP, "Lollipop");
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, "LollipopMR1");
        array.append(Build.VERSION_CODES.M, "Marshmallow");
        array.append(Build.VERSION_CODES.N, "Nougat");
        array.append(Build.VERSION_CODES.N_MR1, "NougatMR1");
        array.append(Build.VERSION_CODES.O, "Oreo");
        array.append(Build.VERSION_CODES.O_MR1, "OreoMR1");
        array.append(Build.VERSION_CODES.P, "Pie");

        Assert.assertEquals(Androidx.getVersionCodeName(), array.get(Build.VERSION.SDK_INT));
        Assert.assertEquals(Androidx.getVersionCodeName(Build.VERSION.SDK_INT), array.get(Build.VERSION.SDK_INT));
    }
}
