package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.DataClasses.Location
import com.clovertech.autolibdz.model.Rental
import com.clovertech.autolibdz.model.Vehicle
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Response


class RentalRepository {

    suspend fun addRental(rental: Rental):Response<Rental>{
        return RetrofitInstance.AddRentalApi.addRental(rental)
    }
    suspend fun endRental(idVehicle:Int,idborn:Int):Response<String>{
        return  RetrofitInstance.AddRentalApi.endRental(idVehicle,idborn)
    }
    suspend fun validateLocation(id:Int):Response<Location>{
        return RetrofitInstance.AddRentalApi.validateLocation(id)
    }
}