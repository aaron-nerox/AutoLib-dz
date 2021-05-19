package repository

import model.Signal
import model.User
import retrofit2.Response
import utils.RetrofitInstance

class Repository {

    suspend fun getUser(): User {
        return RetrofitInstance.api.getUser()
    }
    suspend fun pushSignal(signal : Signal) : Response<Signal> {
        return RetrofitInstance.signalApi.pushSignal(signal)
    }
}