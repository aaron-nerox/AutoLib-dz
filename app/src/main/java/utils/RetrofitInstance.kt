package utils

import api.AuthenticationApi
import api.BorneApi
import api.RegistrationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        retrofitInstance("http://192.168.43.103:8005").create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        retrofitInstance("http://192.168.43.103:8100").create(RegistrationApi::class.java)

    }

    val borneApi: BorneApi by lazy {
        retrofitInstance("http://192.168.43.103:8200").create(BorneApi::class.java)
    }






}