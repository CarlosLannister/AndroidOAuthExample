package com.example.oauthgithub

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.oauthgithub.GithubConstants.CLIENT_ID
import com.example.oauthgithub.GithubConstants.CLIENT_SECRET
import com.example.oauthgithub.GithubConstants.REDIRECT_URI
import com.example.oauthgithub.GithubConstants.SCOPE
import com.example.oauthgithub.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection


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
                            + "?client_id=" + CLIENT_ID + "&scope=" + SCOPE
                            + "?redirect_uri=" + REDIRECT_URI + "&state=" + state
                )
            )
            startActivity(webIntent)
        }

    }

    override fun onResume() {
        super.onResume()

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state").toString()

            if (code != null) {
                //requestForAccessToken(code)

                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                viewModel.getAccessToken(clientId = CLIENT_ID, code = code)

                viewModel.myResponse.observe(this, Observer { response ->
                    if(response.isSuccessful){

                        Log.d("Response", response.body().toString())


                    }else{
                        Log.d("Response", response.errorBody().toString())
                    }

                })


            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }


}