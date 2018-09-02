package me.panpf.androidxkt.arch.test

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import me.panpf.androidxkt.arch.bindViewModel

class TestFragment : Fragment(){

    val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)

    val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModelFragment")
        }
    })
}