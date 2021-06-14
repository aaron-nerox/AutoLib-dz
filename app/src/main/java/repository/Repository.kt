package repository

import model.Authentication
import model.AuthenticationResponse
import retrofit2.Response
import utils.RetrofitInstance

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<AuthenticationResponse> {
        return RetrofitInstance.authenticationApi.pushAuthentication(authentication)
    }

}