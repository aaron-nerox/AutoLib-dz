package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.Signal
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignalApi {

    @POST("signals")
    suspend fun pushSignal(
        @Body signal : Signal
    ): Response<Signal>
}