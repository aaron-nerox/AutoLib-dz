package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.DownResponse
import com.clovertech.autolibdz.model.Facture
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FactureApi {
    @GET("all")
    suspend fun getFactures():Response<MutableList<Facture>>

    companion object{
        operator fun invoke(): FactureApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl +"bill/")
                    .build()
                    .create(FactureApi::class.java)

        }
    }
    @GET("download/{idBill}")
    suspend fun getBillByID(@Path("idBill") idBill : Int): Response<DownResponse>
}