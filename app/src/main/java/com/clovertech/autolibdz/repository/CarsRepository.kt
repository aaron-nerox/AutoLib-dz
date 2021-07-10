package com.clovertech.autolibdz.repository

import api.CarsApi
import api.SafeCarsRequest
import model.Rental

class CarsRepository(
        private val apiCarsByStat : CarsApi
) :SafeCarsRequest(){
    suspend fun getCarsByStat(Stat:String,idborn:Int)=ApiCarRequest{apiCarsByStat.getCarsListByState(Stat,idborn)}
    suspend fun addRental(rental: Rental)=ApiCarRequest { apiCarsByStat.addRental(rental) }
    }
