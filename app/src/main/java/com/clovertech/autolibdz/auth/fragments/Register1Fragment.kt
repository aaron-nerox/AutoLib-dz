package com.clovertech.autolibdz.auth.fragments

import com.clovertech.autolibdz.ViewModel.RegisterViewModel
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_register1.view.*

class Register1Fragment : Fragment() {

    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, {
            view.email.append(it.userName)
            view.nom.append(it.lastName)
            view.prenom.append(it.firstName)
            view.adresse.append(it.address)
        })

        view.nom.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val nom = view.nom.text
                if (nom.isEmpty()) {
                    view.nom.error = "Fill in your name"
                } else {
                    viewModel.setNom(nom.toString())
                }
            }
        }

        view.email.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val email = view.email.text
                if (!Patterns.EMAIL_ADDRESS.matcher(view.email.text).matches()) {
                    view.email.error = "Invalid email address"
                } else {
                    viewModel.setEmail(email.toString())
                }
            }
        }
        view.prenom.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val prenom = view.prenom.text
                if (prenom.isEmpty()) {
                    view.prenom.error = "Fill in your first name"
                } else {
                    viewModel.setPrenom(prenom.toString())
                }
            }
        }

        view.adresse.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val adresse = view.adresse.text
                if (adresse.isEmpty()) {
                    view.adresse.error = "Fill in your address"
                } else {
                    viewModel.setaddress(adresse.toString())
                }
            }
        }
    }

}