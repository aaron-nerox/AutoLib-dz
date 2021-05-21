package utils

import api.AuthenticationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val retrofitAuthentication by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("http://e2366c91b468.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val authenticationApi : AuthenticationApi by lazy {
        retrofitAuthentication.create(AuthenticationApi::class.java)
    }

}