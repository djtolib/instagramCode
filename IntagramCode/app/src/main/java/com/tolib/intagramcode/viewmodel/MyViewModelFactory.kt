package com.tolib.intagramcode.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.system.exitProcess

class MyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {


            @Suppress("UNCHECKED_CAST")
            MainViewModel(application) as T
        } else {
            throw Exception("ad")
        }
    }
}
