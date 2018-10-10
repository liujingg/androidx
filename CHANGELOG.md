## v0.2.5

* :sparkles: Networdx add isActivated, isWifiActivated, isNoMeteredWifiActivated,
    isMobileActivated, isBluetoothActivated, isVPNActivated, isMetered, isRoaming,
    isFailover, getType, getTypeName, getSubtypeName, getExtraInfo, getNetworkInfo, getConnectivity

Upgrade
* :arrow_up: Upgrade Javax 0.6.7



## v0.2.4

Bug
* :bug: Fix StorageManagerCompat's getVolumeList and getStorageVolumes methods return inconsistent bugs on the 8.1 emulator

Removed or Renamed
* :fire: Displayx getDisplayMetrics rename to getScreenMetrics, getDensity rename to getDensity, getScreenDensityDpi rename to getDensityDpi, isPortraitOrientation rename to isOrientationPortrait
* :fire: Windowx remove isLandscape, isPortrait method
* :fire: Windowx getDisplayRotation, getStatusBarHeight, hasNavigationBar, getNavigationBarHeight, getNavigationBarWidth method moved to Displayx
* :fire: Androidx remove isAtLeastI, isAtLeast14, isAtLeast4_0, isAtLeastIMR1, isAtLeast15, isAtLeast4_0_3 method
* :fire: Androidx getSdkVersionName rename to getVersionName
* :fire: StorageManagerCompat getStorageVolumes rename to getVolumes, getStorageVolume rename to getVolume
* :fire: Storagex getVolumeList method now returns List<StorageVolumeCompat>, increments getVolumes method returns StorageVolumeCompat\[\]
* :fire: Color All method names that have started with make are changed to create
* :fire: Intent All method names that have started with make are changed to create
* :fire: Some duplicate classes or constants have been removed. Please use the corresponding class or constant in androidx now.
    * ClipContent.kt, ClipHtmlText.kt, ClipIntent.kt, ClipPlainText.kt, ClipUri.kt,
    * NetworkState.kt, StorageManagerCompat.kt, StorageVolumeCompat.kt, WeakAsyncTask.kt

Hammer
* :hammer: Activityx.getImplWithParent method marked static
* :hammer: Now all methods in androidx-kt are extensions to androidx

New
* :sparkles: Displayx add isOrientationLandscape, isOrientationUndefined, getRotation method
* :sparkles: InputMethodx add delayShowSoftInput method
* :sparkles: Androidx add getVersionCodeName method
* :sparkles: Settingsx add getWindowBrightness, isWindowBrightnessFlowSystem method

Upgrade
* :arrow_up: Upgrade Javax 0.6.6

## v0.2.3
* :hammer: Network getIpAddress rename to getLocalIpAddress
* :sparkles: Network add getLocalIpV4Address
* :arrow_up: minSdkVersion rose to 16
* :sparkles: Add StorageManagerCompat and StorageVolumeCompat
* :hammer: StatFs getCompatAvailableBytes, getCompatFreeBytes, getCompatTotalBytes rename to getAvailableBytesCompat, getFreeBytesCompat, getTotalBytesCompat
* :hammer: Refactoring Storage
* :arrow_up: Upgrade Javax 0.6.5

## v0.2.2
* Upgrade Javax 0.6.4

## v0.2.1
* Activity and Fragment add isDestroyedCompat method
* Optimization WeakAsyncTask test script

## v0.2
* 增加 Text 增加 keywordMadeColorBySpannable() 方法用于将文件中关键词部分改变颜色
* 重构 Color
* 升级 Javax 依赖
* Color 增加 argbEvaluate 方法
* 增加 WeakAsyncTask
* Build、Process、System、Thread 合并到 Android 中
* 增加 Clipboard
* Bitmap makeBitmapByColor 方法重命名为 create
* Drawable 重构
* Fragment add instance method
* Json 增加 format 方法
* Window 增加 hasNavigationBar getStatusBarHeight getNavigationBarWidth 方法
* Permission 增加 filterDeniedPermissions
* Fragment 里的 canStartActivity 和 safeStartActivity 移动到 Intent 中
* Package.get 重命名为 getPackage
* ViewAnim 简化所有方法名
* 升级 Javax 0.6.2

## v0.1
* 初次发布