package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.api.FactureApi
import com.clovertech.autolibdz.api.SafeFactureRequest

class FactureRepository(
        private val apiFactures : FactureApi
) : SafeFactureRequest(){
         suspend fun getFactures()=ApiFactureRequest{apiFactures.getFactures()}
        suspend fun geBillByID(id:Int)=ApiFactureRequest{apiFactures.getBillByID(id)}

    }

