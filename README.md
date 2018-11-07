# ANDROIDX

[![Platform][platform_android_icon]][platform_android_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
[![API][min_api_icon]][min_api_link]
[![License][license_icon]][license_link]

Extensions to the Android standard library and support libraries and some basic tools

### :warning: `Warning`
`Currently in the development stage, some APIs have not been tested yet, please use them carefully`

#### Child module
* [androidx-kt-arch]: Kotlin extension to Android Architecture related libraries

## Getting Started

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_java_icon]][version_java_link]

Dependencies:
* [com.android.support:support-fragment][support_fragment]: [28.0.0][support_fragment_versions]
* [me.panpf:javax][javax]: [0.8.4][javax_versions]

### Kotlin Expansion:

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx-kt:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_kotlin_icon]][version_kotlin_link]

Dependencies:
* [org.jetbrains.kotlin:kotlin-stdlib-jdk7][kotlin_stdlib]: [1.2.71][kotlin_versions]
* [com.android.support:support-fragment][support_fragment]: [28.0.0][support_fragment_versions]
* [me.panpf:javax-kt][javax]: [0.8.4][javax_versions]

`androidx-kt is a androidx extension on the Kotlin platform that allows you to use androidx more comfortably on Kotlin`

## Index

* Android: [Androidx.java] ([Test][AndroidxTest.java]) | [Androidx.kt] ([Test][AndroidxTest.kt])
    * getMainHandler, runInUI, waitRunInUI, waitRunInUIResult, waitRunInUINullableResult, isRooted, isMainThread,
    * getInProcessName, getInProcessNameSuffix, isMainProcess, isAtLeast\*, getVersionName, getVersionCodeName

### app
* Activity: [Activityx.java] ([Test][ActivityxTest.java]) | [Activityx.kt] ([Test][ActivityxTest.kt])
    * isDestroyedCompat, convertActivityFromTranslucent, convertActivityToTranslucent, getImplWithParent
    * appContext, canStart, safeStart, read\*Arg, read\*UriArg, read\*UriIntentArg, read\*IntentUriArg
    * start
* Args: [Argsx.java] | [Argsx.kt]
    * read\*Arg, read\*ArgOr, read\*UriArg, read\*UriArgOr, read\*IntentUriArg, read\*IntentUriArgOr,
    * read\*UriIntentArg, read\*UriIntentArgOr
* ArgsBinder: [ArgsBinder.kt]
    * bind\*Arg, bind\*ArgOr, bind\*UriArg, bind\*UriArgOr, bind\*IntentUriArg, bind\*IntentUriArgOr,
    * bind\*UriIntentArg, bind\*UriIntentArgOr
* Dialog: [Dialogx.java] | [Dialogx.kt]
    * setClickButtonClosable, showProgressDialog
* Fragment: [Fragmentx.java] | [Fragmentx.kt]
    * isDestroyedCompat, getImplWithParent, instance, findUserVisibleChildFragment, findFragmentByViewPagerCurrentItem
    * requireContext, requireAppContent, read\*Arg
* Permission: [Permissionx.java] ([Test][PermissionxTest.java]) | [Permissionx.kt] ([Test][PermissionxTest.kt])
    * isGrantPermissions, filterDeniedPermissions
* Service: [Servicex.java] | [Servicex.kt]
    * isRunning, start, tryStart, stop, tryStop

### content
* Package: [Packagex.java] | [Packagex.kt]
    * isInstalled, getVersionCode, getVersionName, getPackage, isSystemApp, getAllAppIdAndVersionCodeMap,
    * getAllAppIdAndVersionCodeSet, getAllAppId, getAllApp, getOne, count, getAppPackageFile,
    * getAppSignatureBytes, getAppIconDrawable, getAppIconBitmap, getApkIconDrawable, getApkIconBitmap
* Asset: [Assetx.java] | [Assetx.kt]
    * openInput, readBytes, readText, readLines, readBitmap
