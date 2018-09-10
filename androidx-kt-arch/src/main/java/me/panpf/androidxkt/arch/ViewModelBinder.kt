@file:Suppress("RedundantVisibilityModifier")

package me.panpf.androidxkt.arch

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

public fun <V : ViewModel> android.support.v4.app.Fragment.bindViewModel(clazz: KClass<V>): ReadOnlyProperty<android.support.v4.app.Fragment, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProviders.of(this).get(clazz.java) }
}

public fun <V : ViewModel> android.support.v4.app.Fragment.bindViewModel(clazz: KClass<V>, factory: ViewModelProvider.Factory): ReadOnlyProperty<android.support.v4.app.Fragment, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProviders.of(this, factory).get(clazz.java) }
}

public fun <V : ViewModel> FragmentActivity.bindViewModel(clazz: KClass<V>): ReadOnlyProperty<FragmentActivity, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProviders.of(this).get(clazz.java) }
}

public fun <V : ViewModel> FragmentActivity.bindViewModel(clazz: KClass<V>, factory: ViewModelProvider.Factory): ReadOnlyProperty<FragmentActivity, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProviders.of(this, factory).get(clazz.java) }
}

private class ViewModelLazy<in REF, out OUT>(val initializer: (REF, KProperty<*>) -> OUT) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    var viewModel: Any? = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (viewModel == EMPTY) {
            viewModel = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return viewModel as OUT
    }
}