package com.clovertech.autolibdz.Adapters

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.DataClasses.Facture
import com.clovertech.autolibdz.R


class BillAdapter (val context: Context, var data:List<Facture>): RecyclerView.Adapter<MyBillHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBillHolder {
        return MyBillHolder(LayoutInflater.from(context).inflate(R.layout.facture_item, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyBillHolder, position: Int) {
        var downId:Long=0


        holder.id_facture.text= data[position].idBill.toString()
        holder.date_facture.text=data[position].creationDate
        holder.prix.text=data[position].totalRate.toString()
        holder.penality.text=data[position].penaltyRate.toString()

        holder.download.setOnClickListener{


            var request = DownloadManager.Request(
                Uri.parse("http://54.37.87.85:5056/bill/download/22"))
                .setTitle("Facture autolib")
                .setDescription("check la facture ")
                .setAllowedOverMetered(true)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);



            var dm= context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                downId = dm.enqueue(request)

        }

        var br = object:BroadcastReceiver(){

            override fun onReceive(context: Context?, intent: Intent?) {

                var id : Long? = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,1)

                if (id==downId)
                {
                    Toast.makeText(context,"download successfully",Toast.LENGTH_SHORT).show()
                }
            }


        }

        context.registerReceiver(br,IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

    }
}






class MyBillHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cv= view.findViewById<CardView>(R.id.facture_item)
    val id_facture= view.findViewById<TextView>(R.id.id_facture)
    val date_facture= view.findViewById<TextView>(R.id.date)
    val prix= view.findViewById<TextView>(R.id.prix)
    val penality= view.findViewById<TextView>(R.id.penality)
    val download= view.findViewById<ImageView>(R.id.download)


}