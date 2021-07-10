package com.clovertech.autolibdz.ui.card

import ViewModel.ViewModelCard
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.DataClass.Pay
import com.clovertech.autolibdz.FindYourCarActivity
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModelFactoryCard
import com.clovertech.autolibdz.ViewModel.RentalViewModel
import com.clovertech.autolibdz.ViewModel.RentalViewModelFactory
import com.clovertech.autolibdz.repository.PaymentRepository
import com.clovertech.autolibdz.repository.RentalRepository
import com.clovertech.autolibdz.ui.promo.idCodePromo
import com.clovertech.autolibdz.ui.promo.idcarHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_card.close
import kotlinx.android.synthetic.main.fragment_confirm_pay.*

class ConfirmPayFragment : BottomSheetDialogFragment() {


    private lateinit var viewModel : ViewModelCard

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_pay,container,false)

    }

    @SuppressLint("ResourceType", "UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
       /* // START ALERT DIALOG
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(R.string.SucdialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.SucdialogMessage)
        builder.setIconAttribute(R.drawable.ic_baseline_done_outline_24)

        //performing positive action
        builder.setPositiveButton("OK"){dialogInterface, which ->
           // Toast.makeText(requireActivity(),"clicked yes",Toast.LENGTH_LONG).show()

            startActivity(Intent(requireContext(),
                FindYourCarActivity::class.java))
        }*/

        // END ALERT DIALOG

     //   super.onViewCreated(view, savedInstanceState)

        close.setOnClickListener{
            this.dismiss()
        }
        cancel.setOnClickListener{
            this.dismiss()
        }
        last4.setText("xxx xxxx "+arguments?.getString("last4").toString())
        val repository = PaymentRepository()
        val viewModelFactory = MainViewModelFactoryCard(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
                .get(ViewModelCard::class.java)
        confirm.setOnClickListener{
            val paymentId=arguments?.getString("paymentId").toString()
            val amount= arguments?.getString("amount").toString()
            val idRental= arguments?.getString("idRental").toString()
            val type=arguments?.getString("type").toString()

            Toast.makeText(context,amount,Toast.LENGTH_LONG).show()
            val pay = Pay(paymentId,amount,idRental,type,idCodePromo)

            viewModel.pay(pay)
            viewModel.PayResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {

                    validateRental(idcarHelper)
                    Log.e("Push", (response.body().toString()))
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                    Log.e("push",response.headers().toString())
                    Log.e("push",response.errorBody().toString())
                    Log.e("push",response.toString())
                    Log.e("push",response.raw().toString())
                    this.dismiss()
                 /*   val builder = AlertDialog.Builder(activity!!)
                    //set title for alert dialog
                    builder.setTitle(R.string.SucdialogTitle)
                    //set message for alert dialog
                    builder.setMessage(R.string.SucdialogMessage)
                    builder.setIconAttribute(R.drawable.ic_baseline_done_outline_24)

                    //performing positive action
                    builder.setPositiveButton("OK"){dialogInterface, which ->
                        // Toast.makeText(requireActivity(),"clicked yes",Toast.LENGTH_LONG).show()
*/
                    /*}

                    // END ALERT DIALOG
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()*/


                }
                else {
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message().toString())
                    Toast.makeText(
                            context,
                            "echec",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }


    }

    fun validateRental(id:Int){
        val rentalRepository=RentalRepository()
        val rentalViewModel:RentalViewModel
        val preferences: SharedPreferences =  requireActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        val idcar=preferences.getInt("idcar",0)
        val factory=RentalViewModelFactory(rentalRepository)
        Log.d("idcarHelperPay",idcar.toString())
        rentalViewModel= ViewModelProvider(this,factory)
            .get(RentalViewModel::class.java)
        rentalViewModel.endRental(idcar)

        rentalViewModel.msg.observe(viewLifecycleOwner, Observer { response ->

            if(response.isSuccessful){
                Log.d("push","yes")
                Log.d("push",response.body().toString())
                Log.d("push",response.code().toString())
                Toast.makeText(requireActivity(),"Location validé",Toast.LENGTH_LONG).show()

                startActivity(Intent(requireContext(),
                    FindYourCarActivity::class.java))
            }else{
                Toast.makeText(requireActivity(),"error pas location",Toast.LENGTH_LONG).show()
                Log.d("errorPay",response.body().toString())
                Log.d("errorPay",response.code().toString())
            }

        })
    }

}