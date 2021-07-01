package com.clovertech.autolibdz.ui.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PromoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is promo Fragment"
    }
    val text: LiveData<String> = _text
}