package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.api.CarsApi
import com.clovertech.autolibdz.api.SafeCarsRequest
import com.clovertech.autolibdz.model.Rental

class CarsRepository(
        private val apiCarsByStat : CarsApi
) :SafeCarsRequest(){
    suspend fun getCarsByStat(Stat:String,idborn:Int)=ApiCarRequest{apiCarsByStat.getCarsListByState(Stat,idborn)}
    suspend fun addRental(rental: Rental)=ApiCarRequest { apiCarsByStat.addRental(rental) }
    }
