package utils

import api.SignalApi
import api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.SIGNAL_BASE_URL
import utils.Constants.Companion.USER_BASE_URL

object RetrofitInstance {

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