package me.panpf.androidxkt.arch.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.panpf.androidxkt.arch.bindViewModel

class TestActivity : androidx.fragment.app.FragmentActivity() {

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