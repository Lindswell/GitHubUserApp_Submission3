package com.example.githubuserapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_data")
data class RoomData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,

    var username: String? = null,

    @ColumnInfo(name = "html_url")
    var html: String? = null,

    @ColumnInfo(name = "avatar_url")
    var photo: String? = null,
)
