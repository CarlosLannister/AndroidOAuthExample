package com.example.oauthgithub.api

import com.example.oauthgithub.GithubConstants
import com.example.oauthgithub.GithubConstants.CLIENT_ID
import com.example.oauthgithub.GithubConstants.CLIENT_SECRET
import com.example.oauthgithub.GithubConstants.REDIRECT_URI
import com.example.oauthgithub.model.AccessToken
import retrofit2.Response
import retrofit2.http.*

interface GithubOAuth {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("grant_type") grant_type: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirect_uri: String = REDIRECT_URI,
        @Field("client_id") clientId: String = CLIENT_ID,
        @Field("client_secret") client_secret: String = CLIENT_SECRET,
    ): Response<AccessToken>
}


