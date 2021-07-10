package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.model.Profil
import kotlinx.coroutines.launch
import model.Authentication
import model.AuthenticationResponse
import model.Signal
import repository.Repository
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {

    val authenticationResponse: MutableLiveData<Response<AuthenticationResponse>> = MutableLiveData()
    val signalResponse: MutableLiveData<Response<Signal>> = MutableLiveData()
    val profil: MutableLiveData<Profil > = MutableLiveData()
    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            val response: Response<AuthenticationResponse> = repository.pushAuthentication(authentication)
            authenticationResponse.value = response
        }
    }

    fun pushSignal(signal: Signal){
        viewModelScope.launch {
            val response: Response<Signal> = repository.pushSignal(signal)
            signalResponse.value = response
        }
    }

    fun getUser(id:Int){
        viewModelScope.launch {
            val rep: Profil =repository.getUserById(id)
            profil.value=rep
        }

    }

}