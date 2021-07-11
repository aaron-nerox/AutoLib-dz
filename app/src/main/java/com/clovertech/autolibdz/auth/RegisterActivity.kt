package com.clovertech.autolibdz.auth

import com.clovertech.autolibdz.ViewModel.RegisterViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.date_picker_spinner_mode.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register1.view.*
import kotlinx.android.synthetic.main.fragment_register2.*
import kotlinx.android.synthetic.main.fragment_register3.view.*
import com.clovertech.autolibdz.model.Locataire
import com.clovertech.autolibdz.model.User
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity()  , View.OnClickListener {

    var currentFragment = 1
    private val model: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, Register1Fragment())
                .commitAllowingStateLoss()

        next_button.setOnClickListener(this)
        login_txt_view.setOnClickListener(this)
        registr_btn.setOnClickListener(this)

        model.setUser(User(0, "", "", "", "", "", "tenant", ""))
        model.setLocataire(Locataire(0, "", "", "", "", ""))

    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.registr_btn -> {
                register()
            }
            R.id.next_button -> {
                when (currentFragment){
                    1 -> {
                        if (email.error == null && nom.error == null && prenom.error == null && adresse.error == null) {
                            currentFragment = 2
                            dot2.setColorFilter(ContextCompat.getColor(baseContext,R.color.yello))
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.container, Register2Fragment())
                                    .commitAllowingStateLoss()
                        }

                    }
                    2 -> {
                        currentFragment = 3
                        dot3.setColorFilter(ContextCompat.getColor(baseContext,R.color.yello))
                        registr_btn.visibility = View.VISIBLE
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, Register3Fragment())
                            .commitAllowingStateLoss()
                    }
                    3 -> {
                    }
                }
            }
            R.id.login_txt_view -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else -> {}
        }
    }

    fun register() {
        RetrofitInstance.registrationApi.registerUser(model.user.value!!).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Register failed.", Toast.LENGTH_SHORT).show()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
            ) {
                val usersResponse = response.body()

//                Toast.makeText(this@RegisterActivity, "code ${response.code()}", Toast.LENGTH_SHORT).show()
                if (response.code() == 200) {
//                    Toast.makeText(this@RegisterActivity, "User here ${usersResponse?.idUser}", Toast.LENGTH_SHORT).show()
                    usersResponse?.idUser?.let {
                        Log.e("id", it.toString())
                        model.setIdUser(it)

                        val locataire = Locataire(it, "", "", "", "", "")

                        RetrofitInstance.registrationApi.registerLocataire(locataire).enqueue(object : Callback<Locataire> {
                            override fun onFailure(call: Call<Locataire>, t: Throwable) {
                                Toast.makeText(this@RegisterActivity, "Register failed.", Toast.LENGTH_SHORT).show()
                                Log.e("error", t.localizedMessage)
                            }

                            override fun onResponse(
                                    call: Call<Locataire>,
                                    response: Response<Locataire>
                            ) {
                                val statusCode = response.code()
                                if (statusCode == 200)
                                    Toast.makeText(this@RegisterActivity, "Register success.", Toast.LENGTH_SHORT).show()
                                else
                                    Toast.makeText(this@RegisterActivity, "Locataire registration failed.", Toast.LENGTH_SHORT).show()
                            }
                        })

                    }

                } else
                    Toast.makeText(this@RegisterActivity, "Register failed. please try again", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        when (currentFragment){
            1 -> {
                super.onBackPressed()
            }
            2 -> {
                currentFragment = 1
                dot2.setColorFilter(ContextCompat.getColor(baseContext,R.color.dark_grey))
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Register1Fragment())
                        .commitAllowingStateLoss()
            }
            3 -> {
                currentFragment = 2
                dot3.setColorFilter(ContextCompat.getColor(baseContext,R.color.dark_grey))
                registr_btn.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Register2Fragment())
                        .commitAllowingStateLoss()
            }
        }
    }
}