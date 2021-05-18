package api

import model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users/3")
    suspend fun getUser():User
}