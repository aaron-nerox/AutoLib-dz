package com.clovertech.autolibdz.repository

import model.Pay
import model.PayResponse
import model.PaymentMethod
import model.paymentResponse
import model.SubscriptionRequest
import model.SubscriptionResponse
import model.paySubRequest
import model.paySubResponse
import retrofit2.Response

class PaymentRepository {
    suspend fun pushCard(paymentMethod: PaymentMethod) : Response<paymentResponse> {
        return RetrofitInstance.api.pushCard(paymentMethod)
    }
    suspend fun pay(pay: Pay) : Response<PayResponse> {
        return RetrofitInstance.api.pay(pay)
    }
    suspend fun addSub(subscriptionRequest: SubscriptionRequest) : Response<SubscriptionResponse> {
        return RetrofitInstance.subApi.addSub(subscriptionRequest)
    }
    suspend fun subPay(paySubRequest: paySubRequest) : Response<paySubResponse> {
        return RetrofitInstance.subApi.subPay(paySubRequest)
    }
}