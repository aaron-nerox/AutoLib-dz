package com.clovertech.autolibdz.ui.card

import java.io.Serializable

data class Card (
    //val nom:String,  // not included in backend
    val paymentId:String,
    val brand:String,
    val last4:Int,

   // val card_img:Int

)