package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.model.Profil
import com.clovertech.autolibdz.utils.RetrofitInstance
import com.clovertech.autolibdz.model.*
import retrofit2.Response

class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<AuthenticationResponse> {
        return RetrofitInstance.authenticationApi.pushAuthentication(authentication)
    }
//
//    suspend fun getToken(authentication : Authentication) : Response<Token> {
//        return RetrofitInst.authenticationApi.getToken(authentication)
//    }
    suspend fun pushSignal(signal: Signal): Response<Signal> {
        return RetrofitInstance.signalApi.pushSignal(signal)
    }

    suspend fun getUser(): User {
        return RetrofitInstance.apiUser.getUser()
    }
    suspend fun getUserById(id :Int): Profil {
        return RetrofitInstance.apiUser.getUserById(id)
    }



}