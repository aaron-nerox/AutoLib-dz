package com.clovertech.autolibdz.api

import retrofit2.Response
import java.io.IOException

abstract class SafeCardsRequest {
    suspend fun<T:Any> ApiCardsRequest(call:suspend ()-> Response<T>):T{
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