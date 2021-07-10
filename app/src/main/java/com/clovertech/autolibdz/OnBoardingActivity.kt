package com.clovertech.autolibdz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.edit
import androidx.viewpager.widget.ViewPager
import com.clovertech.autolibdz.auth.LoginActivity
import com.clovertech.autolibdz.utils.Constants
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.android.synthetic.main.view_pager_item.*
import java.util.ArrayList

class OnBoardingActivity : AppCompatActivity() {

    private var sliderAdapter: SliderAdapter? = null
    private val mDots: ArrayList<ImageView> = ArrayList<ImageView>(4)
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_on_boarding)
        val prefs = getSharedPreferences(Constants.APP_PREFS, MODE_PRIVATE)

        sliderAdapter = SliderAdapter(this)
        view_pager.adapter = sliderAdapter
        mDots.add(dot1)
        mDots.add(dot2)
        mDots.add(dot3)
        view_pager.addOnPageChangeListener(onPageChangeListener)
        editDotIndicatorColor(0)

        skip_button.setOnClickListener{
            prefs.edit {
                putBoolean("FIRST_RUN", true)
            }
            val toMain = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(toMain)
            finish()
        }

        backward_button.setOnClickListener {
            if(view_pager.currentItem - 1 >= 0){
                view_pager.currentItem = view_pager.currentItem - 1
            }else if(view_pager.currentItem == 0){
                finish()
            }
        }
    }

    fun editDotIndicatorColor(position: Int) {
        for (i in mDots.indices) {
            if (i == position) {
                mDots[i].setBackgroundResource( R.drawable.ic_circle_activated_dot)
            } else {
                mDots[i].setBackgroundResource( R.drawable._circle_white)
            }
        }
    }

    private var onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
        ) {
            pos=position
        }

        override fun onPageSelected(position: Int) {
            editDotIndicatorColor(position)
            if (position == 3){
                skip_button.text = "Next"
            }else {
                skip_button.text = "Skip"
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

}