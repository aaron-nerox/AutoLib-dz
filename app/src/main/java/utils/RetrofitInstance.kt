package utils

import api.*
import com.clovertech.autolibdz.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.Constants.Companion.AUTH_BASE_URL
import utils.Constants.Companion.BORNE_BASE_URL
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
        retrofitInstance(AUTH_BASE_URL).create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        retrofitInstance(USER_BASE_URL).create(RegistrationApi::class.java)

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
        retrofitInstance(BORNE_BASE_URL).create(BorneApi::class.java)
    }

    private val retrofitGetCard by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Cars_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val retrofitGetPring by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Pricing_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val retrofitGetCards by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.CARD_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val retrofitRental by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Rental_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val AddRentalApi: RentalApi by lazy {
        retrofitRental.create(RentalApi::class.java)
    }
    private val retrofitAddCard by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Cars_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val retrofitReduPrice: PromoApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Pricing_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PromoApi::class.java)
    }
    val subApi: PayApi by lazy {
        Retrofit.Builder().baseUrl(Constants.SUB_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build().create(PayApi::class.java)

    }
    val factureApi: FactureApi by lazy {
        Retrofit.Builder().baseUrl(Constants.Bill_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build().create(FactureApi::class.java)

    }
    val apiUser : UserApi by lazy {
        retrofitUser.create(UserApi::class.java)
    }

    val cardApi : PayApi by lazy {
        retrofitGetCards.create(PayApi::class.java)
    }

}