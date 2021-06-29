package com.clovertech.autolibdz.ViewModel

import ViewModel.ViewModelCard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.repository.PaymentRepository

class MainViewModelFactoryCard(private val  repository: PaymentRepository) : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelCard(repository) as T
    }
}