package com.clovertech.autolibdz.ui

import ViewModel.MainViewModel
import ViewModel.MainViewModelFactory
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.activity_signal.*
import model.Signal
import repository.Repository

class SignalAct : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signal)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(MainViewModel::class.java)


        val types = arrayOf("theft","panne")
        reporting_type.setOnClickListener {
            val mbuilder =
                AlertDialog.Builder(this@SignalAct)
            mbuilder.setTitle("What's the type of the alert ?")
            mbuilder.setSingleChoiceItems(types, -1,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    reporting_type.text = types[i]
                    dialogInterface.dismiss()
                })
            mbuilder.show()
        }
        report_btn.setOnClickListener {
            if (msg.text.toString().isEmpty()){
                msg.error = "Please set your alert message"
            }else {
                val mbuilder =
                    AlertDialog.Builder(this@SignalAct)
                mbuilder.setTitle("Do you really want to confirm this report ?")
                mbuilder.setPositiveButton(
                    "Yes"
                ) { dialog, which ->

                    val signal = Signal(6,reporting_type.text.toString(),3,"2021-05-18T02:37:03.137Z"
                        ,"Auto",msg.text.toString(),14)

                    viewModel.pushSignal(signal)
                    viewModel.signalResponse.observe(this, Observer {
                            response ->
                        if (response.isSuccessful){
                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())
                            Log.e("Push",response.message())
                        }
                    })
                }
                mbuilder.setNegativeButton(
                    "No"
                ) { dialog, which ->

                    dialog.dismiss()
                }
                mbuilder.show()
            }


        }
    }
}