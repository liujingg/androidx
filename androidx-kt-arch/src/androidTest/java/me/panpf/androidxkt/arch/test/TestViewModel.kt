package me.panpf.androidxkt.arch.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class TestViewModel(application: Application) : AndroidViewModel(application)

class TestFactoryViewModel(val tag: String) : ViewModel()