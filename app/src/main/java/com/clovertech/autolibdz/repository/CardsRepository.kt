package com.clovertech.autolibdz.repository

import api.CardsApi

import com.clovertech.autolibdz.APIs.SafeCardsRequest

class CardsRepository (
    private val apiGetCard : CardsApi
) : SafeCardsRequest(){
        suspend fun getCards()=ApiCardsRequest { apiGetCard.getAllCards()}
    }



