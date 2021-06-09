package com.clovertech.autolibdz.APIs

import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.DataClass.PayResponse
import com.clovertech.autolibdz.DataClass.PaymentMethod
import com.clovertech.autolibdz.DataClass.paymentResponse
import com.clovertech.autolibdz.DataClasses.SubscriptionRequest
import com.clovertech.autolibdz.DataClasses.SubscriptionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

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

}