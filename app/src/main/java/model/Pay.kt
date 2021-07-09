package model

data class Pay (
        val paymentId:String,
        val amount:String,
        val idRental:String,
        val type:String,
        val idCodePromo:Int
)