package com.clovertech.autolibdz.utils

import com.clovertech.autolibdz.APIs.baseUrl

class Constants {
    companion object{
        const val Cars_BASE_URL = baseUrl+"/cars/"
        const val Pricing_BASE_URL=baseUrl+"pricing/"
        const val Pay_BASE_URL=baseUrl+"payment/cards/"
        const val Rental_BASE_URL="http://54.37.87.85:7000/cars/"
        const val Bill_BASE_URL=baseUrl+"bill/"
        const val CARD_BASE_URL = baseUrl+"payment/cards/"
        const val SUB_BASE_URL = baseUrl+"subscription/"
        const val USER_BASE_URL = "http://54.37.87.85:7000/users/"
        const val SIGNAL_BASE_URL = "http://54.37.87.85:7000/signalements/"
        const val APP_PREFS = "AUTO_LIB"
    }
}