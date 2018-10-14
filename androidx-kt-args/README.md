# ANDROIDX-KT-ARGS

[![Platform][platform_android_icon]][platform_android_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
[![API][min_api_icon]][min_api_link]
[![License][license_icon]][license_link]

Extension of Activity and Fragment parameters based on Kotlin features

## Getting Started

Add the following to your `build.gradle` file

```grovvy
implementation "me.panpf:androidx-kt-args:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_icon]][version_link]

Dependencies:
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.2.61
* com.android.support:support-fragment: 27.1.1

## Usage Guide

### Read parameters from Activity Intent or Fragment Arguments

Define the properties to be accessed directly when they are used, as follows:

```kotlin
class TestBindActivity : FragmentActivity() {

    val booleanRequired by bindBooleanArg("booleanRequired")
    val byteRequired by bindByteArg("byteRequired")
    val charRequired by bindCharArg("charRequired")
    val shortRequired by bindShortArg("shortRequired")
    val floatRequired by bindFloatArg("floatRequired")
    val intRequired by bindIntArg("intRequired")
    val doubleRequired by bindDoubleArg("doubleRequired")
    val longRequired by bindLongArg("longRequired")
    val charSequenceRequired by bindCharSequenceArg("charSequenceRequired")
    val stringRequired by bindStringArg("stringRequired")
    val parcelableRequired by bindParcelableArg<TestParcelable>("parcelableRequired")
    val serializableRequired by bindSerializableArg<TestSerializable>("serializableRequired")

    override fun onCreate(savedInstanceState: Bundle?) {
        println(booleanRequired)
    }
}
```

Also supports `bindBooleanArrayArg` array binding, please see [ArgsBinder.kt] for more binding methods.

### Encapsulate all parameters into a data class

First define the parameter data class, then inherit ArgsActivity and configure the parameter generics as follows:

```kotlin
class TestArgsActivity : ArgsActivity<TestArgsActivity.Args>() {

    @Parcelize
    data class Args(val age: Int, val name: String) : Parcelable
}
```

You can then directly use the data defined in the parameter data class by using the args property defined in ArgsActivity, as follows:

```kotlin
class TestArgsActivity : ArgsActivity<TestArgsActivity.Args>() {

    @Parcelize
    data class Args(val age: Int, val name: String) : Parcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        println(args.age)
        println(args.name)
    }
}
```

You need to start TestArgsActivity like this:

```
val context: Cotext...
context.startArgsActivity(TestArgsActivity::class.java, TestArgsActivity.Args(21, "Kevin"))
```

There are four base classes available for inheritance:
* [ArgsActivity][ArgsActivity.kt]
* [ArgsFragmentActivity][ArgsActivity.kt]
* [ArgsOriginFragment][ArgsFragment.kt]
* [ArgsSupportFragment][ArgsFragment.kt]

Instantiate an ArgsFragment as follows:

```kotlin
class TestArgsFragment : ArgsSupportFragment<TestArgsFragment.Args>() {

    @Parcelize
    data class Args(val age: Int, val name: String) : Parcelable
}

TestArgsFragment().setArgs(TestArgsFragment.Args(21, "Kevin"))
```

`See the test code for more examples.`

## Change log

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
[version_icon]: https://api.bintray.com/packages/panpf/maven/androidx-kt-args/images/download.svg
[version_link]: https://bintray.com/panpf/maven/androidx-kt-args/_latestVersion

[CHANGELOG.md]: CHANGELOG.md
[ArgsBinder.kt]: src/main/java/me/panpf/androidxkt/args/ArgsBinder.kt
[ArgsActivity.kt]: src/main/java/me/panpf/androidxkt/args/ArgsActivity.kt
[ArgsFragment.kt]: src/main/java/me/panpf/androidxkt/args/ArgsFragment.kt