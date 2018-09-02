package me.panpf.androidxkt.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

class TestSupportActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.at_test)

        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, Fragment()).commit()
    }
}