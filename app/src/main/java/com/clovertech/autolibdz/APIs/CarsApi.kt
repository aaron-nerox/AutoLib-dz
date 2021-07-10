package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Location
import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.DataClasses.Vehicle
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CarsApi {
    @GET("vehiclesListByState/{state}/{idborn}")
    suspend fun getCarsListByState(@Path("state") state : String,
                                    @Path("idborn") idborn:Int): Response<MutableList<Vehicle>>

    @POST("addRental")
    suspend fun addRental(
            @Body rental:Rental
    ):Response<Rental>


    companion object{
        operator fun invoke():CarsApi{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl+"cars/")
                    .build()
                .create(CarsApi::class.java)

        }
    }
}