package com.bautoidem.jetpackcomposemvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object{
        private lateinit var instance : MyApplication

        fun getInstance() = instance
    }
}