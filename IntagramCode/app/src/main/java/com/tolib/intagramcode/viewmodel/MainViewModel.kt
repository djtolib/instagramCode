package com.tolib.intagramcode.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.tolib.intagramcode.model.AppDatabase
import com.tolib.intagramcode.model.InstagramAccount
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database: AppDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java, "app-database"
    ).build()

    val accounts: LiveData<List<InstagramAccount>> = database.accountDao().getAllAccounts()

    fun insertAccount(username: String) {
        viewModelScope.launch {
            database.accountDao().insert(InstagramAccount(username = username))
        }
    }
}