* Clipboard: [Clipboardx.java] ([Test][ClipboardxTest.java]) | [Clipboardx.kt] ([Test][ClipboardxTest.kt])
    * copy, copyText, copyHtmlText, copyIntent, copyUri, copyRawUri, copyMimeTypeUri, copyContents,
    * get, getLabel, getContents, getText, getHtmlText, getIntent, getUri, addPrimaryClipChangedListener,
    * removePrimaryClipChangedListener, clear
* Content: [Contentx.java] | [Contentx.kt]
    * openInput, readBytes, readText, readLines, readBitmap
* Context: [Contextx.java] ([Test][ContextxTest.java]) | [Contextx.kt] ([Test][ContextxTest.kt])
    * systemService, systemServiceOrNull, \*Manager
* Intent: [Intentx.java] | [Intentx.kt]
    * createRecordingIntent, createLaunchDialingIntent, createCallPhoneIntent, createLaunchSendSmsIntent,
    * createLaunchWebBrowserIntent, createScanFileBroadcastIntent, createInstallAppIntent, createUninstallAppIntent,
    * createLaunchAppIntent, createAppDetailInSystemIntent, createActivityIntentByResolveInfo, createTakePhotoIntent,
    * createPickImageIntent, createCropImageIntent, createSendTextIntent, createSendTextFileIntent,
    * createSendImageFileIntent, createSendFileIntent
* Preference: [Preferencex.java] | [Preferencex.kt]
    * getPreference, putInt, putLong, putBoolean, putFloat, putString, putStringSet,
    * getInt, getLong,getBoolean, getFloat, getString, getStringSet, getAll, remove, clear

### graphics
* Drawable: [Drawablex.java] ([Test][DrawablexTest.java]) | [Drawablex.kt] ([Test][DrawablexTest.kt])
    * toBitmapWithIntrinsicSize, toBitmapWithBoundsSize, toDrawableByColor, toDrawableByColorFromDrawableRes
* Bitmap: [Bitmapx.java] | [Bitmapx.kt]
    * centerCrop, centerCropTo, circular, circularTo, tint, createByColor, toByteArray,
    * writeToFile, readBitmap, toDrawableByColor, use, calculateSamplingSize, calculateSamplingSizeForRegion
* Image: [Imagex.java] ([Test][ImagexTest.java])   | [Imagex.kt] ([Test][ImagexTest.kt])
    * getMimeType, getMimeSubType
* Color: [Colorx.java] ([Test][ColorxTest.java]) | [Colorx.kt] ([Test][ColorxTest.kt])
    * WHITE/BLACK/RED..., getAlpha, setAlpha, addAlpha, getHSVHue, setHSVHue, getHSVSaturation,
    * setHSVSaturation, addHSVSaturation, getHSVValue, setHSVValue, addHSVValue, isLight,
    * createMatrixColorFilter, argbEvaluate
* Matrix: [Matrixx.java] | [Matrixx.kt]
    * getValue, getScale
* OpenGl: [OpenGlx.java] | [OpenGlx.kt]
    * getVersion, getMaxTextureSize,
* Paint: [Paintx.java] | [Paintx.kt]
    * getTextWidth, getTextHeight, getTextWidthByBounds, getTextHeightByBounds, getTextLeading

### hardware
* Hardware: [Hardwarex.java] ([Test][HardwarexTest.java]) | [Hardwarex.kt] ([Test][HardwarexTest.kt])
    * getProduct, getBrand, getModel, getDevice, getHardware, getSupportedAbis, getPhoneNumber, getDeviceId,
    * getAndroidId, getSubscriberId, getSimSerialNumber, getSerial, getIMEI, getIMSI, getMacAddress

