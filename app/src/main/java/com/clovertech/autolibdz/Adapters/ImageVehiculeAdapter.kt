package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.model.Vehicle
import com.clovertech.autolibdz.R


class ImageVehiculeAdapter(val context: Context): RecyclerView.Adapter<MyImageHolder>(){

    lateinit var data: List<Vehicle>

    fun setCarList(cars:List<Vehicle>){
        this.data=cars.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageHolder {
        return MyImageHolder(LayoutInflater.from(context).inflate(R.layout.car_image, parent, false))
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: MyImageHolder, position: Int) {
        Glide.with(context).load(data[position].image).into(holder.img)
    }



}


class MyImageHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img = view.findViewById<ImageView>(R.id.img)
}