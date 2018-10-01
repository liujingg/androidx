# ANDROIDX

[![Platform][platform_android_icon]][platform_android_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
[![API][min_api_icon]][min_api_link]
[![License][license_icon]][license_link]

Extensions to the Android standard library and support libraries and some basic tools

#### Child module
* [androidx-kt-arch]: Kotlin extension to Android Architecture related libraries
* [androidx-kt-args]: Extension of Activity and Fragment parameters based on Kotlin features

## Getting Started

### Java version:

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_java_icon]][version_java_link]

Dependencies:
* [com.android.support:support-fragment][support_fragment]: [27.1.1][support_fragment_versions]
* [me.panpf:javax][javax]: [0.6.5][javax_versions]

### Kotlin version:

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx-kt:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_kotlin_icon]][version_kotlin_link]

Dependencies:
* [org.jetbrains.kotlin:kotlin-stdlib-jdk7][kotlin_stdlib]: [1.2.61][kotlin_versions]
* [com.android.support:support-fragment][support_fragment]: [27.1.1][support_fragment_versions]
* [me.panpf:javax-kt][javax]: [0.6.5][javax_versions]

`You can add Java or Kotlin versions to your project as needed.
If your project is Java and Kotlin mixed development, then both versions can be imported,
because there are different package names, so there will be no class duplicates.`

## Index

* Android: [Androidx.java] | [Android.kt]
    * getMainHandler, runInUI, isRooted, isMainThread, getInProcessName,
    * getInProcessNameSuffix, isMainProcess, isAtLeast\*, getVersionName, getVersionCodeName

### app
* Activity: [Activityx.java] | [Activity.kt]
    * isDestroyedCompat, convertActivityFromTranslucent, convertActivityToTranslucent, getImplWithParent
* Dialog: [Dialogx.java] | [Dialog.kt]
    * setClickButtonClosable, showProgressDialog
* Fragment: [Fragmentx.java] | [Fragment.kt]
    * isDestroyedCompat, getImplWithParent, instance,
* Permission: [Permissionx.java] ([Test][PermissionTest.java]) | [Permission.kt] ([Test][PermissionTest.kt])
    * isGrantPermissions, filterDeniedPermissions

### content
* Package: [Packagex.java] | [Package.kt]
    * isInstalled, getVersionCode, getVersionName, getPackage, isSystemApp, getAllAppIdAndVersionCodeMap,
    * getAllAppIdAndVersionCodeSet, getAllAppId, getAllApp, getOne, count, getAppPackageFile,
    * getAppSignatureBytes, getAppIconDrawable, getAppIconBitmap, getApkIconDrawable, getApkIconBitmap
* Asset: [Assetx.java] | [Asset.kt]
    * openInput, readBytes, readText, readLines, readBitmap
* Clipboard: [Clipboardx.java] ([Test][ClipboardTest.java]) | [Clipboard.kt] ([Test][ClipboardTest.kt])
    * copy, copyText, copyHtmlText, copyIntent, copyUri, copyRawUri, copyMimeTypeUri, copyContents,
    * get, getLabel, getContents, getText, getHtmlText, getIntent, getUri, addPrimaryClipChangedListener,
    * removePrimaryClipChangedListener, clear
* Content: [Contentx.java] | [Content.kt]
    * openInput, readBytes, readText, readLines, readBitmap
* Intent: [Intentx.java] | [Intent.kt]
    * createRecordingIntent, createLaunchDialingIntent, createCallPhoneIntent, createLaunchSendSmsIntent,
    * createLaunchWebBrowserIntent, createScanFileBroadcastIntent, createInstallAppIntent, createUninstallAppIntent,
    * createLaunchAppIntent, createAppDetailInSystemIntent, createActivityIntentByResolveInfo, createTakePhotoIntent,
    * createPickImageIntent, createCropImageIntent, canStartActivity, safeStartActivity
* Preference: [Preferencex.java] | [Preference.kt]
    * getPreference, putInt, putLong, putBoolean, putFloat, putString, putStringSet,
    * getInt, getLong,getBoolean, getFloat, getString, getStringSet, getAll, remove, clear