### net
* NetworkState: [NetworkState.java] | [NetworkState.kt]
    * isActivated, isWifiActivated, isNoMeteredWifiActivated, isMobileActivated, isBluetoothActivated,
    * getType, isVPNActivated, isMetered, isRoaming, isFailover, getTypeName, getSubtypeName,
    * getExtraInfo, getNetworkInfo, getConnectivity
* Network: [Networkx.java] | [Networkx.kt]
    * getState, isActivated, isWifiActivated, isNoMeteredWifiActivated, isMobileActivated,
    * isBluetoothActivated, getType, isVPNActivated, isMetered, isRoaming, isFailover, getTypeName,
    * getSubtypeName, getExtraInfo, getNetworkInfo, getConnectivity, getWifiState,
    * isWifiEnabled, setWifiEnabled, isMobileEnabled, setMobileEnabled, getGateway, getDNS1, getDNS2

### os
* Storage: [Storagex.java] ([Test][StoragexTest.java])  | [Storagex.kt] ([Test][StoragexTest.kt])
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
    * getAppObbDir, getAppObbDirs, lengthAppObbDirs, cleanAppObbDirs, filterByMinBytes, getFileIn
* StatFs: [StatFsx.java] ([Test][StatFsxTest.java]) | [StatFsx.kt] ([Test][StatFsxTest.kt])
    * getCompatAvailableBytes, getCompatFreeBytes, getCompatTotalBytes
* StorageManagerCompat: [StorageManagerCompat.java] ([Test][StorageManagerCompatTest.java]) | [StorageManagerCompat.kt] ([Test][StorageManagerCompatTest.kt])
    * getVolumeList, getVolumes, getVolumePaths, getVolumeState, getVolume
* StorageVolumeCompat: [StorageVolumeCompat.java] ([Test][StorageVolumeCompatTest.java]) | [StorageVolumeCompat.kt] ([Test][StorageVolumeCompatTest.kt])
    * getPath, getPathFile, isPrimary, isRemovable, isEmulated, getState, allowMassStorage, getMaxFileSize,
* Rom: [Romx.java] ([Test][RomxTest.java])
    * getType, isType, isMiuiType, isEmuiType, isFlymeType, isColorType, isFuntouchOSType, isSmartisanOSType,
    * isH2OSType, getTypeName, getVersionName, getVersionCode, getVersionIncremental
* SystemProperties: [SystemPropertiesx.java] ([Test][SystemPropertiesxTest.java])
    * get, getInt, getLong, getBoolean, set, addChangeCallbacks, callChangeCallbacks

### provider
* Settings: [Settingsx.java] | [Settingsx.kt]
    * isScreenBrightnessModeAutomatic, setScreenBrightnessModeAutomatic, getScreenBrightness,
    * setScreenBrightness, getWindowBrightness, setWindowBrightness, isWindowBrightnessFlowSystem,
    * getScreenOffTimeout, setScreenOffTimeout, isAirplaneModeOn, setAirplaneModeOn, isBluetoothOn,
    * setBluetoothOn, getMediaVolume, setMediaVolume, getRingVolume, setRingVolume

### util
* Dimen: [Dimenx.java] | [Dimenx.kt]
    * dp2px, px2dp, sp2px, px2sp, unit2px
* Json: [Jsonx.java] | [Jsonx.kt]
    * isEmpty, isNotEmpty, toJsonArray, toJson, toStringList, toStringArray, toIntArray,
    * toBeanList, toBean, optString, optInt, optLong, format
* Text: [Textx.java] | [Textx.kt]
    * textToBitmap, toHtmlColorFlag, toHtmlRedFlag, keywordMadeColorByHtml, keywordMadeRedByHtml,
    * keywordMadeColorBySpannable, keywordMadeRedBySpannable
* WeakAsyncTask: [WeakAsyncTask.java] ([Test][WeakAsyncTaskTest.java]) | [WeakAsyncTask.kt] ([Test][WeakAsyncTaskTest.kt])

