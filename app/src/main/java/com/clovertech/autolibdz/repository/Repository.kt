package repository

import com.clovertech.autolibdz.utils.RetrofitInst
import com.clovertech.autolibdz.utils.RetrofitInstance
import model.Authentication
import model.AuthenticationResponse
import model.Token
import retrofit2.Response

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<AuthenticationResponse> {
        return RetrofitInst.authenticationApi.pushAuthentication(authentication)
    }

    suspend fun getToken(authentication : Authentication) : Response<Token> {
        return RetrofitInst.authenticationApi.getToken(authentication)
    }

}