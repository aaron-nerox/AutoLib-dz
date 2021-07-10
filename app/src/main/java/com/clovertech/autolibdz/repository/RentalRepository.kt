package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Response

class RentalRepository {

    suspend fun addRental(rental: Rental):Response<Rental>{
        return RetrofitInstance.AddRentalApi.addRental(rental)
    }
    suspend fun endRental(id:Int):Response<String>{
        return  RetrofitInstance.AddRentalApi.endRental(id)
    }
}