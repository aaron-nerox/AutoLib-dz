package com.clovertech.autolibdz.ui.subscription
import ViewModel.ViewModelCard
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.model.SubscriptionRequest
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.MainViewModelFactoryCard
import com.clovertech.autolibdz.repository.PaymentRepository
import com.clovertech.autolibdz.ui.promo.idTenantHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_sub.*
class AddSubFragment :  BottomSheetDialogFragment() {
    private lateinit var viewModel : ViewModelCard

    val dropdownlist= arrayOf("Select an option","Mensuel","Par 6 mois","Annuel")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_sub,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close2.setOnClickListener{
            this.dismiss()
        }
        val adapter =
                context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,dropdownlist) }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context,"Please select an option",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                var idSpinner=spinner.selectedItemPosition
            }
        }
        create_card.setOnClickListener {

            val repository = PaymentRepository()
            val viewModelFactory = MainViewModelFactoryCard(repository)
            viewModel = ViewModelProvider(this,viewModelFactory)
                    .get(ViewModelCard::class.java)

            var idSpinner=spinner.selectedItemPosition
            Toast.makeText(context,"you entered $idSpinner",Toast.LENGTH_SHORT).show()

            val subscriptionRequest= SubscriptionRequest(idTenantHelper,idSpinner)
            viewModel.addSub(subscriptionRequest)
            viewModel.SubResponse.observe(viewLifecycleOwner, Observer { response ->
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
                }
                else {
                    Log.e("Push", response.body().toString())
                    Log.e("Push", response.code().toString())
                    Log.e("Push", response.message().toString())
                    Log.e("push",response.headers().toString())
                    Log.e("push",response.errorBody().toString())
                    Log.e("push",response.toString())
                    Log.e("push",response.raw().toString())

                    Toast.makeText(
                            context,
                            "echec",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
    }


}