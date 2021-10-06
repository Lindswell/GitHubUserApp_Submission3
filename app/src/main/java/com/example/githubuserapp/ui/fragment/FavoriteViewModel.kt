package com.example.githubuserapp.ui.fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.database.FavDatabase
import com.example.githubuserapp.database.RoomData

class FavoriteViewModel: ViewModel() {
    private var user = MutableLiveData<ArrayList<RoomData>>()
    private var _user: LiveData<ArrayList<RoomData>> = user

    fun getFavData(context: Context): LiveData<ArrayList<RoomData>> {
        val database = FavDatabase.getDatabase(context.applicationContext).favDao()
        user.value = database.getFavUser() as ArrayList<RoomData>
        _user = user
        return _user
    }
}