package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CarsApi {
    @GET("vehiclesListByState/{state}")
    suspend fun getCarsListByState(@Path("state") state : String): Response<MutableList<Vehicle>>

    companion object{
        operator fun invoke():CarsApi{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://54.37.87.85:5000/pricing/")
                    .build()
                .create(CarsApi::class.java)

        }
    }
}