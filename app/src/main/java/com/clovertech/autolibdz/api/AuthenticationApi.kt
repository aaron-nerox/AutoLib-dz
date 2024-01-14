package com.clovertech.autolibdz.api

import com.clovertech.autolibdz.model.Authentication
import com.clovertech.autolibdz.model.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("signin")
    suspend fun pushAuthentication(
        @Body authetication : Authentication
    ): Response<AuthenticationResponse>

}