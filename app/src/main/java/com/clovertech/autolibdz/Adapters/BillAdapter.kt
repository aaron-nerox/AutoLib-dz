package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClasses.Facture
import com.clovertech.autolibdz.R

class BillAdapter (val context: Context, var data:List<Facture>): RecyclerView.Adapter<MyBillHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBillHolder {
        return MyBillHolder(LayoutInflater.from(context).inflate(R.layout.facture_item, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyBillHolder, position: Int) {


        holder.id_facture.text= data[position].idBill.toString()
        holder.date_facture.text=data[position].creationDate
        holder.lieu_facture.text=data[position].address
        holder.card_img.setImageResource(R.drawable.visa_logo)
        var cv = data[position] //ID OF CARDVIEW

    }
}

class MyBillHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cv= view.findViewById<CardView>(R.id.cardView)
    val id_facture= view.findViewById<TextView>(R.id.id_facture)
    val date_facture= view.findViewById<TextView>(R.id.date)
    val lieu_facture= view.findViewById<TextView>(R.id.lieu)
    val card_img= view.findViewById<ImageView>(R.id.card_img)

}