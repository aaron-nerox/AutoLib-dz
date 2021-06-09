package com.clovertech.autolibdz.ui.cardetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CarDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is facture Fragment"
    }
    val text: LiveData<String> = _text
}