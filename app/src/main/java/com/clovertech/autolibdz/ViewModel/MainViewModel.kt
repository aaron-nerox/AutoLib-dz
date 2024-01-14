package com.clovertech.autolibdz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.model.Profil
import kotlinx.coroutines.launch
import com.clovertech.autolibdz.model.Authentication
import com.clovertech.autolibdz.model.AuthenticationResponse
import com.clovertech.autolibdz.model.Signal
import com.clovertech.autolibdz.model.User
import com.clovertech.autolibdz.repository.Repository
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<User> = MutableLiveData()
    val signalResponse: MutableLiveData<Response<Signal>> = MutableLiveData()
    fun getUser(){
        viewModelScope.launch {
            val response: User = repository.getUser()
            myResponse.value = response
        }
    }
    fun pushSignal(signal: Signal){
        viewModelScope.launch {
            val response: Response<Signal> = repository.pushSignal(signal)
            signalResponse.value = response
        }
    }
    val authenticationResponse: MutableLiveData<Response<AuthenticationResponse>> = MutableLiveData()
    val profil: MutableLiveData<Profil > = MutableLiveData()
    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            try {
                val response: Response<AuthenticationResponse> = repository.pushAuthentication(authentication)
                authenticationResponse.value = response
            } catch(e: Exception) {
                Log.e("pushAuthentication err", e.localizedMessage)
            }
        }
    }

    fun getUser(id:Int){
        viewModelScope.launch {
            val rep: Profil =repository.getUserById(id)
            profil.value=rep
        }

    }

}