### graphics
* Drawable: [Drawablex.java] ([Test][DrawableTest.java])  | [Drawable.kt] ([Test][DrawableTest.kt])
    * toBitmapWithIntrinsicSize, toBitmapWithBoundsSize, toDrawableByColor, toDrawableByColorFromDrawableRes
* Bitmap: [Bitmapx.java] | [Bitmap.kt]
    * centerCrop, tint, createByColor, toByteArray, writeToFile, readBitmap, toDrawableByColor,
* Color: [Colorx.java] ([Test][ColorTest.java]) | [Color.kt] ([Test][ColorTest.kt])
    * WHITE/BLACK/RED..., getAlpha, setAlpha, addAlpha, getHSVHue, setHSVHue, getHSVSaturation,
    * setHSVSaturation, addHSVSaturation, getHSVValue, setHSVValue, addHSVValue, isLight,
    * createMatrixColorFilter, argbEvaluate
* Matrix: [Matrixx.java] | [Matrix.kt]
    * getValue, getScale
* OpenGl: [OpenGlx.java] | [OpenGl.kt]
    * getVersion, getMaxTextureSize,
* Paint: [Paintx.java] | [Paint.kt]
    * getTextWidth, getTextHeight, getTextWidthByBounds, getTextHeightByBounds, getTextLeading

### hardware
* Hardware: [Hardwarex.java] ([Test][HardwareTest.java]) | [Hardware.kt] ([Test][HardwareTest.kt])
    * getDeviceModel, getDeviceName, getHardware, getSupportedAbis, getPhoneNumber, getDeviceId,
    * getAndroidId, getSubscriberId, getSimSerialNumber, getSerial, getIMEI, getIMSI, getMacAddress

### net
* NetworkState: [NetworkState.java] | [NetworkState.kt]
    * isActivated, isWifiActivated, isNoMeteredWifiActivated, isMobileActivated, isBluetoothActivated,
    * getType, isVPNActivated, isMetered, isRoaming, isFailover, getTypeName, getSubtypeName,
    * getExtraInfo, getNetworkInfo, getConnectivity
* Network: [Networkx.java] | [Network.kt]
    * getState, getWifiState, isWifiEnabled, setWifiEnabled, isMobileEnabled, setMobileEnabled, getIpAddress

### os
* Storage: [Storagex.java] | [Storage.kt]
    * getFreeBytes, getTotalBytes, getAvailableBytes, getVolumeState, isVolumeMounted, isPrimaryVolume,
    * isVolumeEmulated, isVolumeRemovable, getVolumePaths, getMountedVolumePaths, getVolumeFiles,
    * getMountedVolumeFiles, getVolumeList, getMountedVolumeList, getVolumes, getMountedVolumes, getVolume,
    * getExternalStorageState, isExternalStorageMounted, isPrimaryExternalStorage, isExternalStorageEmulated,
    * isExternalStorageEmulated, isExternalStorageRemovable, isExternalStorageRemovable,
    * getExternalStorageDirectory, getMountedExternalStorageDirectory, getExternalStorageDirectorys,
    * getMountedExternalStorageDirectorys, getExternalStorageDirectorys, getMountedExternalStorageDirectorys,
    * getExternalStorageDirectorysWithPath, getMountedExternalStorageDirectorysWithPath,
    * getAppExternalCacheDir, getAppExternalCacheDirs, getAppInternalCacheDir, getAppCacheDirs,
    * lengthAppCacheDirs, cleanAppCacheDirs, getAppExternalFilesDir, getAppExternalFilesDirs,
    * getAppInternalFilesDir, getAppFilesDirs, lengthAppFilesDirs, cleanAppFilesDirs,
    * getAppObbDir, getAppObbDirs, lengthAppObbDirs, cleanAppObbDirs, filterByMinBytes
* StatFs: [StatFsx.java] ([Test][StatFsTest.java]) | [StatFs.kt] ([Test][StatFsTest.kt])
    * getCompatAvailableBytes, getCompatFreeBytes, getCompatTotalBytes
* StorageManagerCompat: [StorageManagerCompat.java] ([Test][StorageManagerCompatTest.java]) | [StorageManagerCompat.kt] ([Test][StorageManagerCompatTest.kt])
    * getVolumeList, getVolumes, getVolumePaths, getVolumeState, getVolume
