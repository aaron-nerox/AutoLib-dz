package com.clovertech.autolibdz.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.Register1Fragment
import com.clovertech.autolibdz.adapter.MyViewPagerAdapter
import com.clovertech.autolibdz.ui.gallery.GalleryFragment
import com.clovertech.autolibdz.ui.home.HomeFragment
import com.clovertech.autolibdz.ui.slideshow.SlideshowFragment
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()  , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val adapter = initViewPagerAdapter()
        viewPager.adapter = adapter
        viewPager.pagingEnabled = true

        //back_to_login.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id){
            /*R.id.back_to_login -> {
                super.onBackPressed()
            }*/
            else -> {}
        }
    }

    fun initViewPagerAdapter(): MyViewPagerAdapter {
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Register1Fragment())
        adapter.addFragment(GalleryFragment())
        adapter.addFragment(SlideshowFragment())
        return adapter
    }
}