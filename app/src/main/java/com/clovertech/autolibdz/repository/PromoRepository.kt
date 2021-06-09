package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.APIs.PromoApi
import com.clovertech.autolibdz.APIs.SafePromoRequest


class PromoRepository( private val apiPromoApi: PromoApi) : SafePromoRequest(){
    suspend fun getPromo()=ApiPromoRequest{apiPromoApi.getAllPromo()}

}