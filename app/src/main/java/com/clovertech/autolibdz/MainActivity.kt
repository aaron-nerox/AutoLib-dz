package com.clovertech.autolibdz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clovertech.autolibdz.auth.LoginActivity
import com.clovertech.autolibdz.auth.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn_btn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        signUp_btn.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }

}