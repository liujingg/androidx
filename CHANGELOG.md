## v0.4.0

* :zap: Romx support Flyme 7.0.1
* :sparkles: Fragmentx adds requireContext, requireAppContent method
* :sparkles: Activityx adds appContext method
* :sparkles: Viewx adds appContext method
* :fire: Intentx canStartActivity and safeStartActivity method removed to Activityx and rename to canStart and safeStart
* :sparkles: Activityx adds start method


## v0.3.2

* :arrow_up: Javax upgrade to 0.8.0
* :fire: Networkx removed getLocalIpAddress, getLocalIpV4Address method
* :sparkles: Bitmapx.kt adds use method


## v.0.3.1

ANDROIDX

* :fire: Activityx and Fragmentx read\*Arg(\*, default) rename to read\*ArgOr(\*, default)
* :fire: Activityx and Fragmentx readOptional\*Arg(\*, default) rename to read\*ArgOrNull(\*, default)
* :sparkles: Activityx adds read\*UriIntentArg and read\*IntentUriArg method
* :sparkles: Contextx adds midiManagerOrNull method
* :arrow_up: Javax upgrade to 0.7.1

ANDROIDX-KT-ARGS

* :fire: bind\*Arg(\*, default) rename to bind\*ArgOr(\*, default)
* :fire: bindOptional\*Arg(\*, default) rename to bind\*ArgOrNull(\*, default)
* :sparkles: Activity adds bind\*UriIntentArgOr and bind\*IntentUriArgOr method
* :sparkles: Activity and Fragment adds bindCharSequenceArrayListArg, bindCharSequenceArrayListArgOr, bindCharSequenceArrayListArgOrNull method

## v0.3.0

* :arrow_up: Kotlin upgrade to 1.2.71
* :arrow_up: Javax upgrade to 0.7.0
* :arrow_up: support-fragment upgrade to 28.0.0
* :arrow_up: lifecycle upgrade to 1.1.1


## v0.2.7

* :sparkles: Storagex adds getFileIn method

## v0.2.6

ANDROIDX

Fixed some bugs:
* :bug: Fixed Storagex getMountedVolumes method StackOverflow error

Renamed or deleted the following:
* :fire: Romx all `is*Type` method rename to `is*`

Added the following:
* :sparkles: Storagex add getFreeBytes(File, long), getTotalBytes(File, long), getAvailableBytes(File, long) method
* :sparkles: Perfect Contextx
* :sparkles: Androidx adds `read*Arg`, `read*UriArg` method
* :sparkles: Fragmentx adds `read*Arg` method

ANDROIDX-KT-ARGS

* :fire: Remove bindOptionalByteArg, bindOptionalShortArg, bindOptionalIntArg, bindOptionalLongArg,
    bindOptionalFloatArg, bindOptionalDoubleArg, bindOptionalBooleanArg, bindOptionalCharArg method
* :sparkles: Adds `bind*UriArg` method

## v0.2.5

* :sparkles: Networdx add isActivated, isWifiActivated, isNoMeteredWifiActivated,
    isMobileActivated, isBluetoothActivated, isVPNActivated, isMetered, isRoaming,
    isFailover, getType, getTypeName, getSubtypeName, getExtraInfo, getNetworkInfo, getConnectivity
* :sparkles: Fragmentx add findUserVisibleChildFragment, findFragmentByViewPagerCurrentItem method
* :sparkles: Bitmapx add circularTo, circular, centerCropTo method and add related overloaded methods
* :sparkles: Add Imagex
* :sparkles: Add Servicex
* :sparkles: Add Romx
* :sparkles: Add Contextx
* :sparkles: Androidx add waitRunInUI
* :bug: Fixed bug where Activityx convertToTranslucent method is invalid
* :bug: Fixed a bug that caused a context.getExternalCacheDirs() and context.getExternalFilesDirs() to return null
* :zap: Activityx isDestroyedCompat method is compatible with isFinishing after JELLY_BEAN_MR1

Upgrade
* :arrow_up: Upgrade Javax 0.6.9


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
* :sparkles: Text adds the keywordMadeColorBySpannable() method
* :fire: Refactoring Color
* :sparkles: Color adds argbEvaluate method
* :sparkles: Add WeakAsyncTask
* :fire: Build, Process, System, Thread merge into Android
* :sparkles: Add Clipboard
* :fire: Bitmap makeBitmapByColor method renamed to create
* :fire: Refactoring Drawable
* :sparkles: Fragment add instance method
* :sparkles: Json adds format method
* :sparkles: Window adds the hasNavigationBar, getStatusBarHeight, getNavigationBarWidth methods
* :sparkles: Permission adds filterDeniedPermissions method
* :fire: CanStartActivity adds safeStartActivity in Fragment are moved to Intent
* :fire: Rename Package.get to getPackage
* :fire: Simplify ViewAnim all method names
* :arrow_up: Upgrade Javax 0.6.2


## v0.1
* Initial release