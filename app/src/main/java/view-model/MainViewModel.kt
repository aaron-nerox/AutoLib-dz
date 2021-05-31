package `view-model`

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Authentication
import model.Signal
import model.Token
import model.User
import repository.Repository
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
    val authenticationResponse: MutableLiveData<Response<Token>> = MutableLiveData()

    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            val response: Response<Token> = repository.pushAuthentication(authentication)
            authenticationResponse.value = response
        }
    }

}