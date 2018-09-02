package me.panpf.androidx.test.app;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.app.Permissionx;
import me.panpf.androidx.os.Buildx;

@RunWith(AndroidJUnit4.class)
public final class PermissionTest {

    @Test
    public final void testSinglePermission() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertTrue(Permissionx.isGrantPermission(context, Manifest.permission.INTERNET));
    }

    @Test
    public final void testSingleNoPermission() {
        Context context = InstrumentationRegistry.getContext();
        if (Buildx.isAtLeastM()) {
            Assert.assertFalse(Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE));
        } else {
            Assert.assertTrue(Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE));
        }
    }

    @Test
    public final void testMultiPermission() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertTrue(Permissionx.isGrantPermissions(context,
                Manifest.permission.INTERNET, Manifest.permission.VIBRATE));
    }

    @Test
    public final void testMultiNoPermission() {
        Context context = InstrumentationRegistry.getContext();
        if (Buildx.isAtLeastM()) {
            Assert.assertFalse(Permissionx.isGrantPermissions(context,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA));
        } else {
            Assert.assertTrue(Permissionx.isGrantPermissions(context,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA));
        }
    }
}
