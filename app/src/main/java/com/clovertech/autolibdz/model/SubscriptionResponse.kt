package com.clovertech.autolibdz.model

data class SubscriptionResponse (
        val subType:Int,
        val creationDate:String,
        val expirationDate:String,
        val subState:String,
        val idSub:Int,
        val solde:Int
)