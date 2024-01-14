package com.clovertech.autolibdz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.ViewModel.MainViewModel
import com.clovertech.autolibdz.ViewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_profile.*
import com.clovertech.autolibdz.repository.Repository

class ProfileAct : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.getUser()
        viewModel.myResponse.observe(this, Observer {
                response ->
            Log.d("Response", response.firstName)
            fullname.text = response.firstName +" "+ response.lastName
            username.text = response.userName
            phonenumber.text = response.phoneNumber.toString()
        })
        report_problem.setOnClickListener {
            val i = Intent(this@ProfileAct, SignalAct::class.java)
            startActivity(i)
        }
    }
}