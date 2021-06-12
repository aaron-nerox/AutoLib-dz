package com.clovertech.autolibdz.DataClasses

import java.io.Serializable

data class Car(
    val prix:Double,
    val marque : String,
    val matricule:Int,
    val img:Int
): Serializable