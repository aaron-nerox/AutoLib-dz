package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.DataClass.PayResponse
import com.clovertech.autolibdz.DataClass.PaymentMethod
import com.clovertech.autolibdz.DataClass.paymentResponse
import com.clovertech.autolibdz.DataClasses.SubscriptionRequest
import com.clovertech.autolibdz.DataClasses.SubscriptionResponse
import com.clovertech.autolibdz.DataClasses.paySubRequest
import com.clovertech.autolibdz.DataClasses.paySubResponse
import com.clovertech.autolibdz.utils.RetrofitInstance
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