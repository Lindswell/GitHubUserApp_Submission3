package com.example.githubuserapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

    @field: SerializedName("id")
    var userId: Int = 0,

    @field: SerializedName("login")
    var username: String,

    @field: SerializedName("name")
    var name: String? = null,

    @field: SerializedName("html_url")
    var html: String? = null,

    @field: SerializedName("location")
    var location: String? = null,

    @field: SerializedName("public_repos")
    var repository: String? = null,

    @field: SerializedName("company")
    var company: String? = null,

    @field: SerializedName("followers_url")
    var follower: String? = null,

    @field: SerializedName("following_url")
    var following: String? = null,

    @field: SerializedName("avatar_url")
    var photo: String? = null,

    @field: SerializedName("following")
    var following_num: String? = null,

    @field: SerializedName("followers")
    var followers_num: String? = null

): Parcelable
