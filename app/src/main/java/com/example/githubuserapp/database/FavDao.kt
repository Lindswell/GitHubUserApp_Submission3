package com.example.githubuserapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {

    @Insert
    fun insert(user: RoomData)

    @Query("delete from room_data where username =:username")
    fun delete(username: String)

    @Query("select * from room_data order by id asc")
    fun getFavUser(): List<RoomData>

    @Query("select exists(select * from room_data where username=:username)")
    fun checkFavUser(username: String): Boolean


}