package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClass.paymentInfo
import com.clovertech.autolibdz.DataClasses.Promo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PromoApi {
    @GET("all")
    suspend fun getAllPromo(): Response<MutableList<Promo>>

    companion object{
        operator fun invoke():PromoApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://54.37.87.85:5090/promocode/getPromoCodes/")
                .build()
                .create(PromoApi::class.java)

        }
    }
}