package com.clovertech.autolibdz.ui.card

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.model.PaymentInfo

class CardAdapter(val context: Context, var data: List<PaymentInfo>, var fragmentManager: FragmentManager,
                  var amount:String, var idrental:String): RecyclerView.Adapter<MyViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false))
        }

        override fun getItemCount()=data.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val cardItem = data[position]
            holder.nom.text= cardItem.name
            holder.rip.text="xxxx  xxxx  xxxx  ${cardItem.card.last4}"
            holder.date_exp.text= "${cardItem.card.expMonth}/${cardItem.card.expYear}"
          //  holder.card_img.setImageResource(data[position].card_img)
            if(cardItem.card.brand == "visa"){
                holder.card_img.setImageResource(R.drawable.ic_visa_inc__logo)
                holder.layout.background = ContextCompat.getDrawable(context, R.drawable.visa_background)
            }else{
                holder.card_img.setImageResource(R.drawable.ic_mc_symbol)
                holder.layout.background = ContextCompat.getDrawable(context, R.drawable.master_card_background)
            }

            holder.layout.setOnClickListener{
                val paymentId=data[position].paymentId
                val last4=data[position].card.last4
                Toast.makeText(context,"amount to pay adapter $amount", Toast.LENGTH_LONG).show()
                Toast.makeText(context,idrental, Toast.LENGTH_LONG).show()
                val args = bundleOf("paymentId" to paymentId,"amount"
                        to amount,"idRental" to idrental, "type" to "rentalRate","last4" to last4)
                val confirmPayFragment = ConfirmPayFragment()
                confirmPayFragment.arguments=args
                confirmPayFragment.show(fragmentManager, "confirm_pay_fragment")
            }

        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layout= view.findViewById<ConstraintLayout>(R.id.payment_card_layout)
        val nom= view.findViewById<TextView>(R.id.nom)
        val rip= view.findViewById<TextView>(R.id.rib)
        val date_exp= view.findViewById<TextView>(R.id.date_exp)
        val card_img= view.findViewById<ImageView>(R.id.card_img)


    }