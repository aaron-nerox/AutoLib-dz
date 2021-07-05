package com.clovertech.autolibdz.DataClasses

data class SubscriptionResponse (
        val subType:Int,
        val creationDate:String,
        val expirationDate:String,
        val subState:String,
        val idSub:Int
)