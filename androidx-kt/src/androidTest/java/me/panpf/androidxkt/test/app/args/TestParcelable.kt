package me.panpf.androidxkt.test.app.args

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestParcelable(val tag: String) : Parcelable