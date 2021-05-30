package com.clovertech.autolibdz.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ui.listcars.ListCarsViewModel
import kotlinx.android.synthetic.main.fragment_car_details.*

class CarDetailsFragment : Fragment() {


    private lateinit var carDetailViewModel: CarDetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        carDetailViewModel =
                ViewModelProvider(this).get(CarDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car_details, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id=arguments?.getString("id")
        val model=arguments?.getString("model")
        car.text=model
        val img=arguments?.getString("img")
        Glide.with(context).load(img).into(img_car)
        val uni_hr=arguments?.getString("hr")
        val uni_jr=arguments?.getString("jr")
        val brand=arguments?.getString("brand")
        price_hr.text= arguments?.getInt("hr",0).toString()+"DA/Hr"
        price_jr.text=arguments?.getInt("jr",0).toString()+"DA/jr"

        button.setOnClickListener {view->
            val bundle = bundleOf("id" to id,"img" to img,"model" to model, "hr" to uni_hr, "jr" to uni_jr,
            "brand" to brand)
            view?.findNavController()?.navigate(R.id.action_nav_listcar_to_nav_slideshow,bundle)


        }
    }

}