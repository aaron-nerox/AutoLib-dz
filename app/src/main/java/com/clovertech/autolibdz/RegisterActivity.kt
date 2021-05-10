package com.clovertech.autolibdz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()  , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        back_to_login.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.back_to_login -> {
                super.onBackPressed()
            }
            else -> {}
        }
    }
}