# ANDROIDX

![Platform][platform_image]
[![API][min_api_image]][min_api_link]
[![License][license_image]][license_link]

对 Android 标准库和支持库的扩展

### 导入

#### Java 版：
```grovvy
compile "me.panpf:android:$lastVersion"
```

请自行替换 `$lastVersion` 为最新的版本 [![Download][VersionBadgeIcon]][VersionBadgeLink]

依赖：
* com.android.support:support-fragment: 27.1.1
* me.panpf:[javax][javax]: 0.6

#### Kotlin 版：

```grovvy
compile "me.panpf:android-kt:$lastVersion"
```

请自行替换 `$lastVersion` 为最新的版本 [![Download][KTVersionBadgeIcon]][KTVersionBadgeLink]

依赖：
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.2.50
* com.android.support:support-fragment: 27.1.1
* me.panpf:[javax-kt][javax]: 0.6

你可以根据需要选择一个版本导入，如果你的项目是 Java 加 Kotlin 混合开发的那么两个版本都可以导入，因为两个版本采用了不同的包名所以不会有类重复问题

### 索引

* Android: [Androidx.java] | [Android.kt]

#### app
* Activity: [Activityx.java] | [Activity.kt]
* Dialog: [Dialogx.java] | [Dialog.kt]
* Fragment: [Fragmentx.java] | [Fragment.kt]
* Permission: [Permissionx.java] ([Test][PermissionTest.java]) | [Permission.kt] ([Test][PermissionTest.kt])

#### content
* Package: [Packagex.java] | [Package.kt]
* Asset: [Assetx.java] | [Asset.kt]
* Content: [Contentx.java] | [Content.kt]
* Intent: [Intentx.java] | [Intent.kt]
* Preference: [Preferencex.java] | [Preference.kt]

#### graphics
* Drawable: [Drawablex.java] | [Drawable.kt]
* Bitmap: [Bitmapx.java] | [Bitmap.kt]
* Color: [Colorx.java] ([Test][ColorTest.java]) | [Color.kt] ([Test][ColorTest.kt])
* Matrix: [Matrixx.java] | [Matrix.kt]
* OpenGl: [OpenGlx.java] | [OpenGl.kt]
* Paint: [Paintx.java] | [Paint.kt]

#### hardware
* Hardware: [Hardwarex.java] ([Test][HardwareTest.java]) | [Hardware.kt] ([Test][HardwareTest.kt])

#### net
* NetworkState: [NetworkState.java] | [NetworkState.kt]
* Network: [Networkx.java] | [Network.kt]

#### os
* Storage: [Storagex.java] | [Storage.kt]
* StatFs: [StatFsx.java] | [StatFs.kt]

#### provider
* Settings: [Settingsx.java] | [Settings.kt]

#### util
* Dimen: [Dimenx.java] | [Dimen.kt]
* Json: [Jsonx.java] | [Json.kt]
* Text: [Textx.java] | [Text.kt]
* WeakAsyncTask: [WeakAsyncTask.java] ([Test][WeakAsyncTaskTest.java]) | [WeakAsyncTask.kt] ([Test][WeakAsyncTaskTest.kt])

#### view
* InputMethod: [InputMethodx.java] | [InputMethod.kt]
* Display: [Displayx.java] | [Display.kt]
* ViewAnim: [ViewAnimx.java] | [ViewAnim.kt]
* View: [Viewx.java] | [View.kt]
* Window: [Windowx.java] | [Window.kt]

#### widget
* Toast: [Toastx.java] ([Test][ToastTest.java]) | [Toast.kt] ([Test][ToastTest.java])

### 更新日志

请查看 [CHANGELOG.md] 文件


### License
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

[platform_image]: https://img.shields.io/badge/Platform-Android-brightgreen.svg
[min_api_image]: https://img.shields.io/badge/API-14%2B-orange.svg
[min_api_link]: https://android-arsenal.com/api?level=14
[license_image]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0
[VersionBadgeIcon]: https://api.bintray.com/packages/panpf/maven/androidx/images/download.svg
[VersionBadgeLink]:https://bintray.com/panpf/maven/androidx/_latestVersion
[KTVersionBadgeIcon]: https://api.bintray.com/packages/panpf/maven/androidx-kt/images/download.svg
[KTVersionBadgeLink]:https://bintray.com/panpf/maven/androidx-kt/_latestVersion
[CHANGELOG.md]: CHANGELOG.md
[javax]: https://github.com/panpf/javax

