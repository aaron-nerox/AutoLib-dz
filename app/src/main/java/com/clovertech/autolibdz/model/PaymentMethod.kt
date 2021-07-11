package com.clovertech.autolibdz.model

data class PaymentMethod (
    val card: CardRequest,
    val adress: Adress,
    val name:String

)