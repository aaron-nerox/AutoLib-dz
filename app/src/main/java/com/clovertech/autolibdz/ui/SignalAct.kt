package com.clovertech.autolibdz.ui

import ViewModel.MainViewModel
import ViewModel.MainViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import model.Signal
import repository.Repository

class SignalAct : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signal)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(MainViewModel::class.java)

        val signal = Signal(6,"theft",2,"2021-05-18T02:37:03.137Z"
        ,"Auto","Pushed signal",14)

        viewModel.pushSignal(signal)
        viewModel.signalResponse.observe(this, Observer {
            response ->
            if (response.isSuccessful){
                Log.e("Push",response.body().toString())
                Log.e("Push",response.code().toString())
                Log.e("Push",response.message())
            }
        })
    }
}