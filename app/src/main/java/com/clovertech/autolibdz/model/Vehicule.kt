package com.clovertech.autolibdz.model

data class Vehicle (
    val idVehicle:Int,
    val vehicletype:String,
    val vehiclebrand:String,
    val availibility:String,
    val unitPricePerHour: Int,
    val unitPricePerDay:Int,
    val vehiclemodel:String,
    val image:String,
    val fuelType:String,
    val vehicleColor:String,
    val registrationNumber:String,
    val idBorne:Int,
    val longitude:String,
    val latitude:String,
    val chassisNumber:String,
    val numberOfPlaces:Int,
    val speed:Int
)