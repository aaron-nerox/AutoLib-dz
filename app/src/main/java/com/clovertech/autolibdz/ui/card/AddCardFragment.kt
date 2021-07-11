package com.clovertech.autolibdz.ui.card

import ViewModel.ViewModelCard
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.model.Adress
import com.clovertech.autolibdz.model.PaymentMethod
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModelFactoryCard
import com.clovertech.autolibdz.repository.PaymentRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_card.*
import com.clovertech.autolibdz.model.CardRequest

class AddCardFragment : BottomSheetDialogFragment () {
    private lateinit var viewModel : ViewModelCard
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        close.setOnClickListener{
            this.dismiss()
        }
        val repository = PaymentRepository()
        val viewModelFactory = MainViewModelFactoryCard(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)
                .get(ViewModelCard::class.java)

        add_card_conf.setOnClickListener {

            val number= card_number.text.toString()
            val exp_month=mois_exp.text.toString()
            val exp_year= year_exp.text.toString()
            val cvc= sec_code.text.toString()
            val nom=nom.text.toString()
            val prenom=prenom.text.toString()
            val name =nom+" "+prenom
            if (number.isEmpty())
            {
                Toast.makeText(context,"Please enter a card number", Toast.LENGTH_SHORT).show()
            }
            else if (exp_month.isEmpty()){
                Toast.makeText(context,"Please enter an expiry month date", Toast.LENGTH_SHORT).show()

            }
            else if (exp_year.isEmpty()){
                Toast.makeText(context,"Please enter an expiry year date", Toast.LENGTH_SHORT).show()

            }
            else if (cvc.isEmpty()){
                Toast.makeText(context,"Please enter a security number", Toast.LENGTH_SHORT).show()

            }
            else if (nom.isEmpty()){
                Toast.makeText(context,"Please enter a family name", Toast.LENGTH_SHORT).show()

            }
            else if (prenom.isEmpty()){
                Toast.makeText(context,"Please enter an first name", Toast.LENGTH_SHORT).show()

            }


            val paymentMethod = PaymentMethod(CardRequest(number,exp_month,exp_year,cvc), Adress("Algiers","Algeria",
                    "line1","16000"),"Lamia"+" "+"Selmane")


            viewModel.pushCard(paymentMethod)
            viewModel.AddCardResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Log.e("Push", (response.body().toString()))
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message())
                    Log.e("push",response.headers().toString())
                    Log.e("push",response.errorBody().toString())
                    Log.e("push",response.toString())
                    Log.e("push",response.raw().toString())


                    Toast.makeText(
                            context,
                            "Card Added sucessfully",
                            Toast.LENGTH_SHORT
                    ).show()
                    this.dismiss()


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
                    this.dismiss()
                }
            })



        }

    }

}