* StorageVolumeCompat: [StorageVolumeCompat.java] ([Test][StorageVolumeCompatTest.java]) | [StorageVolumeCompat.kt] ([Test][StorageVolumeCompatTest.kt])
    * getPath, getPathFile, isPrimary, isRemovable, isEmulated, getState, allowMassStorage, getMaxFileSize,

### provider
* Settings: [Settingsx.java] | [Settings.kt]
    * isScreenBrightnessModeAutomatic, setScreenBrightnessModeAutomatic, getScreenBrightness,
    * setScreenBrightness, getWindowBrightness, setWindowBrightness, isWindowBrightnessFlowSystem,
    * getScreenOffTimeout, setScreenOffTimeout, isAirplaneModeOn, setAirplaneModeOn, isBluetoothOn,
    * setBluetoothOn, getMediaVolume, setMediaVolume, getRingVolume, setRingVolume

### util
* Dimen: [Dimenx.java] | [Dimen.kt]
    * dp2px, px2dp, sp2px, px2sp, unit2px
* Json: [Jsonx.java] | [Json.kt]
    * isEmpty, isNotEmpty, toJsonArray, toJson, toStringList, toStringArray, toIntArray,
    * toBeanList, toBean, optString, optInt, optLong, format
* Text: [Textx.java] | [Text.kt]
    * textToBitmap, toHtmlColorFlag, toHtmlRedFlag, keywordMadeColorByHtml, keywordMadeRedByHtml,
    * keywordMadeColorBySpannable, keywordMadeRedBySpannable
* WeakAsyncTask: [WeakAsyncTask.java] ([Test][WeakAsyncTaskTest.java]) | [WeakAsyncTask.kt] ([Test][WeakAsyncTaskTest.kt])

### view
* InputMethod: [InputMethodx.java] | [InputMethod.kt]
    * showSoftInput, delayShowSoftInput, hideSoftInput, moveCursorToEnd, moveCursorToStart, moveCursorTo
* Display: [Displayx.java] | [Display.kt]
    * getScreenSize, getScreenWidth, getScreenHeight, getMetrics, getDensity, getDisplayRotation
    * getDensityDpi, isOrientationPortrait, isOrientationLandscape, isOrientationUndefined, getRotation
    * hasNavigationBar, getStatusBarHeight, getNavigationBarHeight, getNavigationBarWidth
* ViewAnim: [ViewAnimx.java] | [ViewAnim.kt]
    * animAlpha, animTranslate, shake, shock, startAnimFromRes, invisibleByAnimAlpha,
    * goneByAnimAlpha, visibleByAnimAlpha,
* View: [Viewx.java] | [View.kt]
    * setLongClickToastHint, setLayoutWidth, setLayoutHeight, setLayoutSize, setLayoutMarginTop,
    * addLayoutHeight, addLayoutWidth, addLayoutSize, addLayoutMarginTop, toBitmap, toBitmapByMaxWidth,
    * toBitmapByMaxHeight, inflateLayout
<!--* Window: [Windowx.java] | [Window.kt]-->

### widget
* Toast: [Toastx.java] ([Test][ToastTest.java]) | [Toast.kt] ([Test][ToastTest.java])
    * showLong, showShort, showLongWithView, showShortWithView

## Change Log

Please view the [CHANGELOG.md] file


## License
    Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[platform_android_icon]: https://img.shields.io/badge/Platform-Android-brightgreen.svg
[platform_android_link]: https://android.com
[platform_kotlin_icon]: https://img.shields.io/badge/Platform-Kotlin-blue.svg
[platform_kotlin_link]: http://kotlinlang.org
[min_api_icon]: https://img.shields.io/badge/API-16%2B-orange.svg
[min_api_link]: https://developer.android.com/about/dashboards/
[license_icon]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0
[version_java_icon]: https://api.bintray.com/packages/panpf/maven/androidx/images/download.svg
[version_java_link]: https://bintray.com/panpf/maven/androidx/_latestVersion
[version_kotlin_icon]: https://api.bintray.com/packages/panpf/maven/androidx-kt/images/download.svg
[version_kotlin_link]: https://bintray.com/panpf/maven/androidx-kt/_latestVersion
[javax]: https://github.com/panpf/javax
[javax_versions]: https://github.com/panpf/javax/blob/master/CHANGELOG.md
[support_fragment]: https://developer.android.com/topic/libraries/support-library/packages#v4-fragment
[support_fragment_versions]: https://developer.android.com/topic/libraries/support-library/revisions
[kotlin_stdlib]: https://kotlinlang.org/
[kotlin_versions]: https://blog.jetbrains.com/kotlin/
[androidx-kt-arch]: androidx-kt-arch
[androidx-kt-args]: androidx-kt-args

