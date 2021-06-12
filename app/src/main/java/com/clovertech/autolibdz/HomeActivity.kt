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

    private var nameTablet: String? = null

    private var mmServerSocket: BluetoothServerSocket? = null

    private lateinit var mSocket: Socket

    private val onError: Emitter.Listener = Emitter.Listener {
        this.runOnUiThread(Runnable {
            try {
                val data: Exception = it[0] as Exception
                data.printStackTrace()
                Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val data: JSONObject = it[0] as JSONObject

            }

        })
    }
    private val onLink: Emitter.Listener = Emitter.Listener {

        val data: JSONObject = it[0] as JSONObject
        nameTablet = data.getString("nomLocataire")
        this.runOnUiThread(Runnable {
            Toast.makeText(this, "Discovering ...", Toast.LENGTH_SHORT).show()
        })
        val discovering = bluetoothAdapter?.startDiscovery()
    }

    private val onDisconnect: Emitter.Listener = Emitter.Listener {
        this.runOnUiThread(Runnable {
            Toast.makeText(this, "Diconnected!", Toast.LENGTH_SHORT).show()
        })
    }

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

        val requestCode = 1
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        startActivityForResult(discoverableIntent, requestCode)


        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 100)
        }

        val acceptThread = AcceptThread(this)
        acceptThread?.start()

        try {
            val opts = IO.Options()
            opts.path = "/socket"


            mSocket = IO.socket("http://192.168.43.103:8123", opts)
            Toast.makeText(this, "Connected successfully here ya khouya", Toast.LENGTH_LONG).show()
        } catch(e: URISyntaxException) {
            e.printStackTrace()
        }

        val testBtn = findViewById<TextView>(R.id.testBtn)
        testBtn.setOnClickListener { v ->
            Toast.makeText(this, "Demande vehicule", Toast.LENGTH_SHORT).show()
            val jsonInfos = JSONObject()
            jsonInfos.put("idVehicule", 2)
            val jsonInfosObj = JSONObject()
            jsonInfosObj.put("id", 1)
            jsonInfosObj.put("nom", "Galaxy J8")
            jsonInfos.put("locataire", jsonInfosObj)
            Toast.makeText(this, "Discover peers button Clicked ...", Toast.LENGTH_SHORT).show()
            mSocket.emit("demande vehicule", jsonInfos)
            Toast.makeText(this, "Demande vehicule executed ya khou", Toast.LENGTH_SHORT).show()
        }

        mSocket.on(Socket.EVENT_CONNECT, onConnected)
        mSocket.on("error", onError)
        mSocket.on("connect_error", onError)
        mSocket.on("start link", onLink)
        mSocket.on("disconnect", onDisconnect)
        mSocket.connect()
    }

    private val onConnected: Emitter.Listener = Emitter.Listener {
        val obj = JSONObject()
        obj.put("id", 1)
        mSocket.emit("connected vehicule", obj)
    }

    public fun closeConnection(mmServerSocket: BluetoothServerSocket?) {
        mmServerSocket?.close()
    }



    override fun onDestroy() {
        super.onDestroy()

        // Don't forget to unregister the ACTION_FOUND receiver.
        // unregisterReceiver(receiver)
        mSocket.disconnect()
        mSocket.off("error", onError)
        mSocket.off("connect_error", onError)
        mSocket.off("start link", onLink)


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
}

private class AcceptThread(
        val activity: HomeActivity
) : Thread() {

    private var mmServerSocket: BluetoothServerSocket? = null

    override fun run() {
        activity.runOnUiThread { Toast.makeText(activity, "Entered here .....", Toast.LENGTH_LONG).show() }
        mmServerSocket = bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord("PermissionsP2p", UUID(100, 200))
        // Keep listening until exception occurs or a socket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {
                mmServerSocket?.accept()
            } catch (e: IOException) {
                Log.e(TAG, "Socket's accept() method failed", e)
                shouldLoop = false
                null
            }
            socket?.also {
                var input = ByteArray(1)
                it.inputStream.read(input)
                activity.runOnUiThread { Toast.makeText(activity, "Accept Thread ${input[0]}", Toast.LENGTH_LONG).show() }
                // mmServerSocket?.close()
                // shouldLoop = false
            }
        }
    }
}