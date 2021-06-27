package api

import model.Borne
import model.Locataire
import model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET


interface BorneApi {

    @GET("bornes")
    fun getBornes():Call<List<Borne>>

}