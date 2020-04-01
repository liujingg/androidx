@file:Suppress("RedundantVisibilityModifier")

package me.panpf.androidxkt.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

public fun <V : ViewModel> androidx.fragment.app.Fragment.bindViewModel(clazz: KClass<V>): ReadOnlyProperty<androidx.fragment.app.Fragment, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProvider(this, defaultViewModelProviderFactory).get(clazz.java) }
}

public fun <V : ViewModel> androidx.fragment.app.Fragment.bindViewModel(clazz: KClass<V>, factory: ViewModelProvider.Factory): ReadOnlyProperty<androidx.fragment.app.Fragment, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProvider(this, factory).get(clazz.java) }
}

public fun <V : ViewModel> androidx.fragment.app.FragmentActivity.bindViewModel(clazz: KClass<V>): ReadOnlyProperty<androidx.fragment.app.FragmentActivity, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProvider(this, defaultViewModelProviderFactory).get(clazz.java) }
}

public fun <V : ViewModel> androidx.fragment.app.FragmentActivity.bindViewModel(clazz: KClass<V>, factory: ViewModelProvider.Factory): ReadOnlyProperty<androidx.fragment.app.FragmentActivity, V> {
    return ViewModelLazy { _, _: KProperty<*> -> ViewModelProvider(this, factory).get(clazz.java) }
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