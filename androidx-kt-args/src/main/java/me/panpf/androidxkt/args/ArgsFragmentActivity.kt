package me.panpf.androidxkt.args

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View

abstract class ArgsFragmentActivity<out Args : Parcelable> : FragmentActivity() {
    val args: Args by bindParcelableArg("args")
}

fun <Args : Parcelable> Context.buildArgsIntent(clazz: Class<Activity>, args: Args): Intent {
    return Intent(this, clazz).apply { putExtra("args", args) }
}

fun <Args : Parcelable> Context.startArgsActivity(clazz: Class<Activity>, args: Args) {
    val localContext = this
    startActivity(localContext.buildArgsIntent(clazz, args).apply { if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}

fun <Args : Parcelable> Fragment.startArgsActivity(clazz: Class<Activity>, args: Args) {
    val localContext = this.context ?: return
    startActivity(localContext.buildArgsIntent(clazz, args).apply { if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}

fun <Args : Parcelable> View.startArgsActivity(clazz: Class<Activity>, args: Args) {
    val localContext = this.context ?: return
    localContext.startActivity(localContext.buildArgsIntent(clazz, args).apply { if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}