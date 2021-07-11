package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.api.PromoApi
import com.clovertech.autolibdz.api.SafePromoRequest


class PromoRepository( private val apiPromoApi: PromoApi) : SafePromoRequest(){
    suspend fun getPromo()=ApiPromoRequest{apiPromoApi.getAllPromo()}

}