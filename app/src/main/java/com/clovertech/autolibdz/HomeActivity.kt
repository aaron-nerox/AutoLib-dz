package com.clovertech.autolibdz

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.clovertech.autolibdz.auth.fragments.Register1Fragment
import com.clovertech.autolibdz.auth.fragments.Register2Fragment
import com.clovertech.autolibdz.auth.fragments.Register3Fragment
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.bottom_bar_layout.*
import kotlinx.android.synthetic.main.bottom_sheet_presistant_park.*
import org.json.JSONObject
import java.io.IOException
import java.net.URISyntaxException
import java.util.*

val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

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

    public fun closeConnection(mmServerSocket: BluetoothServerSocket?) {
        mmServerSocket?.close()
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

