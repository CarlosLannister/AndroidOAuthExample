package com.example.oauthgithub.model

import com.google.gson.annotations.SerializedName


data class GithubUser(
    var login : String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    var url: String,
    @SerializedName("followers_url")
    var followers: String,
    var name: String,
)