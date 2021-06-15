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
import kotlin.math.roundToInt

class PromoAdapter(val context: Context, var data:List<Promo>): RecyclerView.Adapter<MyPHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPHolder {
        return MyPHolder(LayoutInflater.from(context).inflate(R.layout.promo_item, parent, false))
    }

    override fun getItemCount()=data.size




    override fun onBindViewHolder(holder: MyPHolder, position: Int) {
     //   holder.CodePromoName.text="Flash32"
        val red = ((data[position].reductionRate)*100).roundToInt()
        val point = data[position].pricePoints.roundToInt()
        holder.reductionRate.text=red.toString()+"%"
        holder.pricePoints.text=point.toString()

        var cv = data[position] //ID OF CARDVIEW


    }

}
class MyPHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cv= view.findViewById<CardView>(R.id.promo_item)
    //val CodePromoName= view.findViewById<TextView>(R.id.CodePromoName)
    val reductionRate= view.findViewById<TextView>(R.id.reductionRate)
    val pricePoints= view.findViewById<TextView>(R.id.pricePoints)


}