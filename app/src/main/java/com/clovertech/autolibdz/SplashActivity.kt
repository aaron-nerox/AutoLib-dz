package com.clovertech.autolibdz


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clovertech.autolibdz.utils.Constants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefs = getSharedPreferences(Constants.APP_PREFS, MODE_PRIVATE)

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (!prefs.getBoolean("FIRST_RUN", false)){
                        val toOnBoardingIntent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                        startActivity(toOnBoardingIntent)
                        finish()
                    }else {
                        if(!prefs.getString("TOKEN","").isNullOrEmpty()){
                            val toMain = Intent(this@SplashActivity, HomeActivity::class.java)
                            startActivity(toMain)
                            finish()
                        }else{
                            val toMain = Intent(this@SplashActivity, MainActivity::class.java)
                            startActivity(toMain)
                            finish()
                        }
                    }
                }
            }
        }
        thread.start()

    }
}