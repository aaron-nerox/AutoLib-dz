package com.clovertech.autolibdz.Adapters

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClasses.Facture
import com.clovertech.autolibdz.R

class BillAdapter (val context: Context, var data:List<Facture>): RecyclerView.Adapter<MyBillHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBillHolder {
        return MyBillHolder(LayoutInflater.from(context).inflate(R.layout.facture_item, parent, false))
    }
    private  val STORAGE_PERMISSION_CODES:Int=1000
    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyBillHolder, position: Int) {


        holder.id_facture.text= data[position].idBill.toString()
        holder.date_facture.text=data[position].creationDate
        holder.prix.text=data[position].totalRate.toString()
        holder.penality.text=data[position].penaltyRate.toString()
        holder.download.setOnClickListener{

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED)

                { //permssion denied
                    requestPermissions(context as Activity,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODES)

                }else { //permssion already granted perform download
                    startDownlaod()

                }
            }else {

            }
        }

    }
}
    private fun startDownlaod()
    {

    }




class MyBillHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cv= view.findViewById<CardView>(R.id.facture_item)
    val id_facture= view.findViewById<TextView>(R.id.id_facture)
    val date_facture= view.findViewById<TextView>(R.id.date)
    val prix= view.findViewById<TextView>(R.id.prix)
    val penality= view.findViewById<TextView>(R.id.penality)
    val download= view.findViewById<ImageView>(R.id.download)


}