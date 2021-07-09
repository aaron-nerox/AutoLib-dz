package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.repository.FactureRepository

class BillViewModelFactory(private val  repository: FactureRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BillViewModel(this.repository) as T
    }
}