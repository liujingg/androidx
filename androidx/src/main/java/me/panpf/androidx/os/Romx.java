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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Method;

import me.panpf.javax.lang.Classx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Arrayx;

@SuppressWarnings("WeakerAccess")
public class Romx {

    public static final int TYPE_MIUI = 1;
    public static final int TYPE_FLYME = 2;
    public static final int TYPE_EMUI = 3;
    public static final int TYPE_COLOR_OS = 4;
    public static final int TYPE_FUNTOUCH_OS = 5;
    public static final int TYPE_SMARTISAN_OS = 6;
    public static final int TYPE_H2OS = 7;
    public static final int TYPE_LINEAGE_OS = 8;
    public static final int TYPE_UNKNOWN = 999;

    @Nullable
    private static final Method GET_BUILD_PROP_METHOD;

    private static final int TYPE;
    @NonNull
    private static final String TYPE_NAME;
    @NonNull
    private static final String VERSION_NAME;
    private static final String VERSION_CODE;
    @NonNull
    private static final String VERSION_INCREMENTAL;

    static {
        /*
         * 为何不采用读取 /root/build.prop 文件的方式？
         * 因为在 MIUI 上没有读取这个文件的权限，在华为 EMUI 上读取的属性不全
         */

        Method getMethod = null;
        try {
            getMethod = Classx.getMethodWithParent(Class.forName("android.os.SystemProperties"), "get", String.class);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        GET_BUILD_PROP_METHOD = getMethod;

        String[] versionInfos;
        if ((versionInfos = checkMIUI()) != null) {
            TYPE = TYPE_MIUI;
            TYPE_NAME = "MIUI";
        } else if ((versionInfos = checkEMUI()) != null) {
            TYPE = TYPE_EMUI;
            TYPE_NAME = "EMUI";
        } else if ((versionInfos = checkColorOS()) != null) {
            TYPE = TYPE_COLOR_OS;
            TYPE_NAME = "ColorOS";
        } else if ((versionInfos = checkFuntouchOS()) != null) {
            TYPE = TYPE_FUNTOUCH_OS;
            TYPE_NAME = "FuntouchOS";
        } else if ((versionInfos = checkH2OS()) != null) {
            TYPE = TYPE_H2OS;
            TYPE_NAME = "H2OS";
        } else if ((versionInfos = checkSmartisanOS()) != null) {
            TYPE = TYPE_SMARTISAN_OS;
            TYPE_NAME = "SmartisanOS";
        } else if ((versionInfos = checkFlyme()) != null) {
            TYPE = TYPE_FLYME;
            TYPE_NAME = "Flyme";
        } else if ((versionInfos = checkLineageOS()) != null) {
            TYPE = TYPE_LINEAGE_OS;
            TYPE_NAME = "LineageOS";
        } else {
            TYPE = TYPE_UNKNOWN;
            TYPE_NAME = "Unknown";
        }

        if (versionInfos != null) {
            VERSION_NAME = versionInfos[0];
            VERSION_CODE = versionInfos[1];
            VERSION_INCREMENTAL = versionInfos[2];
        } else {
            VERSION_NAME = "";
            VERSION_CODE = "";
            VERSION_INCREMENTAL = "";
        }
    }

    private static String[] checkMIUI() {
        String versionName = getBuildProperties("ro.miui.ui.version.name");
        if (Stringx.isSafe(versionName)) {
            String versionCode = getBuildProperties("ro.miui.ui.version.code");
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, versionCode, versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkFlyme() {
        if ("flyme".equalsIgnoreCase(getBuildProperties("ro.build.user")) || Stringx.isSafe(getBuildProperties("ro.flyme.published"))) {
            String displayId = getBuildProperties("ro.build.display.id");
            String versionName = displayId.startsWith("Flyme OS ") ? displayId.substring("Flyme OS ".length()) : displayId;
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, "", versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkColorOS() {
        String versionName = getBuildProperties("ro.build.version.opporom");
        if (Stringx.isSafe(versionName)) {
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, "", versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkEMUI() {
        String versionName = getBuildProperties("ro.build.version.emui");
        versionName = versionName.startsWith("EmotionUI_") ? versionName.substring("EmotionUI_".length()) : versionName;
        String versionCode = getBuildProperties("ro.oppo.version"); // oppo rom 的 build_prop 文件中定义了 ro.oppo.version 属性，但是值始终是空的
        String versionIncremental = getBuildProperties("ro.build.version.incremental");
        if (Stringx.isSafe(versionName)) {
            return Arrayx.arrayOf(versionName, versionCode, versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkFuntouchOS() {
        if ("Funtouch".equalsIgnoreCase(getBuildProperties("ro.vivo.os.name"))) {
            String versionName = getBuildProperties("ro.vivo.os.version");
            String versionCode = getBuildProperties("ro.vivo.product.version");
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, versionCode, versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkSmartisanOS() {
        String versionName = getBuildProperties("ro.smartisan.version");
        if (Stringx.isSafe(versionName)) {
            versionName = Arrayx.firstOrNull(versionName.split("-"));
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, "", versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkH2OS() {
        if ("OnePlus".equalsIgnoreCase(getBuildProperties("ro.build.user"))) {
            String versionName = getBuildProperties("ro.rom.version");
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, "", versionIncremental);
        } else {
            return null;
        }
    }

    private static String[] checkLineageOS() {
        if ("lineage".equalsIgnoreCase(getBuildProperties("ro.build.user"))) {
            String versionName = getBuildProperties("ro.cm.build.version");
            String versionIncremental = getBuildProperties("ro.build.version.incremental");
            return Arrayx.arrayOf(versionName, "", versionIncremental);
        } else {
            return null;
        }
    }

    @NonNull
    public static String getBuildProperties(@NonNull String key) {
        if (GET_BUILD_PROP_METHOD != null) {
            //noinspection ConstantConditions
            return Stringx.orEmpty((String) Classx.callMethod(GET_BUILD_PROP_METHOD, new Object[]{key}));
        } else {
            return "";
        }
    }

    /**
     * Get the ROM type, Ranges: {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI},
     * {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}
     */
    public static int getType() {
        return TYPE;
    }

    /**
     * Return true if the current ROM type is the same as the specified type
     *
     * @param type Ranges: {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI},
     *             {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}
     */
    public static boolean isType(int type) {
        return TYPE == type;
    }

    /**
     * Return true if the ROM type is MIUI
     */
    public static boolean isMiuiType() {
        return TYPE == TYPE_MIUI;
    }

    /**
     * Return true if the ROM type is EMUI
     */
    public static boolean isEmuiType() {
        return TYPE == TYPE_EMUI;
    }

    /**
     * Return true if the ROM type is Flyme
     */
    public static boolean isFlymeType() {
        return TYPE == TYPE_FLYME;
    }

    /**
     * Return true if the ROM type is Color OS
     */
    public static boolean isColorOSType() {
        return TYPE == TYPE_COLOR_OS;
    }

    /**
     * Return true if the ROM type is Funtouch OS
     */
    public static boolean isFuntouchOSType() {
        return TYPE == TYPE_FUNTOUCH_OS;
    }

    /**
     * Return true if the ROM type is Smartisan OS
     */
    public static boolean isSmartisanOSType() {
        return TYPE == TYPE_SMARTISAN_OS;
    }

    /**
     * Return true if the ROM type is H2OS
     */
    public static boolean isH2OSType() {
        return TYPE == TYPE_H2OS;
    }

    /**
     * Return true if the ROM type is Lineage OS
     */
    public static boolean isLineageOSType() {
        return TYPE == TYPE_LINEAGE_OS;
    }

    /**
     * Return true if the ROM type is Unknown
     */
    public static boolean isUnknownType() {
        return TYPE == TYPE_UNKNOWN;
    }

    /**
     * Get the name of the ROM type
     */
    public static String getTypeName() {
        return TYPE_NAME;
    }

    /**
     * Get the ROM version name
     */
    public static String getVersionName() {
        return VERSION_NAME;
    }

    /**
     * Get the ROM version code
     */
    public static String getVersionCode() {
        return VERSION_CODE;
    }

    /**
     * Get the ROM version incremental
     */
    @NonNull
    public static String getVersionIncremental() {
        return VERSION_INCREMENTAL;
    }

    public static String getInfo() {
        return TYPE_NAME + ":" + VERSION_NAME + ":" + VERSION_CODE + ":" + VERSION_INCREMENTAL;
    }
}
