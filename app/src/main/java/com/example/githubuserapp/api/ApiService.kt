package com.example.githubuserapp.api

import android.telecom.Call
import com.example.githubuserapp.data.GithubUser
import com.example.githubuserapp.data.User
import retrofit2.http.*

interface ApiService {

    @GET("/users")
    @Headers("Authorization: token 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")
    fun getUser(): retrofit2.Call<ArrayList<User>>

    @GET("/search/users")
    fun getSearch(@Query("q") query: String): retrofit2.Call<GithubUser>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): retrofit2.Call<User>

    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): retrofit2.Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): retrofit2.Call<ArrayList<User>>


}