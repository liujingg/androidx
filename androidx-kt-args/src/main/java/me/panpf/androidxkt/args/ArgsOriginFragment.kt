@file:Suppress("unused")

package me.panpf.androidxkt.args

import android.app.Fragment as OriginFragment
import android.os.Bundle
import android.os.Parcelable

abstract class ArgsOriginFragment<Args : Parcelable> : OriginFragment() {
    val args: Args by bindParcelableArg("args")

    fun setArgs(args: Args): ArgsOriginFragment<Args> {
        arguments = Bundle().apply { putParcelable("args", args) }
        return this
    }
}