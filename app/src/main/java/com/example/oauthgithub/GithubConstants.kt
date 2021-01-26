package com.example.oauthgithub

object GithubConstants {

    val CLIENT_ID = "CLIENT_ID"
    val CLIENT_SECRET = "YOUR_SECRET"
    val REDIRECT_URI = "futurestudio://callback"
    val SCOPE = "read:user,user:email"
    val AUTHURL = "https://github.com/login/oauth/authorize"
    val TOKENURL = "https://github.com/login/oauth/access_token"
}