package com.clovertech.autolibdz.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.promo.PromoViewModel

class ProfilFragment :Fragment() {
    private lateinit var profilViewModel: ProfilViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profilViewModel=
                ViewModelProvider(this).get(ProfilViewModel::class.java)

        val root = inflater.inflate(R.layout.activity_profile, container, false)
        return root
    }
}