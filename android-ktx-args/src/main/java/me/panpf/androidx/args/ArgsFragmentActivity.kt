@file:Suppress("unused")

package me.panpf.androidx.args

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import android.view.View
import kotlin.reflect.KClass
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

abstract class ArgsFragmentActivity<out Args : Parcelable> : FragmentActivity() {
    val args: Args by bindParcelableArg("args")
}

fun <Args : Parcelable> Context.startArgsActivity(clazz: KClass<ArgsFragmentActivity<Args>>, args: Args) {
    val localContext = this
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> SupportFragment.startArgsActivity(clazz: KClass<ArgsFragmentActivity<Args>>, args: Args) {
    val localContext = this.context ?: return
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> OriginFragment.startArgsActivity(clazz: KClass<ArgsFragmentActivity<Args>>, args: Args) {
    val localContext = this.activity.baseContext ?: return
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> View.startArgsActivity(clazz: KClass<ArgsFragmentActivity<Args>>, args: Args) {
    val localContext = this.context ?: return
    localContext.startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}