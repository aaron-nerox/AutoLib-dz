package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.api.CardsApi

import com.clovertech.autolibdz.api.SafeCardsRequest

class CardsRepository (
    private val apiGetCard : CardsApi
) : SafeCardsRequest(){
        suspend fun getCards()=ApiCardsRequest { apiGetCard.getAllCards()}
    }



