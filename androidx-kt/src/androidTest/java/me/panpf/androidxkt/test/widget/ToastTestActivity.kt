package me.panpf.androidxkt.test.widget

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.test.R

class ToastTestActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.at_test)

        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, Fragment()).commit()
    }
}