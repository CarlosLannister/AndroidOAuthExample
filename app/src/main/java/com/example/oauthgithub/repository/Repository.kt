package com.example.oauthgithub.repository

import com.example.oauthgithub.api.RetrofitInstance
import com.example.oauthgithub.model.AccessToken
import com.example.oauthgithub.model.GithubUser
import retrofit2.Response

class Repository {

    suspend fun getAccessToken(
        clientId: String,
        code: String,
        state: String,
    ): Response<AccessToken> {
        return RetrofitInstance.apiOAuth.getAccessToken(clientId=clientId,code=code,state=state)
    }

    suspend fun getUser(
        token: String,
    ): Response<GithubUser> {
        val authorization = "Bearer $token"
        return RetrofitInstance.apiGithub.getUser(authorization)
    }

}
