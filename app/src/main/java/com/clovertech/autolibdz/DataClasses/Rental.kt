package com.clovertech.autolibdz.DataClasses

data class Rental(

    val idRental:Int,
    val idVehicle:Int,
    val rentaldate:String,
    val rentaltime:String,
    val plannedrestitutiondate:String,
    val plannedrestitutiontime:String,
    val restitutionDate:String,
    val restitutionTime: String,
    val rentalType:String,
    val iddepartborne:Int,
    val iddestborne:Int,
    val rentalstate:String


)