package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClass.paymentInfo
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.card.ConfirmPayFragment

class CardAdapter(val context: Context, var data:List<paymentInfo>,var fragmentManager: FragmentManager): RecyclerView.Adapter<MyVHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVHolder {
        return MyVHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false))
    }

    override fun getItemCount()=data.size




    override fun onBindViewHolder(holder: MyVHolder, position: Int) {
        holder.nom.text=data[position].paymentId
        holder.rip.text="xxxx "+data[position].card.last4
        holder.date_exp.text=data[position].card.brand
        //  holder.card_img.setImageResource(data[position].card_img)
        var cv = data[position] //ID OF CARDVIEW
        holder.cv.setOnClickListener{
            val paymentId=data[position].paymentId
            val last4=data[position].card.last4
            val args = bundleOf("paymentId" to paymentId,"amount" to "1900","idRental" to "10", "type" to "penaltyRate","last4" to last4)

            val confirmPayFragment = ConfirmPayFragment()
            confirmPayFragment.arguments=args
            confirmPayFragment.show(fragmentManager, "confirm_pay_fragment")


        }

    }

}
class MyVHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cv= view.findViewById<CardView>(R.id.promo_item)
    val nom= view.findViewById<TextView>(R.id.nom)
    val rip= view.findViewById<TextView>(R.id.rib)
    val date_exp= view.findViewById<TextView>(R.id.date_exp)
    val card_img= view.findViewById<ImageView>(R.id.card_img)


}