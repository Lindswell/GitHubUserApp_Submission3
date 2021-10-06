package com.example.githubuserapp.ui.fragment

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.api.ApiConfig
import com.example.githubuserapp.data.GithubUser
import com.example.githubuserapp.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class HomeFragmentViewModel: ViewModel() {

    companion object {
        const val TAG = "TAG"
    }

    private val _listGitUser = MutableLiveData<Boolean>()
    val listGitUser : LiveData<Boolean> = _listGitUser

    fun getUserList(context: Context): LiveData<ArrayList<User>> {
        val user = MutableLiveData<ArrayList<User>>()
        val finUser : LiveData<ArrayList<User>>
        _listGitUser.value = true
        ApiConfig.getApiService().getUser().enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                _listGitUser.value = false
                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    Toast.makeText(context, "onFailure: ${response.message()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                _listGitUser.value = false
            }

        })
        finUser = user
        return finUser
    }

    fun getSearchList(username: String, context: Context): LiveData<GithubUser> {
        val user = MutableLiveData<GithubUser>()
        val finUser: LiveData<GithubUser>
        _listGitUser.value = true
        ApiConfig.getApiService().getSearch(username)
            .enqueue(object : Callback<GithubUser>{
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    _listGitUser.value = false
                    if (response.isSuccessful) {
                        user.value = response.body()
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                    _listGitUser.value = false
                }

            })
        finUser = user
        return finUser
    }
}