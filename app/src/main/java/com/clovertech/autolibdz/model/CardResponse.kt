package com.clovertech.autolibdz.model

import com.google.gson.annotations.SerializedName

data class CardResponse (
    val brand :String,
    val last4:String,
    @SerializedName("exp_month") val expMonth: Int,
    @SerializedName("exp_year") val expYear: Int
)