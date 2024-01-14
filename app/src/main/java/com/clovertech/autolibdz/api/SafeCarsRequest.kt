package com.clovertech.autolibdz.api

import retrofit2.Response
import java.io.IOException

abstract class SafeCarsRequest {
    suspend fun<T:Any> ApiCarRequest(call:suspend ()->Response<T>):T{
        val response=call.invoke()
        if(response.isSuccessful){
            return  response.body()!!
        }
        else{
            throw ApiException(response.toString())
        }
    }
}

class ApiException(msg:String):IOException(msg)
