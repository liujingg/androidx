package me.panpf.androidxkt.args

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.View
import kotlin.reflect.KClass

abstract class ArgsActivity<out Args : Parcelable> : Activity() {
    val args: Args by bindParcelableArg("args")
}

fun <Args : Parcelable> Context.startArgsActivity(clazz: KClass<ArgsActivity<Args>>, args: Args) {
    val localContext = this
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> Fragment.startArgsActivity(clazz: KClass<ArgsActivity<Args>>, args: Args) {
    val localContext = this.context ?: return
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> android.app.Fragment.startArgsActivity(clazz: KClass<ArgsActivity<Args>>, args: Args) {
    val localContext = this.activity.baseContext ?: return
    startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}

fun <Args : Parcelable> View.startArgsActivity(clazz: KClass<ArgsActivity<Args>>, args: Args) {
    val localContext = this.context ?: return
    localContext.startActivity(Intent(localContext, clazz::class.java).apply {
        if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra("args", args)
    })
}