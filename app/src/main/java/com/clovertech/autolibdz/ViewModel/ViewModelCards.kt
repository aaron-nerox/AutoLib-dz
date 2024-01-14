package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.api.Couroutines
import com.clovertech.autolibdz.model.PaymentInfo

import com.clovertech.autolibdz.repository.CardsRepository
import kotlinx.coroutines.Job


class ViewModelCards(private val repository: CardsRepository): ViewModel()  {
    private lateinit var job: Job
    private val myResponse= MutableLiveData<List<PaymentInfo>>()
    val userCards: LiveData<List<PaymentInfo>>
        get() = myResponse
    fun getCards(){
        job=Couroutines.ioThenMain(
                {repository.getCards()},
                {myResponse.value=it})
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }

}