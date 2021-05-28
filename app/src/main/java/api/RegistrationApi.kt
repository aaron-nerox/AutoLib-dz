package api

import model.Locataire
import model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {

    @POST("users")
    fun registerUser(
            @Body user : User
    ): Call<User>

    @POST("locataires")
    fun registerLocataire(
            @Body user : Locataire
    ): Call<Locataire>

}