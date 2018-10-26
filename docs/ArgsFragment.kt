package me.panpf.androidxkt.args

import android.os.Bundle
import android.os.Parcelable

abstract class ArgsOriginFragment<Args : Parcelable> : android.app.Fragment() {
    val args: Args by bindParcelableArg("args")

    fun setArgs(args: Args): ArgsOriginFragment<Args> {
        arguments = Bundle().apply { putParcelable("args", args) }
        return this
    }
}

abstract class ArgsSupportFragment<Args : Parcelable> : android.support.v4.app.Fragment() {
    val args: Args by bindParcelableArg("args")

    fun setArgs(args: Args): ArgsSupportFragment<Args> {
        arguments = Bundle().apply { putParcelable("args", args) }
        return this
    }
}