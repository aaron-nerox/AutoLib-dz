package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.repository.PromoRepository

class PromoViewModelFactory (private val  repository: PromoRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PromoCodeViewModel(this.repository) as T
    }
}