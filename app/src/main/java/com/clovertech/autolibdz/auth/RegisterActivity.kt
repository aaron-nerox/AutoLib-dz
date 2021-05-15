package com.clovertech.autolibdz.auth

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.date_picker_spinner_mode.*
import kotlinx.android.synthetic.main.fragment_register3.view.*

class RegisterActivity : AppCompatActivity()  , View.OnClickListener {

    var currentFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, Register1Fragment())
                .commitAllowingStateLoss()
        next_layout.setOnClickListener(this)
        login_txt_view.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.next_layout -> {
                when (currentFragment){
                    1 -> {
                        currentFragment = 2
                        dot2.setColorFilter(ContextCompat.getColor(baseContext,R.color.yello))
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.container, Register2Fragment())
                                .commitAllowingStateLoss()
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