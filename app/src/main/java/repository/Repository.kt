package repository

import model.Authentication
import retrofit2.Response
import utils.RetrofitInstance

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<Authentication> {
        return RetrofitInstance.authenticationApi.pushAuthentication(authentication)
    }

}