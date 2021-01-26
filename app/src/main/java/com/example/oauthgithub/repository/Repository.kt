package com.example.oauthgithub.repository

import com.example.oauthgithub.api.RetrofitInstance
import com.example.oauthgithub.model.AccessToken
import retrofit2.Response

class Repository {

    suspend fun getAccessToken(
        clientId: String,
        code: String,
    ): Response<AccessToken> {
        return RetrofitInstance.api.getAccessToken(clientId=clientId,code=code)
    }

}
