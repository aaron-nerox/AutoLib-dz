package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.DataClasses.Location
import com.clovertech.autolibdz.model.Rental
import com.clovertech.autolibdz.repository.RentalRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class RentalViewModel (private val repository: RentalRepository): ViewModel() {

val myResponse: MutableLiveData<Rental> = MutableLiveData()
val rentalResponse: MutableLiveData<Response<Rental>> = MutableLiveData()

    var msg:MutableLiveData<Response<String>> = MutableLiveData()
    var locationRep:MutableLiveData<Response<Location>> = MutableLiveData()

    fun addRental(rental: Rental){
        viewModelScope.launch {
            val response: Response<Rental> = repository.addRental(rental)
            rentalResponse.value = response
        }
    }

    fun endRental(idVehicle:Int,idborn:Int){
        viewModelScope.launch {
            val rep:Response<String> = repository.endRental(idVehicle,idborn)
            msg.value=rep
        }
    }

    fun validateLocation(id:Int){
        viewModelScope.launch {
            val rep:Response<Location> = repository.validateLocation(id)
            locationRep.value=rep
        }
    }
}