package com.clovertech.autolibdz.auth

import com.clovertech.autolibdz.ViewModel.MainViewModel
import com.clovertech.autolibdz.ViewModel.MainViewModelFactory
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.HomeActivity
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.password.ResetPasswordActivity
import com.clovertech.autolibdz.ui.promo.idTenantHelper
import com.clovertech.autolibdz.utils.Constants
import kotlinx.android.synthetic.main.activity_login.*
import com.clovertech.autolibdz.model.Authentication
import com.clovertech.autolibdz.repository.Repository


class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener(this)
        forgotPassword_txt_view.setOnClickListener(this)
        register_txt_view.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.login_btn -> {
                login()
            }
            R.id.forgotPassword_txt_view -> {
                startActivity(Intent(this, ResetPasswordActivity::class.java))
            }
            R.id.register_txt_view -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            else -> {}
        }
    }

    private fun login(){
        /// Authentification Api
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)

        startActivity(Intent(this, HomeActivity::class.java))

        if (email_edit_txt.text.toString() == ""){
            email_edit_txt.setError("Email required !")
        }else if (password_edit_txt.text.toString() == ""){
            password_edit_txt.setError("Password required !")
        } else {
            var authentication = Authentication(email_edit_txt.text.toString(), password_edit_txt.text.toString())
            viewModel.pushAuthentication(authentication)
            viewModel.authenticationResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    Toast.makeText(this, "SignIn Successfully", Toast.LENGTH_SHORT).show()
                    saveUserToken(response.body()?.token.toString(),response.body()?.id!!)
                    val idTenant=response.body()?.id

                    idTenantHelper=idTenant!!
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    Log.e("Push", response.body()?.id.toString())
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())

                } else {
                    Toast.makeText(this, "Login failed !!! ${response.message()}", Toast.LENGTH_SHORT).show()
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                }
            })
        }
    }

    private fun saveUserToken(token: String,id:Int){
        val preferences: SharedPreferences = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE)
        preferences.edit().putString("TOKEN", token).apply()
        preferences.edit().putInt("idUser",id).apply()
        Log.d("idTenant",id.toString())
        Log.d("token",token)
        /// Retrive Saved TOKEN
        //val retrivedToken = preferences.getString("TOKEN", null) //second parameter default value.
    }

}