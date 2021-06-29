package com.clovertech.autolibdz.ViewModel

import ViewModel.LoginViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.Repository


class LoginViewModelFactory (private val  repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}