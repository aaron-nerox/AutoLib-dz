package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.APIs.CardsApi

import com.clovertech.autolibdz.APIs.SafeCardsRequest

class CardsRepository (
    private val apiGetCard : CardsApi) : SafeCardsRequest(){
        suspend fun getCards()=ApiCardsRequest { apiGetCard.getAllCards()}
    }


