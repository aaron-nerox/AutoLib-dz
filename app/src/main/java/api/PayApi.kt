package api

import model.Pay
import model.PayResponse
import model.PaymentMethod
import model.paymentResponse
import model.SubscriptionRequest
import model.SubscriptionResponse
import model.paySubRequest
import model.paySubResponse
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
    @POST("debitBalance")
    suspend fun subPay(
            @Body paySubRequest: paySubRequest
    ): Response<paySubResponse>
}