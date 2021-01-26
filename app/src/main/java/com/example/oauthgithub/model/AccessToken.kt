package com.example.oauthgithub.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    var accessToken : String,
    @SerializedName("token_type")
    var tokenType: String,
    @SerializedName("scope")
    var scope: String,
)

