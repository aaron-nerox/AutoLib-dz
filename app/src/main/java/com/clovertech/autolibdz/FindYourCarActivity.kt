package com.clovertech.autolibdz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_find_your_car.*
import model.AssociationData
import org.json.JSONObject
import java.lang.Exception
import java.net.URISyntaxException

class FindYourCarActivity : AppCompatActivity() {

    private lateinit var mSocket: Socket
    private val onStartLink: Emitter.Listener = Emitter.Listener {
        this@FindYourCarActivity.runOnUiThread(Runnable {
            //val data: JSONObject = it[0] as JSONObject
            Toast.makeText(this,"Linking ...", Toast.LENGTH_SHORT).show()
        })
    }
    private val onLinkStarted: Emitter.Listener = Emitter.Listener {
        this@FindYourCarActivity.runOnUiThread(Runnable {
            //val data: JSONObject = it[0] as JSONObject
            Toast.makeText(this,"Vehicule found & Connection Established", Toast.LENGTH_SHORT).show()
        })
    }
    private val onLinkFailed: Emitter.Listener = Emitter.Listener {
        this@FindYourCarActivity.runOnUiThread(Runnable {
            //val data: JSONObject = it[0] as JSONObject
            Toast.makeText(this,"Link Failed !", Toast.LENGTH_SHORT).show()
        })
    }
    private val onError: Emitter.Listener = Emitter.Listener {
        this@FindYourCarActivity.runOnUiThread(Runnable {
            try {
                val data: Exception = it[0] as Exception
                Toast.makeText(this@FindYourCarActivity, data.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val data: JSONObject = it[0] as JSONObject

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_your_car)

        rippleBackground.startRippleAnimation()

        try {
            val opts = IO.Options()
            opts.port = 8000
            opts.path = "/socket"
            mSocket = IO.socket("http://192.168.43.222:8123", opts)
        } catch(e: URISyntaxException) {
            Log.e("212121",e.message.toString())
        }

        mSocket.on("start link", onStartLink)
        mSocket.on("link started", onLinkStarted)
        mSocket.on("link failed", onLinkFailed)
        mSocket.on("error", onError)
        mSocket.on("connect_error", onError)
        mSocket.connect()

        val obj = JSONObject()
        obj.put("idLocataire", 1)
        obj.put("idVehicule", 3)
        mSocket.emit("demande vehicule", obj)

    }

    override fun onDestroy() {
        super.onDestroy()

        mSocket.disconnect()
        mSocket.off("start link", onStartLink)
        mSocket.off("link started", onLinkStarted)
        mSocket.off("link failed", onLinkFailed)
        mSocket.off("error", onError)
        mSocket.off("connect_error", onError)
    }

}