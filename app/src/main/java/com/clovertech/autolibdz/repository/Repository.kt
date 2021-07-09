package repository

import model.*
import retrofit2.Response

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<AuthenticationResponse> {
        return RetrofitInst.authenticationApi.pushAuthentication(authentication)
    }

    suspend fun getToken(authentication : Authentication) : Response<Token> {
        return RetrofitInst.authenticationApi.getToken(authentication)
    }
    suspend fun pushSignal(signal: Signal): Response<Signal> {
        return RetrofitInstance.signalApi.pushSignal(signal)
    }

    suspend fun getUser(): User {
        return RetrofitInstance.apiUser.getUser()
    }

}