package com.example.githubuserapp.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.api.ApiConfig
import com.example.githubuserapp.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    companion object {
        const val TAG = "TAG"
    }

    private var user = MutableLiveData<User>()
    private var finUser : LiveData<User> = user

    private val _detailGitUser = MutableLiveData<Boolean>()
    val detailGitUser : LiveData<Boolean> = _detailGitUser

    fun getUserDetail(username: String, context: Context): LiveData<User> {
        _detailGitUser.value = true
        ApiConfig.getApiService().getUserDetail(username).enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                _detailGitUser.value = false
                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    Toast.makeText(context, "onFailure: ${response.message()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _detailGitUser.value = false
            }

        })
        finUser = user
        return user
    }

    private var userFollow = MutableLiveData<ArrayList<User>>()
    private var finUserFollow : LiveData<ArrayList<User>> = userFollow

    fun getFollower(username: String, context: Context): LiveData<ArrayList<User>> {
        _detailGitUser.value = true
        ApiConfig.getApiService().getUserFollowers(username).enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                _detailGitUser.value = false
                if (response.isSuccessful) {
                    userFollow.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                _detailGitUser.value = false
            }

        })
        finUserFollow = userFollow
        return finUserFollow
    }

    fun getFollowing(username: String, context: Context): LiveData<ArrayList<User>> {
        _detailGitUser.value = true
        ApiConfig.getApiService().getUserFollowing(username).enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                _detailGitUser.value = false
                if (response.isSuccessful) {
                    userFollow.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                _detailGitUser.value = false
            }

        })
        finUserFollow = userFollow
        return finUserFollow
    }
}