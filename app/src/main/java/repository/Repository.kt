package repository

import model.Authentication
import model.Signal
import model.Token
import retrofit2.Response
import utils.RetrofitInstance

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<Token> {
        return RetrofitInstance.authenticationApi.pushAuthentication(authentication)
    }suspend fun pushSignal(signal : Signal) : Response<Signal> {
        return RetrofitInstance.signalApi.pushSignal(signal)
    }

}