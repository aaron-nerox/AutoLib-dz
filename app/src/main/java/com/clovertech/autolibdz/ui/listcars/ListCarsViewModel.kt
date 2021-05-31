package com.clovertech.autolibdz.ui.listcars
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.clovertech.autolibdz.DataClasses.Resource

import kotlinx.coroutines.Dispatchers

class ListCarsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is facture Fragment"
    }
    val text: LiveData<String> = _text






}
