package com.clovertech.autolibdz

import android.app.Dialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_bar_layout.*
import kotlinx.android.synthetic.main.custom_search_dialog_yello.*
import kotlinx.android.synthetic.main.custom_search_dialog_black.*
import java.security.AccessController.getContext
import java.util.*


class HomeActivity : AppCompatActivity() {

    private val layouts : ArrayList<LinearLayout> = ArrayList()
    private val images : ArrayList<ImageView> = ArrayList()
    private val fragments : ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
        supportFragmentManager.beginTransaction().replace(R.id.fragments_container, fragments[0]).commit()

        for (i in layouts.indices) {
            layouts[i].setOnClickListener {
                editTint(i)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragments_container, fragments[i])
                    .commit()
            }
        }

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
        fragments.add(FindYourCarFragment())
        fragments.add(Register2Fragment())
        fragments.add(Register3Fragment())
    }

    private fun editTint(pos: Int) {
        images.get(pos).setColorFilter(
            ContextCompat.getColor(this@HomeActivity, R.color.yello),
            PorterDuff.Mode.SRC_IN
        )
        for (i in images.indices) {
            if (i != pos) {
                images.get(i).setColorFilter(
                    ContextCompat.getColor(this@HomeActivity, R.color.dark_grey),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }
}