package ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolibdz.model.Pay
import com.clovertech.autolibdz.model.PayResponse
import com.clovertech.autolibdz.model.PaymentMethod
import com.clovertech.autolibdz.model.paymentResponse
import com.clovertech.autolibdz.model.SubscriptionRequest
import com.clovertech.autolibdz.model.SubscriptionResponse
import com.clovertech.autolibdz.model.paySubRequest
import com.clovertech.autolibdz.model.paySubResponse
import com.clovertech.autolibdz.repository.PaymentRepository
import com.clovertech.autolibdz.model.CardRequest
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