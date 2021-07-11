package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.Borne
import com.clovertech.autolibdz.model.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BorneApi {

    @GET("bornes")
    fun getBornes():Call<List<Borne>>

    @GET("borne/{id}/vehicles")
    fun getVehiculesBorne(@Path("id") idBorne: Int): Call<List<Vehicle>>

}