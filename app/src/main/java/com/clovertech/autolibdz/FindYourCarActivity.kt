package com.clovertech.autolibdz

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_find_your_car.*
import model.AssociationData
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
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
        setContentView(R.layout.activity_find_your_car)


        // rippleBackground.startRippleAnimation()

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
        activity.runOnUiThread { Toast.makeText(activity, "Entered here .....", Toast.LENGTH_LONG).show() }
        mmServerSocket = bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord("PermissionsP2p", UUID(100, 200))
        // Keep listening until exception occurs or a socket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {
                val bluetoothSocket = mmServerSocket?.accept()
                activity.runOnUiThread { Toast.makeText(activity, "Change the UI to indicate that the connexion has been done successfully!", Toast.LENGTH_LONG).show() }
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
                    Toast.makeText(activity, "Accept Thread ${input[0]}", Toast.LENGTH_LONG).show()
                    val associationStatus = activity.findViewById<TextView>(R.id.association_status_id)
                    associationStatus.text = "Association effectuée avec ${socket.remoteDevice.name}"
                }
                // mmServerSocket?.close()
                // shouldLoop = false
            }
        }
    }
}