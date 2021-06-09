package com.clovertech.autolibdz.DataClass

import com.clovertech.autolibdz.ui.card.CardRequest
import com.clovertech.autolibdz.ui.card.CardResponse

data class paymentInfo (
    val paymentId:String,
    val card: CardResponse
)