### view
* InputMethod: [InputMethodx.java] | [InputMethodx.kt]
    * showSoftInput, delayShowSoftInput, hideSoftInput, moveCursorToEnd, moveCursorToStart, moveCursorTo
* Display: [Displayx.java] | [Displayx.kt]
    * getScreenSize, getScreenWidth, getScreenHeight, getMetrics, getDensity, getDisplayRotation
    * getDensityDpi, isOrientationPortrait, isOrientationLandscape, isOrientationUndefined, getRotation
    * hasNavigationBar, getStatusBarHeight, getNavigationBarHeight, getNavigationBarWidth
* ViewAnim: [ViewAnimx.java] | [ViewAnimx.kt]
    * animAlpha, animTranslate, shake, shock, startAnimFromRes, invisibleByAnimAlpha,
    * goneByAnimAlpha, visibleByAnimAlpha,
* View: [Viewx.java] | [Viewx.kt]
    * setLongClickToastHint, setLayoutWidth, setLayoutHeight, setLayoutSize, setLayoutMarginTop,
    * addLayoutHeight, addLayoutWidth, addLayoutSize, addLayoutMarginTop, toBitmap, toBitmapByMaxWidth,
    * toBitmapByMaxHeight, inflateLayout, appContext
<!--* Window: [Windowx.java] | [Windowx.kt]-->

