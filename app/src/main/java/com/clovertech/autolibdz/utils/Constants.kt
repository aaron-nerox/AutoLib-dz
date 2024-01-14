package com.clovertech.autolibdz.utils

import com.clovertech.autolibdz.api.baseUrl

class Constants {
    companion object{
        const val Cars_BASE_URL = "http://54.37.87.85:7000/cars/"
        const val Pricing_BASE_URL=baseUrl+"pricing/"
        const val Pay_BASE_URL=baseUrl+"payment/cards/"
        const val Rental_BASE_URL="http://54.37.87.85:7000/cars/"
        const val Bill_BASE_URL=baseUrl+"bill/"
        const val CARD_BASE_URL = baseUrl+"payment/cards/"
        const val SUB_BASE_URL = baseUrl+"subscription/"
        const val USER_BASE_URL = "http://54.37.87.85:7000/user/"
        const val SIGNAL_BASE_URL = "http://54.37.87.85:7000/signalements/"
        const val APP_PREFS = "AUTO_LIB"
        const val AUTH_BASE_URL = "http://54.37.87.85:7000/auth/"
        const val BORNE_BASE_URL = "http://54.37.87.85:7000/bornes/"
        const val ASSOCIATION_BASE_URL = "https://protected-depths-44465.herokuapp.com/"
        const val END_RENTAL_URL="http://54.37.87.85:7000/cars/endRental/"
         var idbornDefault=0
    }
}