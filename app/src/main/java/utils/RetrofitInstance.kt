package utils

import api.AuthenticationApi
import api.SignalApi
import api.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.SIGNAL_BASE_URL
import utils.Constants.Companion.USER_BASE_URL
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
    private val retrofitUser by lazy {
        Retrofit.Builder()
            .baseUrl(USER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : UserApi by lazy {
        retrofitUser.create(UserApi::class.java)
    }
    private val retrofitSignal by lazy {
        Retrofit.Builder()
            .baseUrl(SIGNAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val signalApi : SignalApi by lazy {
        retrofitSignal.create(SignalApi::class.java)
    }

}