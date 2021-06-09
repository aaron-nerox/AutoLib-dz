package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.repository.CardsRepository
import com.clovertech.autolibdz.repository.FactureRepository
import com.clovertech.autolibdz.repository.RentalRepository

class RentalViewModelFactory (private val  repository: RentalRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RentalViewModel(this.repository) as T
    }

}