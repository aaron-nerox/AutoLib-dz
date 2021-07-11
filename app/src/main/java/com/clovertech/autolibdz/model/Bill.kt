package com.clovertech.autolibdz.model

data class Bill(
     val idUser:Int,
     val userName:String,val phoneNumber:String,val lastName:String,
     val firstName:String,val address:String,val userType:String,
     val validTenant:String,val refPermit:String,val profilePicture:String,val permitPicture:String,
     val  selfie:String,val  subCard:String,val  points:String,val accountState:String,val stateMessage:String,val longitude:String,
     val latitude:String,val dateSignUp:String,val idRental:String,
     val idVehicle:String,val rentaldate:String,val rentaltime:String,val plannedrestitutiondate:String,
     val plannedrestitutiontime:String,val restitutionDate:String,val restitutionTime:String,val rentalType:String,
     val iddepartborne: Int,
     val   iddestborne: Int,
     val rentalstate:String,
     val idBill: Int,
     val nbBill: Int,
     val creationDate:String,
     val baseRate: Int,
     val penaltyRate: Int,
     val totalRate: Int,
     val report:String,
     val updatedAt :String
)







