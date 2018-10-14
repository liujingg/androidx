package me.panpf.androidxkt.args

import android.os.Bundle
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import android.support.v4.app.Fragment as SupportFragment


// todo 支持从 uri 中取参数

class ArgLazy<in REF, out OUT : Any>(private val argName: String, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '$argName' from arguments. 2" }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class OptionalArgLazy<in REF, out OUT>(private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT?> {
    private object EMPTY

    var arg: Any? = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT? {
        if (arg == EMPTY) {
            arg = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

/**
 * 当 Bundle 中存在 子 Bundle，子 Bundle 中又存在自定义的 Parcelable 时 get Parcelable 的时候会报 ClassNotFound 异常
 * 这是因为子 Bundle 在反序列化的时候其 classLoader 丢失了，get Parcelable 的时候就用 BootClassLoader 代替了
 * BootClassLoader 只有 java 和 android 的类，因此这里给 Bundle 设置 PathClassLoader 才能正常反序列化我们自定义的 Parcelable
 */
fun Bundle?.setAppClassLoader(): Bundle? {
    this?.classLoader = ArgLazy::class.java.classLoader
    return this
}