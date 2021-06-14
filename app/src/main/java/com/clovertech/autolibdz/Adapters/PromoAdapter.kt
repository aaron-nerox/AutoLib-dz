package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClasses.Promo
import com.clovertech.autolibdz.R

class PromoAdapter(val context: Context, var data:List<Promo>): RecyclerView.Adapter<MyPHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPHolder {
        return MyPHolder(LayoutInflater.from(context).inflate(R.layout.promo_item, parent, false))
    }

    override fun getItemCount()=data.size




    override fun onBindViewHolder(holder: MyPHolder, position: Int) {
        holder.CodePromoName.text="Flash30"
        holder.reductionRate.text=data[position].pricePoints.toString()
        holder.pricePoints.text=data[position].pricePoints.toString()

        var cv = data[position] //ID OF CARDVIEW
       /* holder.cv.setOnClickListener{
            val paymentId=data[position].paymentId
            val last4=data[position].card.last4
            val args = bundleOf("paymentId" to paymentId,"amount" to "1900","idRental" to "10", "type" to "penaltyRate","last4" to last4)
        }*/

    }

}
class MyPHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cv= view.findViewById<CardView>(R.id.promo_item)
    val CodePromoName= view.findViewById<TextView>(R.id.CodePromoName)
    val reductionRate= view.findViewById<TextView>(R.id.reductionRate)
    val pricePoints= view.findViewById<TextView>(R.id.pricePoints)


}