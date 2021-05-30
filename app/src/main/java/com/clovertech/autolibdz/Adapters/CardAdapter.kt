package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.card.Card
import com.clovertech.autolibdz.ui.card.MyViewHolder

class CardAdapter(val context: Context, var data:List<Card>): RecyclerView.Adapter<MyVHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVHolder {
        return MyVHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false))
    }

    override fun getItemCount()=data.size




    override fun onBindViewHolder(holder: MyVHolder, position: Int) {
        holder.nom.text=data[position].paymentId
        holder.rip.text="xxx xxxx"+data[position].last4.toString()
        holder.date_exp.text=data[position].brand
        //  holder.card_img.setImageResource(data[position].card_img)
        var cv = data[position] //ID OF CARDVIEW

    }

}
class MyVHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cv= view.findViewById<CardView>(R.id.cardView)
    val nom= view.findViewById<TextView>(R.id.nom)
    val rip= view.findViewById<TextView>(R.id.rib)
    val date_exp= view.findViewById<TextView>(R.id.date_exp)
    val card_img= view.findViewById<ImageView>(R.id.card_img)


}