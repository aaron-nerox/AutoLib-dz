package com.clovertech.autolibdz.Adapters

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.api.FactureApi
import com.clovertech.autolibdz.model.Facture
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.repository.FactureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BillAdapter (val context: Context, var data:List<Facture>): RecyclerView.Adapter<MyBillHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBillHolder {
        return MyBillHolder(LayoutInflater.from(context).inflate(R.layout.facture_item, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyBillHolder, position: Int) {
        var downId:Long=0


        holder.id_facture.text= data[position].idBill.toString()
        val factID=data[position].idBill
        holder.date_facture.text=data[position].creationDate.subSequence(0,10)
        holder.prix.text=data[position].totalRate.toString()
        val penality=data[position].penaltyRate
        if (penality>0)
        {
            holder.penality.text="With Penality"

        }else {
            holder.penality.text="Without Penality"

        }
        holder.download.setOnClickListener{

            val factureApi =  FactureApi()
            val repository= FactureRepository(factureApi)


            CoroutineScope(Dispatchers.Main).launch{
                val response=repository.geBillByID(factID)
                if(response.ok==true){
                    Toast.makeText(context,response.urlBill,Toast.LENGTH_SHORT).show()



                    var request = DownloadManager.Request(
                            Uri.parse("${response.urlBill}"))
                            .setTitle("Facture AutoLibDZ")
                            .setDescription("check your Bill ")
                            .setAllowedOverMetered(true)
                            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Test.pdf")
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    var dm= context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                    downId = dm.enqueue(request)


                    var br = object:BroadcastReceiver(){

                        override fun onReceive(context: Context?, intent: Intent?) {

                            var id : Long? = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,1)

                            if (id==downId)
                            {

                                Toast.makeText(context,"Bill Downloaded successfully",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    context.registerReceiver(br,IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

                }else {
                    Toast.makeText(
                            context,
                            "Error Occurred: ${response.ok}",
                            Toast.LENGTH_LONG
                    ).show()
                }


                }

            }





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