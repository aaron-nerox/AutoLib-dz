package com.clovertech.autolibdz

import com.clovertech.autolibdz.ViewModel.MainViewModel
import com.clovertech.autolibdz.ViewModel.MainViewModelFactory
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_signal.*
import com.clovertech.autolibdz.model.Signal
import com.clovertech.autolibdz.repository.Repository

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

                    val signal = Signal(0,reporting_type.text.toString(),3,""
                        ,"Tenant",msg.text.toString(),14)

                    viewModel.pushSignal(signal)
                    viewModel.signalResponse.observe(this, Observer {
                            response ->
                        if (response.isSuccessful){
                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())
                            Log.e("Push",response.message())
                            msg.setText("")
                            reporting_type.setText("")
                            Toast.makeText(this,"Alert sent sucessfully", Toast.LENGTH_SHORT).show()


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