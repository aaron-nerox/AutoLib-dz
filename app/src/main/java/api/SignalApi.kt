package api

import model.Signal
import model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignalApi {

    @POST("signals")
    suspend fun pushSignal(
        @Body signal : Signal
    ): Response<Signal>
}