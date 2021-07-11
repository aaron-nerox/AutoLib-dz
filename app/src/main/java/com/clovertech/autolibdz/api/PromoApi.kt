package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.Promo
import com.clovertech.autolibdz.model.ReduPriceResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PromoApi {
    @GET("getPromoCodes")
    suspend fun getAllPromo(): Response<MutableList<Promo>>

    companion object{
        operator fun invoke(): PromoApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl +"promocode/")
                .build()
                .create(PromoApi::class.java)

        }
    }
    @GET("getReductionPrice/{basePrice}/{idPromoCode}/{idTenant}")
    fun getReduPriceByidPromo(@Path("basePrice") basePrice : Int,@Path("idPromoCode") idPromoCode :
    Int,@Path("idTenant") idTenant : Int): Call<ReduPriceResponse>

}