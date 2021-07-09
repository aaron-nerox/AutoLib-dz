package com.clovertech.autolibdz.repository

import model.Rental
import retrofit2.Response

class RentalRepository {

    suspend fun addRental(rental: Rental):Response<Rental>{
        return RetrofitInstance.AddRentalApi.addRental(rental)
    }
}