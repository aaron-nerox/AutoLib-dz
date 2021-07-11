package com.clovertech. autolibdz.ui.subscription

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.model.SubStateResponse
import com.clovertech.autolibdz.model.SubscriptionResponse
import com.clovertech.autolibdz.ui.subscription.AddSubFragment
import com.clovertech.autolibdz.ui.subscription.ConfirmSubPayFragment
import com.clovertech.autolibdz.utils.Constants
import com.clovertech.autolibdz.utils.RetrofitInstance
import kotlinx.android.synthetic.main.fragment_subscription.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscriptionFragment  : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_subscription, container, false)

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val amount= arguments?.getInt("amount")
        val prefs = requireActivity().getSharedPreferences(
            Constants.APP_PREFS, AppCompatActivity.MODE_PRIVATE)
        val idUser=prefs.getInt("idUser",0)
        Log.d("idHere",idUser.toString())

        val call = idUser?.let { RetrofitInstance.subApi.getSubByTenant(it) }
        if (call != null) {
            call.enqueue(object:Callback<SubscriptionResponse>{
                override fun onFailure(call: Call<SubscriptionResponse>, t: Throwable) {
                    Toast.makeText(context,"CAN NOT GET SUB", Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(call: Call<SubscriptionResponse>, response: Response<SubscriptionResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context,"SUB SUCCESS", Toast.LENGTH_SHORT).show()
                        date_creation.setText(response.body()?.creationDate?.subSequence(0,10).toString())
                        date_expiration.setText(response.body()?.expirationDate?.subSequence(0,10).toString())
                        val subtype= response.body()?.subType
                        if(subtype==1){
                            type.setText("Mensuel")

                        }
                        if(subtype==2){
                            type.setText("Par 6 mois")

                        }
                        if(subtype==3){
                            type.setText("Annuel")

                        }
                        solde.setText(response.body()?.solde.toString()+" DA")
                        val idSub= response.body()?.idSub
                        Log.d("idSub",idSub.toString())
                        val expiry= response.body()?.expirationDate

                        sub_card.setOnClickListener {

                            val sub = RetrofitInstance.subApi.getSubStateByTenant(idUser)
                            sub.enqueue(object:Callback<SubStateResponse>{
                                override fun onFailure(
                                    call: Call<SubStateResponse>,
                                    t: Throwable
                                ) {
                                    Log.d("push date ex",t.toString())
                                }

                                @SuppressLint("ResourceType")
                                override fun onResponse(
                                    call: Call<SubStateResponse>,
                                    response: Response<SubStateResponse>
                                ) {
                                    if(response.isSuccessful)
                                    {
                                        Log.d("push date",response.raw().toString())
                                        val msg = response.body()?.msg
                                        if(msg=="expired")
                                        {
                                            Log.d("carte expiré",response.raw().toString())
                                            val builder = AlertDialog.Builder(context)

                                            builder.setTitle("Attention!")
                                            builder.setMessage("Votre carte d'abonnement est expirée !")
                                            builder.setIconAttribute(R.drawable.ic_baseline_warning_24)

                                            builder.setPositiveButton("Ok"){dialogInterface, which ->
                                            }
                                            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                                            }
                                            val alertDialog: AlertDialog = builder.create()
                                            alertDialog.setCancelable(false)
                                            alertDialog.show()
                                        }

                                    }


                                }


                            })

                            Log.d("idSub",idSub.toString())
                            val confirmSubPayFragment = ConfirmSubPayFragment()
                            val args= bundleOf("amount" to amount,"idSub" to idSub)
                            confirmSubPayFragment.arguments=args
                            fragmentManager?.let { it1 -> confirmSubPayFragment.show(it1, "confirm_pay_fragment") }
                        }
                    }
                }

            })
        }

        val add_sub_fragment= AddSubFragment()
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager


        create.setOnClickListener {
            add_sub_fragment.show(fragmentManager, "add_sub_fragment") //show add card fragment
        }


    }


}




