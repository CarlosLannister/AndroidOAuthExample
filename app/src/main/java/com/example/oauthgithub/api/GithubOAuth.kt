package com.example.oauthgithub.api

import com.example.oauthgithub.model.AccessToken
import com.example.oauthgithub.util.GithubConstants
import com.example.oauthgithub.util.GithubConstants.Companion.clientSecret
import com.example.oauthgithub.util.GithubConstants.Companion.redirectUri
import retrofit2.Response
import retrofit2.http.*

interface GithubOAuth {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("code") code: String,
        @Field("redirect_uri") redirect_uri: String = redirectUri,
        @Field("client_id") clientId: String = GithubConstants.clientId,
        @Field("client_secret") client_secret: String = clientSecret,
        @Field("state") state: String,
    ): Response<AccessToken>
}


