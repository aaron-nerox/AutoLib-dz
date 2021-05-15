package com.clovertech.autolibdz.password.fragments

import android.app.Service
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_reset_password2.view.*


class ResetPassword2Fragment : Fragment() {

    var activeEditText = 1
    var editTextes : ArrayList<EditText> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reset_password2, container, false)

        editTextes.add(view.number1)
        editTextes.add(view.number2)
        editTextes.add(view.number3)
        editTextes.add(view.number4)
        editTextes.add(view.number5)

        view.number1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (view.number1.text != null) {
                    view.number1.isEnabled = false
                    view.number2.isEnabled = true
                    view.number2.isFocusable = true
                    view.number2.requestFocus()
                    val imm: InputMethodManager =
                        activity?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(view.number2, 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        view.number2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (view.number2.text != null) {
                    view.number2.isEnabled = false
                    view.number3.isEnabled = true
                    view.number3.isFocusable = true
                    view.number3.requestFocus()
                    val imm: InputMethodManager =
                        activity?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(view.number3, 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        view.number3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (view.number3.text != null) {
                    view.number3.isEnabled = false
                    view.number4.isEnabled = true
                    view.number4.isFocusable = true
                    view.number4.requestFocus()
                    val imm: InputMethodManager =
                        activity?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(view.number4, 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        view.number4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (view.number4.text != null) {
                    view.number4.isEnabled = false
                    view.number5.isEnabled = true
                    view.number5.isFocusable = true
                    view.number5.requestFocus()
                    val imm: InputMethodManager =
                        activity?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(view.number5, 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        view.number5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })


        return view
    }



    private fun editEditTextes(){

    }

}