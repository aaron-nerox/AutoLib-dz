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


class HomeActivity : AppCompatActivity() , View.OnClickListener {

    private val layouts : ArrayList<LinearLayout> = ArrayList()
    private val images : ArrayList<ImageView> = ArrayList()
    private val fragments : ArrayList<Fragment> = ArrayList()
    lateinit var searchDialogPosition : Dialog
    lateinit var searchDialogPark : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        searchDialogPosition = Dialog(this)
        searchDialogPark = Dialog(this)

        init()
        supportFragmentManager.beginTransaction().replace(R.id.fragments_container, fragments[0]).commit()

        for (i in layouts.indices) {
            layouts[i].setOnClickListener {
                editTint(i)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragments_container, fragments[i])
                    .commit()
                if (i == 0) {
                    search_position_dialog.visibility = View.VISIBLE
                    search_park_dialog.visibility = View.VISIBLE
                }
                else {
                    search_position_dialog.visibility = View.GONE
                    search_park_dialog.visibility = View.GONE
                }
            }
        }

        checked_position.setOnClickListener(this)
        checked_park.setOnClickListener(this)
        search_position.setOnClickListener(this)
        search_park.setOnClickListener(this)

    }

    override fun onClick(view : View?) {
        when(view?.id){
            R.id.checked_position -> {
                moveSearchPositionDialog()
            }
            R.id.checked_park -> {
                moveSearchParkDialog()
            }
            R.id.search_position -> {
                searchDialogPosition.setContentView(R.layout.custom_search_dialog_position_expanded)
                searchDialogPosition.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                searchDialogPosition.show()
            }
            R.id.search_park -> {
                searchDialogPark.setContentView(R.layout.custom_search_dialog_park_expanded)
                searchDialogPark.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                searchDialogPark.show()
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
        fragments.add(Register2Fragment())
        fragments.add(Register3Fragment())
        fragments.add(Register1Fragment())
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

    fun Float.toDips() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)

    private fun moveSearchPositionDialog(){
        var handler= Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (150f).toDips().toInt()
                )
                params.addRule(RelativeLayout.BELOW , R.id.app_bar)
                search_position_dialog.layoutParams = params
                search_position_dialog.x = search_position_dialog.x + (search_position_dialog.width * 0.9).toFloat()
                checked_position.visibility = View.GONE
                search_position.visibility = View.GONE
            }
        }, 500)
    }

    private fun moveSearchParkDialog(){
        var handler= Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (150f).toDips().toInt()
                )
                params.addRule(RelativeLayout.BELOW , R.id.search_position_dialog)
                search_park_dialog.layoutParams = params
                search_park_dialog.x = search_park_dialog.x + (search_park_dialog.width * 0.9).toFloat()
                checked_park.visibility = View.GONE
                search_park.visibility = View.GONE
            }
        }, 500)
    }
}