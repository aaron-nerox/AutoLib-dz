package ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import model.Pay
import model.PayResponse
import model.PaymentMethod
import model.paymentResponse
import model.SubscriptionRequest
import model.SubscriptionResponse
import model.paySubRequest
import model.paySubResponse
import com.clovertech.autolibdz.repository.PaymentRepository
import model.CardRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelCard(private val repository: PaymentRepository): ViewModel() {
    val CardResponse: MutableLiveData<CardRequest> = MutableLiveData()
    val AddCardResponse: MutableLiveData<Response<paymentResponse>> = MutableLiveData()
    val PayResponse: MutableLiveData<Response<PayResponse>> = MutableLiveData()
    val SubResponse: MutableLiveData<Response<SubscriptionResponse>> = MutableLiveData()
    val PaySubResponse: MutableLiveData<Response<paySubResponse>> = MutableLiveData()



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
    fun subPay(paySubRequest: paySubRequest){
        viewModelScope.launch {
            val response: Response<paySubResponse> = repository.subPay(paySubRequest)
            PaySubResponse.value = response
        }
    }
}