package com.clovertech.autolibdz.ui.card

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClass.paymentInfo
import com.clovertech.autolibdz.R

class CardAdapter(val context: Context, var data: List<paymentInfo>,var fragmentManager: FragmentManager,
                  var amount:String,var idrental:String): RecyclerView.Adapter<MyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false))
        }

        override fun getItemCount()=data.size


        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


            holder.nom.text=data[position].paymentId
            holder.rip.text="xxx xxxx"+data[position].card.last4
            holder.date_exp.text=data[position].card.brand
          //  holder.card_img.setImageResource(data[position].card_img)
            var cv = data[position] //ID OF CARDVIEW
            holder.cv.setOnClickListener{

                val paymentId=data[position].paymentId
                val last4=data[position].card.last4
                Toast.makeText(context,amount, Toast.LENGTH_LONG).show()
                Toast.makeText(context,idrental, Toast.LENGTH_LONG).show()
                val args = bundleOf("paymentId" to paymentId,"amount"
                        to amount,"idRental" to idrental, "type" to "penaltyRate","last4" to last4)

                val confirmPayFragment = ConfirmPayFragment()
                confirmPayFragment.arguments=args
                confirmPayFragment.show(fragmentManager, "confirm_pay_fragment")


            }



        }


    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cv= view.findViewById<CardView>(R.id.promo_item)
        val nom= view.findViewById<TextView>(R.id.nom)
        val rip= view.findViewById<TextView>(R.id.rib)
        val date_exp= view.findViewById<TextView>(R.id.date_exp)
        val card_img= view.findViewById<ImageView>(R.id.card_img)


    }