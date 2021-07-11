package com.clovertech.autolibdz.api

import retrofit2.Response
import java.io.IOException

open class SafePromoRequest {
    suspend fun<T:Any> ApiPromoRequest(call:suspend ()-> Response<T>):T{
        val response=call.invoke()
        if(response.isSuccessful){
            return  response.body()!!
        }
        else{
            throw ApiException(response.code().toString())
        }
    }
    class ApiException(msg:String): IOException(msg)
}