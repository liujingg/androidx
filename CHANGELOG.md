## new
* remove: Remove the androidx-kt-arch library, because the official lifecycle-viewmodel-ktx library already provides the same function
* change: Toastx.show\*(\*, \*, Object...) method rename to showWithFormat\*
* change: Toastx.show\*(\*, String) method change to show\*(\*, CharSequence)
* fix: Fix the bug that Toastx.show\*(\*, int) method may not get multilingual information
* fix: Fix the bug that there may be null elements in the array returned by Storagex.getAppExternal\*Dirs() method
* new: Storagex.clean\*Dirs() method now returns boolean
* change: LifecycleBroadcastReceiver can now register multiple times
* improve: Improved Argsx's readByteArgOr, readShortArgOr, readIntArgOr, readLongArgOr, readFloatArgOr, readDoubleArgOr, readBooleanArgOr, readCharArgOr methods

## v1.0.0-alpha09
* remove: Remove dependency on javax
* remove: Remove Settingx.getMediaVolume(), setMediaVolume(), getRingVolume(), setRingVolume() method
* arrow_up: Fragment upgrade to 1.2.0
* arrow_up: Lifecycle upgrade to 2.2.0
* arrow_up: Kotlin stblib upgrade to 1.3.61
* compatibility: Compatible with android 10

## v1.0.0-alpha08

* fix: Fix Hardware.getMacAddress() method return mac address segment length abnormal bug
* new: Added SingletonLazy for delayed production of singleton objects
* downgrade: Fragment downgrade to 1.1.0

## v1.0.0-alpha07

* fix: Fix some strange bugs caused by Context.getSystemService method to force ApplicationContext to get all services

## v1.0.0-alpha06

* fix: Fix Argsx.get \* UriArgOr \* series methods crash when encountering inteng.getData() returning null
* fix: Fix NetworkState getTypeName (), getSubtypeName (), getExtraInfo () method may return null
* new: New ApplicationSingletonLazy class for singleton implementation

## v1.0.0-alpha05

Rename
* :fire: SystemPropertiesx.get(String, String) rename to getOr(String, String) 
* :fire: SystemPropertiesx.getInt(String, String) rename to getIntOr(String, String) 
* :fire: SystemPropertiesx.getLong(String, String) rename to getLongOr(String, String) 
* :fire: SystemPropertiesx.getBoolean(String, String) rename to getBooleanOr(String, String) 
* :fire: Storagex.getFreeBytes(File, long) rename to getFreeBytesOr(File, long)
* :fire: Storagex.getTotalBytes(File, long) rename to getTotalBytesOr(File, long)
* :fire: Storagex.getAvailableBytes(File, long) rename to getAvailableBytesOr(File, long)
* :fire: PackageType rename to AcceptPackageType

Remove
* :fire: Remove Servicex.startIfNoRunning and stopIfRunning method
* :fire: Remove Storagex.getFreeBytes(File) method
* :fire: Remove Storagex.getTotalBytes(File) method
* :fire: Remove Storagex.getAvailableBytes(File) method

Update
* :fire: Servicex.isRunning(Context, Class) add String parameter

New
* :sparkles: Servicex adds isAccessibilityServiceEnabled method
* :sparkles: Fragmentx adds getApplication and requireApplication method
* :sparkles: Views adds addPaddingTopByStatusBarHeight method

Upgrade
* :arrow_up: Upgrade javax 1.0.0-alpha04

## v1.0.0-alpha4

Upgrade
* :arrow_up: Kotlin upgrade to 1.3.21
* :arrow_up: Javax upgrade to 1.0.0-alpha3
* :arrow_up: Fragment upgrade to 1.2.0-alpha01

Removed
* :fire: Remove the Jsonx related code, which is now a standalone project: https://github.com/panpf/jsonx

Rename
* :fire: Activityx start(*, Class<? extends Activity>) method rename to startByClass(*, Class<? extends Activity>)
* :fire: Packagex getPackage rename to get, getPackageOrNull rename to getOrNull, listPackageNameAndVersionCode rename to listVersionCodePair, listPackageNameAndVersionCodeMap rename to getVersionCodeMap, listPackage rename to list, getOnePackage rename to getOne
* :fire: Fragmentx and Activityx getImplWithParent method rename to getImplFromParent

