package com.clovertech.autolibdz.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.DataClass.PayResponse
import com.clovertech.autolibdz.DataClass.PaymentMethod
import com.clovertech.autolibdz.DataClass.paymentResponse
import com.clovertech.autolibdz.DataClasses.SubscriptionRequest
import com.clovertech.autolibdz.DataClasses.SubscriptionResponse
import com.clovertech.autolibdz.repository.CardsRepository
import com.clovertech.autolibdz.repository.PaymentRepository
import com.clovertech.autolibdz.ui.card.CardRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: PaymentRepository): ViewModel() {
    val CardResponse: MutableLiveData<CardRequest> = MutableLiveData()
    val AddCardResponse: MutableLiveData<Response<paymentResponse>> = MutableLiveData()
    val PayResponse: MutableLiveData<Response<PayResponse>> = MutableLiveData()
    val SubResponse: MutableLiveData<Response<SubscriptionResponse>> = MutableLiveData()



    /*  fun getCards(){
        viewModelScope.launch {
            val response: List<Card> = repository.getCards()
            CardResponse.value = response[]
        }
    }*/
    fun pushCard(paymentMethod: PaymentMethod){
        viewModelScope.launch {
            val response: Response<paymentResponse> = repository.pushCard(paymentMethod)
            AddCardResponse.value = response
        }
    }
    fun pay(pay: Pay){
        viewModelScope.launch {
            val response: Response<PayResponse> = repository.pay(pay)
            PayResponse.value = response
        }
    }
    fun addSub(subscriptionRequest: SubscriptionRequest){
        viewModelScope.launch {
            val response: Response<SubscriptionResponse> = repository.addSub(subscriptionRequest)
            SubResponse.value = response
        }
    }
}