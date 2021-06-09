package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClasses.Rental
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RentalApi {
    @POST("addRental")
    suspend fun addRental(
            @Body rental: Rental
    ): Response<Rental>


  //  @GET("rental/{id}")
}