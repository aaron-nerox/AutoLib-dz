package com.clovertech.autolibdz.DataClasses


import com.clovertech.autolibdz.ui.card.CardResponse

data class PaymentInfo (
    val paymentId:String,
    val card: CardResponse,
    val name: String = ""
)