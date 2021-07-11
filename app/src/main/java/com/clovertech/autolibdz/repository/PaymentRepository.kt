package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.model.Pay
import com.clovertech.autolibdz.model.PayResponse
import com.clovertech.autolibdz.model.PaymentMethod
import com.clovertech.autolibdz.model.paymentResponse
import com.clovertech.autolibdz.model.SubscriptionRequest
import com.clovertech.autolibdz.model.SubscriptionResponse
import com.clovertech.autolibdz.model.paySubRequest
import com.clovertech.autolibdz.model.paySubResponse
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Response


class PaymentRepository {
    suspend fun pushCard(paymentMethod: PaymentMethod) : Response<paymentResponse> {
        return RetrofitInstance.cardApi.pushCard(paymentMethod)
    }
    suspend fun pay(pay: Pay) : Response<PayResponse> {
        return RetrofitInstance.cardApi.pay(pay)
    }
    suspend fun addSub(subscriptionRequest: SubscriptionRequest) : Response<SubscriptionResponse> {
        return RetrofitInstance.subApi.addSub(subscriptionRequest)
    }
    suspend fun subPay(paySubRequest: paySubRequest) : Response<paySubResponse> {
        return RetrofitInstance.subApi.subPay(paySubRequest)
    }
}