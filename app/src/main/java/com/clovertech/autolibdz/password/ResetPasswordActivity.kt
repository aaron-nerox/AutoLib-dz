package com.clovertech.autolibdz.password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.password.fragments.ResetPassword1Fragment
import com.clovertech.autolibdz.password.fragments.ResetPassword2Fragment
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    var currentFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ResetPassword1Fragment())
            .commitAllowingStateLoss()

        send_btn.setOnClickListener{
            when(currentFragment){
                1 -> {
                    currentFragment = 2
                    send_btn.text = "Continue"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ResetPassword2Fragment())
                        .commitAllowingStateLoss()
                }
                2 -> {

                }
                else -> {}
            }
        }

    }

    override fun onBackPressed() {
        when(currentFragment){
            1 -> {
                super.onBackPressed()
            }
            2 -> {
                currentFragment = 1
                send_btn.text = "Send"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ResetPassword1Fragment())
                    .commitAllowingStateLoss()
            }
            else -> {}
        }
    }
}