[CHANGELOG.md]: CHANGELOG.md

[Androidx.java]: androidx/src/main/java/me/panpf/androidx/Androidx.java
[AndroidTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/AndroidTest.java
[Android.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/Android.kt
[AndroidTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/AndroidTest.kt

[Activityx.java]: androidx/src/main/java/me/panpf/androidx/app/Activityx.java
[ActivityTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/ActivityTest.java
[Activity.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Activity.kt
[ActivityTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ActivityTest.kt

[Dialogx.java]: androidx/src/main/java/me/panpf/androidx/app/Dialogx.java
[DialogTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/DialogTest.java
[Dialog.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Dialog.kt
[DialogTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/DialogTest.kt

[Fragmentx.java]: androidx/src/main/java/me/panpf/androidx/app/Fragmentx.java
[FragmentTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/FragmentTest.java
[Fragment.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Fragment.kt
[FragmentTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/FragmentTest.kt

[Permissionx.java]: androidx/src/main/java/me/panpf/androidx/app/Permissionx.java
[PermissionTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/PermissionTest.java
[Permission.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Permission.kt
[PermissionTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/PermissionTest.kt

[Packagex.java]: androidx/src/main/java/me/panpf/androidx/content/pm/Packagex.java
[PackageTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/pm/PackageTest.java
[Package.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/pm/Package.kt
[PackageTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/pm/PackageTest.kt

[Assetx.java]: androidx/src/main/java/me/panpf/androidx/content/res/Assetx.java
[AssetTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/res/AssetTest.java
[Asset.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/res/Asset.kt
[AssetTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/res/AssetTest.kt

[Contentx.java]: androidx/src/main/java/me/panpf/androidx/content/Contentx.java
[ContentTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/ContentTest.java
[Content.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Content.kt
[ContentTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ContentTest.kt

[Clipboardx.java]: androidx/src/main/java/me/panpf/androidx/content/Clipboardx.java
[ClipboardTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/ClipboardTest.java
[Clipboard.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Clipboard.kt
[ClipboardTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ClipboardTest.kt

[Intentx.java]: androidx/src/main/java/me/panpf/androidx/content/Intentx.java
[IntentTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/IntentTest.java
[Intent.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Intent.kt
[IntentTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/IntentTest.kt

[Preferencex.java]: androidx/src/main/java/me/panpf/androidx/content/Preferencex.java
[PreferenceTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/PreferenceTest.java
[Preference.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Preference.kt
[PreferenceTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/PreferenceTest.kt

[Drawablex.java]: androidx/src/main/java/me/panpf/androidx/graphics/drawable/Drawablex.java
[DrawableTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/drawable/DrawableTest.java
[Drawable.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/drawable/Drawable.kt
[DrawableTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/drawable/DrawableTest.kt

[Bitmapx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Bitmapx.java
[BitmapTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/BitmapTest.java
[Bitmap.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Bitmap.kt
[BitmapTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/BitmapTest.kt

[Colorx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Colorx.java
[ColorTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/ColorTest.java
[Color.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Color.kt
[ColorTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/ColorTest.kt

[Matrixx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Matrixx.java
[MatrixTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/MatrixTest.java
[Matrix.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Matrix.kt
[MatrixTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/MatrixTest.kt

[OpenGlx.java]: androidx/src/main/java/me/panpf/androidx/graphics/OpenGlx.java
[OpenGlTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/OpenGlTest.java
[OpenGl.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/OpenGl.kt
[OpenGlTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/OpenGlTest.kt

[Paintx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Paintx.java
[PaintTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/PaintTest.java
[Paint.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Paint.kt
[PaintTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/PaintTest.kt

[Hardwarex.java]: androidx/src/main/java/me/panpf/androidx/hardware/Hardwarex.java
[HardwareTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/hardware/HardwareTest.java
[Hardware.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/hardware/Hardware.kt
[HardwareTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/hardware/HardwareTest.kt

[NetworkState.java]: androidx/src/main/java/me/panpf/androidx/net/NetworkState.java
[NetworkStateTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkStateTest.java
[NetworkState.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/net/NetworkState.kt
[NetworkStateTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkStateTest.kt

[Networkx.java]: androidx/src/main/java/me/panpf/androidx/net/Networkx.java
[NetworkTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkTest.java
[Network.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/net/Network.kt
[NetworkTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkTest.kt

[Storagex.java]: androidx/src/main/java/me/panpf/androidx/os/storage/Storagex.java
[StorageTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageTest.java
[Storage.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/Storage.kt
[StorageTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageTest.kt

[StorageManagerCompat.java]: androidx/src/main/java/me/panpf/androidx/os/storage/StorageManagerCompat.java
[StorageManagerCompatTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageManagerCompatTest.java
[StorageManagerCompat.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/StorageManagerCompat.kt
[StorageManagerCompatTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageManagerCompatTest.kt

[StorageVolumeCompat.java]: androidx/src/main/java/me/panpf/androidx/os/storage/StorageVolumeCompat.java
[StorageVolumeCompatTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageVolumeCompatTest.java
[StorageVolumeCompat.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/StorageVolumeCompat.kt
[StorageVolumeCompatTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageVolumeCompatTest.kt

[StatFsx.java]: androidx/src/main/java/me/panpf/androidx/os/StatFsx.java
[StatFsTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/StatFsTest.java
[StatFs.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/StatFs.kt
[StatFsTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/StatFsTest.kt

[Settingsx.java]: androidx/src/main/java/me/panpf/androidx/provider/Settingsx.java
[SettingsTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/provider/SettingsTest.java
[Settings.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/provider/Settings.kt
[SettingsTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/provider/SettingsTest.kt

[Dimenx.java]: androidx/src/main/java/me/panpf/androidx/util/Dimenx.java
[DimenTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/DimenTest.java
[Dimen.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Dimen.kt
[DimenTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/DimenTest.kt

[Jsonx.java]: androidx/src/main/java/me/panpf/androidx/util/Jsonx.java
[JsonTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/JsonTest.java
[Json.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Json.kt
[JsonTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/JsonTest.kt

[Textx.java]: androidx/src/main/java/me/panpf/androidx/util/Textx.java
[TextTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/TextTest.java
[Text.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Text.kt
[TextTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/TextTest.kt

[InputMethodx.java]: androidx/src/main/java/me/panpf/androidx/view/inputmethod/InputMethodx.java
[InputMethodTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/inputmethod/InputMethodTest.java
[InputMethod.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/inputmethod/InputMethod.kt
[InputMethodTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/inputmethod/InputMethodTest.kt

[Displayx.java]: androidx/src/main/java/me/panpf/androidx/view/Displayx.java
[DisplayTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/DisplayTest.java
[Display.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/Display.kt
[DisplayTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/DisplayTest.kt

[ViewAnimx.java]: androidx/src/main/java/me/panpf/androidx/view/ViewAnimx.java
[ViewAnimTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewAnimTest.java
[ViewAnim.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/ViewAnim.kt
[ViewAnimTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewAnimTest.kt

[Viewx.java]: androidx/src/main/java/me/panpf/androidx/view/Viewx.java
[ViewTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewTest.java
[View.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/View.kt
[ViewTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewTest.kt

[Windowx.java]: androidx/src/main/java/me/panpf/androidx/view/Windowx.java
[WindowTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/WindowTest.java
[Window.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/Window.kt
[WindowTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/WindowTest.kt

[Toastx.java]: androidx/src/main/java/me/panpf/androidx/widget/Toastx.java
[ToastTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/widget/ToastTest.java
[Toast.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/widget/Toast.kt
[ToastTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/widget/ToastTest.kt

[WeakAsyncTask.java]: androidx/src/main/java/me/panpf/androidx/util/WeakAsyncTask.java
[WeakAsyncTaskTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/WeakAsyncTaskTest.java
[WeakAsyncTask.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/WeakAsyncTask.kt
[WeakAsyncTaskTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/WeakAsyncTaskTest.kt
