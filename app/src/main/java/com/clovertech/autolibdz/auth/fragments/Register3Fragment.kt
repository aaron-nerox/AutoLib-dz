package com.clovertech.autolibdz.auth.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.date_picker_spinner_mode.*
import kotlinx.android.synthetic.main.fragment_register3.*
import kotlinx.android.synthetic.main.fragment_register3.view.*

class Register3Fragment : Fragment() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register3, container, false)

        view.date_txt_view.setOnClickListener(this)

        return view
    }

    override fun onClick(view : View?) {
        when (view?.id){
            R.id.date_txt_view -> {
                var date = ""
                val dialog = activity?.let { Dialog(it) }
                dialog?.setContentView(R.layout.date_picker_spinner_mode)
                dialog?.date_picker?.init(2021,0,1,
                        DatePicker.OnDateChangedListener { datePicker, year, month, day->
                            date = "$day/"+(month+1).toString()+"/$year"
                        })

                dialog?.ok?.setOnClickListener {
                    view.date_txt_view.text = date
                    dialog.dismiss()
                }

                dialog?.annuler?.setOnClickListener {
                    dialog.dismiss()
                }
                dialog?.show()

            }
        }
    }

}