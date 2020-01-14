package me.panpf.androidx.test.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.util.SingletonLazy;

@RunWith(AndroidJUnit4.class)
public class SingletonLazyTest {

    private SingletonLazy<Context, String> packageNameLazy = new SingletonLazy<>(new SingletonLazy.Callback<Context, String>() {
        @NonNull
        @Override
        public String createInstantiate(@NonNull Context context) {
            return new String(context.getPackageName().getBytes());
        }
    });

    @Test
    public void test() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertEquals(context.getPackageName(), packageNameLazy.get(context));
        Assert.assertNotSame(context.getPackageName(), packageNameLazy.get(context));
        Assert.assertSame(packageNameLazy.get(context), packageNameLazy.get(context));
    }
}