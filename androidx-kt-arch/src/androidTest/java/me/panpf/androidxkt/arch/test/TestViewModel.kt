package me.panpf.androidxkt.arch.test

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel

class TestViewModel(application: Application) : AndroidViewModel(application)

class TestFactoryViewModel(val tag: String) : ViewModel()