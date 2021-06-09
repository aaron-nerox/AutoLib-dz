package com.clovertech.autolibdz.ui.tarification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TarificationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tarification Fragment"
    }
    val text: LiveData<String> = _text
}