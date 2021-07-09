package com.clovertech.autolibdz

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bottom_bar_layout.*
import java.util.ArrayList

class CarsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val layouts : ArrayList<LinearLayout> = ArrayList()
    private val images : ArrayList<ImageView> = ArrayList()
    private val fragments : ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_facture, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
       // init()
      /*  supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragments[0]).commit()

        for (i in layouts.indices) {
            layouts[i].setOnClickListener {
                editTint(i)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, fragments[i])
                        .commit()
            }
        }*/


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

   /* private fun editTint(pos: Int) {
        images.get(pos).setColorFilter(
                ContextCompat.getColor(this@CarsActivity, R.color.yello),
                PorterDuff.Mode.SRC_IN
        )
        for (i in images.indices) {
            if (i != pos) {
                images.get(i).setColorFilter(
                        ContextCompat.getColor(this@CarsActivity, R.color.dark_grey),
                        PorterDuff.Mode.SRC_IN
                )
            }
        }
    }*/
    }