package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarsApi {
    @GET("vehiclesListByState/{state}")
    suspend fun getCarsListByState(@Path("state") state : String): Response<MutableList<Vehicle>>

    @POST("addRental")
    suspend fun addRental(
            @Body rental:Rental
    ):Response<Rental>

    companion object{
        operator fun invoke():CarsApi{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl+"rental/")
                    .build()
                .create(CarsApi::class.java)

        }
    }
}