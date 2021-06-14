package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Authentication
import model.AuthenticationResponse
import repository.Repository
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {

    val authenticationResponse: MutableLiveData<Response<AuthenticationResponse>> = MutableLiveData()

    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            val response: Response<AuthenticationResponse> = repository.pushAuthentication(authentication)
            authenticationResponse.value = response
        }
    }

}