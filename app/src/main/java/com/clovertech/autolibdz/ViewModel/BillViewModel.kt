package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.api.CouroutineFact
import com.clovertech.autolibdz.model.Facture
import com.clovertech.autolibdz.repository.FactureRepository
import kotlinx.coroutines.Job

class BillViewModel(private val repository: FactureRepository): ViewModel() {
    private lateinit var job: Job
    private val myResponse= MutableLiveData<List<Facture>>()
    val facts: LiveData<List<Facture>>
        get() = myResponse
    fun getFact(){
        job= CouroutineFact.ioThenMain(
                {repository.getFactures()},
                {myResponse.value=it})
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }

}