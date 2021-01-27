package com.example.oauthgithub

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.oauthgithub.repository.Repository
import com.example.oauthgithub.util.GithubConstants.Companion.clientId
import com.example.oauthgithub.util.GithubConstants.Companion.redirectUri
import com.example.oauthgithub.util.GithubConstants.Companion.scope
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var githubAuthURLFull: String
    lateinit var githubdialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        github_login_btn.setOnClickListener {

            val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

            val webIntent  = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://github.com/login/oauth/authorize"
                            + "?client_id=" + clientId + "&scope=" + scope
                            + "?redirect_uri=" + redirectUri + "&state=" + state
                )
            )
            startActivity(webIntent)
        }

    }

    override fun onResume() {
        super.onResume()

        // the intent filter defined in AndroidManifest will handle the return
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(redirectUri)) {
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state").toString()
            var token = ""

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            if (code != null) {
                viewModel.getAccessToken(clientId = clientId, code = code, state=state)
                viewModel.myResponse.observe(this, Observer { response ->
                    if(response.isSuccessful){

                        Log.d("Response", response.body().toString())
                        token = response.body()?.accessToken.toString()
                        if (token.isNotEmpty()){
                            viewModel.getUser(token)
                            viewModel.userResponse.observe(this, Observer { response ->
                                if(response.isSuccessful){
                                    Log.d("Response", response.body().toString())
                                    textView.text = response.body()?.name
                                }else{
                                    Log.d("Response", response.errorBody().toString())
                                }
                            })
                        }

                    }else{
                        Log.d("Response", response.errorBody().toString())
                    }
                })
            } else if (uri.getQueryParameter("error") != null) {
                Log.d("Error","query parameter error")
            }

        }
    }

}