package com.clovertech.autolibdz.ui.signals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R


class SignalFragment:Fragment() {

    private lateinit var signalViewModel: SignalViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        signalViewModel=
                ViewModelProvider(this).get(SignalViewModel::class.java)

        val root = inflater.inflate(R.layout.activity_signal, container, false)
        return root
    }
}