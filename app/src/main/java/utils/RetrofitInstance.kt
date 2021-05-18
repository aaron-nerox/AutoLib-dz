package utils

import api.SignalApi
import api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofitUser by lazy {
        Retrofit.Builder()
            .baseUrl("https://b96c4f85d0c1.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : UserApi by lazy {
        retrofitUser.create(UserApi::class.java)
    }
    private val retrofitSignal by lazy {
        Retrofit.Builder()
            .baseUrl("https://9c29d43ba240.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val signalApi : SignalApi by lazy {
        retrofitSignal.create(SignalApi::class.java)
    }
}