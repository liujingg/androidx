package me.panpf.androidxkt.args.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestParcelable(val tag: String) : Parcelable