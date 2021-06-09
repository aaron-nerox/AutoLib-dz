package com.clovertech.autolibdz.DataClass

import com.clovertech.autolibdz.ui.card.CardRequest

data class PaymentMethod (
    val card: CardRequest,
    val adress: Adress,
    val name:String

)