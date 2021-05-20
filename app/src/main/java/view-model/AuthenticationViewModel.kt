package `view-model`

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Authentication
import repository.Repository
import retrofit2.Response

class AuthenticationViewModel (private val repository: Repository): ViewModel() {

    val authenticationResponse: MutableLiveData<Response<Authentication>> = MutableLiveData()

    fun pushAuthentication(authentication : Authentication){
        viewModelScope.launch {
            val response: Response<Authentication> = repository.pushAuthentication(authentication)
            authenticationResponse.value = response
        }
    }

}