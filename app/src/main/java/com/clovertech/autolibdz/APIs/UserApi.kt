package api

import model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/3")
    suspend fun getUser():User

    @GET("users/{id}")
    suspend fun getUserById(@Path("id")id :Int):User
}