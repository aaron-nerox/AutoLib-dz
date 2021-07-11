package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.DataClasses.Location
import com.clovertech.autolibdz.api.Couroutines
import com.clovertech.autolibdz.model.Rental
import com.clovertech.autolibdz.model.Vehicle
import com.clovertech.autolibdz.repository.CarsRepository
import kotlinx.coroutines.Job
import retrofit2.Response


class ViewModelCars (private val repository: CarsRepository): ViewModel() {
    private lateinit var job:Job
    private val myResponse= MutableLiveData<List<Vehicle>>()
    private val locationRep= MutableLiveData<Response<Location>>()


    val carsByStat:LiveData<List<Vehicle>>
        get() = myResponse

      fun getListCarsByStat(Status: String,idborn:Int){
         job= Couroutines.ioThenMain(
             {repository.getCarsByStat(Status,idborn)},{
                myResponse.value=it
             })
        /*val carsByStat=com.clovertech.autolibdz.repository.getCarsByStat(Status)
        this.myResponse.value=carsByStat*/
    }
    suspend fun addRental(rental: Rental): Rental {
        val response=repository.addRental(rental)
       return response
    }

 /*   suspend fun validateLocation(id:Int){
        job=Couroutines.ioThenMain(
            {com.clovertech.autolibdz.repository.validateLocation(id)},{it->
                locationRep.value=it
            }
        )
    }*/

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }


}