package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.repository.CardsRepository


class ViewModelCardsFactory (private val  repository: CardsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelCards(this.repository) as T
    }

}