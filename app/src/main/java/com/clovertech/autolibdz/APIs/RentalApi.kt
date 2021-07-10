package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Rental
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface RentalApi {
    @POST("addRental")
    suspend fun addRental(
            @Body rental: Rental
    ): Response<Rental>



    @POST("endRental/{id}")
    suspend fun endRental(
            @Path("id")id:Int
    ):Response<String>

  //  @GET("rental/{id}")
}