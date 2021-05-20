package utils

import api.AuthenticationApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.BASE_URL

object RetrofitInstance {

    private val retrofitAuthentication by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val authenticationApi : AuthenticationApi by lazy {
        retrofitAuthentication.create(AuthenticationApi::class.java)
    }

}