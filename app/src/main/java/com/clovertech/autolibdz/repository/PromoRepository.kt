package com.clovertech.autolibdz.repository

import api.PromoApi
import api.SafePromoRequest


class PromoRepository( private val apiPromoApi: PromoApi) : SafePromoRequest(){
    suspend fun getPromo()=ApiPromoRequest{apiPromoApi.getAllPromo()}

}