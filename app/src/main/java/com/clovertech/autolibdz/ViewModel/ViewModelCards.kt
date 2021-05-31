package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.APIs.Couroutines

import com.clovertech.autolibdz.repository.CardsRepository
import com.clovertech.autolibdz.ui.card.Card
import kotlinx.coroutines.Job


class ViewModelCards(private val repository: CardsRepository): ViewModel()  {
    private lateinit var job: Job
    private val myResponse= MutableLiveData<List<Card>>()
    val userCards: LiveData<List<Card>>
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