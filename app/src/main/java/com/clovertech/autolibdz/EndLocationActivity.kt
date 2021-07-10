package com.clovertech.autolibdz

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import kotlinx.android.synthetic.main.bottom_bar_layout.*
import kotlinx.android.synthetic.main.end_location.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class EndLocationActivity: AppCompatActivity() {
    private val layouts : ArrayList<LinearLayout> = ArrayList()
    private val images : ArrayList<ImageView> = ArrayList()
    private val fragments : ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_location)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
  //      setSupportActionBar(toolbar)
       // init()
        //supportFragmentManager.beginTransaction().replace(R.id.fragments_container, fragments[0]).commit()

       /* for (i in layouts.indices) {
            layouts[i].setOnClickListener {
                editTint(i)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragments_container, fragments[i])
                    .commit()
            }
        }*/
        countDown()
    }




    private fun init() {
        layouts.add(home_layout as LinearLayout)
        layouts.add(event_layout as LinearLayout)
        layouts.add(favorite_layout as LinearLayout)
        layouts.add(profile_layout as LinearLayout)
        images.add(home_img as ImageView)
        images.add(event_img as ImageView)
        images.add(favorite_img as ImageView)
        images.add(profile_img as ImageView)
        fragments.add(HomeFragment())
        fragments.add(Register1Fragment())
        fragments.add(Register2Fragment())
        fragments.add(Register3Fragment())
    }

    private fun editTint(pos: Int) {
        images.get(pos).setColorFilter(
            ContextCompat.getColor(this@EndLocationActivity, R.color.yello),
            PorterDuff.Mode.SRC_IN
        )
        for (i in images.indices) {
            if (i != pos) {
                images.get(i).setColorFilter(
                    ContextCompat.getColor(this@EndLocationActivity, R.color.dark_grey),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun countDown() {
        val countDownTimer = object : CountDownTimer(1584700200, 1000) {
            override fun onTick(p0: Long) {
                val millis: Long = p0
                val hms = String.format(
                    "%02d:%02d:%02d:%02d",
                    TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toDays(millis)),
                    (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(
                        TimeUnit.MILLISECONDS.toDays(
                            millis
                        )
                    )),
                    (TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                    (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                    ))
                )

                System.out.println("Time : " + hms)
                time_id.setText(hms);//set text
            }

            override fun onFinish() {
                /*clearing all fields and displaying countdown finished message          */
                fin_loc.setText("Votre Location est expiré");
                System.out.println("Time up")
            }
        }
        countDownTimer.start()
    }
    }