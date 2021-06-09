package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.APIs.CardsApi

import com.clovertech.autolibdz.APIs.SafeCardsRequest
import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.DataClass.PayResponse
import com.clovertech.autolibdz.DataClass.PaymentMethod
import com.clovertech.autolibdz.DataClass.paymentResponse
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Response

class CardsRepository (
    private val apiGetCard : CardsApi) : SafeCardsRequest(){
        suspend fun getCards()=ApiCardsRequest { apiGetCard.getAllCards()}
    }



