package com.clovertech.autolibdz.auth

import `view-model`.AuthenticationViewModel
import `view-model`.AuthenticationViewModelFactory
import android.content.Intent
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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import model.Authentication
import org.json.JSONObject
import repository.Repository


class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var viewModel : AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /// Authentification Api
        val repository = Repository()
        val viewModelFactory = AuthenticationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AuthenticationViewModel::class.java)

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
        if (email_edit_txt.text.toString() == ""){
            email_edit_txt.setError("Email required !")
        }else if (password_edit_txt.text.toString() == ""){
            password_edit_txt.setError("Password required !")
        } else {
            val authentication = Authentication("ha_saoudi@esi.dz", "123456789")
            viewModel.pushAuthentication(authentication)
            viewModel.authenticationResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    Toast.makeText(this, "SignIn Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    Log.e("Push", response.body()?.token.toString())
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                } else {
                    Toast.makeText(this, "Login failed !!!", Toast.LENGTH_SHORT).show()
                    Log.e("Push",response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                }
            })
        }
    }

}