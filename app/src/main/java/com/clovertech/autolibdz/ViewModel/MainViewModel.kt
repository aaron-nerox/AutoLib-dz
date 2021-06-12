package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Authentication
import model.Token
import repository.Repository
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {

    val authenticationResponse: MutableLiveData<Response<Token>> = MutableLiveData()

    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            val response: Response<Token> = repository.pushAuthentication(authentication)
            authenticationResponse.value = response
        }
    }

}