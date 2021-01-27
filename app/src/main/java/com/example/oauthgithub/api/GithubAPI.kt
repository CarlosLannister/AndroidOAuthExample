package com.example.oauthgithub.api

import com.example.oauthgithub.model.AccessToken
import com.example.oauthgithub.model.GithubUser
import com.example.oauthgithub.util.GithubConstants
import retrofit2.Response
import retrofit2.http.*

interface GithubAPI {

        @GET("user")
        suspend fun getUser(
            @Header("Authorization") token : String,
        ): Response<GithubUser>

}


