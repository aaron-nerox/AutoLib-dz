package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.ui.card.Card
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CardsApi {
    @GET("all")
    suspend fun getAllCards(): Response<MutableList<Card>>

    companion object{
        operator fun invoke():CardsApi{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://54.37.87.85:5040/payment/cards/")
                    .build()
                    .create(CardsApi::class.java)

        }
    }
}