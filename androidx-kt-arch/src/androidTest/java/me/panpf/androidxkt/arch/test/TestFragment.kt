package me.panpf.androidxkt.arch.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import me.panpf.androidxkt.arch.bindViewModel

class TestFragment : androidx.fragment.app.Fragment(){

    val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)

    val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModelFragment")
        }
    })
}