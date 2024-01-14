package com.clovertech.autolibdz.ui.profil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModel
import com.clovertech.autolibdz.ViewModel.MainViewModelFactory
import com.clovertech.autolibdz.utils.Constants
import kotlinx.android.synthetic.main.activity_profile.*
import com.clovertech.autolibdz.repository.Repository

class ProfilFragment :Fragment() {
    private lateinit var profilViewModel: ProfilViewModel
    private lateinit var viewModel : MainViewModel
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(MainViewModel::class.java)

        val prefs = requireActivity().getSharedPreferences(Constants.APP_PREFS, AppCompatActivity.MODE_PRIVATE)
        val idUser=prefs.getInt("idUser",0)
        Log.d("idHere",idUser.toString())
        viewModel.getUser(idUser)
        viewModel.profil.observe(viewLifecycleOwner, Observer {
                response ->
            Log.d("Response", response.firstName)
            fullname.text = response.firstName +" "+ response.lastName
            username.text = response.userName
            phonenumber.text = "0"+response.phoneNumber.toString()
        })
        rapport.setOnClickListener {v->
          v?.findNavController()?.navigate(R.id.nav_to_signal)

        }
        payment_txt.setOnClickListener { v->
            v?.findNavController()?.navigate(R.id.nav_to_payment)

        }
    }
}