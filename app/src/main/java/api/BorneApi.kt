package api

import model.Borne
import retrofit2.Call
import retrofit2.http.GET

interface BorneApi {
    @GET("bornes")
    fun getBornes(): Call<List<Borne>>
}