[Androidx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/Androidx.java
[AndroidTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/AndroidTest.java
[Android.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/Android.kt
[AndroidTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/AndroidTest.kt

[Activityx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/app/Activityx.java
[ActivityTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/app/ActivityTest.java
[Activity.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/app/Activity.kt
[ActivityTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/ActivityTest.kt

[Dialogx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/app/Dialogx.java
[DialogTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/app/DialogTest.java
[Dialog.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/app/Dialog.kt
[DialogTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/DialogTest.kt

[Fragmentx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/app/Fragmentx.java
[FragmentTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/app/FragmentTest.java
[Fragment.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/app/Fragment.kt
[FragmentTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/FragmentTest.kt

[Permissionx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/app/Permissionx.java
[PermissionTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/app/PermissionTest.java
[Permission.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/app/Permission.kt
[PermissionTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/app/PermissionTest.kt

[Packagex.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/content/pm/Packagex.java
[PackageTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/content/pm/PackageTest.java
[Package.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/content/pm/Package.kt
[PackageTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/pm/PackageTest.kt

[Assetx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/content/res/Assetx.java
[AssetTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/content/res/AssetTest.java
[Asset.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/content/res/Asset.kt
[AssetTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/res/AssetTest.kt

[Contentx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/content/Contentx.java
[ContentTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/content/ContentTest.java
[Content.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/content/Content.kt
[ContentTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/ContentTest.kt

[Intentx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/content/Intentx.java
[IntentTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/content/IntentTest.java
[Intent.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/content/Intent.kt
[IntentTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/IntentTest.kt

[Preferencex.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/content/Preferencex.java
[PreferenceTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/content/PreferenceTest.java
[Preference.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/content/Preference.kt
[PreferenceTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/content/PreferenceTest.kt

[Drawablex.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/drawable/Drawablex.java
[DrawableTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/drawable/DrawableTest.java
[Drawable.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/drawable/Drawable.kt
[DrawableTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/drawable/DrawableTest.kt

[Bitmapx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/Bitmapx.java
[BitmapTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/BitmapTest.java
[Bitmap.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Bitmap.kt
[BitmapTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/BitmapTest.kt

[Colorx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/Colorx.java
[ColorTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/ColorTest.java
[Color.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Color.kt
[ColorTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/ColorTest.kt

[Matrixx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/Matrixx.java
[MatrixTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/MatrixTest.java
[Matrix.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Matrix.kt
[MatrixTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/MatrixTest.kt

[OpenGlx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/OpenGlx.java
[OpenGlTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/OpenGlTest.java
[OpenGl.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/OpenGl.kt
[OpenGlTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/OpenGlTest.kt

[Paintx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/graphics/Paintx.java
[PaintTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/graphics/PaintTest.java
[Paint.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/graphics/Paint.kt
[PaintTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/graphics/PaintTest.kt

[Hardwarex.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/hardware/Hardwarex.java
[HardwareTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/hardware/HardwareTest.java
[Hardware.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/hardware/Hardware.kt
[HardwareTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/hardware/HardwareTest.kt

[NetworkState.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/net/NetworkState.java
[NetworkStateTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkStateTest.java
[NetworkState.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/net/NetworkState.kt
[NetworkStateTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkStateTest.kt

[Networkx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/net/Networkx.java
[NetworkTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/net/NetworkTest.java
[Network.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/net/Network.kt
[NetworkTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/net/NetworkTest.kt

[Storagex.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/os/storage/Storagex.java
[StorageTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/os/storage/StorageTest.java
[Storage.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/os/storage/Storage.kt
[StorageTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/storage/StorageTest.kt

[StatFsx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/os/StatFsx.java
[StatFsTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/os/StatFsTest.java
[StatFs.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/os/StatFs.kt
[StatFsTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/os/StatFsTest.kt

[Settingsx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/provider/Settingsx.java
[SettingsTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/provider/SettingsTest.java
[Settings.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/provider/Settings.kt
[SettingsTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/provider/SettingsTest.kt

[Dimenx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/util/Dimenx.java
[DimenTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/util/DimenTest.java
[Dimen.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/util/Dimen.kt
[DimenTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/DimenTest.kt

[Jsonx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/util/Jsonx.java
[JsonTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/util/JsonTest.java
[Json.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/util/Json.kt
[JsonTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/JsonTest.kt

[Textx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/util/Textx.java
[TextTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/util/TextTest.java
[Text.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/util/Text.kt
[TextTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/TextTest.kt

[InputMethodx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/view/inputmethod/InputMethodx.java
[InputMethodTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/view/inputmethod/InputMethodTest.java
[InputMethod.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/view/inputmethod/InputMethod.kt
[InputMethodTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/inputmethod/InputMethodTest.kt

[Displayx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/view/Displayx.java
[DisplayTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/view/DisplayTest.java
[Display.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/view/Display.kt
[DisplayTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/DisplayTest.kt

[ViewAnimx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/view/ViewAnimx.java
[ViewAnimTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewAnimTest.java
[ViewAnim.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/view/ViewAnim.kt
[ViewAnimTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewAnimTest.kt

[Viewx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/view/Viewx.java
[ViewTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/view/ViewTest.java
[View.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/view/View.kt
[ViewTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/ViewTest.kt

[Windowx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/view/Windowx.java
[WindowTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/view/WindowTest.java
[Window.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/view/Window.kt
[WindowTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/view/WindowTest.kt

[Toastx.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/widget/Toastx.java
[ToastTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/widget/ToastTest.java
[Toast.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/widget/Toast.kt
[ToastTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/widget/ToastTest.kt

[WeakAsyncTask.java]: https://github.com/panpf/androidx/blob/master/androidx/src/main/java/me/panpf/androidx/util/WeakAsyncTask.java
[WeakAsyncTaskTest.java]: https://github.com/panpf/androidx/blob/master/androidx/src/androidTest/java/me/panpf/androidx/test/util/WeakAsyncTaskTest.java
[WeakAsyncTask.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/main/java/me/panpf/androidxkt/util/WeakAsyncTask.kt
[WeakAsyncTaskTest.kt]: https://github.com/panpf/androidx/blob/master/androidx-kt/src/androidTest/java/me/panpf/androidxkt/test/util/WeakAsyncTaskTest.kt



