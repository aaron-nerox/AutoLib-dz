package com.clovertech.autolibdz.DataClasses
import java.io.Serializable


data class Pay (
        val paymentId:String,
        val amount:Long,
        val idLocation : Int
) : Serializable