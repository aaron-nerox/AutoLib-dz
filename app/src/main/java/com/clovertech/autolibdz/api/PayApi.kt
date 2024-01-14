package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PayApi {
    @POST("add")
    suspend fun pushCard(
            @Body paymentMethod: PaymentMethod
    ): Response<paymentResponse>
    @POST("pay")
    suspend fun pay(
            @Body pay: Pay
    ): Response<PayResponse>
    @POST("addSub")
    suspend fun addSub(
            @Body subscriptionRequest: SubscriptionRequest
    ): Response<SubscriptionResponse>

    @GET("getSubByTenant/{idTenant}")
    fun getSubByTenant(@Path("idTenant") idTenant:Int): Call<SubscriptionResponse>
    @GET("subState/{idTenant}")
    fun getSubStateByTenant(@Path("idTenant") idTenant:Int): Call<SubStateResponse>
    @POST("debitBalance")
    suspend fun subPay(
            @Body paySubRequest: paySubRequest
    ): Response<paySubResponse>
}