package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.repository.RentalRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class RentalViewModel (private val repository: RentalRepository): ViewModel() {

val myResponse: MutableLiveData<Rental> = MutableLiveData()
val rentalResponse: MutableLiveData<Response<Rental>> = MutableLiveData()


    fun addRental(rental: Rental){
        viewModelScope.launch {
            val response: Response<Rental> = repository.addRental(rental)
            rentalResponse.value = response
        }
    }
}