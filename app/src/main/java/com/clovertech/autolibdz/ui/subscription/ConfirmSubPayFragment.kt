package com.clovertech.autolibdz.ui.subscription

import ViewModel.ViewModelCard
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.clovertech.autolibdz.FindYourCarActivity
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModelFactoryCard
import com.clovertech.autolibdz.model.paySubRequest
import com.clovertech.autolibdz.repository.PaymentRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_confirm_sub_pay.*
import kotlinx.android.synthetic.main.fragment_subscription.*


class ConfirmSubPayFragment : BottomSheetDialogFragment()  {

    private lateinit var viewModel : ViewModelCard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_sub_pay, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close.setOnClickListener {
            this.dismiss()
        }
        val amount = arguments?.getInt("amount")
        val idSub = arguments?.getInt("idSub")

        Toast.makeText(
            context,
            "ampunt $amount, id sub $idSub",
            Toast.LENGTH_SHORT
        ).show()
        val repository = PaymentRepository()
        val viewModelFactory = MainViewModelFactoryCard(repository)

        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(ViewModelCard::class.java)
        confirm.setOnClickListener {
            if (amount!=null && idSub!=null)
            {
                val paySubRequest= paySubRequest(idSub,amount)

                viewModel.subPay(paySubRequest)

                viewModel.PaySubResponse.observe(viewLifecycleOwner, Observer { response ->
                    if (response.isSuccessful) {
                        Log.e("Push", (response.body()?.msg.toString()))
                        Log.e("Push", response.code().toString())
                        Log.e("Push", response.message())
                        Log.e("push",response.headers().toString())
                        Log.e("push",response.errorBody().toString())
                        Log.e("push",response.toString())
                        Log.e("push",response.raw().toString())

                        val builder = AlertDialog.Builder(context)

                        builder.setTitle("Success !")
                        builder.setMessage("Votre paiement avec carte d'abonnement est effectué avec success !")
                        builder.setIconAttribute(R.drawable.ic_baseline_warning_24)

                        builder.setPositiveButton("Ok"){dialogInterface, which ->
                        }
                        builder.setNeutralButton("Cancel"){dialogInterface , which ->
                        }
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                        requireActivity().solde.setText(response.body()?.balance.toString() + "DA")
                        this.dismiss()
                        startActivity(
                            Intent(requireContext(),
                                FindYourCarActivity::class.java)
                        )
                    }
                    else {
                        Log.e("Push", response.body()?.msg.toString())
                        Log.e("Push", response.code().toString())
                        Log.e("Push", response.message().toString())
                        Log.e("push",response.headers().toString())
                        Log.e("push",response.errorBody().toString())
                        Log.e("push",response.toString())
                        Log.e("push",response.raw().toString())

                        Toast.makeText(
                            context,
                            "fail sub payment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

        }}



}