package com.clovertech.autolibdz.model

data class PaymentInfo (
    val paymentId:String,
    val card: CardResponse,
    val name: String = ""
)