package me.panpf.androidxkt.args

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import android.view.View
import me.panpf.androidxkt.app.bindParcelableArg

abstract class ArgsFragmentActivity<out Args : Parcelable> : FragmentActivity() {
    val args: Args by bindParcelableArg("args")
}

abstract class ArgsActivity<out Args : Parcelable> : Activity() {
    val args: Args by bindParcelableArg("args")
}


fun <T : Activity, Args : Parcelable> Context.buildArgsActivityIntent(clazz: Class<T>, args: Args): Intent {
    return Intent(this, clazz).apply { putExtra("args", args) }
}

fun <T : Activity, Args : Parcelable> android.support.v4.app.Fragment.buildArgsActivityIntent(clazz: Class<T>, args: Args): Intent {
    val localContext = this.activity ?: throw IllegalStateException("Fragment destroyed")
    return Intent(localContext, clazz).apply { putExtra("args", args) }
}

fun <T : Activity, Args : Parcelable> android.app.Fragment.buildArgsActivityIntent(clazz: Class<T>, args: Args): Intent {
    val localContext = this.activity ?: throw IllegalStateException("Fragment destroyed")
    return Intent(localContext, clazz).apply { putExtra("args", args) }
}

fun <T : Activity, Args : Parcelable> View.buildArgsActivityIntent(clazz: Class<T>, args: Args): Intent {
    return Intent(this.context, clazz).apply { putExtra("args", args) }
}


fun <T : Activity, Args : Parcelable> Context.startArgsActivity(clazz: Class<T>, args: Args) {
    val localContext = this
    startActivity(buildArgsActivityIntent(clazz, args).apply { if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}

fun <T : Activity, Args : Parcelable> android.support.v4.app.Fragment.startArgsActivity(clazz: Class<T>, args: Args) {
    this.activity ?: return
    startActivity(buildArgsActivityIntent(clazz, args))
}

fun <T : Activity, Args : Parcelable> android.app.Fragment.startArgsActivity(clazz: Class<T>, args: Args) {
    this.activity ?: return
    startActivity(buildArgsActivityIntent(clazz, args))
}

fun <T : Activity, Args : Parcelable> View.startArgsActivity(clazz: Class<T>, args: Args) {
    val localContext = this.context ?: return
    localContext.startActivity(buildArgsActivityIntent(clazz, args).apply { if (localContext !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}