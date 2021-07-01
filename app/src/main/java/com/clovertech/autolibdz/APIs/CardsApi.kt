package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClass.*
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.ui.card.Card
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardsApi {
    @GET("all")
    suspend fun getAllCards(): Response<MutableList<paymentInfo>>

    companion object{
        operator fun invoke():CardsApi{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl+"payment/cards/")
                    .build()
                    .create(CardsApi::class.java)

        }
    }
}