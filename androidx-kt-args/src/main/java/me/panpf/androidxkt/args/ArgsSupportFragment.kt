package me.panpf.androidxkt.args

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment as SupportFragment

abstract class ArgsSupportFragment<Args : Parcelable> : SupportFragment() {
    val args: Args by bindParcelableArg("args")

    fun setArgs(args: Args): ArgsSupportFragment<Args> {
        arguments = Bundle().apply { putParcelable("args", args) }
        return this
    }
}