package api

import model.Borne
import model.Locataire
import model.User
import model.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path


interface BorneApi {

    @GET("bornes")
    fun getBornes():Call<List<Borne>>

    @GET("borne/{id}/vehicles")
    fun getVehiculesBorne(@Path("id") idBorne: Int): Call<List<Vehicle>>

}