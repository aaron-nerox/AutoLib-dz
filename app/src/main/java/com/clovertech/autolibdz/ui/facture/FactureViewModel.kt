package com.clovertech.autolibdz.ui.facture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FactureViewModel :ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "This is facture Fragment"
    }
    val text: LiveData<String> = _text
}