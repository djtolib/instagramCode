package com.tolib.intagramcode.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instagram_account")
data class InstagramAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String
)
