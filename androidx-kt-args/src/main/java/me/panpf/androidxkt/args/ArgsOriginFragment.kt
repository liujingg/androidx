package me.panpf.androidxkt.args

import android.os.Bundle
import android.os.Parcelable
import android.app.Fragment as OriginFragment

abstract class ArgsOriginFragment<Args : Parcelable> : OriginFragment() {
    val args: Args by bindParcelableArg("args")

    fun setArgs(args: Args): ArgsOriginFragment<Args> {
        arguments = Bundle().apply { putParcelable("args", args) }
        return this
    }
}