New
* :sparkles: Displayx adds getActionBarSize() method
* :sparkles: Bitmapx adds crop, cropTo method
* :sparkles: Packagex adds requestInstall, requestUninstall method

Behavior change:
* :fire: Intentx createInstallAppIntent method add RequiresPermission(REQUEST_INSTALL_PACKAGES) annotated
* :fire: Fragmentx de isDestroyedCompat method also returns true when the Lifecycle state is INITIALIZED

Update
* :fire: The excludeSystemApp and excludeSelf parameters of the Package* list*, getOne, count methods are combined into one packageType

Fixed bug
* :bug: Fix Fragmentx and Activityx getImplWithParent method always returns a bug in the interface class

## v1.0.0-alpha3

Behavior change:
* :sparkles: The Fragmentx.instantiate method now returns the generic type specified by Class
* :zap: Argsx supports attempting to parse its String type data when reading data of the underlying type


## v1.0.0-alpha2

Behavior change:
* :fire: The Argsx.read\*Uri\* family of methods no longer checks if the Intent action is ACTION_VIEW

Upgrade the following dependencies:
* :arrow_up: Upgrade Javax 1.0.0-alpha2


## v1.0.0-alpha1

Fix the following bug:
* :bug: Fix a bug where Networkx isMetered actually called the isVPNActivated method

Delete or rename the following methods:
* :fire: Fragmentx instance rename to instantiate, instanceOrigin rename to instantiateOrigin
* :fire: Servicex tryStart rename to startIfNoRunning, tryStop rename to stopIfRunning
* :fire: Remove Assetx
* :fire: Remove Contentx
* :fire: Refactoring Preferencex
* :fire: Refactoring Packagex
* :fire: Dimenx unit2px rename to applyDimension
* :fire: Settingsx getWindowBrightness, setWindowBrightness, isWindowBrightnessFlowSystem move to Windowx
* :fire: Activityx appContext, Fragmentx requireContext and requireAppContext, Viewx appContext move to Contextx
* :fire: Networkx remove getConnectivity method
* :fire: Networkx.kt isWifiEnabled, setWifiEnabled, isMobileEnabled, setMobileEnabled, getGateway method rename to isWifiNetworkEnabled, setWifiNetworkEnabled, isMobileNetworkEnabled, setMobileNetworkEnabled, getNetworkGateway
* :fire: The default return value for some methods of NetworkState is changed from 'Unknown' to 'unknown'
* :fire: Drawablex toDrawableByColor rename to changeColor, toDrawableByColorFromDrawableRes rename to changeResDrawableColor
* :fire: Bitmapx remove toDrawableByColor

Add the following methods or tool classes:
* :sparkles: Adds BundleBuilder
* :sparkles: Bitmapx adds toDrawable
* :sparkles: Add PackageInfox
* :sparkles: Intentx adds getShareFileUri adds
* :sparkles: Adds LifecycleBroadcastReceiver

Complete the following tool classes of tests:
* :white_check_mark: Perfect Intentx testing
* :white_check_mark: Perfect Dialogx testing
* :white_check_mark: Perfect Fragmentx testing
* :white_check_mark: Perfect Servicex testing
* :white_check_mark: Perfect Assetx testing
* :white_check_mark: Prefect Preferencex testing
* :white_check_mark: Prefect Packagex testing
* :white_check_mark: Prefect Dimenx testing
* :white_check_mark: Prefect Jsonx testing
* :white_check_mark: Prefect Textx testing
* :white_check_mark: Prefect Settingsx testing
* :white_check_mark: Prefect Viewx testing
* :white_check_mark: Prefect Displayx testing
* :white_check_mark: Prefect ViewAnimx testing
* :white_check_mark: Prefect InputMethodx testing
* :white_check_mark: Prefect Resizex testing
* :white_check_mark: Prefect Argsx and ArgsBinder testing
* :white_check_mark: Prefect Networkx testing
* :white_check_mark: Prefect NetworkState testing
* :white_check_mark: Prefect Matrixx testing
* :white_check_mark: Prefect OpenGlx testing
* :white_check_mark: Prefect Paintx testing
* :white_check_mark: Prefect Colorx testing
* :white_check_mark: Prefect Imagex testing

