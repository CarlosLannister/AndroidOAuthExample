package com.example.oauthgithub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthgithub.model.AccessToken
import com.example.oauthgithub.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<AccessToken>> = MutableLiveData()

    fun getAccessToken(
        clientId: String,
        code: String,
    ) {
        viewModelScope.launch {
            val response = repository.getAccessToken(clientId=clientId,code=code)
            myResponse.value = response
        }
    }
}