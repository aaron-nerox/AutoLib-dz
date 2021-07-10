package com.clovertech.autolibdz.Adapters

import android.content.Context
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolibdz.HomeFragment
import model.paymentInfo
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.card.ConfirmPayFragment
import model.Borne

class BorneAdapter(val context: Context, var fragmentManager: HomeFragment): RecyclerView.Adapter<MyBorneHolder>() {
    lateinit var data: List<Borne>
    val selectedBorne = MutableLiveData<Borne>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBorneHolder {
        return MyBorneHolder(LayoutInflater.from(context).inflate(R.layout.row_park, parent, false))
    }

    override fun getItemCount()=data.size

    fun setBornes(bornes: List<Borne>) {
        data = bornes

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyBorneHolder, position: Int) {
        holder.name.text=data[position].city

        holder.view.setOnClickListener {
            selectedBorne.value = data[position]
        }
    }

}
class MyBorneHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val name= view.findViewById<TextView>(R.id.name)

}