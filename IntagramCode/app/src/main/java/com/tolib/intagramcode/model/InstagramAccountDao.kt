package com.tolib.intagramcode.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InstagramAccountDao {
    @Insert
    suspend fun insert(account: InstagramAccount)

    @Query("SELECT * FROM INSTAGRAM_ACCOUNT")
    fun getAllAccounts(): LiveData<List<InstagramAccount>>
}