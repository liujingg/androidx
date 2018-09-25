# ANDROIDX-KT-ARCH

[![Platform][platform_android_icon]][platform_android_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
[![API][min_api_icon]][min_api_link]
[![License][license_icon]][license_link]

Kotlin extension to Android Architecture related libraries

## Getting Started

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx-kt-arch:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_icon]][version_link]

Dependencies:
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.2.50
* com.android.support:support-fragment: 27.1.1
* android.arch.lifecycle:viewmodel: 1.1.0
* android.arch.lifecycle:extensions: 1.1.0

## Usage Guide

### ViewModelBinder

Provide ViewModel binding capabilities as follows:

```kotlin
class TestActivity : FragmentActivity() {

    val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)
}
```

Use ViewModelProvider.Factory:

```kotlin
class TestActivity : FragmentActivity() {

    val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModel")
        }
    })
}
```

`See the test code for more examples.`

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
[version_icon]: https://api.bintray.com/packages/panpf/maven/androidx-kt-arch/images/download.svg
[version_link]: https://bintray.com/panpf/maven/androidx-kt-arch/_latestVersion

[CHANGELOG.md]: CHANGELOG.md