### widget
* Toast: [Toastx.java] ([Test][ToastxTest.java]) | [Toastx.kt] ([Test][ToastxTest.kt])
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
[AndroidxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/AndroidxTest.java
[Androidx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/Androidx.kt
[AndroidxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/AndroidxTest.kt

[Activityx.java]: androidx/src/main/java/me/panpf/androidx/app/Activityx.java
[ActivityxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/ActivityxTest.java
[Activityx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Activityx.kt
[ActivityxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ActivityxTest.kt

[Argsx.java]: androidx/src/main/java/me/panpf/androidx/app/Argsx.java
[ArgsxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/ArgsxTest.java
[Argsx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Argsx.kt
[ArgsxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ArgsxTest.kt

[ArgsBinder.java]: androidx/src/main/java/me/panpf/androidx/app/ArgsBinder.java
[ArgsBinderTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/ArgsBinderTest.java
[ArgsBinder.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/ArgsBinder.kt
[ArgsBinderTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ArgsBinderTest.kt

[Dialogx.java]: androidx/src/main/java/me/panpf/androidx/app/Dialogx.java
[DialogxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/DialogxTest.java
[Dialogx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Dialogx.kt
[DialogxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/DialogxTest.kt

[Fragmentx.java]: androidx/src/main/java/me/panpf/androidx/app/Fragmentx.java
[FragmentxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/FragmentxTest.java
[Fragmentx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Fragmentx.kt
[FragmentxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/FragmentxTest.kt

[Permissionx.java]: androidx/src/main/java/me/panpf/androidx/app/Permissionx.java
[PermissionxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/PermissionxTest.java
[Permissionx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Permissionx.kt
[PermissionxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/PermissionxTest.kt

[Servicex.java]: androidx/src/main/java/me/panpf/androidx/app/Servicex.java
[ServicexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/app/ServicexTest.java
[Servicex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/app/Servicex.kt
[ServicexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ServicexTest.kt

[Packagex.java]: androidx/src/main/java/me/panpf/androidx/content/pm/Packagex.java
[PackagexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/pm/PackagexTest.java
[Packagex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/pm/Packagex.kt
[PackagexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/pm/PackagexTest.kt

[Assetx.java]: androidx/src/main/java/me/panpf/androidx/content/res/Assetx.java
[AssetxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/res/AssetxTest.java
[Assetx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/res/Assetx.kt
[AssetxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/res/AssetxTest.kt

[Contentx.java]: androidx/src/main/java/me/panpf/androidx/content/Contentx.java
[ContentxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/ContentxTest.java
[Contentx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Contentx.kt
[ContentxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ContentxTest.kt

[Contextx.java]: androidx/src/main/java/me/panpf/androidx/content/Contextx.java
[ContextxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/ContextxTest.java
[Contextx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Contextx.kt
[ContextxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ContextxTest.kt

[Clipboardx.java]: androidx/src/main/java/me/panpf/androidx/content/Clipboardx.java
[ClipboardxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/ClipboardxTest.java
[Clipboardx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Clipboardx.kt
[ClipboardxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ClipboardxTest.kt

[Intentx.java]: androidx/src/main/java/me/panpf/androidx/content/Intentx.java
[IntentxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/IntentxTest.java
[Intentx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Intentx.kt
[IntentxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/IntentxTest.kt

[Preferencex.java]: androidx/src/main/java/me/panpf/androidx/content/Preferencex.java
[PreferencexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/content/PreferencexTest.java
[Preferencex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/content/Preferencex.kt
[PreferencexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/PreferencexTest.kt

[Drawablex.java]: androidx/src/main/java/me/panpf/androidx/graphics/drawable/Drawablex.java
[DrawablexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/drawable/DrawablexTest.java
[Drawablex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/drawable/Drawablex.kt
[DrawablexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/drawable/DrawablexTest.kt

[Bitmapx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Bitmapx.java
[BitmapxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/BitmapxTest.java
[Bitmapx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Bitmapx.kt
[BitmapxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/BitmapxTest.kt

[Imagex.java]: androidx/src/main/java/me/panpf/androidx/graphics/Imagex.java
[ImagexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/ImagexTest.java
[Imagex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Imagex.kt
[ImagexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/ImagexTest.kt

[Colorx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Colorx.java
[ColorxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/ColorxTest.java
[Colorx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Colorx.kt
[ColorxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/ColorxTest.kt

[Matrixx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Matrixx.java
[MatrixxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/MatrixxTest.java
[Matrixx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Matrixx.kt
[MatrixxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/MatrixxTest.kt

[OpenGlx.java]: androidx/src/main/java/me/panpf/androidx/graphics/OpenGlx.java
[OpenGlxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/OpenGlxTest.java
[OpenGlx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/OpenGlx.kt
[OpenGlxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/OpenGlxTest.kt

[Paintx.java]: androidx/src/main/java/me/panpf/androidx/graphics/Paintx.java
[PaintxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/graphics/PaintxTest.java
[Paintx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Paintx.kt
[PaintxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/PaintxTest.kt

[Hardwarex.java]: androidx/src/main/java/me/panpf/androidx/hardware/Hardwarex.java
[HardwarexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/hardware/HardwarexTest.java
[Hardwarex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/hardware/Hardwarex.kt
[HardwarexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/hardware/HardwarexTest.kt

[NetworkState.java]: androidx/src/main/java/me/panpf/androidx/net/NetworkState.java
[NetworkStateTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkStateTest.java
[NetworkState.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/net/NetworkState.kt
[NetworkStateTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkStateTest.kt

[Networkx.java]: androidx/src/main/java/me/panpf/androidx/net/Networkx.java
[NetworkxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkxTest.java
[Networkx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/net/Networkx.kt
[NetworkxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkxTest.kt

[Storagex.java]: androidx/src/main/java/me/panpf/androidx/os/storage/Storagex.java
[StoragexTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StoragexTest.java
[Storagex.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/Storagex.kt
[StoragexTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StoragexTest.kt

[StorageManagerCompat.java]: androidx/src/main/java/me/panpf/androidx/os/storage/StorageManagerCompat.java
[StorageManagerCompatTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageManagerCompatTest.java
[StorageManagerCompat.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/StorageManagerCompat.kt
[StorageManagerCompatTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageManagerCompatTest.kt

[StorageVolumeCompat.java]: androidx/src/main/java/me/panpf/androidx/os/storage/StorageVolumeCompat.java
[StorageVolumeCompatTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageVolumeCompatTest.java
[StorageVolumeCompat.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/StorageVolumeCompat.kt
[StorageVolumeCompatTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageVolumeCompatTest.kt

[StatFsx.java]: androidx/src/main/java/me/panpf/androidx/os/StatFsx.java
[StatFsxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/StatFsxTest.java
[StatFsx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/StatFsx.kt
[StatFsxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/StatFsxTest.kt

[SystemPropertiesx.java]: androidx/src/main/java/me/panpf/androidx/os/SystemPropertiesx.java
[SystemPropertiesxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/SystemPropertiesxTest.java
[SystemPropertiesx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/SystemPropertiesx.kt
[SystemPropertiesxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/SystemPropertiesxTest.kt

[Romx.java]: androidx/src/main/java/me/panpf/androidx/os/Romx.java
[RomxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/os/RomxTest.java
[Romx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/os/Romx.kt
[RomxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/RomxTest.kt

[Settingsx.java]: androidx/src/main/java/me/panpf/androidx/provider/Settingsx.java
[SettingsxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/provider/SettingsxTest.java
[Settingsx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/provider/Settingsx.kt
[SettingsxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/provider/SettingsxTest.kt

[Dimenx.java]: androidx/src/main/java/me/panpf/androidx/util/Dimenx.java
[DimenxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/DimenxTest.java
[Dimenx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Dimenx.kt
[DimenxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/DimenxTest.kt

[Jsonx.java]: androidx/src/main/java/me/panpf/androidx/util/Jsonx.java
[JsonxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/JsonxTest.java
[Jsonx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Jsonx.kt
[JsonxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/JsonxTest.kt

[Textx.java]: androidx/src/main/java/me/panpf/androidx/util/Textx.java
[TextxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/TextxTest.java
[Textx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/Textx.kt
[TextxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/TextxTest.kt

[InputMethodx.java]: androidx/src/main/java/me/panpf/androidx/view/inputmethod/InputMethodx.java
[InputMethodxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/inputmethod/InputMethodxTest.java
[InputMethodx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/inputmethod/InputMethodx.kt
[InputMethodxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/inputmethod/InputMethodxTest.kt

[Displayx.java]: androidx/src/main/java/me/panpf/androidx/view/Displayx.java
[DisplayxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/DisplayxTest.java
[Displayx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/Displayx.kt
[DisplayxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/DisplayxTest.kt

[ViewAnimx.java]: androidx/src/main/java/me/panpf/androidx/view/ViewAnimx.java
[ViewAnimxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewAnimxTest.java
[ViewAnimx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/ViewAnimx.kt
[ViewAnimxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewAnimxTest.kt

[Viewx.java]: androidx/src/main/java/me/panpf/androidx/view/Viewx.java
[ViewxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewxTest.java
[Viewx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/Viewx.kt
[ViewxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewxTest.kt

[Windowx.java]: androidx/src/main/java/me/panpf/androidx/view/Windowx.java
[WindowxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/view/WindowxTest.java
[Windowx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/view/Windowx.kt
[WindowxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/WindowxTest.kt

[Toastx.java]: androidx/src/main/java/me/panpf/androidx/widget/Toastx.java
[ToastxTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/widget/ToastxTest.java
[Toastx.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/widget/Toastx.kt
[ToastxTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/widget/ToastxTest.kt

[WeakAsyncTask.java]: androidx/src/main/java/me/panpf/androidx/util/WeakAsyncTask.java
[WeakAsyncTaskTest.java]: androidx/src/androidTest/java/me/panpf/androidx/test/util/WeakAsyncTaskTest.java
[WeakAsyncTask.kt]: androidx-kt/src/main/java/me/panpf/androidxkt/util/WeakAsyncTask.kt
[WeakAsyncTaskTest.kt]: androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/WeakAsyncTaskTest.kt
