package me.panpf.androidxkt.args.test

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.panpf.androidxkt.args.ArgsFragmentActivity

class TestArgsActivity : ArgsFragmentActivity<TestArgsActivity.Args>() {

    @Parcelize
    data class Args(val int: Int, val child: TestParcelable) : Parcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.at_test)

        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, TestArgsFragment().setArgs(args)).commit()
    }
}