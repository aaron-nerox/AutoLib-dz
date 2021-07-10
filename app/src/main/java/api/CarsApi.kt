package api

import model.Rental
import model.Vehicle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarsApi {
    @GET("vehiclesListByState/{state}/{idborn}")
    suspend fun getCarsListByState(@Path("state") state : String,
                                    @Path("idborn") idborn:Int): Response<MutableList<Vehicle>>

    @POST("addRental")
    suspend fun addRental(
            @Body rental: Rental
    ):Response<Rental>

    companion object{
        operator fun invoke(): CarsApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl +"rental/")
                    .build()
                .create(CarsApi::class.java)

        }
    }
}