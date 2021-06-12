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

    private val retrofitAuthentication by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("http://d5950702210f.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val authenticationApi : AuthenticationApi by lazy {
        retrofitAuthentication.create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        retrofitAuthentication.create(RegistrationApi::class.java)
    }

    val borneApi: BorneApi by lazy {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()


        Retrofit.Builder().baseUrl("http://1f398cfe382e.ngrok.io"). addConverterFactory(
            GsonConverterFactory.create()).client(client). build().create(BorneApi::class.java)
    }




}