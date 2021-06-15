package utils

import api.AuthenticationApi
import api.RegistrationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    fun instance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val authenticationApi : AuthenticationApi by lazy {
        instance("http://192.168.43.222:8005/").create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        instance("http://192.168.43.222:8100/").create(RegistrationApi::class.java)
    }

//    val borneApi: BorneApi by lazy {
//        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(logging)
//            .build()
//
//
//        Retrofit.Builder().baseUrl("http://1f398cfe382e.ngrok.io"). addConverterFactory(
//            GsonConverterFactory.create()).client(client). build().create(BorneApi::class.java)
//    }




}