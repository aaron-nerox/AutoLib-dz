package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.APIs.CarsApi
import com.clovertech.autolibdz.APIs.FactureApi
import com.clovertech.autolibdz.APIs.SafeCarsRequest
import com.clovertech.autolibdz.APIs.SafeFactureRequest

class FactureRepository( private val apiFactures : FactureApi) : SafeFactureRequest(){
    suspend fun getFactures()=ApiFactureRequest{apiFactures.getFactures()}

}