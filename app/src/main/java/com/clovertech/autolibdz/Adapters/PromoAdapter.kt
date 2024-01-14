package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView

import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.model.Promo
import com.clovertech.autolibdz.model.ReduPriceResponse

import com.clovertech.autolibdz.ui.promo.*
import com.clovertech.autolibdz.utils.Constants

import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class PromoAdapter(val context: Context, var data:List<Promo>, var priceReduHelper:EditText, val totalprice:Int): RecyclerView.Adapter<MyPHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPHolder {
        return MyPHolder(LayoutInflater.from(context).inflate(R.layout.promo_item, parent, false))
    }

    override fun getItemCount()=data.size




    override fun onBindViewHolder(holder: MyPHolder, position: Int) {
        //   holder.CodePromoName.text="Flash32"
        val promoFragment = PromoFragment()
        val red = ((data[position].reductionRate)*100).roundToInt()
        val point = data[position].pricePoints.roundToInt()
        val prefs = context.getSharedPreferences(Constants.APP_PREFS, AppCompatActivity.MODE_PRIVATE)
        val idUser=prefs.getInt("idUser",0)
        Log.d("idUSER",idUser.toString())
        Log.d("idUSER",idUser.toString())
        holder.reductionRate.text=red.toString()+"%"
        holder.pricePoints.text=point.toString()+"points"

        holder.cv.setOnClickListener {
            val id=data[position].idPromoCode
            idCodePromo=id
            //  Toast.makeText(context,"total for reduction : $totalprice",Toast.LENGTH_SHORT).show()

            val call =RetrofitInstance.retrofitReduPrice.getReduPriceByidPromo(totalprice,id,idUser)
            call.enqueue(object:Callback<ReduPriceResponse> {
                override fun onFailure(call: Call<ReduPriceResponse>, t: Throwable) {
                    Log.d("fail", t.toString())
                    Toast.makeText(context,"ECHEC D'APPLICATION DU CODE PROMO",Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(
                    call: Call<ReduPriceResponse>,
                    response: Response<ReduPriceResponse>
                ) {
                    if(response.isSuccessful)
                    {

                        var price = response.body()?.price
                        var currentPoints=response.body()?.currentPoints
                        if (currentPoints != null) {
                            mesPointsHelper=currentPoints
                            pointListner=true
                        }
                        priceReduHelper.setText(price.toString()+"  DA")
                        if (price != null) {
                            pricetotarif=price
                        }
                        Log.d("succcess push",response.body().toString())
                        Log.d("succcess push",response.code().toString())

                    }

                }

            })



        }



    }

}
class MyPHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cv= view.findViewById<CardView>(R.id.promo_item)
    //val CodePromoName= view.findViewById<TextView>(R.id.CodePromoName)
    val reductionRate= view.findViewById<TextView>(R.id.reductionRate)
    val pricePoints= view.findViewById<TextView>(R.id.pricePoints)


}