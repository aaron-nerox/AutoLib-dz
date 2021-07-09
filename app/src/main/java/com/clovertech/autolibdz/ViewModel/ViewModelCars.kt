package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.APIs.Couroutines
import model.Rental
import model.Vehicle
import com.clovertech.autolibdz.repository.CarsRepository
import kotlinx.coroutines.Job


class ViewModelCars (private val repository: CarsRepository): ViewModel() {
    private lateinit var job:Job
    private val myResponse= MutableLiveData<List<Vehicle>>()


    val carsByStat:LiveData<List<Vehicle>>
        get() = myResponse

      fun getListCarsByStat(Status: String,idborn:Int){
         job= Couroutines.ioThenMain(
             {repository.getCarsByStat(Status,idborn)},{
                myResponse.value=it
             })
        /*val carsByStat=repository.getCarsByStat(Status)
        this.myResponse.value=carsByStat*/
    }
    suspend fun addRental(rental: Rental): Rental {
        val response=repository.addRental(rental)
       return response
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }


}