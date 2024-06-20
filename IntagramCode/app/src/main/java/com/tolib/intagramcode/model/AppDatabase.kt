package com.tolib.intagramcode.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InstagramAccount::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): InstagramAccountDao
}
