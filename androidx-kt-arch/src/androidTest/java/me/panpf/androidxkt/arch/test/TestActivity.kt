package me.panpf.androidxkt.arch.test

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.arch.bindViewModel

class TestActivity : FragmentActivity() {

    val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)

    val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModel")
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(me.panpf.androidxkt.arch.test.R.layout.at_test)

        supportFragmentManager.beginTransaction().replace(me.panpf.androidxkt.arch.test.R.id.testAt_frame, TestFragment()).commit()
    }
}