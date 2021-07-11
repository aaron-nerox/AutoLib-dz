package com.clovertech.autolibdz

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_find_your_car.*
import org.json.JSONObject
import java.io.IOException
import java.net.URISyntaxException
import java.util.*

class FindYourCarActivity : AppCompatActivity() {

    private var nameTablet: String? = null

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

        /*val data: JSONObject = it[0] as JSONObject
        nameTablet = data.getString("nomLocataire")
        */
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
        setContentView(R.layout.activity_find_your_car)

         rippleBackground.startRippleAnimation()

        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton(
                                "OK"
                        ) { _, _ ->
                            //Prompt the user once explanation has been shown
                            requestLocationPermission()
                        }
                        .create()
                        .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission()
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

        try {
            val opts = IO.Options()
            opts.path = "/socket"
            mSocket = IO.socket("http://192.168.137.93:8123", opts)
        } catch(e: URISyntaxException) {
            e.printStackTrace()
        }

        val jsonInfos = JSONObject()
        jsonInfos.put("idVehicule", 4)
        val jsonInfosObj = JSONObject()
        jsonInfosObj.put("id", 5)


        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val name: String = mBluetoothAdapter.name
        jsonInfosObj.put("nom", name)
        jsonInfos.put("locataire", jsonInfosObj)
        mSocket.emit("demande vehicule", jsonInfos)

        // end location { idLoc }


        val acceptThread = AcceptThread(this)
        acceptThread?.start()





        mSocket.on(Socket.EVENT_CONNECT, onConnected)
        mSocket.on("error", onError)
        mSocket.on("connect_error", onError)
        mSocket.on("link started", onLink)
        mSocket.on("disconnect", onDisconnect)
        mSocket.connect()

    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ),
                    100
            )
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    100
            )
        }
    }

    private val onConnected: Emitter.Listener = Emitter.Listener {
        val obj = JSONObject()
        obj.put("id", 3)
        // mSocket.emit("connected vehicule", obj)
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

}


private class AcceptThread(

    val activity: FindYourCarActivity
) : Thread() {

    private var mmServerSocket: BluetoothServerSocket? = null

    override fun run() {
        try {
            mmServerSocket = bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord(
                "PermissionsP2p",
                UUID(100, 200)
            )
        } catch (e: Exception) {
            Log.e("exception", e.localizedMessage)
        }
        // Keep listening until exception occurs or a socket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {

                val bluetoothSocket = mmServerSocket?.accept()
//                activity.runOnUiThread {
//                    Toast.makeText(
//                        activity,
//                        "Change the UI to indicate that the connexion has been done successfully!",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }

                bluetoothSocket
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Socket's accept() method failed", e)
                shouldLoop = false
                null
            }
            socket?.also {
                var input = ByteArray(1)
                it.inputStream.read(input)
                activity.runOnUiThread {
                    val associationStatus =
                        activity.findViewById<TextView>(R.id.association_status_id)
                    associationStatus.text =
                        "Association effectuée avec ${socket.remoteDevice.name}"
                }
                // mmServerSocket?.close()
                // shouldLoop = false
            }
        }
    }
}