Upgrade the following dependencies:
* :arrow_up: Upgrade to Jetpack
* :arrow_up: Upgrade Javax 1.0.0-alpha1

## v0.6.0

Fix bug:
* :bug: Fix bug with uri error generated by Intentx createUninstallAppIntent method

Rename:
* :fire: Androix waitRunInUI(ResultRunnable) rename to waitRunInUIResult(ResultRunnable), waitRunInUI(NullableResultRunnable) rename to waitRunInUINullableResult(NullableResultRunnable)
* :fire: ResizeCalculator rename to Resizex
* :fire: Activityx.java convertToTranslucent method rename to convertToTranslucentCompat, convertFromTranslucent method rename to convertFromTranslucentCompat

Behavior change:
* :fire: Activityx all start method may now throw ActivityNotFoundException
* :fire: The parameters of the Bundle type of the Activityx start(\*, Class, Bundle) method are now used as pass parameters
* :fire: Activityx convertToTranslucentCompat and convertFromTranslucentCompat add @RequiresApi(Build.VERSION_CODES.KITKAT) annotation

Removed:
* :fire: Activityx remove safeStart(Context, Intent, Bundle) method

Add:
* :sparkles: Activityx adds safeStart(\*, Class, Bundle) and safeStart(\*, Class) method

Perfect test:
* :white_check_mark: Perfect Activityx testing
* :white_check_mark: Perfect Colorx testing
* :white_check_mark: Perfect Drawablex testing
* :white_check_mark: Perfect Hardwarex testing
* :white_check_mark: Perfect Toastx testing

Upgrade
* :arrow_up: Upgrade Javax 0.8.5


## v0.5.1

* :arrow_up: Javax upgrade to 0.8.4

## v0.5.0

Fix bug
* :bug: Fix bug where Contextx's captioningManager method crashes in non-main thread

Renamed or deleted the following:
* :fire: Activityx.read\*Arg, Fragmentx.read\*Arg merge to Argsx
* :fire: Deleted androidx-kt-args module, bind\*Arg method moved to androidx-kt module ArgsBinder.kt
* :fire: Hardwordx's getPhoneNumber, getDeviceId, getAndroidId, getSubscriberId, getSimSerialNumber, getIMEI, getIMSI methods now return 'unknown' by default
* :fire: Hardwordx.java getDeviceModel rename to getModel and getDeviceName rename to getDevice
* :fire: Hardwordx.kt removed getDeviceModel, getDeviceName, getHardware, getSupportedAbis, getSerial method
* :fire: Romx removed getBuildProperties method

New
* :sparkles: Bitmapx adds calculateSamplingSize, calculateSamplingSizeForRegion method
* :sparkles: Argsx.read\*Arg method supported string res
* :sparkles: ArgsBinder.bind\*Arg method supported string res
* :sparkles: Hardwordx's adds getProduct, getBrand method
* :sparkles: Add SystemPropertiesx

Upgrade
* :arrow_up: Upgrade Javax 0.8.3


## v0.4.0

Renamed or deleted the following:
* :fire: Jsonx.kt format method rename to formatJson
* :fire: Intentx canStartActivity and safeStartActivity method removed to Activityx and rename to canStart and safeStart

New
* :sparkles: Romx support Flyme 7.0.1
* :sparkles: Fragmentx adds requireContext, requireAppContent method
* :sparkles: Activityx adds appContext method
* :sparkles: Viewx adds appContext method
* :sparkles: Activityx adds start method
* :sparkles: Networkx adds getGateway, getDNS1, getDNS2 method
* :sparkles: Intentx adds createSendTextIntent, createSendTextFileIntent, createSendImageFileIntent, createSendFileIntent method

Other
* :zap: Networkx getWifiState and setWifiEnabled method compatible WifiManager is null
* :art: Contextx.kt and Viewx.kt partial method changed to inline
* :hammer: @NotNull replace to @NonNull

Upgrade
* :arrow_up: Upgrade Javax 0.8.1


## v0.3.2

* :fire: Networkx removed getLocalIpAddress, getLocalIpV4Address method
* :sparkles: Bitmapx.kt adds use method

Upgrade
* :arrow_up: Upgrade Javax0.8.0


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

Upgrade
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