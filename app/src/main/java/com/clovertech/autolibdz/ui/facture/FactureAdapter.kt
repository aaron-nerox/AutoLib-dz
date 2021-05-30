package com.clovertech.autolibdz.ui.facture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.R

class FactureAdapter (val context: Context, var data:List<Facture>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.facture_item, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.id_facture.text= data[position].id_facture.toString()
        holder.date_facture.text=data[position].date_facture
        holder.lieu_facture.text=data[position].lieu_facture
        holder.card_img.setImageResource(data[position].card_img)
        var cv = data[position] //ID OF CARDVIEW

    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cv= view.findViewById<CardView>(R.id.cardView)
    val id_facture= view.findViewById<TextView>(R.id.id_facture)
    val date_facture= view.findViewById<TextView>(R.id.date)
    val lieu_facture= view.findViewById<TextView>(R.id.lieu)
    val card_img= view.findViewById<ImageView>(R.id.card_img)

}