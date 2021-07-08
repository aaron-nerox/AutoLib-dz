package utils

import api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.SIGNAL_BASE_URL
import utils.Constants.Companion.USER_BASE_URL
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val client by lazy {
        OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }
    fun retrofitInstance(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    val authenticationApi : AuthenticationApi by lazy {
        retrofitInstance("http://192.168.137.93:8005").create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        retrofitInstance("http://192.168.137.93:8100").create(RegistrationApi::class.java)

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

    val borneApi: BorneApi by lazy {
        retrofitInstance("http://192.168.137.93:8200").create(BorneApi::class.java)
    }

}