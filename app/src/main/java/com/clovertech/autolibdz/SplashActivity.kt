package com.clovertech.autolibdz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (firstRun()){
                        val toMain = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                        toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                        val editor = prefs.edit()
                        editor.putBoolean("FirstRun", false)
                        editor.apply()
                        startActivity(toMain)
                        finish()
                    }else {
                        val toMain = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                        toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(toMain)
                    }
                }
            }
        }
        thread.start()

    }

    private fun firstRun(): Boolean {
        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("FirstRun", true)

    }
}